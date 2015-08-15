package luolc.project.thecourses.config;

import android.os.Environment;

import java.io.File;

/**
 * Created by Luo Liangchen on 2015/8/15.
 */
public class Account {

    private static String id;
    private static String password;
    private static String name;
    private static String nickName;
    private static String major;

    public static void init() {
        id = "";
        password = "";
        name = "";
        nickName = "";
        major = "";
    }

    public static void getData() {
        id = LocalEditor.getString(Constant.KEY_DATA_ID);
        password = LocalEditor.getString(Constant.KEY_DATA_PASSWORD);
        name = LocalEditor.getString(Constant.KEY_DATA_NAME);
        nickName = LocalEditor.getString(Constant.KEY_DATA_NICK_NAME);
        major = LocalEditor.getString(Constant.KEY_DATA_MAJOR);
        if ("".equals(id)) {
            id = "";
            password = "";
            name = "";
            nickName = "";
            major = "";
        }
    }

    public static boolean mkdirs() {
        if (!isLogin()) {
            return false;
        }
        File file = new File(Environment.getExternalStorageDirectory()
                + "/" + Constant.DIR_APP_NAME + "/" + id);
        if (file.exists()) {
            if (!file.isDirectory()) {
                file.delete();
                file.mkdirs();
            }
        } else {
            file.mkdirs();
        }
        return true;
    }

    public static boolean isLogin() {
        return !"".equals(LocalEditor.getString(Constant.KEY_DATA_ID));
    }

    public static void clear() {
        LocalEditor.putString(Constant.KEY_DATA_ID, "");
        LocalEditor.putString(Constant.KEY_DATA_PASSWORD, "");
        LocalEditor.putString(Constant.KEY_DATA_NAME, "");
        LocalEditor.putString(Constant.KEY_DATA_NICK_NAME, "");
        LocalEditor.putString(Constant.KEY_DATA_MAJOR, "");

        id = "";
        password = "";
        name = "";
        nickName = "";
        major = "";
    }
}
