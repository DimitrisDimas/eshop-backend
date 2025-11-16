package com.eshop.eshop.service.Impl;

import com.eshop.eshop.Repository.BasketRepository;
import com.eshop.eshop.dto.BasketItemResponse;
import com.eshop.eshop.dto.BasketResponse;
import com.eshop.eshop.entity.Basket;
import com.eshop.eshop.entity.BasketItem;
import com.eshop.eshop.service.BasketService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;

    public BasketServiceImpl(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }
    @Override
    public List<BasketResponse> getAllBaskets() {
        return null;
    }

    @Override
    public BasketResponse getBasketById(String basketId) {
        return null;
    }

    @Override
    public void deleteBasketById(String basketId) {

    }

    @Override
    public BasketResponse createBasket(Basket basket) {
        return null;
    }


    private BasketResponse convertToBasketResponse(Basket basket) {
        if(basket == null){
            return null;
        }
        List<BasketItemResponse> itemResponses = basket.getItems().stream()
                .map(this::convertToBasketItemResponse)
                .collect(Collectors.toList());
        return BasketResponse.builder()
                .id(basket.getId())
                .items(itemResponses)
                .build();
    }

    private BasketItemResponse convertToBasketItemResponse(BasketItem basketItem) {
        return BasketItemResponse.builder()
                .id(basketItem.getId())
                .name(basketItem.getName())
                .description(basketItem.getDescription())
                .price(basketItem.getPrice())
                .pictureUrl(basketItem.getPictureUrl())
                .productBrand(basketItem.getProductBrand())
                .productType(basketItem.getProductType())
                .quantity(basketItem.getQuantity())
                .build();
    }
}
