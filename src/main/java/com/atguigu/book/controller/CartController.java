package com.atguigu.book.controller;

import com.atguigu.book.pojo.Book;
import com.atguigu.book.pojo.Cart;
import com.atguigu.book.pojo.CartItem;
import com.atguigu.book.pojo.User;
import com.atguigu.book.service.CartItemService;
import com.google.gson.Gson;

import javax.servlet.http.HttpSession;

public class CartController {
    private CartItemService cartItemService;

    public String index(HttpSession session){
        User user = (User)session.getAttribute("currUser");
        Cart cart = cartItemService.getCart(user);
        user.setCart(cart);
        session.setAttribute("currUser",user);
        return "cart/cart";
    }
    //将指定图书添加到购物车
    public String addCart(Integer bookId, HttpSession session){
        User user = (User)session.getAttribute("currUser");
        CartItem cartItem = new CartItem(new Book(bookId),1,user);
        cartItemService.addOrUpdateCartItem(cartItem,user.getCart());

        return "redirect:cart.do";
    }

    public String editCart(Integer cartItemId,Integer buyCount){
        cartItemService.updateCartItem(new CartItem(cartItemId,buyCount));
        return "";
    }

    public String cartInfo(HttpSession session){
        User user = (User)session.getAttribute("currUser");
        Cart cart = cartItemService.getCart(user);

        cart.getTotalBookCount();
        cart.getTotalCount();
        cart.getTotalMoney();

        Gson gson = new Gson();
        String cartJsonStr = gson.toJson(cart);
        return "json:"+cartJsonStr;
    }
}
