package opgave02;

import java.util.Scanner;

public class Craps {
    private static int rollCount = 0;
    private static int point = 0;
    private static int test = 0;
    private static boolean vundet = false;
    private static boolean tabt = false;
    private static int spilVundet = 0;
    private static int spilTabt = 0;
    private static boolean showResult = false;


    public static void main(String[] args) {
        System.out.println("Velkommen til spillet Craps.");
        printRules();
        System.out.println();
        Scanner scanner = new Scanner(System.in);

        playCraps(scanner);

        System.out.println();
        System.out.println("Tak for at spille, Craps.");
        scanner.close();

    }

    private static void printRules() {
        System.out.println("=====================================================");
        System.out.println("Regler for Craps");
        System.out.println("Spilleren ruller to terninger, så længde man lyster.");
        System.out.println("Du vinder hvis du slår 7 eller 11 i første kast.");
        System.out.println("Du taber hvis du slår 2, 3 eller 12 i første kast.");
        System.out.println("Hvis du slår et andet tal, så bliver dette tal til 'point'.");
        System.out.println("Du vinder ved at slå 'point' igen, du taver hvis du slår 7 efter 'point' er etableret.");
        System.out.println("=====================================================");
    }
    //Win = 7 eller 11
    //Lose = 2, 3 eller 12
    //Rest = point
    //Lose ved 7 hvis point er etableret
    private static void playCraps(Scanner scanner) {
        restart();
        System.out.print("Rul en terning? ('ja/nej') ");
        String answer = scanner.nextLine();
        int[] face = new int[2];
        while (!answer.equals("nej") && vundet == false && tabt == false) {
            rollDice(face);
            System.out.println("Du rullede: " + face[0] + " og " + face[1]);

            updateStatistics(face);
            if (vundet == false && tabt == false) {
                System.out.println("Du skal slå " + point + " for at vinde.");
                System.out.println();
                System.out.print("Rul en terning? ('ja/nej') ");
                answer = scanner.nextLine();
            }
            test = 2;
        }

        printStatistics(scanner);

    }

    private static int[] rollDice(int[] face) {
        for (int i = 0; i < face.length; i++) {
            face[i] = (int) (Math.random() * 6 + 1);
        }
        if (test == 0) {
            point = face[0] + face[1];
                //Her tjekker vi om vi har vundet eller tabt i første kast
            if (point == 7 || point == 11) {
                vundet = true;
            }
            if (point == 2 || point == 3 || point == 12) {
                tabt = true;
            }
            //test bliver til 1, så dette if-statement ikke kører igen
            test = 1;
        }
        return face;
    }


    private static void updateStatistics(int[] face) {
        rollCount++;
        //Vi gør så test skal være 2, da dette søger for at man ikke vinder og taber samtidig ved at slå 7 i første kast
        if (face[0] + face[1] == point && test == 2) {
            vundet = true;
        }
        if (face[0] + face[1] == 7 && test == 2) {
            tabt = true;
        }

    }
    private static void restart() {
        rollCount = 0;
        point = 0;
        test = 0;
        vundet = false;
        tabt = false;
    }

    private static void printStatistics(Scanner scanner) {
        System.out.println("\nResults:");
        System.out.println("-------");
        System.out.printf("%17s %4d\n", "Antal rul:", rollCount);
        if (vundet == true) {
            System.out.println("Du vandt");
            spilVundet++;
        } else {
            System.out.println("Du tabte");
            spilTabt++;
        }
        Scanner input = new Scanner(System.in);
        System.out.println("Vil du spille igen ('ja/nej')");
        String igen = input.nextLine();
        if (!igen.equalsIgnoreCase("nej")) {
            playCraps(scanner);
        }
        if (showResult == false) {
            System.out.println("Du har i alt vundet " + spilVundet + " spil.");
            System.out.println("Du har i alt tabt " + spilTabt + " spil.");
            showResult = true;
        }

    }

}
