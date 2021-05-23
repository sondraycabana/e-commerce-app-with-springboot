package com.ecommerce.service.card;

import com.ecommerce.data.model.Card;
import com.ecommerce.data.repository.CardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CardServiceImplTest {
    @Mock
    CardRepository cardRepository;
    @InjectMocks
    CardService cardService = new CardServiceImpl();
            Card card;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks (this);
        card = new Card();
    }

    @Test
    void saveCard() {
        when(cardRepository.save(card)).thenReturn(card);
        cardService.saveCard(card);
        verify(cardRepository,times(1)).save(card);
    }

    @Test
    void updateCard() {
        when(cardRepository.save(card)).thenReturn(card);
        cardService.updateCard(card);
        verify(cardRepository, times(1)).save(card);
    }

    @Test
    void findCardById() {
        when(cardRepository.findById(1)).thenReturn(Optional.of(card));
        cardService.findCardById(1);
        verify(cardRepository, times(1)).findById(1);
    }

    @Test
    void findAllCard() {
        when(cardRepository.findAll()).thenReturn(List.of(card));
        cardService.findAllCard();
        verify(cardRepository, times(1)).findAll();
    }

    @Test
    void deleteCardById() {
        when(cardRepository.findById(1)).thenReturn(Optional.of(card));
        cardService.deleteCardById(1);
        verify(cardRepository, times(1)).deleteById(1);
    }
}