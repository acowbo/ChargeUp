package fun.acowbo.simpleaccounting.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author <a href="https://acowbo.fun">acowbo</a>
 * @since 2025/3/26
 */
@Data
public class TypeSumWithTotalVO {

    private Date startTime;

    private Date endTime;

    private Integer type;
}
