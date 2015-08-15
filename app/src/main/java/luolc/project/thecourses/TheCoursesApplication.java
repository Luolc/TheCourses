package luolc.project.thecourses;

import android.app.Application;

import luolc.project.thecourses.util.DisplayUtil;

/**
 * Created by Luo Liangchen on 2015/7/27.
 */
public class TheCoursesApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DisplayUtil.init(getApplicationContext());
    }
}
