package app.commands;

import app.AppContext;
import app.ui.ConsoleIO;
import app.ui.Menu;
import model.Employee;

import java.util.Comparator;

public class SelectComparatorCommand implements Command {
    private final AppContext context;
    private final Comparator<Employee> comparator;
    private final String comparatorName;

    public SelectComparatorCommand(AppContext context, Comparator<Employee> comparator, String comparatorName) {
        this.context = context;
        this.comparator = comparator;
        this.comparatorName = comparatorName;
    }

    @Override
    public void execute() {
        context.setComparator(comparator, comparatorName);
    }
}
