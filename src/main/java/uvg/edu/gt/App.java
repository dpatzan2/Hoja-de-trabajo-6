package uvg.edu.gt;

import uvg.edu.gt.controller.MapController;
import uvg.edu.gt.controller.UserController;
import uvg.edu.gt.model.Card;
import uvg.edu.gt.service.MapServiceFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        long startLoadTime = System.nanoTime();
        // Cargar el mapa de cartas
        Map<String, Card> cardMap = loadCardMap("cards_desc.txt");
        long endLoadTime = System.nanoTime();
        long loadTime = endLoadTime - startLoadTime;
        System.out.println("Tiempo de carga del mapa de cartas: " + loadTime + " nanosegundos");

        // Inicializar controladores
        MapController mapController = new MapController(cardMap);
        UserController userController = new UserController(new HashMap<>());

        // Menú de la aplicación
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nSeleccione una opción:");
            System.out.println("1. Agregar una carta a la colección del usuario");
            System.out.println("2. Mostrar el tipo de una carta específica");
            System.out.println("3. Mostrar la colección del usuario");
            System.out.println("4. Mostrar la colección del usuario ordenada por tipo");
            System.out.println("5. Mostrar todas las cartas disponibles");
            System.out.println("6. Mostrar todas las cartas disponibles ordenadas por tipo");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Ingrese el nombre de la carta que desea agregar: ");
                    scanner.nextLine(); // Consume la nueva línea
                    String cardName = scanner.nextLine();
                    // Obtener la carta del mapa usando el nombre
                    long startAddTime = System.nanoTime();
                    Card cardToAdd = cardMap.get(cardName);
                    if (cardToAdd != null) {
                        mapController.addCard(cardName, cardToAdd); // Agregar la carta al mapa y al usuario
                        userController.addCard(cardName);
                        long endAddTime = System.nanoTime();
                        long addTime = endAddTime - startAddTime;
                        System.out.println("Tiempo de agregado de carta: " + addTime + " nanosegundos");
                        System.out.println("Carta agregada a la colección del usuario.");
                    } else {
                        System.out.println("Error: La carta no existe en la lista de cartas disponibles.");
                    }
                case 2:
                    System.out.print("Ingrese el nombre de la carta: ");
                    scanner.nextLine(); // Consume la nueva línea
                    cardName = scanner.nextLine();
                    long startGetTypeTime = System.nanoTime();
                    String cardType = mapController.getCardType(cardName);
                    long endGetTypeTime = System.nanoTime();
                    long getTypeTime = endGetTypeTime - startGetTypeTime;
                    System.out.println("Tipo de la carta: " + cardType);
                    System.out.println("Tiempo de obtener tipo de carta: " + getTypeTime + " nanosegundos");
                    break;
                case 3:
                    long startUserCollectionTime = System.nanoTime();
                    userController.showUserCollection();
                    long endUserCollectionTime = System.nanoTime();
                    long userCollectionTime = endUserCollectionTime - startUserCollectionTime;
                    System.out.println("Tiempo de mostrar colección del usuario: " + userCollectionTime + " nanosegundos");
                    break;
                case 4:
                    long startUserCollectionByTypeTime = System.nanoTime();
                    userController.showUserCollectionByType(cardMap);
                    long endUserCollectionByTypeTime = System.nanoTime();
                    long userCollectionByTypeTime = endUserCollectionByTypeTime - startUserCollectionByTypeTime;
                    System.out.println("Tiempo de mostrar colección del usuario ordenada por tipo: " + userCollectionByTypeTime + " nanosegundos");
                    break;
                case 5:
                    long startAllCardsTime = System.nanoTime();
                    mapController.showAllCards();
                    long endAllCardsTime = System.nanoTime();
                    long allCardsTime = endAllCardsTime - startAllCardsTime;
                    System.out.println("Tiempo de mostrar todas las cartas disponibles: " + allCardsTime + " nanosegundos");
                    break;
                case 6:
                    long startAllCardsByTypeTime = System.nanoTime();
                    mapController.showAllCardsByType();
                    long endAllCardsByTypeTime = System.nanoTime();
                    long allCardsByTypeTime = endAllCardsByTypeTime - startAllCardsByTypeTime;
                    System.out.println("Tiempo de mostrar todas las cartas disponibles ordenadas por tipo: " + allCardsByTypeTime + " nanosegundos");
                    break;
                case 0:
                    System.out.println("Saliendo de la aplicación...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        } while (choice != 0);

        scanner.close();
    }

    public static Map<String, Card> loadCardMap(String filename) {
        Map<String, Card> cardMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 2) {
                    String cardName = parts[0].trim();
                    String cardType = parts[1].trim();
                    cardMap.put(cardName, new Card(cardName,cardType));
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo: " + e.getMessage());
        }
        return cardMap;
    }
}
