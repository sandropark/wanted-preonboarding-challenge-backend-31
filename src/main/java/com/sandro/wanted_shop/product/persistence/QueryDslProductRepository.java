package com.sandro.wanted_shop.product.persistence;

import com.sandro.wanted_shop.product.dto.ProductFilterDto;
import com.sandro.wanted_shop.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QueryDslProductRepository {
    Page<Product> findAll(Pageable pageable, ProductFilterDto filter);

    List<Product> findAllPopular();

    List<Product> findAllPopular(int topN);

    List<Product> findAllNew();

    List<Product> findAllNew(int topN);
}
