package com.kdhr.service;

import com.kdhr.dto.ShoppingCartDTO;
import com.kdhr.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    /**
     * 新增購物車
     *
     * @param shoppingCartDTO
     */
    void addShoppingCart(ShoppingCartDTO shoppingCartDTO);

    /**
     * 查看購物車
     *
     * @return
     */
    List<ShoppingCart> showShoppingCart();

    /**
     * 清空購物車
     */
    void cleanShoppingCart();

    /**
     * 刪除購物車中一個商品
     *
     * @param shoppingCartDTO
     */
    void subShoppingCart(ShoppingCartDTO shoppingCartDTO);
}
