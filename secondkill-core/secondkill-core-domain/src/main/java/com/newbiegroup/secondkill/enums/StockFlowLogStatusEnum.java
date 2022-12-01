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
 * @date 2021/12/14 19:59
 */
@AllArgsConstructor
@Getter
public enum StockFlowLogStatusEnum {
    INIT(0, "初始化"),
    INVENTORY_DEDUCTION(1,"库存预占"),
    ROLL_BACK(3, "库存预占释放");
    int code;
    String value;

    public static StockFlowLogStatusEnum toEnum(int code) {
        for (StockFlowLogStatusEnum item : StockFlowLogStatusEnum.values()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }
}
