package com.newbiegroup.secondkill.message.rocketmq;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/12/4 18:16
 */
@Data
public class RocketMqMessage<T> implements Serializable {
    private static final long serialVersionUID = -7050576061695363581L;

    private String content;

    private String msgKey;

    private String producerTopic;

    private String producerGroup;

    private String producerTag;
}
