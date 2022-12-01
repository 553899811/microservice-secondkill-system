package com.newbiegroup.secondkill.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.newbiegroup.secondkill.common.utils.BigDecimalUtils;
import com.newbiegroup.secondkill.entity.WareItem;
import com.newbiegroup.secondkill.entity.WareItemStock;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/8/1 19:04
 */
@Data
public class WareItemVO {

    private Long id;
    /**
     * 商品明细ID
     */
    private Long wareItemId;

    /**
     * 商品名称
     */
    @NotBlank(message = "商品名称不能为空")
    private String wareItemName;

    /**
     * 商品SKU
     */
    private Long skuId;
    /**
     * 商品库存
     */
    private Long stock;

    /**
     * 商品售价
     */
    private BigDecimal price;

    /**
     * 商品实销
     */
    private Integer sales;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 商品主图URL
     */
    private String imgUrl;

    /**
     * 促销ID
     */
    private Long promotionId;

    /**
     * 促销类型
     */
    private Integer promotionType;

    private String promotionTypeDesc;

    /**
     * 促销状态
     */
    private Integer promotionStatus;

    private String promotionStatusDesc;

    /**
     * 促销开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date promotionStartDate;

    /**
     * 促销结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date promotionEndDate;

    /**
     * 商品促销价格
     */
    private BigDecimal promotionItemPrice;

    public static WareItemVO getInstance(WareItem wareItem) {
        WareItemVO vo = new WareItemVO();
        BeanUtils.copyProperties(wareItem, vo);
        vo.setPrice(BigDecimalUtils.amountLongToDecimal(wareItem.getPrice()));
        return vo;
    }
}
