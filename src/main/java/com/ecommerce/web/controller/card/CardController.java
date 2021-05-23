package com.ecommerce.web.controller.card;

import com.ecommerce.data.model.Card;
import com.ecommerce.service.card.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardService cardService;

    @PostMapping("/create")
    public ResponseEntity<?> saveCard(@RequestBody Card card){
        cardService.saveCard(card);
        return new ResponseEntity<>(card, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public  ResponseEntity<?> updateCard(@RequestBody Card card){
        cardService.updateCard(card);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> findAddressById(@PathVariable Integer id){
        Card card = cardService.findCardById(1);
        return new ResponseEntity<>(card, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddressById(@PathVariable Integer id){
        cardService.deleteCardById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<?> findAllAddress(){
        List<Card> cardList = cardService.findAllCard();
        return new ResponseEntity<>(cardList,HttpStatus.OK);
    }
}
