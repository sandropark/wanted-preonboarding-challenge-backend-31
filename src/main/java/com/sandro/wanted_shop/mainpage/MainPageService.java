package com.sandro.wanted_shop.mainpage;

import com.sandro.wanted_shop.category.CategoryRepository;
import com.sandro.wanted_shop.product.dto.ProductListDto;
import com.sandro.wanted_shop.product.persistence.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Comparator.comparing;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MainPageService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public MainPageDto getMainPage() {
        // 1. 신규 상품 조회 (최근 등록순 10개)
        List<ProductListDto> newProducts = productRepository.findAllNew()
                .stream()
                .map(ProductListDto::from)
                .toList();

        // 2. 인기 상품 조회 (리뷰 평점 높은순 10개)
        List<ProductListDto> popularProducts = productRepository.findAllPopular()
                .stream()
                .map(ProductListDto::from)
                .sorted(comparing(ProductListDto::rating).reversed()
                        .thenComparing(comparing(ProductListDto::reviewCount).reversed()))
                .toList();

        // 3. 주요 카테고리 조회 (1단계 카테고리 중 상품이 많은 순으로 5개)
        List<MainPageDto.CategoryDto> categories = categoryRepository.findMainPageCategories();

        return MainPageDto.of(popularProducts, newProducts, categories);
    }
}
