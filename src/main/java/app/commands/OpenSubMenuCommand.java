package app.commands;

import app.ui.ConsoleIO;
import app.ui.Menu;

public class OpenSubMenuCommand implements Command {
    private final Menu menu;
    private final ConsoleIO io;
    private final boolean closeAfterAction;

    public OpenSubMenuCommand(Menu menu, ConsoleIO io, boolean closeAfterAction) {
        this.menu = menu;
        this.io = io;
        this.closeAfterAction = closeAfterAction;
    }

    @Override
    public void execute() {
        while (true) {
            menu.show(io);
            String choice = io.readLine().trim();
            if ("0".equals(choice)) {
                return;
            }
            menu.handle(io, choice);
            if (closeAfterAction) {
                return;
            }
        }
    }
}
