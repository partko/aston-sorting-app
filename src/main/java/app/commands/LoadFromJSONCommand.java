package app.commands;

import app.AppContext;
import app.ui.ConsoleIO;
import collection.CustomList;
import file.EmployeeJsonReader;
import model.Employee;

public class LoadFromJSONCommand implements Command {
    private final AppContext context;
    private final ConsoleIO io;
    private final EmployeeJsonReader reader;

    public LoadFromJSONCommand(AppContext context, ConsoleIO io, EmployeeJsonReader reader) {
        this.context = context;
        this.io = io;
        this.reader = reader;
    }

    @Override
    public void execute() {
        io.println("Absolute path to JSONL file: ");
        String path = io.readLine().trim();
        int limit = io.readInt("Enter limit (0 = no limit): ");
        try {
            CustomList<Employee> loadedList = reader.read(path, limit);
            context.setEmployees(loadedList);
            io.println("Loaded employees: " + loadedList.size());
        } catch (Exception e) {
            io.println("Error: " + e.getMessage());
        }
    }
}
