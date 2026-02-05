package app.commands;

import app.context.AppContext;
import app.ui.UserIO;
import input.EmployeeManualInput;
import model.Employee;
import thread.EmployeeOccurrenceCounter;

public class CountOccurrencesCommand implements Command {
    private final AppContext ctx;
    private final UserIO io;
    private final EmployeeManualInput input;

    public CountOccurrencesCommand(AppContext ctx, UserIO io, EmployeeManualInput input) {
        this.ctx = ctx;
        this.io = io;
        this.input = input;
    }

    @Override
    public void execute() {
        io.println("Enter employee to search:");
        Employee target = input.readEmployee(io);
        int threadCount = io.readInt("Enter thread count:");
        try {
            int answer = EmployeeOccurrenceCounter.countOccurrences(ctx.collection().get(), target, threadCount);
            io.println("Occurrences found: " + answer);
        } catch (Exception e) {
            io.println("Error: " + e.getMessage());
        }
    }
}
