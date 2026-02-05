package app.ui.header;

import app.AppContext;
import app.ui.ConsoleIO;
import app.ui.UserIO;

public class SortMenuHeader implements MenuHeader {
    private final AppContext context;

    public SortMenuHeader(AppContext context) {
        this.context = context;
    }

    @Override
    public void print(UserIO io) {
        io.println("--------------------------------");
        io.println("    Current sorting settings");
        io.println("    Strategy: " + context.getStrategyName());
        io.println("    Comparator: " + context.getComparatorName());
        io.println("--------------------------------");
    }
}
