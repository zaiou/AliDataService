package com.lb.core.service;

import com.lb.core.order.entity.AliOrder;

import java.util.List;

/**
 * @author LB
 * @date 2017/6/29 10:46
 */
public interface OrderService {
    public List<AliOrder> getOrderList();
}
