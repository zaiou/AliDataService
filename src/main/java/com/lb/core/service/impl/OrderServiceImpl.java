package com.lb.core.service.impl;

import com.lb.core.order.entity.AliOrder;
import com.lb.core.order.mapper.AliOrderMapper;
import com.lb.core.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LB
 * @date 2017/6/29 10:48
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private AliOrderMapper orderMapper;
    @Override
    public List<AliOrder> getOrderList() {
        return orderMapper.selectAll();
    }
}
