package manager.exception;

public class BaseServiceException extends Exception {

    public BaseServiceException() {
        super();
    }

    public BaseServiceException(String message) {
        super(message);
    }

    public BaseServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public BaseServiceException(Throwable cause) {
        super(cause);
    }

    protected BaseServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
