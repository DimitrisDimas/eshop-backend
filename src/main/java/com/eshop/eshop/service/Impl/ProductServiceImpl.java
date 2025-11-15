package com.eshop.eshop.service.Impl;

import com.eshop.eshop.Repository.ProductRepository;
import com.eshop.eshop.dto.ProductResponse;
import com.eshop.eshop.entity.Product;
import com.eshop.eshop.exception.ProductNotFoundException;
import com.eshop.eshop.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponse getProductById(Integer productId) {
        log.info("fetching Product by Id: {}", productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Category", "id", productId));
        //now convert the Product to Product Response
        ProductResponse productResponse = convertToProductResponse(product);
        log.info("Fetched Product by Product Id: {}", productId);
        return productResponse;
    }

    @Override
    public Page<ProductResponse> getProducts(Pageable pageable) {
        log.info("Fetching Products!!!");
        // Fetch Products with pagination directly from repository
        Page<Product> productsPage = productRepository.findAll(pageable);

        // Map Product -> ProductResponse using Page.map()
        Page<ProductResponse> productResponses = productsPage.map(this::convertToProductResponse);

        log.info("Fetched Products!!!");
        return productResponses;
    }


    private ProductResponse convertToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .pictureUrl(product.getPictureUrl())
                .productBrand(product.getBrand().getName())
                .productType(product.getType().getName())
                .build();
    }

}