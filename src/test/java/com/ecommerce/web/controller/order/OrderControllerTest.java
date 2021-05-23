package com.ecommerce.web.controller.order;

import com.ecommerce.data.model.Customer;
import com.ecommerce.data.model.Order;
import com.ecommerce.data.model.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class OrderControllerTest {
    @Autowired
    MockMvc mockMvc;
    Order order;
    Customer customer;
    Product product;
    ObjectMapper objectMapper = new ObjectMapper();
    @BeforeEach
    void setUp() {
        order = new Order();
        customer = new Customer();
        product = new Product();
        objectMapper = new ObjectMapper();
    }

    @Test
    void saveOrder() throws Exception {
        customer.setFirstName("Kenny");
        customer.setLastName("maroko");
        customer.setContact("0908756432");
        customer.setPassword("98765");
        customer.setEmail("kenny@gmail.com");

        product.setName("cassava");
        product.setPrice(4500.00);
        product.setQuantity(4);
        product.setDescription("original cassava");
        product.setExpDate("3-5-2022");
        product.setOrders(List.of(order));

        order.setCanceled(false);
        order.setDelivered(false);
        order.setDate("12-05-2021");
        order.setCustomer(customer);
        order.setProducts(List.of(product));
        this.mockMvc.perform(post("/order/create")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(order)))
                .andDo(print()).andExpect(status().isCreated()).andReturn();
    }

    @Test
    void updateOrder() throws Exception {
        customer.setId(1);
        product.setId(2);
        order.setId(1);
        order.setDate("01-01-2022");
        order.setCustomer(customer);
        order.setProducts(List.of(product));


        this.mockMvc.perform(post("/order/update").contentType("application/json")
        .content(objectMapper.writeValueAsString(order)))
                .andDo(print()).andExpect(status().isOk())
                .andReturn();

    }

    @Test
    void findOrderById() throws Exception {
        this.mockMvc.perform(get("/order/1")
                .contentType("application/json"))
                .andDo(print()).andExpect(status().isOk()).andReturn();
    }

    @Test
    void deleteOrderById() throws Exception {
        this.mockMvc.perform(delete("/order/1"))
                .andDo(print()).andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void findAllOrder() throws Exception {
        this.mockMvc.perform(get("/order/all"))
                .andDo(print()).andExpect(status().isOk()).andReturn();

    }
}