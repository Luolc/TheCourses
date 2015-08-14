package luolc.project.thecourses.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import luolc.project.thecourses.config.Constant;
import luolc.project.thecourses.view.CourseListFragment;

/**
 * Created by Luo Liangchen on 2015/7/25.
 */
public class TabAdapter extends FragmentPagerAdapter {

    public TabAdapter(FragmentManager manager) {
        super(manager);
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
            return Constant.COURSE_TAB_TITLE[position];
        }
        return null;
    }
}
