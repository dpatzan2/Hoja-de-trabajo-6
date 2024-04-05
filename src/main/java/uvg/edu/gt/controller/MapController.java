package uvg.edu.gt.controller;

import uvg.edu.gt.model.Card;

import java.util.HashMap;
import java.util.Map;


public class MapController {
    private Map<String, Card> cardMap;

    public MapController(Map<String, Card> cardMap) {
        this.cardMap = cardMap;
    }

    public void addCard(String cardName, Card card) {
        cardMap.put(cardName, card);
    }

    public String getCardType(String cardName) {
        Card card = cardMap.get(cardName);
        if (card != null) {
            return card.getType();
        } else {
            return "Carta no encontrada";
        }
    }

    public void showAllCards() {
        System.out.println("Todas las cartas disponibles:");
        for (Map.Entry<String, Card> entry : cardMap.entrySet()) {
            System.out.println("Nombre: " + entry.getKey() + ", Tipo: " + entry.getValue().getType());
        }
    }

    public void showAllCardsByType() {
        System.out.println("Todas las cartas disponibles ordenadas por tipo:");
        Map<String, Integer> cardTypeCount = new HashMap<>();
        for (Card card : cardMap.values()) {
            String type = card.getType();
            cardTypeCount.put(type, cardTypeCount.getOrDefault(type, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : cardTypeCount.entrySet()) {
            System.out.println("Tipo: " + entry.getKey() + ", Cantidad: " + entry.getValue());
        }
    }
}
