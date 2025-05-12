package com.sandro.wanted_shop.product.dto;

import com.sandro.wanted_shop.product.entity.Product;
import com.sandro.wanted_shop.review.entity.Review;

import java.math.BigDecimal;
import java.time.LocalDateTime;

// TODO: 고도화하기
public record ProductListDto(
        Long id,
        String name,
        BigDecimal basePrice,
        double rating,
        int reviewCount,
        LocalDateTime createdAt
) {
    public static ProductListDto from(Product product) {
        return new ProductListDto(
                product.getId(),
                product.getName(),
                product.getPrice().getBasePrice(),
                getAverageRating(product),
                product.getReviews().size(),
                product.getCreatedAt()
        );
    }

    private static double getAverageRating(Product product) {
        return product.getReviews().stream()
                .mapToInt(Review::getRating)
                .average()
                .orElse(0);
    }
}
