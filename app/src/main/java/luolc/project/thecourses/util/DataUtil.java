package luolc.project.thecourses.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import luolc.project.thecourses.config.Constant;

/**
 * Created by Luo Liangchen on 2015/7/26.
 */
public class DataUtil {

    public static String getJson(String url) throws IOException {
        String json;

        URLConnection connection = new URL(url).openConnection();
        connection.setConnectTimeout(Constant.HTTP_CONNECT_TIME_LIMIT);
        json = IoUtil.readStream(connection.getInputStream(), "utf-8");

        return json;
    }

    public static int getVersionCode(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            int code = info.versionCode;
            return code;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String getVersionName(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String name = info.versionName;
            return name;
        } catch (Exception e) {
            e.printStackTrace();
            return "1.0.0";
        }
    }
}
