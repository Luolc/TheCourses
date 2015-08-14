package luolc.project.thecourses.biz;

import java.io.IOException;

import luolc.project.thecourses.exception.JsonSyntaxException;

/**
 * Created by Luo Liangchen on 2015/8/7.
 */
public abstract class Biz {

    public abstract <T> T getInfo(int... para) throws IOException, JsonSyntaxException;
}
