package luolc.project.thecourses.biz;

import com.google.gson.Gson;

import java.io.IOException;

import luolc.project.thecourses.bean.VersionBean;
import luolc.project.thecourses.exception.JsonSyntaxException;
import luolc.project.thecourses.util.DataUtil;
import luolc.project.thecourses.util.ResourceUtil;

/**
 * Created by Luo Liangchen on 2015/8/3.
 */
public class AppBiz extends Biz{

    public static final int VERSION = 0;

    private int infoType;

    public AppBiz(int infoType) {
        this.infoType = infoType;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getInfo(int... para) throws IOException, JsonSyntaxException {
        switch (infoType) {
            case VERSION:
                return (T) getVersionInfo();
            default:
                return null;
        }
    }

    public VersionBean getVersionInfo() throws IOException, JsonSyntaxException {
        Gson gson = new Gson();
        VersionBean versionBean;
        String json = DataUtil.getJson(ResourceUtil.getVersionInfoUrl());
        try {
            versionBean = gson.fromJson(json, VersionBean.class);
        } catch (com.google.gson.JsonSyntaxException e) {
            throw new JsonSyntaxException("json string is: " + json);
        }
        return versionBean;
    }
}
