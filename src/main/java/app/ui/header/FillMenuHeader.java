package app.ui.header;

import app.context.AppContext;
import app.ui.UserIO;

public class FillMenuHeader implements MenuHeader {
    private final AppContext context;

    public FillMenuHeader(AppContext context) {
        this.context = context;
    }

    @Override
    public void print(UserIO io) {
        io.println("--------------------------------");
        io.println("   Current collection size: " + context.collection().get().size());
        io.println("        Replace mode: " + (context.io().isReplaceMode() ? "ON" : "OFF"));
        io.println("--------------------------------");
    }
}
