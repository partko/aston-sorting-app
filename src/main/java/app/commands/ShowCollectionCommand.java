package app.commands;

import app.AppContext;
import app.ui.UserIO;
import model.Employee;

public class ShowCollectionCommand implements Command {
    private final AppContext context;
    private final UserIO io;

    public ShowCollectionCommand(AppContext context, UserIO io) {
        this.context = context;
        this.io = io;
    }

    @Override
    public void execute() {
        if (context.getEmployees().size() == 0) {
            io.println("Collection is empty");
        } else {
            for (Employee e : context.getEmployees()) {
                io.println(e.toString());
            }
        }
    }
}
