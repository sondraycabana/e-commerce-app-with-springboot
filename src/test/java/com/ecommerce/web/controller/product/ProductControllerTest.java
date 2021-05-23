package com.ecommerce.web.controller.product;

import com.ecommerce.data.model.Customer;
import com.ecommerce.data.model.Order;
import com.ecommerce.data.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
    @AutoConfigureMockMvc
@RequestMapping("/product")
class ProductControllerTest {
        @Autowired
        MockMvc mockMvc;
        Product product;

        ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        product = new Product();
        mapper = new ObjectMapper();
    }

    @Test
    void createProduct() throws Exception {
        product.setName("gizard and chips");
        product.setDescription("Sweet fish and chips from top notch spot");
        product.setExpDate("12-3-2021");
        product.setPrice(1400.00);
        product.setQuantity(1);


        this.mockMvc.perform(post("/product/create")
        .contentType("application/json")
        .content(mapper.writeValueAsString(product)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    void updateProduct() throws Exception {
        product.setId(1);
        product.setName("snail and chips");


        this.mockMvc.perform(post("/product/update")
                .contentType("application/json")
                .content(mapper.writeValueAsString(product)))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void deleteProduct() throws Exception{
        this.mockMvc.perform(delete("/product/deleting/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void getAllproducts() throws  Exception{
       this.mockMvc.perform(get("/product/all"))
               .andDo(print())
               .andExpect(status().isOk())
               .andReturn();
    }

    @Test
    void getProductById() throws  Exception{
        this.mockMvc.perform(get("/product/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}