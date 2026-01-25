package input;

import model.Employee;

import java.util.Scanner;

public class EmployeeManualInput {

    private Scanner scanner;

    public EmployeeManualInput(Scanner scanner) {
        this.scanner = scanner;
    }

    public Employee readEmployee() {

        while (true) {
            try {
                System.out.println("Введите имя сотрудника:");
                String name = scanner.nextLine().trim();

                System.out.println("Введите опыт сотрудника:");
                int experienceYears = Integer.parseInt(scanner.nextLine());

                System.out.println("Введите зарплату сотрудника:");
                double salary = Double.parseDouble(scanner.nextLine());

                return Employee.builder()
                        .name(name)
                        .experienceYears(experienceYears)
                        .salary(salary)
                        .build();
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: опыт и зарплата должны быть числами. Попробуйте снова.");
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка ввода: " + e.getMessage());
                System.out.println("Попробуйте ввести данные снова.");
            }
        }
    }
}
