package com.wee.test.controller.shop;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ShopControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("on list all shops, with{data in DB}, returns values")
    void onListAllShopsWithDataInDBReturnValues() throws Exception {
        var getRequest = get("/data");
        var response = mockMvc.perform(getRequest)
            .andExpect(status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        var shops = new ObjectMapper().readValue(response, new TypeReference<List<Shop>>(){});

        assertFalse(shops.isEmpty());
        assertEquals(3, shops.size());
    }
}
