package com.eshop.eshop.service.Impl;



import com.eshop.eshop.Repository.BrandRepository;
import com.eshop.eshop.Repository.OrderRepository;
import com.eshop.eshop.Repository.TypeRepository;
import com.eshop.eshop.dto.basketDto.BasketItemResponse;
import com.eshop.eshop.dto.orderDto.OrderDto;
import com.eshop.eshop.dto.orderDto.OrderResponse;
import com.eshop.eshop.entity.order.Order;
import com.eshop.eshop.entity.order.OrderItem;
import com.eshop.eshop.entity.order.ProductItemOrder;
import com.eshop.eshop.mapper.OrderMapper;
import com.eshop.eshop.service.BasketService;
import com.eshop.eshop.service.OrderService;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Log4j2
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final BrandRepository brandRepository;
    private final TypeRepository typeRepository;
    private final BasketService basketService;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, BrandRepository brandRepository, TypeRepository typeRepository, BasketService basketService, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.brandRepository = brandRepository;
        this.typeRepository = typeRepository;
        this.basketService = basketService;
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderResponse getOrderById(Integer orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        return optionalOrder.map(orderMapper::OrderToOrderResponse).orElse(null);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return List.of();
    }

    @Override
    public Page<OrderResponse> getAllOrders(Pageable pageable) {
        return null;
    }

    @Override
    public Integer createOrder(OrderDto order) {
        return 0;
    }

    @Override
    public void deleteOrder(Integer orderId) {

    }


    private OrderItem mapBasketItemToOrderItem(BasketItemResponse basketItemResponse) {
        if(basketItemResponse!=null){
            OrderItem orderItem = new OrderItem();
            orderItem.setItemOrdered(mapBasketItemToProduct(basketItemResponse));
            orderItem.setQuantity(basketItemResponse.getQuantity());
            return orderItem;
        }else{
            return null;
        }
    }

    private ProductItemOrder mapBasketItemToProduct(BasketItemResponse basketItemResponse) {
        ProductItemOrder productItemOrdered = new ProductItemOrder();
        //Populate
        productItemOrdered.setName(basketItemResponse.getName());
        productItemOrdered.setPictureUrl(basketItemResponse.getPictureUrl());
        productItemOrdered.setProductId(basketItemResponse.getId());
        return productItemOrdered;
    }



}
