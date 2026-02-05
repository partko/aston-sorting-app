package app.commands;

import app.context.AppContext;
import app.ui.UserIO;
import file.EmployeeJsonWriter;

public class SaveToFileCommand implements Command {
    private final AppContext ctx;
    private final UserIO io;
    private final EmployeeJsonWriter writer;

    public SaveToFileCommand(AppContext context, UserIO io, EmployeeJsonWriter writer) {
        this.ctx = context;
        this.io = io;
        this.writer = writer;
    }

    @Override
    public void execute() {
        io.println("Enter output full file path: ");
        String path = io.readLine().trim();
        try {
            writer.write(path, ctx.collection().get());
            io.println("Collection saved");
        } catch (Exception e) {
            io.println("Error: " + e.getMessage());
        }
    }
}
