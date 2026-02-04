package app.ui;

import java.util.Scanner;

public class ConsoleIO {
    private final Scanner scanner = new Scanner(System.in);

    public void print(String text) {
        System.out.print(text);
    }

    public void println(String text) {
        System.out.println(text);
    }

    public String readLine() {
        return scanner.nextLine();
    }

    public int readInt(String message) {
        while (true) {
            System.out.println(message);
            try {
                return Integer.parseInt(readLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Enter a valid integer!");
            }
        }
    }

    public Scanner getScanner() {
        return scanner;
    }
}
