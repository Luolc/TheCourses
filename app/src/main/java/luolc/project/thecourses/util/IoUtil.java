package luolc.project.thecourses.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Luo Liangchen on 2015/7/26.
 */
public class IoUtil {

    public static String readStream(InputStream is, String charsetName)
            throws IOException {
        String ret = "";

        InputStreamReader isr;
        String line;
        isr = new InputStreamReader(is, charsetName);
        BufferedReader br = new BufferedReader(isr);
        while ((line = br.readLine()) != null) {
            ret += line;
        }
        is.close();
        return ret;
    }
}
