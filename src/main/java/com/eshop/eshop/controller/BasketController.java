package com.eshop.eshop.controller;

import com.eshop.eshop.dto.BasketResponse;
import com.eshop.eshop.service.BasketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/baskets")
public class BasketController {

    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @GetMapping
    public List<BasketResponse> getAllBaskets() {
        return basketService.getAllBaskets();
    }

    @GetMapping("/{basketId}")
    public BasketResponse getBasketById(@PathVariable String basketId){
        return basketService.getBasketById(basketId);
    }

    @DeleteMapping("/{basketId}")
    public void deleteBasketById(@PathVariable String basketId){
        basketService.deleteBasketById(basketId);
    }

}
