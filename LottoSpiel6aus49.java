package LottoSpiele;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class LottoSpiel6aus49 {

    public static void main(String[] args) {
    	
    	System.out.println("**********************************");
        System.out.println("Willkommen zum LOTTO 6aus49!");
    	System.out.println("*********************************");
        System.out.println("Wählen Sie 6 Glückszahlen zwischen 1 und 49.");
        System.out.println("Die Superzahl wird automatisch generiert.");
        System.out.println("Wenn Sie 6 Richtige plus Superzahl haben,\ngewinnen Sie den Jackpot!");
        System.out.println("\nDer Jackpot kann auf bis zu 50.000.000 Euro steigen.");
    	System.out.println("--------------------------------\n");

        Scanner scanner = new Scanner(System.in);

        System.out.print("Möchten Sie die Zahlen manuell eingeben? (j/n): ");
        char manualInput = scanner.nextLine().charAt(0);

        List<Integer> luckyNumbers = new ArrayList<>();

        if (manualInput == 'n') {
            System.out.print("Wie oft sollen Zufallsgenerierungen durchgeführt werden? \nGeben Sie 1, 6 oder 15 ein: \n");
            int numberOfGenerations = scanner.nextInt();

            for (int i = 0; i < numberOfGenerations; i++) {
                List<Integer> generatedNumbers = generateLottoNumbers();
                System.out.println("Tipp #" + (i + 1) + ": " + generatedNumbers);
                luckyNumbers.addAll(generatedNumbers);
            }
        }
        
        System.out.println("-------------------------------------");
        System.out.println("\nErgebnisse");
        System.out.println("-------------------------------------");

        System.out.print("Glückszahlen: ");
        List<Integer> userNumbers = generateLottoNumbers(); 
        System.out.println(userNumbers);

        int superzahl = generateSuperzahl();
        System.out.println("Superzahl: " + superzahl);
        System.out.println("-------------------------------------");


        checkResults(luckyNumbers, userNumbers, superzahl);

        System.out.println("Aktueller Jackpot: 10,000,000"); 
    }

    private static List<Integer> generateLottoNumbers() {
        List<Integer> numbers = new ArrayList<>();
        Random random = new Random();

        while (numbers.size() < 6) {
            int randomNumber = random.nextInt(49) + 1;
            if (!numbers.contains(randomNumber)) {
                numbers.add(randomNumber);
            }
        }

        Collections.sort(numbers);
        return numbers;
    }

    private static int generateSuperzahl() {
        return new Random().nextInt(10);
    }

    private static void checkResults(List<Integer> luckyNumbers, List<Integer> userNumbers, int superzahl) {
        List<Integer> matchedNumbers = new ArrayList<>(luckyNumbers);
        matchedNumbers.retainAll(userNumbers);

        System.out.println("Auswertung");

        int numberOfMatches = matchedNumbers.size() + (userNumbers.contains(superzahl) ? 1 : 0);
        int superzahlMatches = userNumbers.contains(superzahl) ? 1 : 0; 
        double winnings = calculateWinnings(numberOfMatches, superzahlMatches);

        if (winnings > 0) {
            System.out.println("Herzlichen Glückwunsch! Sie haben in Gewinnklasse " + numberOfMatches +
                    " gewonnen. Ihr Gewinn beträgt " + winnings + " Euro.");
        } else {
            System.out.println("Leider keine Gewinnkombination. Vielleicht beim nächsten Mal mehr Glück!");
        }
    }

    private static double calculateWinnings(int numberOfMatches, int superzahlMatches) {
        switch (numberOfMatches) {
            case 6:
                if (superzahlMatches == 1) {
                    return 12585434.4; 
                } else {
                    return 12585434.4; 
                }
            case 5:
                if (superzahlMatches == 1) {
                    return 12135.4; 
                } else {
                    return 4019.2; 
                }
            case 4:
                if (superzahlMatches == 1) {
                    return 191.1; 
                } else {
                    return 50.3; 
                }
            case 3:
                if (superzahlMatches == 1) {
                    return 21.2; 
                } else {
                    return 11.1; 
                }
            case 2:
                if (superzahlMatches == 1) {
                    return 6.0; 
                } else {
                    return 0.0; 
                }
            default:
                return 0.0; 
        }
    }
}