package com.ali.shali.json;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * @Author shali
 * @Date 2023/11/9 18:18
 * @Project:shali
 * @Description:
 * @Version 1.0
 */
@Data
public class Model1 {
    /**
     * 发布模板id
     */
    @NotNull
    private Long publishModelId;

    /**
     * 渠道id
     */
    @NotNull
    private Long channelId;

    /**
     * 关联的渠道产品id
     */
    private Long channelProductId;

    /**
     * 状态
     */
    @NotNull
    private Integer status;
}
