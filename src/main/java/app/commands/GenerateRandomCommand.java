package app.commands;

import app.AppContext;
import app.ui.ConsoleIO;
import collection.CustomList;
import input.EmployeeGenerator;
import model.Employee;

public class GenerateRandomCommand implements Command {
    private static final int DEFAULT_COUNT = 50;

    private final AppContext context;
    private final ConsoleIO io;

    public GenerateRandomCommand(AppContext context, ConsoleIO io) {
        this.context = context;
        this.io = io;
    }

    @Override
    public void execute() {
        int max = io.readInt("Enter employees count (0 = default value):");
        int count = (max <= 0) ? DEFAULT_COUNT : max;

        CustomList<Employee> generatedList = EmployeeGenerator.generateEmployees(count);
        context.setEmployees(generatedList);
        io.println("Generated employees: " + generatedList.size());
    }
}
