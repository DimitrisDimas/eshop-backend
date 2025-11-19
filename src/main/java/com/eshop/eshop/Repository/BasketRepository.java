package com.eshop.eshop.Repository;

import com.eshop.eshop.entity.basket.Basket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BasketRepository extends CrudRepository<Basket, String> {
}
