package com.newbiegroup.secondkill.vo;

import com.newbiegroup.secondkill.common.utils.BigDecimalUtils;
import com.newbiegroup.secondkill.entity.PromotionInfo;
import com.newbiegroup.secondkill.enums.PromotionStatusEnum;
import com.newbiegroup.secondkill.enums.PromotionTypeEnum;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/8/2 13:26
 */
@Data
public class PromotionVO implements Serializable {
    private static final long serialVersionUID = -4878391326120430119L;
    private Long id;

    /**
     * 商品ID;
     */
    private Long wareItemId;

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
     * 促销名称
     */
    private String promotionName;

    /**
     * 促销商品价格
     */
    private BigDecimal promotionItemPrice;

    /**
     * 促销开始时间
     */
    private Date startDate;

    /**
     * 促销结束时间
     */
    private Date endDate;

    public static PromotionVO getInstance(PromotionInfo promotionInfo) {
        PromotionVO vo = new PromotionVO();
        BeanUtils.copyProperties(promotionInfo, vo);
        vo.setPromotionStatusDesc(PromotionStatusEnum.toEnum(promotionInfo.getPromotionStatus()).getValue());
        vo.setPromotionTypeDesc(PromotionTypeEnum.toEnum(promotionInfo.getPromotionType()).getValue());
        vo.setPromotionItemPrice(BigDecimalUtils.amountLongToDecimal(promotionInfo.getPromotionItemPrice()));
        return vo;
    }
}
