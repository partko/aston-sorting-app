package app.commands;

import app.context.AppContext;
import app.options.EmployeeComparator;
import app.ui.UserIO;
import model.Employee;

import java.util.Comparator;

public class ToggleExperienceComparatorCommand implements Command {
    private final AppContext ctx;
    private final UserIO io;


    public ToggleExperienceComparatorCommand(AppContext ctx, UserIO io) {
        this.ctx = ctx;
        this.io = io;
    }

    @Override
    public void execute() {
        Comparator<Employee> current = ctx.io().getComparator();

        EmployeeComparator next = (current == EmployeeComparator.BY_EXPERIENCE_ASC.comparator())
                ? EmployeeComparator.BY_EXPERIENCE_DESC
                : EmployeeComparator.BY_EXPERIENCE_ASC;

        ctx.io().setComparator(next);
        io.println("Comparator switched to: " + next.label());
    }
}
