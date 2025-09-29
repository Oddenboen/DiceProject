package opgave01;

import java.util.Scanner;
import java.util.Arrays;

public class RollTwoDice {
    private static int rollCount = 0;
    private static int doubleCount = 0;
    private static int highestCombination = 0;
    private static int sum = 0;

    public static void main(String[] args) {
        System.out.println("Velkommen til spillet, rul en terning.");
        printRules();
        System.out.println();

        playTwoDice();

        System.out.println();
        System.out.println("Tak for at spille, rul en terning.");
    }

    private static void printRules() {
        System.out.println("=====================================================");
        System.out.println("Regler for rul en terning");
        System.out.println("Spilleren ruller to terninger, så længde man lyster.");
        System.out.println("=====================================================");
    }

    private static void playTwoDice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Rul en terning? ('ja/nej') ");
        String answer = scanner.nextLine();
        int[] face = new int[2];
        int[] countArray = new int[6];
        while (!answer.equals("nej")) {
            rollDice(face);

            System.out.println("Du rullede: " + face[0] + " og " + face[1]);
            System.out.println();

            updateStatistics(countArray, face);

            System.out.print("Rul en terning? ('ja/nej') ");
            answer = scanner.nextLine();
        }

        printStatistics(face, countArray);
        scanner.close();
    }

    private static int[] rollDice(int[] face) {
        for (int i = 0; i < face.length; i++) {
            face[i] = (int) (Math.random() * 6 + 1);
            sum += face[i];
        }
        if (face[0] == face[1]) {
            sammeØjne(face);
        }
        if (face[0] + face[1] > highestCombination) {
            highestCombination = face[0] + face[1];
        }

        return face;
    }

    private static void sammeØjne(int[] face) {
        doubleCount++;
    }

    private static void updateStatistics(int[] countArray, int[] face) {
        rollCount++;
        countArray[face[0] - 1]++;
        countArray[face[1] - 1]++;
    }

    private static void printStatistics(int[] face, int[] countArray) {
        System.out.println("\nResults:");
        System.out.println("-------");
        System.out.printf("%17s %4d\n", "Antal rul:", rollCount);
        System.out.println("Summen af alle dine terninger er: " + sum);
        System.out.println("Det samme antal øjne i ét kast forekom: " + doubleCount + " gange");
        System.out.println("Det højeste kast du slog var: " + highestCombination);
        for (int i = 0; i < countArray.length; i++) {
            System.out.println("Du har slået " + (i + 1) + "'ere, " + countArray[i] + " gange.");
        }
    }

}
