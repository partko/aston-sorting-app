package sort;

import static org.junit.jupiter.api.Assertions.*;

import collection.CustomList;
import model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Comparator;

class EvenExperienceSortStrategyTest {

    private EvenExperienceSortStrategy strategy;
    private Comparator<Employee> salaryComparator;

    @BeforeEach
    void setUp() {
        strategy = new EvenExperienceSortStrategy();
        salaryComparator = Employee.BY_SALARY_ASC;
    }

    @Test
    @DisplayName("Должен сортировать только сотрудников с чётным стажем, оставляя нечётных на месте")
    void sort_OnlyEvenExperienceShouldBeSorted() {
        Employee e1 = Employee.of("Rich Even", 2, 100000);
        Employee e2 = Employee.of("Rich Odd Stay", 3, 50000);
        Employee e3 = Employee.of("Poor Even", 4, 10000);
        Employee e4 = Employee.of("Rich Odd Stay 2", 3, 20000);
        Employee e5 = Employee.of("Mid Even", 6, 40000);
        Employee e6 = Employee.of("Mid Even 2", 6, 40000);
        Employee e7 = Employee.of("Poor Odd Stay 2", 3, 10000);

        CustomList<Employee> data = new CustomList<>();
        data.add(e1); data.add(e2); data.add(e3); data.add(e4); data.add(e5); data.add(e6); data.add(e7);

        strategy.sort(data, salaryComparator);

        assertSame(e3, data.get(0)); // Самая низкая з/п среди чётных
        assertSame(e2, data.get(1)); // Нечётный - на месте
        // Для e5 и e6 проверяем только зарплату, т.к. порядок может меняться
        assertEquals(40000, data.get(2).getSalary());
        assertSame(e4, data.get(3)); // Нечётный - на месте
        assertEquals(40000, data.get(4).getSalary());
        assertSame(e1, data.get(5)); // Самая высокая з/п среди чётных
        assertSame(e7, data.get(6)); // Нечётный - на месте

    }

    @Test
    @DisplayName("Не должен менять список, если чётных сотрудников меньше 2")
    void sort_ShouldDoNothingIfLessThanTwoEven() {
        Employee e1 = Employee.of("Even", 2, 100000);
        Employee e2 = Employee.of("Odd", 3, 10000);

        CustomList<Employee> data = new CustomList<>();
        data.add(e1); data.add(e2);

        strategy.sort(data, salaryComparator);

        assertEquals(e1, data.get(0));
        assertEquals(e2, data.get(1));
    }

    @Test
    @DisplayName("Должен корректно обрабатывать пустой список")
    void sort_EmptyList() {
        CustomList<Employee> data = new CustomList<>();
        assertDoesNotThrow(() -> strategy.sort(data, salaryComparator));
        assertEquals(0, data.size());
    }

    @Test
    @DisplayName("Должен корректно сортировать, если все сотрудники имеют чётный стаж")
    void sort_AllEven() {
        Employee e1 = Employee.of("Rich", 2, 90);
        Employee e2 = Employee.of("Poor", 2, 10);
        Employee e3 = Employee.of("Middle", 2, 10);

        CustomList<Employee> data = new CustomList<>();
        data.add(e1); data.add(e2); data.add(e3);

        strategy.sort(data, salaryComparator);

        assertEquals(10, data.get(0).getSalary());
        assertEquals(90, data.get(2).getSalary());
    }
}
