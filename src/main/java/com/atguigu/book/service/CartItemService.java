package com.atguigu.book.service;

import com.atguigu.book.pojo.Cart;
import com.atguigu.book.pojo.CartItem;
import com.atguigu.book.pojo.User;

import java.util.List;

public interface CartItemService {
    void addCartItem(CartItem cartItem);
    void updateCartItem(CartItem cartItem);
    void addOrUpdateCartItem(CartItem cartItem, Cart cart);
    //获取指定用户的购物车列表，会将book的详细信息设置进去
    List<CartItem> getCartItemList(User user);
    Cart getCart(User user);//加载指定用户的购物车信息
}
