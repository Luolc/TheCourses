package luolc.project.thecourses.exception;

/**
 * Created by Luo Liangchen on 2015/7/27.
 */
public class JsonSyntaxException extends Exception {

    public JsonSyntaxException() {
        super();
    }
    public JsonSyntaxException(String message) {
        super(message);
    }
    public JsonSyntaxException(String message, Throwable cause) {
        super(message, cause);
    }
    public JsonSyntaxException(Throwable cause) {
        super(cause);
    }
}
