package app.commands;

import app.AppContext;
import app.ui.UserIO;

public class ExecuteSortCommand implements Command {
    private final AppContext context;
    private final UserIO io;

    public ExecuteSortCommand(AppContext context, UserIO io) {
        this.context = context;
        this.io = io;
    }

    @Override
    public void execute() {
        if (context.getEmployees() == null || context.getEmployees().size() < 2) {
            io.println("Nothing to sort!");
            return;
        }
        context.getSortContext().execute(context.getEmployees(), context.getComparator());
        io.println("Collection sorted");
    }
}
