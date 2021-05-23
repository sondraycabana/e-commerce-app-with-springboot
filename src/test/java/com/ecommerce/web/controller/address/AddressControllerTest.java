package com.ecommerce.web.controller.address;

import com.ecommerce.data.model.Address;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;




@SpringBootTest
@AutoConfigureMockMvc
class AddressControllerTest {
    @Autowired
    private MockMvc mockMvc;
    Address address;
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        address = new Address();
        objectMapper = new ObjectMapper();
    }

    @Test
    void saveAddress() throws Exception {
        address.setState("Adamawa");
        address.setCity("Gandoki city");
        address.setCountry("Nigeria");
        address.setStreet("Lafia yagi street");
        address.setZipcode("95673");
        this.mockMvc.perform(post("/address/create")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(address)))
                .andDo(print()).andExpect(status().isCreated()).andReturn();
    }

    @Test
    void updateAddress() throws Exception {
        address.setId(5);
        address.setStreet("11, olaleye pamilerin street");

        this.mockMvc.perform(post("/address/update")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(address)))
                .andDo(print()).andExpect(status().isOk()).andReturn();
    }

    @Test
    void findAddressById() throws Exception {
        this.mockMvc.perform(get("/address/2").contentType("application/json"))
                .andDo(print()).andExpect(status().isOk()).andReturn();
    }

    @Test
    void deleteAddressById() throws Exception {
        this.mockMvc.perform(delete("/address/3"))
                .andDo(print()).andExpect(status().isOk()).andReturn();
    }

    @Test
    void findAllAddress() throws Exception {
        this.mockMvc.perform(get("/address/all"))
                .andDo(print()).andExpect(status().isOk()).andReturn();
    }
}