package com.newbiegroup.secondkill.operational.service;

import com.newbiegroup.secondkill.vo.PromotionVO;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/8/2 14:09
 */
public interface PromotionInfoService {

    /**
     * 发布促销活动
     * @param promotionId
     */
    void publishPromotion(Long promotionId);
}
