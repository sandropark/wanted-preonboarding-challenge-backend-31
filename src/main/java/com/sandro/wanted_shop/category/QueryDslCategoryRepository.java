package com.sandro.wanted_shop.category;

import com.sandro.wanted_shop.mainpage.MainPageDto;

import java.util.List;

public interface QueryDslCategoryRepository {
    List<MainPageDto.CategoryDto> findMainPageCategories();

    List<MainPageDto.CategoryDto> findMainPageCategories(int topN);
}
