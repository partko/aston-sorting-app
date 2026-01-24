package input;

import collection.CustomList;
import model.Employee;
import java.util.Random;
import java.util.stream.IntStream;

public class EmployeeGenerator {

    private static final String [] NAMES = {
            "Иван", "Анна", "Петр", "Мария", "Сергей", "Ольга", "Алексей", "Елена", "Дмитрий", "Наталья", "Андрей",
            "Екатерина", "Михаил", "Татьяна", "Николай", "Юлия", "Владимир", "Анастасия", "Александр", "Ирина"
    };

    private final static Random RANDOM = new Random();

    public static CustomList<Employee> generateEmployees(int count) {
        CustomList<Employee> employees = new CustomList<>(count);

        IntStream.range(0, count)
                .mapToObj(i -> Employee.of(
                        (NAMES[RANDOM.nextInt(NAMES.length)]),
                        RANDOM.nextInt(21),
                        (30000 + RANDOM.nextInt(25) * 5000))
                )
                .forEach(employees::add);

        return employees;
    }
}
