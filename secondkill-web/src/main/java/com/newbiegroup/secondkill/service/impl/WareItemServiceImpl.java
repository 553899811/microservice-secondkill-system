package com.newbiegroup.secondkill.service.impl;

import com.newbiegroup.secondkill.common.constants.CacheConstants;
import com.newbiegroup.secondkill.entity.WareItem;
import com.newbiegroup.secondkill.entity.WareItemStock;
import com.newbiegroup.secondkill.enums.PromotionStatusEnum;
import com.newbiegroup.secondkill.manager.CacheManager;
import com.newbiegroup.secondkill.manager.impl.GuavaCacheUtilsImpl;
import com.newbiegroup.secondkill.manager.impl.WareItemManagerImpl;
import com.newbiegroup.secondkill.manager.impl.WareItemStockManagerImpl;
import com.newbiegroup.secondkill.service.PromotionInfoService;
import com.newbiegroup.secondkill.service.WareItemService;
import com.newbiegroup.secondkill.vo.PromotionVO;
import com.newbiegroup.secondkill.vo.WareItemVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/8/1 19:02
 */
@Slf4j
@Service
public class WareItemServiceImpl implements WareItemService {

    @Autowired
    private WareItemManagerImpl wareItemManager;
    @Autowired
    private WareItemStockManagerImpl wareItemStockManager;
    @Autowired
    private PromotionInfoService promotionInfoService;
    @Autowired
    private GuavaCacheUtilsImpl guavaCacheTemplate;
    @Autowired
    private CacheManager redisTemplate;

    @Override
    public List<WareItemVO> list() {
        List<WareItem> wareItems = wareItemManager.list();
        if (CollectionUtils.isEmpty(wareItems)) {
            return new ArrayList<>();
        }
        List<WareItemVO> voList = wareItems.parallelStream()
                .map(item -> {
                    WareItemStock wareItemStock = wareItemStockManager.selectByWareItemId(item.getWareItemId());
                    WareItemVO wareItemVO = WareItemVO.getInstance(item);
                    wareItemVO.setStock(wareItemStock.getStock());
                    return wareItemVO;
                }).collect(Collectors.toList());
        return voList;
    }

    @Override
    public WareItemVO getWareItemById(Long wareItemId) {
        log.info("getWareItemById,wareItemId:{}", wareItemId);
        WareItemVO result = null;
        result = redisTemplate.get(String.format(CacheConstants.WARE_ITEM_DETAIL, wareItemId), WareItemVO.class);
        if (result != null) {
            Long stock = getWareItemStockFromCache(wareItemId);
            result.setStock(stock);
            return result;
        }
        WareItem wareItem = wareItemManager.selectByWareItemId(wareItemId);
        if (wareItem == null) {
            return null;
        }
        WareItemStock wareItemStockDO = wareItemStockManager.selectByWareItemId(wareItem.getWareItemId());
        WareItemVO wareItemVO = WareItemVO.getInstance(wareItem);
        wareItemVO.setStock(wareItemStockDO.getStock());
        /**
         * 未来必须将促销和商品解耦
         * 1. 先创建商品
         * 2. 再创建促销，并绑定对应商品;
         */
        PromotionVO promotionVO = promotionInfoService.selectByWareItemId(wareItem.getWareItemId());
        if (promotionVO != null && PromotionStatusEnum.END.getCode() != promotionVO.getPromotionStatus()) {
            wareItemVO.setPromotionStatus(promotionVO.getPromotionStatus());
            wareItemVO.setPromotionItemPrice(promotionVO.getPromotionItemPrice());
            wareItemVO.setPromotionId(promotionVO.getId());
            wareItemVO.setPromotionStartDate(promotionVO.getStartDate());
            wareItemVO.setPromotionEndDate(promotionVO.getEndDate());
        }
        redisTemplate.set(String.format(CacheConstants.WARE_ITEM_DETAIL, wareItemId), wareItemVO, 10L, TimeUnit.MINUTES);
        return wareItemVO;
    }

    private Long getWareItemStockFromCache(Long wareItemId) {
        String stock = redisTemplate.get(String.format(CacheConstants.PROMOTION_ITEM_STOCK, wareItemId));
        if (stock == null) {
            WareItemStock wareItemStock = wareItemStockManager.selectByWareItemId(wareItemId);
            if (wareItemStock != null) {
                redisTemplate.set(String.format(CacheConstants.PROMOTION_ITEM_STOCK, wareItemId), wareItemStock.getStock());
                return wareItemStock.getStock();
            } else {
                return 0L;
            }
        }
        return Long.valueOf(stock);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WareItemVO createWareItem(WareItemVO wareItemVO) {
        WareItem wareItem = WareItem.getInstance(wareItemVO);
        wareItemManager.create(wareItem);
        wareItemVO.setWareItemId(wareItem.getWareItemId());
        WareItemStock wareItemStock = WareItemStock.getInstanceFromWareItem(wareItem);
        wareItemStock.setStock(wareItemVO.getStock());
        wareItemStockManager.create(wareItemStock);
        return this.getWareItemById(wareItem.getWareItemId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void increaseSales(Long wareItemId, Integer increment) {
        WareItemVO wareItemVO = this.getWareItemById(wareItemId);
        WareItem wareItem = WareItem.getInstance(wareItemVO);
        wareItemManager.increaseSales(wareItem, increment);
    }

}
