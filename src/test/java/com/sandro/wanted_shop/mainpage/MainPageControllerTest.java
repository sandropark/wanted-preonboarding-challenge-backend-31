package com.sandro.wanted_shop.mainpage;

import com.sandro.wanted_shop.config.IntegrationTestContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MainPageControllerTest extends IntegrationTestContext {

    @DisplayName("메인 페이지 데이터 조회")
    @Test
    void mainPage() throws Exception {
        ResultActions resultActions = mvc.perform(get("/api/main"));

        resultActions
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$.popularProducts.size()").value(10),
                        jsonPath("$.popularProducts[0].rating").value(5),
                        jsonPath("$.popularProducts[9].rating").value(4),
                        jsonPath("$.newProduct.size()").value(10),
                        jsonPath("$.categories.size()").value(2)
                )
                .andDo(print());
    }
}