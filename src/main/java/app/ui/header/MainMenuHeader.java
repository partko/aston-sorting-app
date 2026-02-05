package app.ui.header;

import app.AppContext;
import app.ui.ConsoleIO;
import app.ui.UserIO;

public class MainMenuHeader implements MenuHeader {
    private final AppContext context;

    public MainMenuHeader(AppContext context) {
        this.context = context;
    }

    @Override
    public void print(UserIO io) {
        io.println("--------------------------------");
        io.println("   Current collection size: " + context.getEmployees().size());
        io.println("--------------------------------");
    }
}
