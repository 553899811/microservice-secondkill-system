package com.newbiegroup.secondkill.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newbiegroup.secondkill.entity.Sequence;
import com.newbiegroup.secondkill.entity.StockFlowLog;
import org.springframework.stereotype.Repository;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/12/13 20:02
 */
@Repository
public interface StockFlowLogMapper  extends BaseMapper<StockFlowLog> {

    StockFlowLog selectByFlowLogId(Long flowLogId);
}
