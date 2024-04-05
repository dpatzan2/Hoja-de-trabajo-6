package uvg.edu.gt.controller;

import uvg.edu.gt.model.Card;

import java.util.HashMap;
import java.util.Map;


public class UserController {
    private Map<String, Integer> userCardCollection;

    public UserController(Map<String, Integer> userCardCollection) {
        this.userCardCollection = userCardCollection;
    }

    public void addCard(String cardName) {
        userCardCollection.put(cardName, userCardCollection.getOrDefault(cardName, 0) + 1);
    }

    public String getCardType(String cardName, Map<String, Card> cardMap) {
        Card card = cardMap.get(cardName);
        if (card != null) {
            return card.getType();
        } else {
            return "Carta no encontrada";
        }
    }

    public void showUserCollection() {
        System.out.println("Colección del usuario:");
        for (Map.Entry<String, Integer> entry : userCardCollection.entrySet()) {
            System.out.println("Nombre: " + entry.getKey() + ", Cantidad: " + entry.getValue());
        }
    }

    public void showUserCollectionByType(Map<String, Card> cardMap) {
        System.out.println("Colección del usuario ordenada por tipo:");
        Map<String, Integer> cardTypeCount = new HashMap<>();
        for (String cardName : userCardCollection.keySet()) {
            Card card = cardMap.get(cardName);
            if (card != null) {
                String type = card.getType();
                cardTypeCount.put(type, cardTypeCount.getOrDefault(type, 0) + 1);
            }
        }
        for (Map.Entry<String, Integer> entry : cardTypeCount.entrySet()) {
            System.out.println("Tipo: " + entry.getKey() + ", Cantidad: " + entry.getValue());
        }
    }
}
