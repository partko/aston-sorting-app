package app.commands;

import app.AppContext;

public class ChangeReplaceModeCommand implements Command {
    private final AppContext context;

    public ChangeReplaceModeCommand(AppContext context) {
        this.context = context;
    }

    @Override
    public void execute() {
        context.setReplaceMode(!context.isReplaceMode());
    }
}
