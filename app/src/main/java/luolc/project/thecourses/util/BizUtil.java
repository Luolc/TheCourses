package luolc.project.thecourses.util;

import android.content.Context;

import java.io.IOException;

import luolc.project.thecourses.R;
import luolc.project.thecourses.biz.Biz;
import luolc.project.thecourses.exception.JsonSyntaxException;

/**
 * Created by Luo Liangchen on 2015/8/7.
 */
public class BizUtil {

    public static <T> T get(Context context, Biz biz, StringBuffer status, int... para) {
        T bean = null;
        try {
            bean = biz.getInfo(para);
            status.append(context.getString(R.string.common_exception_none));
        } catch (IOException e) {
            e.printStackTrace();
            status.append(context.getString(R.string.common_exception_io));
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            status.append(context.getString(R.string.common_exception_json));
        } catch (Exception e) {
            e.printStackTrace();
            status.append(context.getString(R.string.common_exception_common) + "," + e.getMessage());
        }
        return bean;
    }
}
