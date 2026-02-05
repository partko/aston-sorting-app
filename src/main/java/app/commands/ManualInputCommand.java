package app.commands;

import app.AppContext;
import app.ui.UserIO;
import collection.CustomList;
import input.EmployeeManualInput;
import model.Employee;

public class ManualInputCommand implements Command {
    private final AppContext context;
    private final UserIO io;
    private final EmployeeManualInput input;

    public ManualInputCommand(AppContext context, UserIO io, EmployeeManualInput input) {
        this.context = context;
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
        context.setEmployees(inputList);
        io.println("Read employees: " + inputList.size());
    }
}
