package app.commands;

import app.context.AppContext;

public class ChangeReplaceModeCommand implements Command {
    private final AppContext ctx;

    public ChangeReplaceModeCommand(AppContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void execute() {
        ctx.io().changeReplaceMode();
    }
}
