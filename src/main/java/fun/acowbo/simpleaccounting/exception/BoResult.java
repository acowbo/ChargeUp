package fun.acowbo.simpleaccounting.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author xiaobo
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("unused")
public class BoResult<T> {
    /**
     * 响应代码
     */
    private int code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应结果
     */
    private T boResult;

    public static <T> BoResult<T> resultOk() {
        return new BoResult<T>(HttpStatus.OK.value(), "请求成功!", null);
    }

    public static <T> BoResult<T> resultOk(T result) {
        return new BoResult<T>(HttpStatus.OK.value(), "请求成功!", result);
    }

    public static <T> BoResult<T> defineError(BusinessException e) {
        BoResult<T> boResult = new BoResult<T>();
        boResult.setCode(e.getErrorCode());
        boResult.setMessage(e.getErrorMessage());
        boResult.setBoResult(null);
        return boResult;
    }

    public static <T> BoResult<T> resultFail(String failMsg) {
        return new BoResult<T>(HttpStatus.FORBIDDEN.value(), failMsg, null);
    }

}
