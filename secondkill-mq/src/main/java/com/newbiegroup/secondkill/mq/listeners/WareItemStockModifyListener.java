package com.newbiegroup.secondkill.mq.listeners;

import com.alibaba.fastjson.JSON;
import com.newbiegroup.secondkill.dto.WareItemStockModifyDTO;
import com.newbiegroup.secondkill.manager.WareItemStockMqOptionManager;
import com.newbiegroup.secondkill.message.rocketmq.RocketMqMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>ClassName: 异步扣减库存消息监听 </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/12/3 16:11
 */
@Slf4j
@Component
@RocketMQMessageListener(nameServer = "${rocketmq.name-server}", topic = "${rocketmq.consume.topic}",
        consumerGroup = "${rocketmq.consume.group}", selectorExpression = "*")
public class WareItemStockModifyListener implements RocketMQListener<RocketMqMessage> {

    @Autowired
    private WareItemStockMqOptionManager<WareItemStockModifyDTO> wareItemStockMqOptionManager;

    @Override
    public void onMessage(RocketMqMessage message) {
        log.info("=======consume message success,message:{}", JSON.toJSONString(message));
        wareItemStockMqOptionManager.onMsg(message.getContent());
    }
}
