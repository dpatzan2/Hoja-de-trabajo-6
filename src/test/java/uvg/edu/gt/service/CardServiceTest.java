package uvg.edu.gt.service;

import junit.framework.TestCase;
import uvg.edu.gt.model.Card;
import java.util.Map;
import java.util.HashMap;

public class CardServiceTest extends TestCase {

    private CardService cardService;
    private Map<String, Card> cardMap;

    protected void setUp() {
        cardMap = new HashMap<>();
        cardService = new CardService(cardMap);
    }

    public void testLoadCards() {
        // Ruta del archivo de prueba
        String filename = "cards_desc.txt";
        cardService.loadCards(filename);

        // Verificar que el mapa de cartas se haya cargado correctamente
        assertFalse(cardMap.isEmpty());
        assertEquals(8861, cardMap.size()); // Número de cartas esperado

        // Verificar que algunas cartas específicas se hayan cargado correctamente
        assertTrue(cardMap.containsKey("Dark Magician"));
        assertEquals("Monstruo", cardMap.get("Dark Magician").getType());

        assertTrue(cardMap.containsKey("Blue-Eyes White Dragon"));
        assertEquals("Monstruo", cardMap.get("Blue-Eyes White Dragon").getType());
    }

}
