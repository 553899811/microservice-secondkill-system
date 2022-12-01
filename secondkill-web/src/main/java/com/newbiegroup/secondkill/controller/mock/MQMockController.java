package com.newbiegroup.secondkill.controller.mock;

import com.newbiegroup.secondkill.common.response.WebResponse;
import com.newbiegroup.secondkill.controller.BaseController;
import com.newbiegroup.secondkill.dto.WareItemStockModifyDTO;
import com.newbiegroup.secondkill.manager.mq.RocketMqProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>ClassName: Mock RocketMQ 发送消息 </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/12/6 20:13
 */
@RestController
@RequestMapping(value = "/mock/mq")
public class MQMockController extends BaseController {

    @Value(value = "${rocketmq.producer.topic}")
    private String topic;

    @Value(value = "${rocketmq.producer.group}")
    private String group;

    @Autowired
    private RocketMqProducer rocketMqProducer;


    @PostMapping("/produce")
    public WebResponse produce(@RequestBody Map<String, Object> map) {
        WareItemStockModifyDTO dto = new WareItemStockModifyDTO();
        dto.setWareItemId(((Integer) map.get("ware_item_id")).longValue());
        dto.setAmount((Integer) map.get("amount"));
        dto.setStockOptType((Integer) map.get("opt_type"));
        rocketMqProducer.send(dto, topic, group);
        return WebResponse.success(dto);
    }
}
