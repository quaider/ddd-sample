package vip.kratos.ddd.zmall.application.common;

public class ApplicationException extends RuntimeException {
    private Integer status;

    private ApplicationException(int status, String message) {
        super(message);
        this.status = status;
    }

    public static ApplicationException notFound(String message) {
        ApplicationException exception = new ApplicationException(404, message);
        return exception;
    }

    public Integer getStatus() {
        return status;
    }
}
