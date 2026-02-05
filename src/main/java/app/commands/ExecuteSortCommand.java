package app.commands;

import app.context.AppContext;
import app.ui.UserIO;

public class ExecuteSortCommand implements Command {
    private final AppContext ctx;
    private final UserIO io;

    public ExecuteSortCommand(AppContext ctx, UserIO io) {
        this.ctx = ctx;
        this.io = io;
    }

    @Override
    public void execute() {
        if (!ctx.collection().isReadyToSort()) {
            io.println("Nothing to sort!");
            return;
        }
        ctx.sorting().execute(ctx.collection().get(), ctx.io().getComparator());
        io.println("Collection sorted");
    }
}
