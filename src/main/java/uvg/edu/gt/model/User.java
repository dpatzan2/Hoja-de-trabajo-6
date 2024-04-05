package uvg.edu.gt.model;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private Map<String, Integer> cardCollection;

    public User(String name) {
        this.name = name;
        this.cardCollection = new HashMap<String, Integer>();
    }

    public void addCard(String cardName) {
        cardCollection.put(cardName, cardCollection.getOrDefault(cardName, 0) + 1);
    }

}
