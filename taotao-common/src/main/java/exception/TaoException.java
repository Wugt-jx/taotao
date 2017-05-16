package exception;

/**
 * Created by Administrator on 2017/5/16.
 */
public class TaoException extends Exception {
    public TaoException() {
    }

    public TaoException(String message) {
        super(message);
    }

    public TaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaoException(Throwable cause) {
        super(cause);
    }
}
