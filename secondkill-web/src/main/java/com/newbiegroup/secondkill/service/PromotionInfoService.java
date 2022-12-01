package com.newbiegroup.secondkill.service;

import com.newbiegroup.secondkill.vo.PromotionVO;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/8/2 14:09
 */
public interface PromotionInfoService {

    PromotionVO selectByWareItemId(Long itemId);

}
