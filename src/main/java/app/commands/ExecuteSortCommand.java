package app.commands;

import app.AppContext;
import app.ui.ConsoleIO;

public class ExecuteSortCommand implements Command {
    private final AppContext context;
    private final ConsoleIO io;

    public ExecuteSortCommand(AppContext context, ConsoleIO io) {
        this.context = context;
        this.io = io;
    }

    @Override
    public void execute() {
        if (context.getEmployees() == null || context.getEmployees().size() < 2) {
            io.println("Nothing to sort!");
            return;
        }
        context.sortContext().execute(context.getEmployees(), context.getComparator());
    }
}
