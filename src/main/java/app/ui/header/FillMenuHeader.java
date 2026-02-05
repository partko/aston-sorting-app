package app.ui.header;

import app.AppContext;
import app.ui.ConsoleIO;

public class FillMenuHeader implements MenuHeader {
    private final AppContext context;

    public FillMenuHeader(AppContext context) {
        this.context = context;
    }

    @Override
    public void print(ConsoleIO io) {
        io.println("--------------------------------");
        io.println("   Current collection size: " + context.getEmployees().size());
        io.println("        Replace mode: " + (context.isReplaceMode() ? "ON" : "OFF"));
        io.println("--------------------------------");
    }
}
