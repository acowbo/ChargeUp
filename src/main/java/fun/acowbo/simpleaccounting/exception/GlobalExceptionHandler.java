package fun.acowbo.simpleaccounting.exception;


import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * description: 全局异常处理
 *
 * @author <a href="https://acowbo.fun">acowbo</a>
 * @version 1.0
 * @since 2025/3/25
 */
@RestControllerAdvice
@SuppressWarnings("unused")
public class GlobalExceptionHandler {

    /*
     * 捕捉校验异常(BindException)
     *
     * @return boResult
    */

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public BoResult validException(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        Map<String, Object> result = this.getValidError(fieldErrors);
        return new BoResult(HttpStatus.BAD_REQUEST.value(), result.get("errorMsg").toString(), result.get("errorList"));
    }

    /*
     * 捕捉校验异常(MethodArgumentNotValidException)
     *
     * @return boResult
    */

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BoResult validException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        Map<String, Object> result = this.getValidError(fieldErrors);
        return new BoResult(HttpStatus.BAD_REQUEST.value(), result.get("errorMsg").toString(), result.get("errorList"));
    }

    /*
     * 捕捉其他所有自定义异常
     *
     * @return boResult
    */

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessException.class)
    public BoResult handle(BusinessException e) {
        return new BoResult(e.getErrorCode() == 0 ? HttpStatus.BAD_REQUEST.value() : e.getErrorCode(), e.getErrorMessage(), null);
    }

    /*
     * 捕捉404异常
     *
     * @return boResult
    */

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public BoResult handle(NoHandlerFoundException e) {
        return new BoResult(HttpStatus.NOT_FOUND.value(), e.getMessage(), null);
    }

    /*
     * 捕捉其他所有异常
     *
     * @param request 请求
     * @param ex      异常
     * @return boResult
    */

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public BoResult globalException(HttpServletRequest request, Throwable ex) {
        return new BoResult(this.getStatus(request).value(), ex.toString() + ": " + ex.getMessage(), null);
    }

    /*
     * 获取状态码
     *
     * @param request 请求
     * @return boResult
    */

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }

    /*
     * 获取校验错误信息
     *
     * @param fieldErrors 字段错误值
     * @return boResult
    */

    private Map<String, Object> getValidError(List<FieldError> fieldErrors) {
        Map<String, Object> result = new HashMap<>(16);
        List<String> errorList = new ArrayList<>();
        StringBuffer errorMsg = new StringBuffer("校验异常(ValidException):");
        for (FieldError error : fieldErrors) {
            errorList.add(error.getField() + "-" + error.getDefaultMessage());
            errorMsg.append(error.getField()).append("-").append(error.getDefaultMessage()).append(".");
        }
        result.put("errorList", errorList);
        result.put("errorMsg", errorMsg);
        return result;
    }

}
