package input;

import collection.CustomList;
import model.Employee;
import java.util.Random;
import java.util.stream.IntStream;

public class EmployeeGenerator {

    private static final int MAX_EXPERIENCE  = 21;
    private static final int MIN_SALARY = 30000;
    private static final int SALARY_STEP = 5000;
    private static final int MAX_SALARY_STEPS = 25;
    private static final String [] NAMES = {
            "Иван", "Анна", "Петр", "Мария", "Сергей", "Ольга", "Алексей", "Елена", "Дмитрий", "Наталья", "Андрей",
            "Екатерина", "Михаил", "Татьяна", "Николай", "Юлия", "Владимир", "Анастасия", "Александр", "Ирина"
    };

    private final static Random RANDOM = new Random();

    public static CustomList<Employee> generateEmployees(int count) {
        CustomList<Employee> employees = new CustomList<>(count);

        IntStream.range(0, count)
                .mapToObj(i -> Employee.builder()
                        .name(NAMES[RANDOM.nextInt(NAMES.length)])
                        .experienceYears(RANDOM.nextInt(MAX_EXPERIENCE))
                        .salary(MIN_SALARY + RANDOM.nextInt(MAX_SALARY_STEPS) * SALARY_STEP)
                        .build()
                )
                .forEach(employees::add);

        return employees;
    }
}