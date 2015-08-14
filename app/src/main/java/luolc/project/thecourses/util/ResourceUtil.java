package luolc.project.thecourses.util;

import luolc.project.thecourses.config.Constant;

/**
 * Created by Luo Liangchen on 2015/7/26.
 */
public class ResourceUtil {

    public static String getCourseListUrl(int position) {
        return Constant.URL_BASE_COURSE_LIST + position + ".json";
    }
    public static String getCourseInfoUrl(int courseCode) {
        return Constant.URL_BASE_COURSE_INFO + courseCode + ".json";
    }
    public static String getCourseDetailUrl(int courseCode) {
        return Constant.URL_BASE_COMMENT_INFO + courseCode + ".json";
    }
    public static String getUserInfoUrl(int studentCode) {
        return Constant.URL_BASE_USER_INFO + studentCode + ".json";
    }
    public static String getVersionInfoUrl() {
        return Constant.URL_VERSION_INFO;
    }
}
