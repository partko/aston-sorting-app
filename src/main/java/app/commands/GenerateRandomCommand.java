package app.commands;

import app.context.AppContext;
import app.ui.UserIO;
import collection.CustomList;
import input.EmployeeGenerator;
import model.Employee;

public class GenerateRandomCommand implements Command {
    private static final int DEFAULT_COUNT = 50;

    private final AppContext ctx;
    private final UserIO io;

    public GenerateRandomCommand(AppContext ctx, UserIO io) {
        this.ctx = ctx;
        this.io = io;
    }

    @Override
    public void execute() {
        int max = io.readInt("Enter employees count (0 = default value):");
        int count = (max <= 0) ? DEFAULT_COUNT : max;

        CustomList<Employee> generatedList = EmployeeGenerator.generateEmployees(count);
        if (ctx.io().isReplaceMode()) {
            ctx.collection().clear();
        }
        ctx.collection().addAll(generatedList);
        io.println("Generated employees: " + generatedList.size());
    }
}
