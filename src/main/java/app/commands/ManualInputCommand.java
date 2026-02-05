package app.commands;

import app.context.AppContext;
import app.ui.UserIO;
import collection.CustomList;
import input.EmployeeManualInput;
import model.Employee;

public class ManualInputCommand implements Command {
    private final AppContext ctx;
    private final UserIO io;
    private final EmployeeManualInput input;

    public ManualInputCommand(AppContext ctx, UserIO io, EmployeeManualInput input) {
        this.ctx = ctx;
        this.io = io;
        this.input = input;
    }

    @Override
    public void execute() {
        int count = io.readInt("Enter employees count: ");
        CustomList<Employee> inputList = new CustomList<>();
        for (int i = 0; i < count; i++) {
            inputList.add(input.readEmployee(io));
        }
        if (ctx.io().isReplaceMode()) {
            ctx.collection().clear();
        }
        ctx.collection().addAll(inputList);
        io.println("Read employees: " + inputList.size());
    }
}
