package com.eshop.eshop.service;

import com.eshop.eshop.dto.BasketResponse;
import com.eshop.eshop.entity.Basket;

import java.util.List;

public interface BasketService {

    List<BasketResponse> getAllBaskets();

    BasketResponse getBasketById(String basketId);

    void deleteBasketById(String basketId);

    BasketResponse createBasket(Basket basket);
}