package app.commands;

import app.context.AppContext;
import app.ui.UserIO;

public class ClearCollectionCommand implements Command {
    private final AppContext ctx;
    private final UserIO io;

    public ClearCollectionCommand(AppContext ctx, UserIO io) {
        this.ctx = ctx;
        this.io = io;
    }

    @Override
    public void execute() {
        ctx.collection().clear();
        io.println("Collection cleared");
    }
}
