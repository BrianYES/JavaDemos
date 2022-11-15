package brian.pojo;

/**
 * 接口返回类
 *
 * @author Brian
 * @date 2018/7/6
 */
public class Result<T> {

    private int code;
    private String msg;
    private T data;

    public static <T> Result<T> success(T data) {
        return new Result<>(data);
    }

    public static Result error(CodeMsg cm) {
        return new Result(cm);
    }

    private Result() {}

    private Result(T data) {
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    private Result(CodeMsg cm) {
        if (null == cm) {
            return;
        }
        this.code = cm.getCode();
        this.msg = cm.getMsg();
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
