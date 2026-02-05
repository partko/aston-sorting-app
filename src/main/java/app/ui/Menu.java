package app.ui;

import app.ui.header.MenuHeader;

import java.util.LinkedHashMap;
import java.util.Map;

public class Menu {

    private final String title;
    private final UserIO io;
    private final Map<String, MenuItem> items = new LinkedHashMap<>();
    private MenuHeader header;
    private final boolean closeAfterAction;

    public Menu(String title, UserIO io, boolean closeAfterAction) {
        this.title = title;
        this.io = io;
        this.closeAfterAction = closeAfterAction;
    }

    public void setHeader(MenuHeader header) {
        this.header = header;
    }

    public void add(MenuItem item) {
        items.put(item.key(), item);
    }

    public void run() {
        while (true) {
            show();

            String choice = io.readLine().trim();
            if ("0".equals(choice)) {
                return;
            }

            handle(choice);

            if (closeAfterAction) {
                return;
            }
        }
    }

    private void show() {
        if (header != null) {
            header.print(io);
        }

        io.println("===== " + title + " =====");
        for (MenuItem item : items.values()) {
            io.println(item.key() + " - " + item.title());
        }
    }

    private void handle(String choice) {
        MenuItem item = items.get(choice);

        if (item == null) {
            io.println("Unknown option");
            return;
        }

        try {
            item.command().execute();
        } catch (Exception e) {
            io.println("Error: " + e.getMessage());
        }
    }
}
