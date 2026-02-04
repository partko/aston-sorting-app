package app.commands;

import app.AppContext;
import app.ui.ConsoleIO;
import file.EmployeeJsonWriter;

public class SaveToFileCommand implements Command {
    private final AppContext context;
    private final ConsoleIO io;

    public SaveToFileCommand(AppContext context, ConsoleIO io) {
        this.context = context;
        this.io = io;
    }

    @Override
    public void execute() {
        io.println("Enter output full file path: ");
        String path = io.readLine().trim();
        try {
            new EmployeeJsonWriter().write(path, context.getEmployees());
            io.println("Collection saved");
        } catch (Exception e) {
            io.println("Error: " + e.getMessage());
        }
    }
}
