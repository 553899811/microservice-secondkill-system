package com.newbiegroup.secondkill.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/8/2 14:15
 */
@AllArgsConstructor
public enum PromotionStatusEnum {
    NOT_STARTED(1, "未开始"),
    PROCESSING_ON(2, "进行中"),
    END(3, "已结束");
    @Getter
    int code;
    @Getter
    String value;

    public static PromotionStatusEnum toEnum(int code) {
        for (PromotionStatusEnum item : PromotionStatusEnum.values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }
}
