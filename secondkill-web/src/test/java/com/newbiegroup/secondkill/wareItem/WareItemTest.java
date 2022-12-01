package com.newbiegroup.secondkill.wareItem;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.newbiegroup.secondkill.SpringBaseTest;
import com.newbiegroup.secondkill.dao.WareItemMapper;
import com.newbiegroup.secondkill.entity.WareItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: </p>
 *
 * @author zhangyong
 * @version 1.0.0
 * @date 2021/8/2 16:57
 */
public class WareItemTest extends SpringBaseTest {

    @Autowired
    private WareItemMapper wareItemMapper;

    @Test
    public void test() {
        UpdateWrapper<WareItem> update = new UpdateWrapper();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", 1);
        WareItem item = wareItemMapper.selectOne(queryWrapper);
        update.set("sales",10000000);
        item.setDescription("Jeck");
        update.eq("id", 1);
        int affectRows = wareItemMapper.update(item, update);
        System.out.println(affectRows);
    }
}
