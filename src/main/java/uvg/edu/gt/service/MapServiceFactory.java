package uvg.edu.gt.service;

import uvg.edu.gt.model.Card;

import java.util.HashMap;
import java.util.Map;

public class MapServiceFactory {
    public static Map<String, Card> createMap(String filename) {
        Map<String, Card> cardMap = new HashMap<>();
        CardService cardService = new CardService(cardMap);
        cardService.loadCards(filename);
        return cardMap;
    }
}
