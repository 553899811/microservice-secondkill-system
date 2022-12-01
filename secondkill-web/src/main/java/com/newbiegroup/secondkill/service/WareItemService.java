package com.newbiegroup.secondkill.service;

import com.newbiegroup.secondkill.vo.WareItemVO;

import java.util.List;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/8/1 19:02
 */
public interface WareItemService {

   List<WareItemVO> list();

   WareItemVO getWareItemById(Long wareItemId);

   WareItemVO createWareItem(WareItemVO wareItemVO);

   void increaseSales(Long wareItemId,Integer increment);

}
