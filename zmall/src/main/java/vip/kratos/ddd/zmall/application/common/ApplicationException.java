package vip.kratos.ddd.zmall.application.common;

import com.google.common.base.Strings;

public class ApplicationException extends RuntimeException {
    private Integer status;

    private ApplicationException(int status, String message) {
        super(message);
        this.status = status;
    }

    public static ApplicationException notFound(String errorMessageTemplate, Object... errorMessageArgs) {
        return new ApplicationException(404, Strings.lenientFormat(errorMessageTemplate, errorMessageArgs));
    }

    public Integer getStatus() {
        return status;
    }
}
