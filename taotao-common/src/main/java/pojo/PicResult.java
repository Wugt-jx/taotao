package pojo;

/**
 * Created by Administrator on 2017/5/10.
 */
public class PicResult {

   private Integer error;
   private String url;
   private String message;

    public PicResult(Integer error, String url, String message) {
        this.error = error;
        this.url = url;
        this.message = message;
    }

    public PicResult() {
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
