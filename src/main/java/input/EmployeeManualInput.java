package input;

import app.ui.UserIO;
import model.Employee;

public class EmployeeManualInput {
    public Employee readEmployee(UserIO io) {
        while (true) {
            try {
                String name = io.readNonBlank("Enter employee name:");
                int experienceYears = io.readInt("Enter experience year:");
                double salary = io.readDouble("Enter salary:");

                return Employee.builder()
                        .name(name)
                        .experienceYears(experienceYears)
                        .salary(salary)
                        .build();

            } catch (IllegalArgumentException e) {
                io.println("Input error: " + e.getMessage());
                io.println("Try to enter the data again");
            }
        }
    }
}
