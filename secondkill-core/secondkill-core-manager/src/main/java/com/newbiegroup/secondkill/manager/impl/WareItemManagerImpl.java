package com.newbiegroup.secondkill.manager.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newbiegroup.secondkill.common.constants.CacheConstants;
import com.newbiegroup.secondkill.common.exception.BusinessException;
import com.newbiegroup.secondkill.dao.WareItemMapper;
import com.newbiegroup.secondkill.dao.WareItemStockMapper;
import com.newbiegroup.secondkill.entity.WareItem;
import com.newbiegroup.secondkill.manager.CacheManager;
import com.newbiegroup.secondkill.manager.WareItemManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>ClassName:  商品明细biz </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/8/1 18:57
 */
@Slf4j
@Service
public class WareItemManagerImpl extends ServiceImpl<WareItemMapper, WareItem> implements WareItemManager {

    @Autowired
    private WareItemMapper wareItemMapper;
    @Autowired
    private WareItemStockMapper wareItemStockMapper;
    @Autowired
    private CacheManager redisTemplate;

    public void create(WareItem wareItem) {
        wareItemMapper.insert(wareItem);
    }

    public WareItem selectByWareItemId(Long wareItemId) {
        QueryWrapper query = new QueryWrapper();
        query.eq("ware_item_id", wareItemId);
        WareItem wareItem = this.getOne(query);
        return Optional.ofNullable(wareItem).<BusinessException>orElseThrow(() -> {
            throw new BusinessException("根据wareItemId 查询不到对应商品行信息");
        });
    }

    /**
     * 增加商品销售
     *
     * @param wareItem
     * @param increment
     */
    public void increaseSales(WareItem wareItem, Integer increment) {
        UpdateWrapper<WareItem> update = new UpdateWrapper();
        update.set("sales", wareItem.getSales() + increment);
        update.eq("ware_item_id", wareItem.getWareItemId());
        int affectRows = wareItemMapper.update(wareItem, update);
        if (affectRows > 0) {
            log.info("update success");
        } else {
            log.error("udpate error");
        }
    }

    @Override
    public boolean decreaseStock(Long wareItemId, Integer amount) {
        Long result = redisTemplate.increment(String.format(CacheConstants.PROMOTION_ITEM_STOCK, wareItemId), amount * -1);
        if (result > 0) {
            return true;
        } else if (result == 0) {
            //库存售罄 标识
            redisTemplate.set(String.format(CacheConstants.WARE_ITEM_STOCK_INVALID, wareItemId), "true");
            return true;
        } else {
            increaseStock(wareItemId, amount);
            return false;
        }
    }

    @Override
    public boolean increaseStock(Long wareItemId, Integer amount) {
        redisTemplate.increment(String.format(CacheConstants.PROMOTION_ITEM_STOCK, wareItemId), amount);
        return true;
    }
}
