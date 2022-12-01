package com.newbiegroup.secondkill.operational.service.impl;

import com.newbiegroup.secondkill.common.constants.CacheConstants;
import com.newbiegroup.secondkill.common.exception.BusinessException;
import com.newbiegroup.secondkill.entity.PromotionInfo;
import com.newbiegroup.secondkill.entity.WareItemStock;
import com.newbiegroup.secondkill.manager.impl.CacheUtilsImpl;
import com.newbiegroup.secondkill.manager.impl.PromotionInfoManagerImpl;
import com.newbiegroup.secondkill.manager.impl.WareItemStockManagerImpl;
import com.newbiegroup.secondkill.operational.service.PromotionInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2022/1/4 16:01
 */
@Slf4j
@Service
public class PromotionInfoServiceImpl implements PromotionInfoService {

    @Autowired
    private PromotionInfoManagerImpl promotionInfoManager;
    @Autowired
    private WareItemStockManagerImpl wareItemStockManager;
    @Autowired
    private CacheUtilsImpl redisTemplate;
    /**
     * 发布促销活动
     * 缓存预热阶段
     *
     * @param promotionId
     */
    @Override
    public void publishPromotion(Long promotionId) {
        PromotionInfo promotionInfo = promotionInfoManager.getById(promotionId);
        if (promotionInfo == null || promotionInfo.getWareItemId() == null) {
            throw new BusinessException("促销活动不存在，请核查后重新发布");
        }
        Long wareItemId = promotionInfo.getWareItemId();
        WareItemStock wareItemStock = wareItemStockManager.selectByWareItemId(wareItemId);
        redisTemplate.set(String.format(CacheConstants.PROMOTION_ITEM_STOCK, wareItemStock.getWareItemId()), wareItemStock.getStock());
    }
}
