package com.newbiegroup.secondkill.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>ClassName:企业级电商 促销类型 枚举</p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/8/2 14:24
 */
@AllArgsConstructor
public enum PromotionTypeEnum {

    SINGLE_PRODUCT(1, "单品促销"),
    FREEBIE(2, "单品买赠促销"),
    CONDITION(3, "条件促销"),
    FULL_CUT(4, "整单促销"),
    GROUP_DISCOUNT(5, "组合促销");
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
