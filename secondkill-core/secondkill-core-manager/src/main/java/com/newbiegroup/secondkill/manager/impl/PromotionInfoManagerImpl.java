package com.newbiegroup.secondkill.manager.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newbiegroup.secondkill.common.exception.BusinessException;
import com.newbiegroup.secondkill.dao.PromotionInfoMapper;
import com.newbiegroup.secondkill.entity.PromotionInfo;
import com.newbiegroup.secondkill.manager.PromotionInfoManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/8/2 12:03
 */
@Slf4j
@Service
public class PromotionInfoManagerImpl extends ServiceImpl<PromotionInfoMapper, PromotionInfo> implements PromotionInfoManager {

    @Autowired
    private PromotionInfoMapper promotionInfoMapper;

    public PromotionInfo selectByWareItemId(Long itemId) {
        QueryWrapper query = new QueryWrapper();
        query.eq("ware_item_id", itemId);
        PromotionInfo promotionInfo = promotionInfoMapper.selectOne(query);
        return Optional.ofNullable(promotionInfo)
                .<BusinessException>orElseThrow(() -> {
                    throw new BusinessException("促销信息不存在");
                });
    }
}
