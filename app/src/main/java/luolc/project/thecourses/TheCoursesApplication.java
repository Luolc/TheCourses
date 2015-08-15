package luolc.project.thecourses;

import android.app.Application;
import android.os.Environment;

import java.io.File;

import luolc.project.thecourses.config.Account;
import luolc.project.thecourses.config.Constant;
import luolc.project.thecourses.config.LocalEditor;
import luolc.project.thecourses.util.DisplayUtil;

/**
 * Created by Luo Liangchen on 2015/7/27.
 */
public class TheCoursesApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        mkdirs();
        LocalEditor.init(getApplicationContext());
        Account.init();
        Account.clear();
        DisplayUtil.init(getApplicationContext());
    }

    private void mkdirs() {
        try {
            File file = new File(Environment.getExternalStorageDirectory()
                    + "/" + Constant.DIR_APP_NAME + "/");
            if (!file.exists()) {
                file.mkdirs();
            } else {
                if (!file.isDirectory()) {
                    file.delete();
                    file.mkdirs();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
