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
 * @date 2021/12/7 16:44
 */
@AllArgsConstructor
@Getter
public enum StkOperTypeEnum {
    STOCK_DEDUCTION(0, "库存扣减"),
    STOCK_INCREASE(1, "库存增加");
    int code;
    String value;

    public static StkOperTypeEnum toEnum(int code) {
        for (StkOperTypeEnum item : StkOperTypeEnum.values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }
}
