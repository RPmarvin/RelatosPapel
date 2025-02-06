package com.relatos_papel.ms_books_payments.service;

import com.relatos_papel.ms_books_payments.controller.model.OrderRequest;
import com.relatos_papel.ms_books_payments.data.model.Order;

import java.util.List;

public interface OrdersService {
    Order createOrder(OrderRequest request);

    Order getOrder(String id);

    List<Order> getOrders();
}
