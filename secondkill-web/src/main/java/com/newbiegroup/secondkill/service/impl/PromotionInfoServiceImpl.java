package com.newbiegroup.secondkill.service.impl;

import com.newbiegroup.secondkill.entity.PromotionInfo;
import com.newbiegroup.secondkill.manager.CacheManager;
import com.newbiegroup.secondkill.manager.impl.PromotionInfoManagerImpl;
import com.newbiegroup.secondkill.manager.impl.WareItemStockManagerImpl;
import com.newbiegroup.secondkill.service.PromotionInfoService;
import com.newbiegroup.secondkill.service.WareItemService;
import com.newbiegroup.secondkill.vo.PromotionVO;
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
 * @date 2021/8/2 14:09
 */
@Slf4j
@Service
public class PromotionInfoServiceImpl implements PromotionInfoService {
    @Autowired
    private PromotionInfoManagerImpl promotionInfoManager;

    @Override
    public PromotionVO selectByWareItemId(Long itemId) {
        PromotionInfo promotionInfo = promotionInfoManager.selectByWareItemId(itemId);
        return PromotionVO.getInstance(promotionInfo);
    }
}
