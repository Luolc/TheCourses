package luolc.project.thecourses.bean;

/**
 * Created by Luo Liangchen on 2015/8/3.
 */
public class VersionBean{
    private int version_code;
    private String version_name;
    private String note;

    public int getVersion_code() {
        return version_code;
    }
    public void setVersion_code(int version_code) {
        this.version_code = version_code;
    }
    public String getVersion_name() {
        return version_name;
    }
    public void setVersion_name(String version_name) {
        this.version_name = version_name;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
}