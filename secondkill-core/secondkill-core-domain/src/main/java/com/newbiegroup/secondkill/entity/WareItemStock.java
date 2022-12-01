package com.newbiegroup.secondkill.entity;

import com.newbiegroup.secondkill.vo.WareItemVO;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/8/2 0:18
 */
@Data
public class WareItemStock implements Serializable {
    private static final long serialVersionUID = -7473116056117421303L;
    private Long id;
    /**
     * 商品明细 ID
     * 针对每一个商品;
     */
    private Long wareItemId;
    /**
     * 库存
     */
    private Long stock;


    public static WareItemStock getInstanceFromWareItem(WareItem wareItem) {
        WareItemStock wareItemStock = new WareItemStock();
        wareItemStock.setWareItemId(wareItem.getId());
        return wareItemStock;
    }
}
