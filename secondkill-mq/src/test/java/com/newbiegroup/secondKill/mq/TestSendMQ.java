package com.newbiegroup.secondKill.mq;

import com.newbiegroup.secondkill.manager.mq.RocketMqProducer;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/12/6 14:29
 */
public class TestSendMQ extends SpringBaseTest {
    @Value(value = "${rocketmq.producer.topic}")
    private String topic;

    @Value(value = "${rocketmq.producer.group}")
    private String group;

    @Autowired
    private RocketMqProducer rocketMqProducer;

    @Test
    public void send() {
        List<String> list = new ArrayList<>();
        list.add("成功与否");
        list.add("1");
        rocketMqProducer.send(list, topic, group);
    }
}
