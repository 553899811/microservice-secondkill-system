package com.newbiegroup.secondkill.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/8/3 13:52
 */
@Data
public class WareItemCreateParam extends BaseParam implements Serializable {
    private static final long serialVersionUID = 9130625058508373765L;

    private Long id;
    /**
     * 商品明细ID
     */
    private Long wareItemId;
    /**
     * 商品名称
     */
    @NotBlank(message = "商品名称不能为空")
    private String title;
    /**
     * 商品SKU
     */
    private Long skuId;
    /**
     * 商品库存
     */
    private Integer stock;

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

}
