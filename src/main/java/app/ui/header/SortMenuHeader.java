package app.ui.header;

import app.context.AppContext;
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
        io.println("    Strategy: " + usefulName(context.sorting().getStrategy().getClass()));
        io.println("    Comparator: " + context.io().getComparatorName());
        io.println("--------------------------------");
    }

    private String usefulName(Class<?> c) {
        String name = c.getSimpleName();
        name = name.replace("SortStrategy", "");
        name = name.replaceAll("([a-z])([A-Z])", "$1 $2");
        return name.trim();
    }
}
