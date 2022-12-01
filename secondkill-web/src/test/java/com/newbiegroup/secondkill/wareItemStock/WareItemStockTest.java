package com.newbiegroup.secondkill.wareItemStock;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.newbiegroup.secondkill.SpringBaseTest;
import com.newbiegroup.secondkill.entity.WareItemStock;
import com.newbiegroup.secondkill.manager.impl.WareItemStockManagerImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/8/2 17:52
 */
public class WareItemStockTest extends SpringBaseTest {

    @Autowired
    private WareItemStockManagerImpl wareItemStockManager;

    @Test
    public void test(){
        UpdateWrapper<WareItemStock> update = new UpdateWrapper<>();
        update.gt("stock", 8);
        update.setSql("stock = stock - " + 2);
        update.eq("ware_item_id", 11);
        boolean update1 = wareItemStockManager.update(update);
        System.out.println(update1);
    }
}
