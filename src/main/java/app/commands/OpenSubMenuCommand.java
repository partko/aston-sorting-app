package app.commands;

import app.ui.Menu;

import java.util.function.BooleanSupplier;

public class OpenSubMenuCommand implements Command {
    private final Menu menu;
    private final Command anotherAction;
    private final BooleanSupplier anotherActionCondition;

    public OpenSubMenuCommand(Menu menu) {
        this.menu = menu;
        anotherAction = null;
        anotherActionCondition = () -> false;
    }

    public OpenSubMenuCommand(Menu menu, Command anotherAction, BooleanSupplier anotherActionCondition) {
        this.menu = menu;
        this.anotherAction = anotherAction;
        this.anotherActionCondition = anotherActionCondition;
    }

    @Override
    public void execute() {
        if (anotherActionCondition.getAsBoolean() && anotherAction != null) {
            anotherAction.execute();
            return;
        }
        menu.run();
    }
}
