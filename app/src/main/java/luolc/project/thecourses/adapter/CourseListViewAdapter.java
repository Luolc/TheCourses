package luolc.project.thecourses.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import luolc.project.thecourses.R;
import luolc.project.thecourses.bean.CourseShortBean;

/**
 * Created by Luo Liangchen on 2015/7/26.
 */
public class CourseListViewAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<CourseShortBean> courseBeans;
    private Context context;

    public CourseListViewAdapter(Context context, List<CourseShortBean> courseBeans) {
        this.layoutInflater = LayoutInflater.from(context);
        this.courseBeans = courseBeans;
        this.context = context;
    }

    public void reset(List<CourseShortBean> courseBeans) {
        this.courseBeans = courseBeans;
    }

    @Override
    public int getCount() {
        if (courseBeans == null) {
            return 0;
        }
        return courseBeans.size();
    }

    @Override
    public Object getItem(int position) {
        if (courseBeans == null || position >= courseBeans.size()) {
            return null;
        }
        return courseBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_course_list, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.tvCourseName = (TextView) convertView.findViewById(R.id.tv_course_name);
            viewHolder.tvTeachers = (TextView) convertView.findViewById(R.id.tv_teachers);
            viewHolder.imCategory = (ImageView) convertView.findViewById(R.id.im_category);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        CourseShortBean courseBean = courseBeans.get(position);
        viewHolder.tvCourseName.setText(courseBean.getName());
        String teacherString = "";
        if(courseBean.getTeachers() != null) {
            for (int i = 0; i < courseBean.getTeachers().size(); ++i) {
                teacherString += courseBean.getTeachers().get(i).getName() + " ";
            }
            viewHolder.tvTeachers.setVisibility(View.VISIBLE);
            viewHolder.tvTeachers.setText(teacherString);
        }

        viewHolder.imCategory.setImageResource(getCategoryIconId(courseBean.getCategory()));

        return convertView;
    }

    static class ViewHolder {
        TextView tvCourseName;
        TextView tvTeachers;
        ImageView imCategory;
    }

    private int getCategoryIconId(String category) {
        int ret = 0;
        if (category.equals(context.getString(R.string.course_list_category_biology))) {
            ret = R.drawable.course_list_category_biology;
        } else if (category.equals(context.getString(R.string.course_list_category_chemistry))) {
            ret = R.drawable.course_list_category_chemistry;
        } else if (category.equals(context.getString(R.string.course_list_category_chinese))) {
            ret = R.drawable.course_list_category_chinese;
        } else if (category.equals(context.getString(R.string.course_list_category_cs))) {
            ret = R.drawable.course_list_category_cs;
        } else if (category.equals(context.getString(R.string.course_list_category_cultural))) {
            ret = R.drawable.course_list_category_cultural;
        } else if (category.equals(context.getString(R.string.course_list_category_english))) {
            ret = R.drawable.course_list_category_english;
        } else if (category.equals(context.getString(R.string.course_list_category_history))) {
            ret = R.drawable.course_list_category_history;
        } else if (category.equals(context.getString(R.string.course_list_category_math))) {
            ret = R.drawable.course_list_category_math;
        } else if (category.equals(context.getString(R.string.course_list_category_military))) {
            ret = R.drawable.course_list_category_military;
        } else if (category.equals(context.getString(R.string.course_list_category_music))) {
            ret = R.drawable.course_list_category_music;
        } else if (category.equals(context.getString(R.string.course_list_category_pe))) {
            ret = R.drawable.course_list_category_pe;
        } else if (category.equals(context.getString(R.string.course_list_category_physics))) {
            ret = R.drawable.course_list_category_physics;
        } else if (category.equals(context.getString(R.string.course_list_category_physiology))) {
            ret = R.drawable.course_list_category_physiology;
        } else if (category.equals(context.getString(R.string.course_list_category_politics))) {
            ret = R.drawable.course_list_category_politics;
        } else if (category.equals(context.getString(R.string.course_list_category_psychology))) {
            ret = R.drawable.course_list_category_psychology;
        } else if (category.equals(context.getString(R.string.course_list_category_sess))) {
            ret = R.drawable.course_list_category_sess;
        }
        return ret;
    }
}
