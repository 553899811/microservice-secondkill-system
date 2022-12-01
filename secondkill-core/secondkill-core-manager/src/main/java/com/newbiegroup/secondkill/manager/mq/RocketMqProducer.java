package com.newbiegroup.secondkill.manager.mq;

import com.alibaba.fastjson.JSON;
import com.newbiegroup.secondkill.message.rocketmq.RocketMqMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/12/6 13:47
 */
@Slf4j
@Configuration
@ConditionalOnProperty(name = "rocketmq.producer.enable", havingValue = "true")
public class RocketMqProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    private <T> SendResult send(T msg, String topic, String group, String tag) {
        if (StringUtils.isBlank(topic) || StringUtils.isBlank(group)) {
            new Throwable("发送方topic 或者 group 不能为空");
        }
        RocketMqMessage message = new RocketMqMessage();
        message.setProducerTopic(topic);
        message.setProducerGroup(group);
        message.setContent(JSON.toJSONString(msg));
        message.setProducerTag(tag);
        Message messageFinal = MessageBuilder.withPayload(message)
                .build();
        String destination = topic;
        if (StringUtils.isNotBlank(tag)) {
            destination = topic + ":" + tag;
        }
        SendResult sendResult = rocketMQTemplate.syncSend(destination, messageFinal);
        log.info("成功发送消息，消息内容为：{}，返回值为：{}", message, sendResult);
        return sendResult;
    }

    /**
     * 发送事务型消息
     *
     * @param destination
     * @param message
     * @param arg
     * @param <T>
     * @return
     */
    public <T> TransactionSendResult sendMessageInTransaction(T msg, String topic, String group, String tag,
                                                              final Object arg) {
        if (StringUtils.isBlank(topic) || StringUtils.isBlank(group)) {
            new Throwable("发送方topic 或者 group 不能为空");
        }
        RocketMqMessage message = new RocketMqMessage();
        message.setProducerTopic(topic);
        message.setProducerGroup(group);
        message.setContent(JSON.toJSONString(msg));
        message.setProducerTag(tag);
        Message messageFinal = MessageBuilder.withPayload(message)
                .build();
        String destination = topic;
        if (StringUtils.isNotBlank(tag)) {
            destination = topic + ":" + tag;
        }
        TransactionSendResult sendResult = rocketMQTemplate.sendMessageInTransaction(destination, messageFinal, arg);
        return sendResult;
    }

    public <T> TransactionSendResult sendMessageInTransaction(T msg, String topic, String group, final Object arg) {
        return this.sendMessageInTransaction(msg, topic, group, null, arg);
    }

    public <T> SendResult send(T msg, String topic, String group) {
        return this.send(msg, topic, group, null);
    }
}
