package app.commands;

import app.context.AppContext;
import app.ui.UserIO;
import model.Employee;

public class ShowCollectionCommand implements Command {
    private final AppContext ctx;
    private final UserIO io;

    public ShowCollectionCommand(AppContext ctx, UserIO io) {
        this.ctx = ctx;
        this.io = io;
    }

    @Override
    public void execute() {
        if (ctx.collection().get().size() == 0) {
            io.println("Collection is empty");
        } else {
            for (Employee e : ctx.collection().get()) {
                io.println(e.toString());
            }
        }
    }
}
