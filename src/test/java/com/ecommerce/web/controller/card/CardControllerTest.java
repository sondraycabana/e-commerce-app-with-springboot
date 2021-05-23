package com.ecommerce.web.controller.card;

import com.ecommerce.data.model.Card;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CardControllerTest {
    @Autowired
    MockMvc mockMvc;
    Card card;
    ObjectMapper objectMapper = new ObjectMapper();
    @BeforeEach
    void setUp() {
        card = new Card();
        objectMapper = new ObjectMapper();
    }

    @Test
    void saveCard() throws Exception {
        card.setCardName("farouk alonge");
        card.setExpDate("1-2-2022");
        card.setCardNumber("123456789");
        card.setCardType("master card");
        card.setCvv(234);
        this.mockMvc.perform(post("/card/create")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(card)))
                .andDo(print()).andExpect(status().isCreated()).andReturn();
    }

    @Test
    void updateCard() throws Exception {
        card.setId(2);
        card.setCardName("fatima festus");

        this.mockMvc.perform(post("/card/create")
                .contentType("application/json").content(objectMapper.writeValueAsString(card)))
                .andDo(print()).andExpect(status().isCreated()).andReturn();
    }

    @Test
    void findAddressById() throws Exception {
        this.mockMvc.perform(get("/card/1"))
                .andDo(print()).andExpect(status().isOk()).andReturn();
    }

    @Test
    void deleteAddressById() throws Exception {
        this.mockMvc.perform(delete("/card/1"))
                .andDo(print()).andExpect(status().isOk()).andReturn();
    }

    @Test
    void findAllAddress() throws Exception {
        this.mockMvc.perform(get("/card/all").contentType("application/json"))
                .andDo(print()).andExpect(status().isOk()).andReturn();
    }
}