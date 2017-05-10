package pojo;

/**
 * Created by Administrator on 2017/5/10.
 */
public class TaoTaoResult<T> {

    private Integer status;
    private String msg;
    private T data;

    public TaoTaoResult() {
    }

    public TaoTaoResult(Integer status, String msg, T obj) {
        this.status = status;
        this.msg = msg;
        this.data = (T)data;
    }

    public TaoTaoResult(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public TaoTaoResult(T obj) {
        this(200,"success");
        this.data = (T)obj;
    }

    public static TaoTaoResult ok(){
        return new TaoTaoResult(200,"success");
    }

    public static TaoTaoResult ok(Object object){
        return new TaoTaoResult(object);
    }

    public static TaoTaoResult build(Integer status,String msg,Object object){
        return new TaoTaoResult(status,msg,object);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
