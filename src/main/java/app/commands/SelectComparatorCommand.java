package app.commands;

import app.context.AppContext;
import app.options.EmployeeComparator;

public class SelectComparatorCommand implements Command {
    private final AppContext ctx;
    private final EmployeeComparator comparator;

    public SelectComparatorCommand(AppContext ctx, EmployeeComparator comparator) {
        this.ctx = ctx;
        this.comparator = comparator;
    }

    @Override
    public void execute() {
        ctx.io().setComparator(comparator);
    }
}
