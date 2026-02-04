package app.commands;

import app.ConsoleApplication;

public class ExitCommand implements Command {
    private ConsoleApplication app;

    public ExitCommand(ConsoleApplication app) {
        this.app = app;
    }

    @Override
    public void execute() {
        app.stop();
    }
}
