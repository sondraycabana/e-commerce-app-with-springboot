package com.ecommerce.service.card;

import com.ecommerce.data.model.Card;
import com.ecommerce.data.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService{
    @Autowired
    CardRepository cardRepository;

    @Override
    public Card saveCard(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public Card updateCard(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public Card findCardById(Integer id) {
        return cardRepository.findById(id).orElse(null);
    }

    @Override
    public List<Card> findAllCard() {
        return cardRepository.findAll();
    }

    @Override
    public void deleteCardById(Integer id) {
        cardRepository.deleteById(id);
    }
}
