package app.commands;

import app.AppContext;
import app.ui.ConsoleIO;
import input.EmployeeManualInput;
import model.Employee;
import thread.EmployeeOccurrenceCounter;

public class CountOccurrencesCommand implements Command {
    private final AppContext context;
    private final ConsoleIO io;

    public CountOccurrencesCommand(AppContext context, ConsoleIO io) {
        this.context = context;
        this.io = io;
    }

    @Override
    public void execute() {
        io.println("Enter employee to search:");
        EmployeeManualInput input = new EmployeeManualInput(io.getScanner());
        Employee target = input.readEmployee();
        int threadCount = io.readInt("Enter thread count:");
        try {
            int answer = EmployeeOccurrenceCounter.countOccurrences(context.getEmployees(), target, threadCount);
            io.println("Occurrences found: " + answer);
        } catch (Exception e) {
            io.println("Error: " + e.getMessage());
        }
    }
}
