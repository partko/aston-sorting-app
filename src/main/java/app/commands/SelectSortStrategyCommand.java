package app.commands;

import app.context.AppContext;
import app.options.EmployeeComparator;
import model.Employee;
import sort.SortStrategy;

import java.util.Comparator;

public class SelectSortStrategyCommand implements Command {
    private final AppContext ctx;
    private final SortStrategy<Employee> strategy;
    private final boolean isEvenExperienceMode;

    public SelectSortStrategyCommand(AppContext ctx, SortStrategy<Employee> strategy, boolean isEvenExperienceMode) {
        this.ctx = ctx;
        this.strategy = strategy;
        this.isEvenExperienceMode = isEvenExperienceMode;
    }

    public SelectSortStrategyCommand(AppContext ctx, SortStrategy<Employee> strategy) {
        this.ctx = ctx;
        this.strategy = strategy;
        this.isEvenExperienceMode = false;
    }

    @Override
    public void execute() {
        ctx.sorting().setStrategy(strategy);
        ctx.setEvenExperienceMode(isEvenExperienceMode);

        if (ctx.isEvenExperienceMode()) {
            Comparator<Employee> c = ctx.io().getComparator();
            if (c != EmployeeComparator.BY_EXPERIENCE_ASC.comparator() &&
                    c != EmployeeComparator.BY_EXPERIENCE_DESC.comparator()) {
                ctx.io().setComparator(EmployeeComparator.BY_EXPERIENCE_ASC);
            }
        }
    }
}
