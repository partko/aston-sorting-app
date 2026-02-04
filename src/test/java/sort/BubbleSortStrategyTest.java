package sort;

import collection.CustomList;
import model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@DisplayName("BubbleSortStrategy tests")
public class BubbleSortStrategyTest {
    private BubbleSortStrategy<Employee> strategy;
    private Comparator<Employee> salaryComparator;

    @BeforeEach
    void setUp() {
        strategy = new BubbleSortStrategy<>();
        salaryComparator = Employee.BY_SALARY_ASC;
    }

    @Test
    @DisplayName("Пустая коллекция должна корректно обрабатываться")
    void sort_EmptyList() {
        CustomList<Employee> data = new CustomList<>();
        assertDoesNotThrow(() -> strategy.sort(data, salaryComparator));
        assertEquals(0, data.size());
    }

    @Test
    @DisplayName("Коллекция с единственным элементом должна корректно обрабатываться")
    void sort_singleElement() {
        CustomList<Employee> data = new CustomList<>();
        Employee e = Employee.of("Maria", 5, 5000);
        data.add(e);
        strategy.sort(data, salaryComparator);

        assertEquals(1, data.size());
        assertEquals(e, data.get(0));
    }

    @Test
    @DisplayName("Уже отсортированная коллекция - все остаются на местах")
    void sort_alreadySorted() {
        Employee e1 = Employee.of("Maria", 2, 1000);
        Employee e2 = Employee.of("Ivan", 3, 5000);
        Employee e3 = Employee.of("Eva", 4, 12000);
        Employee e4 = Employee.of("Vladimir", 3, 20000);
        Employee e5 = Employee.of("Vika", 6, 40000);
        Employee e6 = Employee.of("Dima", 6, 45000);
        Employee e7 = Employee.of("Lena", 3, 100000);

        CustomList<Employee> data = new CustomList<>();
        data.add(e1); data.add(e2); data.add(e3); data.add(e4); data.add(e5); data.add(e6); data.add(e7);

        strategy.sort(data, salaryComparator);

        assertSame(e1, data.get(0));
        assertSame(e2, data.get(1));
        assertSame(e3, data.get(2));
        assertSame(e4, data.get(3));
        assertSame(e5, data.get(4));
        assertSame(e6, data.get(5));
        assertSame(e7, data.get(6));
    }

    @Test
    @DisplayName("Проверка сортировки")
    void sort_checkSort() {
        Employee e1 = Employee.of("Eva", 4, 12000);
        Employee e2 = Employee.of("Lena", 3, 100000);
        Employee e3 = Employee.of("Maria", 2, 1000);
        Employee e4 = Employee.of("Vika", 6, 40000);
        Employee e5 = Employee.of("Vladimir", 3, 20000);
        Employee e6 = Employee.of("Dima", 6, 45000);
        Employee e7 = Employee.of("Ivan", 3, 5000);

        CustomList<Employee> data = new CustomList<>();
        data.add(e1); data.add(e2); data.add(e3); data.add(e4); data.add(e5); data.add(e6); data.add(e7);

        strategy.sort(data, salaryComparator);

        assertSame(e3, data.get(0));
        assertSame(e7, data.get(1));
        assertSame(e1, data.get(2));
        assertSame(e5, data.get(3));
        assertSame(e4, data.get(4));
        assertSame(e6, data.get(5));
        assertSame(e2, data.get(6));
    }

    @Test
    @DisplayName("Проверка сортировки c дубликатами")
    void sort_checkSortWithDuplicates() {
        Employee e1 = Employee.of("Lena", 4, 12000);
        Employee e2 = Employee.of("Lena", 4, 12000);
        Employee e3 = Employee.of("Maria", 2, 10000);
        Employee e4 = Employee.of("Lena", 4, 12000);
        Employee e5 = Employee.of("Sveta", 3, 20000);
        Employee e6 = Employee.of("Maria", 2, 10000);
        Employee e7 = Employee.of("Maria", 2, 10000);

        CustomList<Employee> data = new CustomList<>();
        data.add(e1); data.add(e2); data.add(e3); data.add(e4); data.add(e5); data.add(e6); data.add(e7);

        strategy.sort(data, salaryComparator);

        assertEquals(10000.0, data.get(0).getSalary(), 0.0001);
        assertEquals(10000.0, data.get(1).getSalary(), 0.0001);
        assertEquals(10000.0, data.get(2).getSalary(), 0.0001);

        assertEquals(12000.0, data.get(3).getSalary(), 0.0001);
        assertEquals(12000.0, data.get(4).getSalary(), 0.0001);
        assertEquals(12000.0, data.get(5).getSalary(), 0.0001);

        assertEquals(20000.0, data.get(6).getSalary(), 0.0001);
    }
}
