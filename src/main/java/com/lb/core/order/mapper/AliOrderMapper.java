package com.lb.core.order.mapper;

import com.lb.core.order.entity.AliOrder;
import java.util.List;

public interface AliOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AliOrder record);

    List<AliOrder> selectAll();
}