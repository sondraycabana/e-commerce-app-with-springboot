package com.ecommerce.web.controller.customer;

import com.ecommerce.data.model.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

//import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    Customer  customer;
    ObjectMapper mapper;
    @BeforeEach
    void setUp() {
        customer = new Customer();
        mapper = new ObjectMapper();
    }

//this is how we can test our controller

    @Test
    void testThatWECanCallCustomerEndpoint_ThenReturnOk() throws Exception{
        customer.setFirstName("snow");
        customer.setLastName("kapocho");
        customer.setContact("0908767564");
        customer.setEmail("a@gmail.com");
        customer.setPassword("123456");

        this.mockMvc.perform(post("/customer/create")
        .contentType("application/json")
        .content(mapper.writeValueAsString(customer)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }
    @Test
    void testThatWeCanUpdateCustomerEndPoint_thenReturnOk() throws Exception{
        customer.setId(2);
        customer.setFirstName("rambo");

        this.mockMvc.perform(post("/customer/update")
        .contentType("application/json")
        .content(mapper.writeValueAsString(customer)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }
    @Test
    void TestFindAllCustomerEndPoint_thenReturnCustomerObject() throws Exception{
        this.mockMvc.perform(get("/customer/all"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();
    }
    @Test
    void testThatWeCanFindCustomerByIdEndPoint_thenWeCanReturnCustomerObject() throws Exception{
        this.mockMvc.perform(get("/customer/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    void testThatWeCanDeleteCustomerById() throws  Exception{
        this.mockMvc.perform(delete("/customer/deleting/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }



}

