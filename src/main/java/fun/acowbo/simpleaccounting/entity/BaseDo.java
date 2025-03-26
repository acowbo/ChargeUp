package fun.acowbo.simpleaccounting.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author xiaobo
 */
@Data
public class BaseDo implements Serializable {

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 上传人
     */
    private Long createBy;

    /**
     * 更新人
     */
    private Long updateBy;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDeleted;
}