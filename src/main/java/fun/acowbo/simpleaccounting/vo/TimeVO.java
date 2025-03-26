package fun.acowbo.simpleaccounting.vo;

import lombok.Builder;
import lombok.Data;

/**
 * @author todoitbo
 * @date 2024/1/7
 */
@Data
@Builder
public class TimeVO {

    /** 获取今天的时间*/
    private String dayTime;

    /** 获取本周时间*/
    private String weekTime;

    /** 获取本月时间*/
    private String monthTime;

    /** 获取今年时间*/
    private String yearTime;
}
