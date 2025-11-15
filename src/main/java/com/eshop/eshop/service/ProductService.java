package com.eshop.eshop.service;

import com.eshop.eshop.dto.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    ProductResponse getProductById(Integer productId);
    Page<ProductResponse> getProducts(Pageable pageable);

}
