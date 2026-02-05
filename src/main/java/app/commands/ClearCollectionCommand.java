package app.commands;

import app.AppContext;
import app.ui.UserIO;
import collection.CustomList;

public class ClearCollectionCommand implements Command {
    private final AppContext context;
    private final UserIO io;

    public ClearCollectionCommand(AppContext context, UserIO io) {
        this.context = context;
        this.io = io;
    }

    @Override
    public void execute() {
        context.setEmployees(new CustomList<>());
        io.println("Collection cleared");
    }
}
