package caw.atm;

import java.util.Scanner;

public class ScannerClass {
    public String scannerGetString(String message) {
        String out = null;

        Scanner scanner = new Scanner(System.in);
        boolean entryCorrect = false;
        do {
            System.out.println(message);
            out = scanner.nextLine();
            if (out.equalsIgnoreCase("Ali") || out.equals("Ali")) {
                entryCorrect = true;
            } else {
                System.out.println("Incorrect input. Try again.");
            }

        } while (!entryCorrect);
        return out;
    }

    /////////////////
    public int scannerGetInt(String message) {
        String out = null;

        Scanner scanner = new Scanner(System.in);
        boolean entryCorrect = false;
        do {
            System.out.println(message);
            out = scanner.nextLine();
            if (out.isBlank() || out.isEmpty()) {
                System.out.println("Incorrect input. Try again.");
            } else {
                entryCorrect = true;
            }

        } while (!entryCorrect);
        return Integer.valueOf(out);
    }

}
