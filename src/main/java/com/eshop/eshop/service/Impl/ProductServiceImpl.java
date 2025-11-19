package com.eshop.eshop.service.Impl;

import com.eshop.eshop.Repository.ProductRepository;
import com.eshop.eshop.dto.ProductResponse;
import com.eshop.eshop.entity.Product;
import com.eshop.eshop.exception.ProductNotFoundException;
import com.eshop.eshop.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
        ProductResponse productResponse = convertToProductResponse(product);
        log.info("Fetched Product by Product Id: {}", productId);
        return productResponse;
    }

    @Override
    public Page<ProductResponse> getProducts(Pageable pageable, Integer brandId, Integer typeId, String keyword) {
        log.info("Fetching Products!!!");

        Specification<Product> spec = null;

        if (brandId != null) {
            Specification<Product> brandSpec = (root, query, cb) -> cb.equal(root.get("brand").get("id"), brandId);
            spec = (spec == null) ? brandSpec : spec.and(brandSpec);
        }

        if (typeId != null) {
            Specification<Product> typeSpec = (root, query, cb) -> cb.equal(root.get("type").get("id"), typeId);
            spec = (spec == null) ? typeSpec : spec.and(typeSpec);
        }

        if (keyword != null && !keyword.isEmpty()) {
            Specification<Product> keywordSpec = (root, query, cb) ->
                    cb.like(cb.lower(root.get("name")), "%" + keyword.toLowerCase() + "%");
            spec = (spec == null) ? keywordSpec : spec.and(keywordSpec);
        }

        log.info("Fetching All Products!!!");
        return productRepository.findAll(spec, pageable)
                .map(this::convertToProductResponse);
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