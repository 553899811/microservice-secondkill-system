package com.newbiegroup.secondkill.entity;

import com.newbiegroup.secondkill.common.utils.BigDecimalUtils;
import com.newbiegroup.secondkill.vo.WareItemVO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/8/1 18:50
 */
@Data
public class WareItem implements Serializable {
    private static final long serialVersionUID = 810242293616449782L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 商品明细ID
     */
    private Long wareItemId;

    /**
     * 商品SKU
     */
    private Long skuId;

    /**
     * 商品名称
     */
    private String wareItemName;


    /**
     * 商品售价
     */
    private Long price;

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


    public static WareItem getInstance(WareItemVO wareItemVO) {
        WareItem wareItem = new WareItem();
        BeanUtils.copyProperties(wareItemVO, wareItem);
        if (wareItemVO.getPrice() != null) {
            wareItem.setPrice(BigDecimalUtils.amountDecimalToLong(wareItemVO.getPrice()));
        }
        return wareItem;
    }
}
