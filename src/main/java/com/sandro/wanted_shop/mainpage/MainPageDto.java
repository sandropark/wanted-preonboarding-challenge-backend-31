package com.sandro.wanted_shop.mainpage;

import com.sandro.wanted_shop.product.dto.ProductListDto;

import java.util.List;

public record MainPageDto(
        List<ProductListDto> popularProducts,
        List<ProductListDto> newProduct,
        List<MainPageDto.CategoryDto> categories
) {
    public static MainPageDto of(List<ProductListDto> popularProducts, List<ProductListDto> newProduct, List<MainPageDto.CategoryDto> categories) {
        return new MainPageDto(popularProducts, newProduct, categories);
    }

    public record CategoryDto(
            Long id,
            String name,
            String slug,
            String imageUrl,
            Integer productCount
    ) implements Comparable<CategoryDto> {
        @Override
        public int compareTo(CategoryDto o) {
            return productCount.compareTo(o.productCount());
        }
    }
}
