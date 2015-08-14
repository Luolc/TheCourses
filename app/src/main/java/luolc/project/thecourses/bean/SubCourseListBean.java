package luolc.project.thecourses.bean;

import java.util.List;

public class SubCourseListBean {
    private int tab_position;
    private List<CourseCodeBean> default_order;

    public int getTab_position() {
        return tab_position;
    }

    public void setTab_position(int tab_position) {
        this.tab_position = tab_position;
    }

    public List<CourseCodeBean> getDefault_order() {
        return default_order;
    }

    public void setDefault_order(List<CourseCodeBean> default_order) {
        this.default_order = default_order;
    }
}
