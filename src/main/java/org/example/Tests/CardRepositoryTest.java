package org.example.Tests;

import org.example.Models.Card;
import org.example.Repositories.CardRepository;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CardRepositoryTest {

    private CardRepository cardRepository = new CardRepository();

    @Test
    public void testAddCard() {
        cardRepository.addCard(2, "Test Card", 100, 1234123412, 12, 2023);
        List<Card> cards = cardRepository.getCardsByUserId(1);
        assertNotNull(cards);
        assertEquals(2, cards.size());
    }

    @Test
    public void testGetCardsByUserId() {
        List<Card> cards = cardRepository.getCardsByUserId(1);
        assertNotNull(cards);
    }

    @Test
    public void testGetCardById() {
        cardRepository.addCard(2, "Test Card", 100, 1234567890, 12, 2023);
        Card card = cardRepository.getCardById(1);
        assertNotNull(card);
    }
}
