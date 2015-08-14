package luolc.project.thecourses.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;

import java.util.List;

import luolc.project.thecourses.R;
import luolc.project.thecourses.adapter.CommentListAdapter;
import luolc.project.thecourses.bean.CommentBean;
import luolc.project.thecourses.bean.CourseDetailBean;
import luolc.project.thecourses.biz.CourseBiz;
import luolc.project.thecourses.config.Constant;
import luolc.project.thecourses.util.BizUtil;

/**
 * Created by Luo Liangchen on 2015/7/28.
 */
public class CourseDetailActivity extends Activity {
    private static final String TAG = "CourseDetailActivity";

    private Context mContext;
    private ListView lvComment;
    private CommentListAdapter adapter;
    private LinearLayout header;

    private int courseCode;
    private String courseName;
    private String courseEnglishName;
    private String teachers;
    private String tags;
    private String characteristic;
    private List<CommentBean> comments;
    private CourseDetailBean courseDetailBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_course_detail);

        getInfoFromCourseActivity();
        setTitleBar();
        setUpListView();
        setUpCommentListAdapter();
        new LoadCourseDetailTask().execute(courseCode);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    private void getInfoFromCourseActivity() {
        Intent intent = getIntent();
        courseCode = intent.getIntExtra(Constant.KEY_COURSE_CODE, -1);
        courseName = intent.getStringExtra(Constant.KEY_COURSE_NAME);
        courseEnglishName = intent.getStringExtra(Constant.KEY_COURSE_NAME_ENGLISH);
        teachers = intent.getStringExtra(Constant.KEY_COURSE_TEACHERS);
    }
    private void setTitleBar() {
        TextView tvCourseNameTitle = (TextView) findViewById(R.id.tv_course_name_title);
        TextView tvCourseEnglishNameTitle =
                (TextView) findViewById(R.id.tv_course_name_english_title);

        tvCourseNameTitle.setText(courseName);
        tvCourseEnglishNameTitle.setText(courseEnglishName);
    }
    private void setBasicQuote() {
        TextView tvCourseName = (TextView) header.findViewById(R.id.tv_course_name);
        TextView tvTeachers = (TextView) header.findViewById(R.id.tv_teachers);
        TextView tvCourseTags = (TextView) header.findViewById(R.id.tv_course_tags);
        TextView tvCharacteristic = (TextView) header.findViewById(R.id.tv_characteristic);

        tvCourseName.setText(courseName);
        tvTeachers.setText(teachers);
        tvCourseTags.setText(tags);
        tvCharacteristic.setText(characteristic);
    }
    private void setUpListView() {
        lvComment = (ListView) findViewById(R.id.lv_comment);
        header = (LinearLayout) View.inflate(this, R.layout.list_header_comment, null);
        lvComment.addHeaderView(header);
    }
    private void setUpCommentListAdapter() {
        adapter = new CommentListAdapter(this, comments);
        lvComment.setAdapter(adapter);
    }
    private void setUpComment() {
        if(comments != null) {
            for(int i = 0; i < comments.size(); ++i) {
                comments.get(i).setContent(comments.get(i).getContent().replace("[sp]", "\n"));
            }
        }
        adapter.reset(comments);
        adapter.notifyDataSetChanged();
    }

    public void onClickBackButton(View view) {
        this.finish();
    }

    public void onClickOverflowButton(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(R.string.course_detail_add_comment_title)
                .setMessage(R.string.course_detail_add_comment_message)
                .setPositiveButton(R.string.course_detail_add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String url = Constant.URL_COMMENT_COLLECTION;
                        Uri uri = Uri.parse(url);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        mContext.startActivity(intent);
                    }
                })
                .setNegativeButton(R.string.settings_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                })
                .create().show();
    }

    class LoadCourseDetailTask extends AsyncTask<Integer, Void, String> {
        @Override
        protected String doInBackground(Integer... params) {
            StringBuffer status = new StringBuffer("");
            courseDetailBean = BizUtil.get(mContext, new CourseBiz(CourseBiz.COURSE_DETAIL),
                    status, params[0]);
            return status.toString();
        }
        @Override
        protected void onPostExecute(String status) {
            super.onPostExecute(status);
            if (status.equals(getString(R.string.common_exception_none))) {
                tags = "";
                for (int i = 0; i < courseDetailBean.getTags().size(); ++i) {
                    if (i > 0) {
                        tags += " | ";
                    }
                    tags += courseDetailBean.getTags().get(i).getTag();
                }
                characteristic = courseDetailBean.getCharacteristic();
                comments = courseDetailBean.getComments();
                setBasicQuote();
                setUpComment();
            } else {
                Toast.makeText(mContext, status, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
