package app.ui.header;

import app.AppContext;
import app.ui.ConsoleIO;

public class MainMenuHeader implements MenuHeader {
    private final AppContext context;

    public MainMenuHeader(AppContext context) {
        this.context = context;
    }

    @Override
    public void print(ConsoleIO io) {
        io.println("--------------------------------");
        io.println("   Current collection size: " + context.getEmployees().size());
        io.println("--------------------------------");
    }
}
