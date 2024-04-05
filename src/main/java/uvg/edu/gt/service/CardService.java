package uvg.edu.gt.service;

import uvg.edu.gt.model.Card;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;



public class CardService {
    private Map<String, Card> cardMap;

    public CardService(Map<String, Card> cardMap) {
        this.cardMap = cardMap;
    }

    public void loadCards(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 2) {
                    String name = parts[0].trim();
                    String type = parts[1].trim();
                    Card card = new Card(name, type);
                    cardMap.put(name, card);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
