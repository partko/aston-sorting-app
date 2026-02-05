package app.ui;

import java.util.Scanner;

public class ConsoleIO implements UserIO {
    private final Scanner scanner = new Scanner(System.in);

    public void println(String text) {
        System.out.println(text);
    }

    public String readLine() {
        return scanner.nextLine();
    }

    public int readInt(String message) {
        while (true) {
            println(message);
            try {
                return Integer.parseInt(readLine().trim());
            } catch (NumberFormatException e) {
                println("Enter a valid integer!");
            }
        }
    }

    public double readDouble(String message) {
        while (true) {
            println(message);
            String s = readLine().trim().replace(',', '.');
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                println("Enter a valid number!");
            }
        }
    }

    public String readNonBlank(String message) {
        while (true) {
            println(message);
            String s = readLine().trim();
            if (!s.isBlank()) return s;
            println("Value must not be empty!");
        }
    }
}
