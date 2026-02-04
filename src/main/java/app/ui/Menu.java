package app.ui;

import app.ui.header.MenuHeader;

import java.util.LinkedHashMap;
import java.util.Map;

public class Menu {
    private final String title;
    private final Map<String, MenuItem> items = new LinkedHashMap<>();
    private MenuHeader header;

    public Menu(String title) {
        this.title = title;
    }

    public void setHeader(MenuHeader header) {
        this.header = header;
    }

    public void add(MenuItem item) {
        items.put(item.key(), item);
    }

    public void show(ConsoleIO io) {
        if (header != null) {
            header.print(io);
        }

        io.println("\n===== " + title + " =====");
        for (MenuItem item : items.values()) {
            io.println(item.key() + " - " + item.title());
        }
    }

    public void handle(ConsoleIO io) {
        //io.println("Choose option:");
        String choice = io.readLine().trim();
        MenuItem item = items.get(choice);
        if (item == null) {
            io.println("Unknown option");
        } else {
            item.command().execute();
        }
    }

    public void handle(ConsoleIO io, String key) {
        MenuItem item = items.get(key);
        if (item == null) {
            io.println("Unknown option");
        } else {
            item.command().execute();
        }
    }
}
