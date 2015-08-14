package luolc.project.thecourses.config;

import java.text.Collator;
import java.util.Comparator;

import luolc.project.thecourses.bean.CourseShortBean;

/**
 * Created by Luo Liangchen on 2015/8/11.
 */
public class SortMode implements Comparator {

    public static final int NAME = 0;

    private int mode;

    public SortMode(int mode) {
        this.mode = mode;
    }

    @Override
    public int compare(Object lhs, Object rhs) {
        if(lhs instanceof CourseShortBean && rhs instanceof CourseShortBean) {
            CourseShortBean mLhs = (CourseShortBean) lhs;
            CourseShortBean mRhs = (CourseShortBean) rhs;
            switch (mode) {
                case NAME:
                    Comparator cmp = Collator.getInstance(java.util.Locale.CHINA);
                    return cmp.compare(mLhs.getName(), mRhs.getName());
                default:
                    return 0;
            }
        }
        return 0;
    }
}
