package com.newbiegroup.secondkill.entity;

import com.newbiegroup.secondkill.common.utils.BigDecimalUtils;
import com.newbiegroup.secondkill.vo.PromotionVO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/8/2 11:56
 */
@Data
public class PromotionInfo implements Serializable {
    private static final long serialVersionUID = -9219744200641367483L;
    private Long id;

    /**
     * 商品明细ID;
     */
    private Long wareItemId;

    /**
     * 促销类型
     */
    private Integer promotionType;

    /**
     * 促销状态
     */
    private Integer promotionStatus;

    /**
     * 促销名称
     */
    private String promotionName;

    /**
     * 促销商品单价
     */
    private Long promotionItemPrice;

    /**
     * 促销开始时间
     */
    private Date startDate;

    /**
     * 促销结束时间
     */
    private Date endDate;

    public static PromotionInfo getInstance(PromotionVO promotionVO) {
        PromotionInfo promotionInfo = new PromotionInfo();
        if (promotionVO.getPromotionItemPrice() != null) {
            promotionInfo.setPromotionItemPrice(BigDecimalUtils.amountDecimalToLong(promotionVO.getPromotionItemPrice()));
        }
        BeanUtils.copyProperties(promotionVO, promotionInfo);
        return promotionInfo;
    }
}
