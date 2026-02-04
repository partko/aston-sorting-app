package app.commands;

import app.AppContext;
import app.ui.ConsoleIO;
import collection.CustomList;

public class ClearCollectionCommand implements Command {
    private final AppContext context;
    private final ConsoleIO io;

    public ClearCollectionCommand(AppContext context, ConsoleIO io) {
        this.context = context;
        this.io = io;
    }

    @Override
    public void execute() {
        context.setEmployees(new CustomList<>());
        io.println("Collection cleared");
    }
}
