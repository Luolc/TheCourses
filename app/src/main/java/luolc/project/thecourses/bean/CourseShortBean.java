package luolc.project.thecourses.bean;

import java.util.List;

public class CourseShortBean {
    private int code;
    private String category;
    private String name;
    private String english_name;
    List<TeacherNameBean> teachers;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnglish_name() {
        return english_name;
    }

    public void setEnglish_name(String english_name) {
        this.english_name = english_name;
    }

    public List<TeacherNameBean> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<TeacherNameBean> teachers) {
        this.teachers = teachers;
    }


}
