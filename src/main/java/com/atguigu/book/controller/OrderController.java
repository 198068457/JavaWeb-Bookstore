package com.atguigu.book.controller;

import com.atguigu.book.pojo.OrderBean;
import com.atguigu.book.pojo.User;
import com.atguigu.book.service.OrderService;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

public class OrderController {
    private OrderService orderService;
    //结账
    public String checkOut(HttpSession session){
        OrderBean orderBean = new OrderBean();

        Date now = getNowSqlDate();
        orderBean.setOrderNo(UUID.randomUUID().toString());
        orderBean.setOrderDate(now);

        User user = (User)session.getAttribute("currUser");
        orderBean.setOrderUser(user);

        orderBean.setOrderMoney(user.getCart().getTotalMoney());

        orderBean.setOrderStatus(0);

        orderService.addOrderBean(orderBean);
        return "index";
    }

    private Date getNowSqlDate(){
        java.util.Date date = new java.util.Date();
        Date now = new Date(date.getTime());
        return now;
    }

    public String getOrderList(HttpSession session){
        User user = (User)session.getAttribute("currUser");
        List<OrderBean> orderList = orderService.getOrderList(user);
        user.setOrderList(orderList);
        session.setAttribute("currUser",user);

        return "order/order";
    }
}
