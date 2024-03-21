package com.atguigu.book.dao.impl;

import com.atguigu.book.dao.OrderDAO;
import com.atguigu.book.pojo.OrderBean;
import com.atguigu.book.pojo.User;
import com.atguigu.myssm.basedao.BaseDAO;

import java.math.BigDecimal;
import java.util.Base64;
import java.util.List;

public class OrderDAOImpl extends BaseDAO<OrderBean> implements OrderDAO {
    @Override
    public void addOrderBean(OrderBean orderBean) {
        int orderBeanId = super.executeUpdate("insert into t_order values(0,?,?,?,?,?)",orderBean.getOrderNo(),orderBean.getOrderDate(),orderBean.getOrderUser().getId(),orderBean.getOrderMoney(),orderBean.getOrderStatus());
        orderBean.setId(orderBeanId);
    }

    @Override
    public List<OrderBean> getOrderList(User user) {
        return executeQuery("select * from t_order where orderUser = ?",user.getId());
    }

    @Override
    public Integer getOrderTotalBookCount(OrderBean orderBean) {
        String sql = "select sum(t3.buyCount) as totalBookCount,t3.orderBean from\n" +
                "(\n" +
                "\tselect t1.id,t2.buyCount,t2.orderBean from t_order t1 inner join t_order_item t2\n" +
                "   on t1.id = t2.orderBean where t1.orderUser = ?\n" +
                ") t3 where t3.orderBean = ? group by t3.orderBean";
        Integer result = ((BigDecimal)executeComplexQuery(sql,orderBean.getOrderUser().getId(),orderBean.getId())[0]).intValue();
        if(result == null){
            result = 0;
        }
        return result;
    }
}
