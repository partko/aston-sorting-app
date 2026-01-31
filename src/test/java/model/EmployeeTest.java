package model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    @DisplayName("Соответствие заявленному: class is immutable")
    void testImmutability() {
        var methods = Employee.class.getDeclaredMethods();
        for (var method : methods) {
            assertFalse(method.getName().startsWith("set"), "Employee class should not have setter methods.");
        }
    }

    @Test
    @DisplayName("Сравнение: equals и hashCode")
    void testEqualsAndHashCode() {
        var employee1 = Employee.builder()
                .name("John Doe")
                .experienceYears(5)
                .salary(75000)
                .build();
        var employee2 = Employee.builder()
                .name("John Doe")
                .experienceYears(5)
                .salary(75000)
                .build();
        var employee3 = Employee.builder()
                .name("John Doe")
                .experienceYears(5)
                .salary(70000)
                .build();

        assertEquals(employee1, employee2, "Employees with same attributes should be equal.");
        assertNotEquals(employee1, employee3, "Employees with different attributes should not be equal.");
        assertEquals(employee1.hashCode(), employee2.hashCode(), "Hash codes should be equal for equal employees.");
        assertNotEquals(employee1.hashCode(), employee3.hashCode(), "Hash codes should differ for different employees.");
    }

    @Test
    @DisplayName("Сравнение: equals должен возвращать false при сравнении с объектом другого типа или null")
    void equals_ShouldReturnFalseForDifferentType() {
        Employee employee = Employee.of("Иван", 5, 50000);
        String notAnEmployee = "просто строка";

        assertNotEquals(notAnEmployee, employee);
        assertNotEquals(null, employee);
    }


    @Test
    @DisplayName("Геттеры")
    void testBuilderAndGetters() {
        Employee employee = Employee.builder()
                .name("Jane Smith")
                .experienceYears(3)
                .salary(60000)
                .build();
        assertEquals("Jane Smith", employee.getName());
        assertEquals(3, employee.getExperienceYears());
        assertEquals(60000, employee.getSalary());
    }

    @Test
    @DisplayName("Создание: Статический фабричный метод of")
    void of() {
        Employee employee1 = Employee.builder()
                .name("Jane Smith")
                .experienceYears(3)
                .salary(60000)
                .build();
        Employee employee2 = Employee.of("Jane Smith", 3, 60000);
        assertEquals(employee1, employee2);
    }

    @Test
    @DisplayName("Создание: Builder при некорректных значениях выбрасывает IllegalArgumentException")
    void testBuilderWithInvalidData() {
        assertThrows(IllegalArgumentException.class, () -> {
            Employee.builder()
                    .name("")
                    .experienceYears(3)
                    .salary(60000)
                    .build();
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Employee.builder()
                    .name(null)
                    .experienceYears(3)
                    .salary(60000)
                    .build();
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Employee.builder()
                    .name("Jane Smith")
                    .experienceYears(-1)
                    .salary(60000)
                    .build();
        });
        assertThrows(IllegalArgumentException.class, () -> {
            Employee.builder()
                    .name("Jane Smith")
                    .experienceYears(3)
                    .salary(-5000)
                    .build();
        });
    }

    // <editor-fold desc="--- Comparators ---">
    @Test
    @DisplayName("Сравнение по имени: без учета регистра")
    void testByNameComparator() {
        Employee e1 = Employee.of("anna", 1, 1000);
        Employee e2 = Employee.of("Boris", 1, 1000);

        assertTrue(Employee.BY_NAME_ASC.compare(e1, e2) < 0, "anna должна быть перед Boris");
        assertTrue(Employee.BY_NAME_DESC.compare(e1, e2) > 0);
    }

    @Test
    @DisplayName("Сравнение по зарплате")
    void testBySalaryComparator() {
        Employee lowPay = Employee.of("Ivan", 1, 1000.0);
        Employee highPay = Employee.of("Ivan", 1, 5000.0);

        assertTrue(Employee.BY_SALARY_ASC.compare(lowPay, highPay) < 0);
        assertTrue(Employee.BY_SALARY_DESC.compare(lowPay, highPay) > 0);
    }

    @Test
    @DisplayName("Сравнение GENERAL: проверка приоритетов (Опыт DESC -> Зарплата DESC -> Имя ASC)")
    void testGeneralComparator() {
        Employee e1 = Employee.of("Zagir", 10, 1000.0);
        Employee e2 = Employee.of("Anna", 5, 10000.0);

        assertTrue(Employee.GENERAL.compare(e1, e2) < 0);

        Employee e3 = Employee.of("Zagir", 5, 5000.0);
        Employee e4 = Employee.of("Anna", 5, 2000.0);

        assertTrue(Employee.GENERAL.compare(e3, e4) < 0);

        Employee e5 = Employee.of("Anna", 5, 5000.0);
        Employee e6 = Employee.of("Boris", 5, 5000.0);

        assertTrue(Employee.GENERAL.compare(e5, e6) < 0);
    }

    @Test
    @DisplayName("Сравнение: Сортировка списка компаратором GENERAL")
    void testListSorting() {
        Employee middle = Employee.of("B", 5, 5000);
        Employee richest = Employee.of("A", 5, 10000);
        Employee experienced = Employee.of("C", 10, 1000);

        List<Employee> list = new ArrayList<>(List.of(middle, richest, experienced));
        list.sort(Employee.GENERAL);

        assertEquals(experienced, list.get(0));
        assertEquals(richest, list.get(1));
        assertEquals(middle, list.get(2));
    }
    // </editor-fold>
}