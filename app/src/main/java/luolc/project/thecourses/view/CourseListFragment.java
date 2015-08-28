package luolc.project.thecourses.view;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.MaterialHeader;
import luolc.project.thecourses.R;
import luolc.project.thecourses.adapter.CourseListViewAdapter;
import luolc.project.thecourses.bean.CourseShortBean;
import luolc.project.thecourses.biz.CourseBiz;
import luolc.project.thecourses.config.Constant;
import luolc.project.thecourses.config.SortMode;
import luolc.project.thecourses.ui.CourseDetailActivity;
import luolc.project.thecourses.util.BizUtil;
import luolc.project.thecourses.util.DisplayUtil;

/**
 * Created by Luo Liangchen on 2015/7/25.
 */
public class CourseListFragment extends Fragment {
    private static final String TAG = "CourseListFragment";

    private ListView lvCourse;
    private CourseListViewAdapter adapter;
    private PtrClassicFrameLayout ptr;

    private LoadCourseInfoTask loadTask;

    private int position;
    private static final String ARGUMENT_POSITION = "position";

    private List<CourseShortBean> courseBeans = new ArrayList<>();


    public static CourseListFragment newInstance(int position) {
        if (position >= Constant.COURSE_TAB_SIZE) {
            throw new IllegalArgumentException("Cannot construct fragment at position " + position);
        }
        Bundle bundle = new Bundle();
        bundle.putInt(ARGUMENT_POSITION, position);

        CourseListFragment fragment = new CourseListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.position = getArguments() != null ? getArguments().getInt(ARGUMENT_POSITION) : 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course_list, container, false);

        Log.i(TAG, "-->on create view");
        setUpListView(view);
        setUpPtrClassicFrameLayout(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.i(TAG, "-->on activity created");
        setUpAdapter();
    }

    @Override
    public void onPause() {
        super.onPause();
        if (loadTask != null && loadTask.getStatus() == AsyncTask.Status.RUNNING) {
            loadTask.cancel(true);
        }
    }

    private void setUpListView(View view) {
        lvCourse = (ListView) view.findViewById(R.id.lv_course);

        lvCourse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "position = " + position + " ; id = " + id);
                CourseShortBean courseBean;
                try {
                    courseBean = courseBeans.get(position);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    Log.i(TAG, "Null");
                    return;
                }
                String teachers = "";
                if (courseBean.getTeachers() != null) {
                    for (int i = 0; i < courseBean.getTeachers().size(); ++i)
                        teachers += " " + courseBean.getTeachers().get(i).getName();
                }
                Intent intent = new Intent(getActivity(), CourseDetailActivity.class);
                intent.putExtra(Constant.KEY_COURSE_CODE, courseBean.getCode());
                intent.putExtra(Constant.KEY_COURSE_NAME, courseBean.getName());
                intent.putExtra(Constant.KEY_COURSE_NAME_ENGLISH, courseBean.getEnglish_name());
                intent.putExtra(Constant.KEY_COURSE_TEACHERS, teachers);
                startActivity(intent);
            }
        });
    }

    private void setUpPtrClassicFrameLayout(View view) {
        ptr = (PtrClassicFrameLayout) view.findViewById(R.id.ptr_course_list);
        ptr.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                loadTask = new LoadCourseInfoTask();
                loadTask.execute(position);
            }
        });
        ptr.postDelayed(new Runnable() {
            @Override
            public void run() {
                ptr.autoRefresh();
            }
        }, 0);
        ptr.setLoadingMinTime(Constant.COURSE_REFRESH_MIN_TIME);
        ptr.setPinContent(true);

        //Init Material ProgressBar
        final MaterialHeader header = new MaterialHeader(getActivity());
        header.setPtrFrameLayout(ptr);
        int[] colors = {getResources().getColor(R.color.bk_primary_dark),
                getResources().getColor(R.color.bk_primary_light)};
        header.setColorSchemeColors(colors);
        header.setLayoutParams(new PtrFrameLayout.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        header.setPadding(0, DisplayUtil.designedDp_to_px(16), 0, DisplayUtil.designedDp_to_px(16));
        ptr.setHeaderView(header);
        ptr.addPtrUIHandler(header);
    }

    private void setUpAdapter() {
        adapter = new CourseListViewAdapter(getActivity(), courseBeans);
        lvCourse.setAdapter(adapter);
        Log.i(TAG, "setAdapter successful");
    }

    class LoadCourseInfoTask extends AsyncTask<Integer, Void, String> {
        @Override
        @SuppressWarnings("unchecked")
        protected String doInBackground(Integer... params) {
            StringBuffer status = new StringBuffer("");
            courseBeans = BizUtil.get(getActivity(), new CourseBiz(CourseBiz.COURSE_BASIC),
                    status, params[0]);
            Collections.sort(courseBeans, new SortMode(SortMode.NAME));
            return status.toString();
        }

        @Override
        protected void onPostExecute(String status) {
            super.onPostExecute(status);
            try {
                String status_common = getString(R.string.common_exception_none);
                if (status.equals(status_common)) {
                    adapter.reset(courseBeans);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getActivity(), status, Toast.LENGTH_SHORT).show();
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
            ptr.refreshComplete();
        }
    }
}
