package com.eshop.eshop.Repository;

import com.eshop.eshop.entity.basket.Basket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//I use repository bcs will extend crudRepository and will call crudRepository bcs
//I don't have to make with sql repository
@Repository
public interface BasketRepository extends CrudRepository<Basket, String> {
}
