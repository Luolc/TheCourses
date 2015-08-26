package luolc.project.thecourses.adapter;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import luolc.project.thecourses.config.Constant;
import luolc.project.thecourses.view.CourseListFragment;

/**
 * Created by Luo Liangchen on 2015/7/25.
 */
public class TabAdapter extends FragmentPagerAdapter {

    private Context context;

    public TabAdapter(FragmentManager manager, Context context) {
        super(manager);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if(position < Constant.COURSE_TAB_SIZE) {
            return CourseListFragment.newInstance(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        return Constant.COURSE_TAB_SIZE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position < Constant.COURSE_TAB_SIZE) {
            return context.getString(Constant.COURSE_TAB_TITLE_ID[position]);
            //return Constant.COURSE_TAB_TITLE[position];
        }
        return null;
    }
}
