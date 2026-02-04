package app.commands;

import app.AppContext;
import app.ui.ConsoleIO;
import collection.CustomList;
import input.EmployeeManualInput;
import model.Employee;

public class ManualInputCommand implements Command {
    private final AppContext context;
    private final ConsoleIO io;

    public ManualInputCommand(AppContext context, ConsoleIO io) {
        this.context = context;
        this.io = io;
    }

    @Override
    public void execute() {
        EmployeeManualInput input = new EmployeeManualInput(io.getScanner());
        int count = io.readInt("Enter employees count: ");
        CustomList<Employee> inputList = new CustomList<>();
        for (int i = 0; i < count; i++) {
            Employee employee = input.readEmployee();
            inputList.add(employee);
        }
        context.setEmployees(inputList);
        io.println("Read employees: " + inputList.size());
    }
}
