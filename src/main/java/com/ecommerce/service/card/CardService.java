package com.ecommerce.service.card;

import com.ecommerce.data.model.Card;

import java.util.List;

public interface CardService {
    public Card saveCard(Card card);
    public Card updateCard(Card card);
    public  Card findCardById(Integer id);
    public List<Card> findAllCard();
    public  void deleteCardById(Integer id);
}
