package com.newbiegroup.secondkill.common.constants;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/10/10 21:52
 */
public class CacheConstants {
    // 用户是否登录
    public static final String USER_IS_LOGIN_CACHE_KEY = "user:islogin:cachekey:%s";
    // 用户注册验证码
    public static final String USER_REGISTER_OPT_CODE_CACHE_KEY = "user:register:optcode:cachekey:%s";
    /**
     * 促销商品库存
     * key: ware_item_id
     */
    public static final String PROMOTION_ITEM_STOCK = "promotion:item:stock:%s";
    /**
     *  商品库存售罄标识
     *  key: ware_item_id
     */
    public static final String WARE_ITEM_STOCK_INVALID = "ware:item:stock:invalid:%s";

    /**
     * 商品详情
     * key: ware_item_id
     */
    public static final String WARE_ITEM_DETAIL = "ware:item:detail:%s";

}
