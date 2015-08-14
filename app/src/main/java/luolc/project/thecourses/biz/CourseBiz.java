package luolc.project.thecourses.biz;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import luolc.project.thecourses.bean.CourseDetailBean;
import luolc.project.thecourses.bean.CourseShortBean;
import luolc.project.thecourses.bean.SubCourseListBean;
import luolc.project.thecourses.exception.JsonSyntaxException;
import luolc.project.thecourses.util.DataUtil;
import luolc.project.thecourses.util.ResourceUtil;

/**
 * Created by Luo Liangchen on 2015/7/26.
 */
public class CourseBiz extends Biz {

    public static final int COURSE_BASIC = 0;
    public static final int COURSE_DETAIL = 1;

    private int infoType;

    public CourseBiz(int infoType) {
        this.infoType = infoType;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getInfo(int... para) throws IOException, JsonSyntaxException {
        switch (infoType) {
            case COURSE_BASIC:
                return (T) getCourseInfo(para[0]);
            case COURSE_DETAIL:
                return (T) getCourseDetail(para[0]);
            default:
                return null;
        }
    }

    public List<CourseShortBean> getCourseInfo(int position)
            throws IOException, JsonSyntaxException {

        Gson gson = new Gson();
        SubCourseListBean courseListInfoBean;
        String listJson = DataUtil.getJson(ResourceUtil.getCourseListUrl(position));
        try {
            courseListInfoBean = gson.fromJson(listJson, SubCourseListBean.class);
        } catch (com.google.gson.JsonSyntaxException e) {
            throw new JsonSyntaxException("listJson string is: " + listJson);
        }

        List<CourseShortBean> courseBeans = new ArrayList<>();
        if(courseListInfoBean == null) {
            return null;
        }
        for (int i = 0; i < courseListInfoBean.getDefault_order().size(); ++i) {
            int courseCode = courseListInfoBean.getDefault_order().get(i).getCode();
            gson = new Gson();
            CourseShortBean courseBean;
            String infoJson = DataUtil.getJson(ResourceUtil.getCourseInfoUrl(courseCode));
            try {
                courseBean = gson.fromJson(infoJson, CourseShortBean.class);
            } catch (com.google.gson.JsonSyntaxException e) {
                e.printStackTrace();
                throw new JsonSyntaxException("infoJson string is: " + infoJson);
            }
            courseBeans.add(courseBean);
        }

        return courseBeans;
    }

    public CourseDetailBean getCourseDetail(int code) throws IOException, JsonSyntaxException {
        Gson gson = new Gson();
        CourseDetailBean courseDetailBean;
        String json = DataUtil.getJson(ResourceUtil.getCourseDetailUrl(code));
        try {
            courseDetailBean = gson.fromJson(json, CourseDetailBean.class);
        } catch (com.google.gson.JsonSyntaxException e) {
            throw new JsonSyntaxException("json string is: " + json);
        }
        return courseDetailBean;
    }
}

