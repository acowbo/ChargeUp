package fun.acowbo.simpleaccounting.exception;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * description: 全局异常处理
 *
 * @author <a href="https://acowbo.fun">acowbo</a>
 * @version 1.0
 * @since 2025/3/25
 */
@Getter
@Setter
@SuppressWarnings("unused")
@Slf4j
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = -4879677283847539655L;

    private int errorCode;

    private String errorMessage;

    private String exceptionMessage;

    private Exception exception;

    public BusinessException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public BusinessException(int errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public BusinessException(int errorCode, String errorMessage, Exception exception) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.exception = exception;
    }

    public BusinessException(String errorMessage, String exceptionMessage) {
        super(errorMessage);
        this.exceptionMessage = exceptionMessage;
        this.errorMessage = errorMessage;
    }

    public BusinessException(int errorCode, String errorMessage, String exceptionMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.exceptionMessage = exceptionMessage;
    }
}
