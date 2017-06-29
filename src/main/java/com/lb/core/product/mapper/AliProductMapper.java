package com.lb.core.product.mapper;

import com.lb.core.product.entity.AliProduct;
import java.util.List;

public interface AliProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AliProduct record);

    List<AliProduct> selectAll();
}