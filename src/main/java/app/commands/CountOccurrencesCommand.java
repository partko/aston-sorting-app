package app.commands;

import app.AppContext;
import app.ui.UserIO;
import input.EmployeeManualInput;
import model.Employee;
import thread.EmployeeOccurrenceCounter;

public class CountOccurrencesCommand implements Command {
    private final AppContext context;
    private final UserIO io;
    private final EmployeeManualInput input;

    public CountOccurrencesCommand(AppContext context, UserIO io, EmployeeManualInput input) {
        this.context = context;
        this.io = io;
        this.input = input;
    }

    @Override
    public void execute() {
        io.println("Enter employee to search:");
        Employee target = input.readEmployee(io);
        int threadCount = io.readInt("Enter thread count:");
        try {
            int answer = EmployeeOccurrenceCounter.countOccurrences(context.getEmployees(), target, threadCount);
            io.println("Occurrences found: " + answer);
        } catch (Exception e) {
            io.println("Error: " + e.getMessage());
        }
    }
}
