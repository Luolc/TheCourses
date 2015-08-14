package luolc.project.thecourses.config;

import luolc.project.thecourses.R;

/**
 * Created by Luo Liangchen on 2015/7/25.
 */
public interface Constant {

    /**
     * common
     */
    int COURSE_TAB_SIZE = 4;

    /**
     * Time
     */
    int SPLASH_TIME_DELAY = 2500;
    int HTTP_CONNECT_TIME_LIMIT = 8000;
    int COURSE_REFRESH_MIN_TIME = 500;

    /**
     * TAG KEY
     */
    String KEY_COURSE_CODE = "key_course_code";
    String KEY_COURSE_NAME = "key_course_name";
    String KEY_COURSE_NAME_ENGLISH = "key_course_name_english";
    String KEY_COURSE_TEACHERS = "key_course_teachers";

    /**
     * String
     */
    int[]COURSE_TAB_TITLE_ID = new int[]{R.string.course_tab_0,
            R.string.course_tab_1, R.string.course_tab_2, R.string.course_tab_3};
    String[]COURSE_TAB_TITLE =
            new String[]{"专业课", "全校必修", "通选课", "公选课"};

    String URL_BASE_COURSE_LIST =
            "http://thecourses.sinaapp.com/appdata/course_info/sub_course_list_";
    String URL_BASE_COURSE_INFO =
            "http://thecourses.sinaapp.com/appdata/course_info/item/course_short_info_";
    String URL_BASE_COMMENT_INFO =
            "http://thecourses.sinaapp.com/appdata/course_info/detail/course_detail_";
    String URL_BASE_USER_INFO =
            "http://thecourses.sinaapp.com/appdata/account_info/";
    String URL_VERSION_INFO =
            "http://thecourses.sinaapp.com/appdata/version_info.json";
    String URL_BASE_DOWNLOAD_APK =
            "http://thecourses.sinaapp.com/download/the-courses-";
    String URL_COMMENT_COLLECTION =
            "http://www.sojump.com/jq/5512195.aspx";

}
