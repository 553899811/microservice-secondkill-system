package com.newbiegroup.secondkill.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/10/25 15:19
 */
public enum YnEnum {

    INVALID(0,"逻辑删除"),

    VALID(1,"有效数据"),

    VALID_ARCHIVE(2,"有效数据且可做归档"),

    INVALID_ARCHIVE(3,"无效数据且可做归档"),

    DELETE(4,"物理删除");

    private int code;
    private String alias;
    private static final Map<Integer, YnEnum> STATES = new HashMap();

    private YnEnum(int code, String alias) {
        this.code = code;
        this.alias = alias;
    }

    public int code() {
        return this.code;
    }

    public String alias() {
        return this.alias;
    }

    public static YnEnum getInstance(int code) {
        return (YnEnum) STATES.get(code);
    }

    static {
        YnEnum[] var0 = values();
        int var1 = var0.length;

        for (int var2 = 0; var2 < var1; ++var2) {
            YnEnum ynEnum = var0[var2];
            STATES.put(ynEnum.code, ynEnum);
        }
    }
}
