package fun.acowbo.simpleaccounting.util;


import fun.acowbo.simpleaccounting.vo.TimeVO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author todoitbo
 * @date 2024/1/7
 */
public class TimeUtils {

    public static TimeVO getAllTimeType() {
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();

        // 今天日期
        String today = now.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日"));

        // 本周日期（从周一到周日）
        LocalDate startOfWeek = now.toLocalDate().with(java.time.temporal.TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY));
        LocalDate endOfWeek = now.toLocalDate().with(java.time.temporal.TemporalAdjusters.nextOrSame(java.time.DayOfWeek.SUNDAY));
        String thisWeek = startOfWeek.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日")) + "至" + endOfWeek.format(DateTimeFormatter.ofPattern("MM月dd日"));

        // 本月日期
        LocalDate startOfMonth = now.toLocalDate().with(java.time.temporal.TemporalAdjusters.firstDayOfMonth());
        LocalDate endOfMonth = now.toLocalDate().with(java.time.temporal.TemporalAdjusters.lastDayOfMonth());
        String thisMonth = startOfMonth.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日")) + "至" + endOfMonth.format(DateTimeFormatter.ofPattern("MM月dd日"));

        // 本年日期
        LocalDate startOfYear = now.toLocalDate().with(java.time.temporal.TemporalAdjusters.firstDayOfYear());
        LocalDate endOfYear = now.toLocalDate().with(java.time.temporal.TemporalAdjusters.lastDayOfYear());
        String thisYear = startOfYear.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日")) + "至" + endOfYear.format(DateTimeFormatter.ofPattern("MM月dd日"));

        return TimeVO.builder().dayTime(today).weekTime(thisWeek).monthTime(thisMonth).yearTime(thisYear).build();
    }
}
