package com.newbiegroup.secondkill.manager.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newbiegroup.secondkill.dao.SequenceMapper;
import com.newbiegroup.secondkill.entity.Sequence;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>ClassName: 订单号生成序列 </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/10/18 18:11
 */
@Slf4j
@Service
public class SequenceManagerImpl extends ServiceImpl<SequenceMapper, Sequence> {

    public Long generateOrderId(Long userId) {
        return null;
    }
}
