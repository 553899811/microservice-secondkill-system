package com.newbiegroup.secondkill.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newbiegroup.secondkill.entity.WareItemStock;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/8/2 0:57
 */
@Repository
public interface WareItemStockMapper extends BaseMapper<WareItemStock> {
    int decreaseStock(@Param("wareItemId") Long wareItemId, @Param("amount") Integer amount);

    int increaseStock(@Param("wareItemId") Long wareItemId, @Param("amount") Integer amount);
}
