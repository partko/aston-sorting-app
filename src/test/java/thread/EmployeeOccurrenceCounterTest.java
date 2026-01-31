package thread;

import collection.CustomList;
import model.Employee;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeOccurrenceCounterTest {
    static Employee targetEmployee;

    @BeforeAll
    static void setupAll() {
        targetEmployee = Employee.of("John", 4, 30_000);
    }

    @Test
    @DisplayName("Валидация аргументов метода countOccurrences")
    void testCountOccurrences_InvalidArguments() {
        assertThrows(IllegalArgumentException.class, () -> {
            EmployeeOccurrenceCounter.countOccurrences(null, targetEmployee, 2);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            EmployeeOccurrenceCounter.countOccurrences(new CustomList<>(), null, 2);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            EmployeeOccurrenceCounter.countOccurrences(new CustomList<>(), targetEmployee, 0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            EmployeeOccurrenceCounter.countOccurrences(new CustomList<>(), targetEmployee, -1);
        });
    }

    @Test
    @DisplayName("Подсчет вхождений сотрудника в пустом списке")
    void testCountOccurrences_EmptyList() {
        CustomList<Employee> employees = new CustomList<>();
        int count = EmployeeOccurrenceCounter.countOccurrences(employees, targetEmployee, 2);
        assertEquals(0, count);
    }

    @Test
    @DisplayName("Один поток: проверка корректности счета")
    void testCountOccurrences_SingleThread() {
        CustomList<Employee> employees = new CustomList<>();
        employees.add(targetEmployee);
        employees.add(Employee.of("Jane", 5, 35_000));
        employees.add(Employee.of("John", 4, 30_000)); // совпадает с targetEmployee
        employees.add(Employee.of("Doe", 3, 25_000));

        int count = EmployeeOccurrenceCounter.countOccurrences(employees, targetEmployee, 1);
        assertEquals(2, count);
    }

    @Test
    @DisplayName("Несколько потоков: проверка корректности счета")
    void countOccurrences_MultipleThreads() {
        Employee other = Employee.of("Anna", 3, 40000);

        CustomList<Employee> list = new CustomList<>();
        list.add(targetEmployee);
        list.add(other);
        list.add(targetEmployee);
        list.add(targetEmployee);
        list.add(other);

        int count = EmployeeOccurrenceCounter.countOccurrences(list, targetEmployee, 3);

        assertEquals(3, count);
    }

    @Test
    @DisplayName("Больше потоков, чем элементов: проверка корректности счета")
    void testCountOccurrences_MoreThreadsThanElements() {
        CustomList<Employee> employees = new CustomList<>();
        employees.add(targetEmployee);
        employees.add(Employee.of("Jane", 5, 35_000));
        employees.add(Employee.of("John", 4, 30_000)); // совпадает c targetEmployee

        int count = EmployeeOccurrenceCounter.countOccurrences(employees, targetEmployee, 10);
        assertEquals(2, count);
    }


}
