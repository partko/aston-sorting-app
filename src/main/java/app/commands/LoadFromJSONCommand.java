package app.commands;

import app.context.AppContext;
import app.ui.UserIO;
import collection.CustomList;
import file.EmployeeJsonReader;
import model.Employee;

public class LoadFromJSONCommand implements Command {
    private final AppContext ctx;
    private final UserIO io;
    private final EmployeeJsonReader reader;

    public LoadFromJSONCommand(AppContext ctx, UserIO io, EmployeeJsonReader reader) {
        this.ctx = ctx;
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
            if (ctx.io().isReplaceMode()) {
                ctx.collection().clear();
            }
            ctx.collection().addAll(loadedList);
            io.println("Loaded employees: " + loadedList.size());
        } catch (Exception e) {
            io.println("Error: " + e.getMessage());
        }
    }
}
