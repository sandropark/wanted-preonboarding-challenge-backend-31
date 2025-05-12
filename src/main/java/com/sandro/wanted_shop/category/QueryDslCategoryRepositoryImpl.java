package com.sandro.wanted_shop.category;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sandro.wanted_shop.mainpage.MainPageDto;
import com.sandro.wanted_shop.product.entity.enums.ProductStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.sandro.wanted_shop.category.QCategory.category;
import static com.sandro.wanted_shop.product.entity.relation.QProductCategory.productCategory;

@RequiredArgsConstructor
@Repository
public class QueryDslCategoryRepositoryImpl implements QueryDslCategoryRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<MainPageDto.CategoryDto> findMainPageCategories() {
        return findMainPageCategories(5);
    }

    @Override
    public List<MainPageDto.CategoryDto> findMainPageCategories(int topN) {
        return queryFactory
                .select(Projections.constructor(
                                MainPageDto.CategoryDto.class,
                                category.id,
                                category.name,
                                category.slug,
                                category.imageUrl,
                                productCategory.count().intValue()
                        )
                ).from(category)
                .leftJoin(productCategory).on(category.eq(productCategory.category)).fetchJoin()
                .where(category.level.eq(1), productCategory.product.status.eq(ProductStatus.ACTIVE))
                .groupBy(category)
                .orderBy(productCategory.count().desc())
                .limit(topN)
                .fetch();
    }
}
