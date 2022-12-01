package com.newbiegroup.secondkill.manager.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newbiegroup.secondkill.common.exception.BusinessException;
import com.newbiegroup.secondkill.dao.WareItemStockMapper;
import com.newbiegroup.secondkill.dto.WareItemStockModifyDTO;
import com.newbiegroup.secondkill.entity.WareItemStock;
import com.newbiegroup.secondkill.enums.StkOperTypeEnum;
import com.newbiegroup.secondkill.manager.WareItemStockManager;
import com.newbiegroup.secondkill.manager.mq.RocketMqProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/8/2 0:59
 */
@Slf4j
@Service
public class WareItemStockManagerImpl extends ServiceImpl<WareItemStockMapper, WareItemStock> implements WareItemStockManager {

    @Autowired
    private WareItemStockMapper wareItemStockMapper;

    @Autowired
    private RocketMqProducer rocketMqProducer;
    @Value(value = "${rocketmq.producer.topic}")
    private String topic;
    @Value(value = "${rocketmq.producer.group}")
    private String group;

    public WareItemStock selectByWareItemId(Long itemId) {
        QueryWrapper query = new QueryWrapper();
        query.eq("ware_item_id", itemId);
        WareItemStock wareItemStock = wareItemStockMapper.selectOne(query);
        return Optional.ofNullable(wareItemStock).<BusinessException>orElseThrow(() -> {
            throw new BusinessException("商品库存不存在");
        });
    }

    public void create(WareItemStock wareItemStock) {
        wareItemStockMapper.insert(wareItemStock);
    }

    /**
     * 异步扣减商品库存
     *
     * @param wareItemId
     * @param amount
     * @return
     */
    @Override
    @Deprecated
    public boolean asyncDecreaseItemStock(Long wareItemId, Integer amount) {
        WareItemStockModifyDTO modifyDTO = new WareItemStockModifyDTO();
        modifyDTO.setWareItemId(wareItemId);
        modifyDTO.setAmount(amount);
        modifyDTO.setStockOptType(StkOperTypeEnum.STOCK_DEDUCTION.getCode());
        SendResult sendResult = rocketMqProducer.send(modifyDTO, topic, group);
        return SendStatus.SEND_OK == sendResult.getSendStatus();
    }

    @Override
    @Transactional
    public void decreaseItemStock(Long wareItemId, Integer decrement) {
        wareItemStockMapper.decreaseStock(wareItemId, decrement);
    }

    @Override
    @Transactional
    public void increaseItemStock(Long wareItemId, Integer increment) {
        wareItemStockMapper.increaseStock(wareItemId, increment);
    }
}
