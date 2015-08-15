package luolc.project.thecourses.config;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Luo Liangchen on 2015/8/15.
 */
public class LocalEditor {

    static SharedPreferences pref;
    static SharedPreferences.Editor editor;

    public static void init(Context context) {
        pref = context.getSharedPreferences("TheCoursesPref", Context.MODE_PRIVATE);
        editor = pref.edit();
        editor.apply();
    }

    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean value) {
        return pref.getBoolean(key, value);
    }

    public static int getInt(String key) {
        return getInt(key, 0);
    }

    public static int getInt(String key, int value) {
        return pref.getInt(key, value);
    }

    public static long getLong(String key) {
        return getLong(key, 0L);
    }

    public static long getLong(String key, long value) {
        return pref.getLong(key, value);
    }

    public static String getString(String key) {
        return getString(key, "");
    }

    public static String getString(String key, String value) {
        return pref.getString(key, value);
    }

    public static void putBoolean(String key, Boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static void putInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public static void putLong(String key, long value) {
        editor.putLong(key, value);
        editor.commit();
    }

    public static void putString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }
}
