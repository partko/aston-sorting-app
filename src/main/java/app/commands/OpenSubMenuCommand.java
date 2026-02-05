package app.commands;

import app.ui.Menu;

public class OpenSubMenuCommand implements Command {
    private final Menu menu;

    public OpenSubMenuCommand(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void execute() {
        menu.run();
    }
}
