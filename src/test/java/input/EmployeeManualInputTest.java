package input;

import model.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeManualInputTest {

    @Test
    @DisplayName("Успешное чтение сотрудника с корректными данными")
    void readEmployee_Success() {
        Scanner scanner = mock(Scanner.class);
        when(scanner.nextLine())
                .thenReturn("Ivan")
                .thenReturn("5")
                .thenReturn("1500.50");

        EmployeeManualInput input = new EmployeeManualInput(scanner);
        Employee employee = input.readEmployee();

        assertNotNull(employee);
        assertEquals("Ivan", employee.getName());
        assertEquals(5, employee.getExperienceYears());
        assertEquals(1500.50, employee.getSalary());
    }

    @Test
    @DisplayName("Запуск повторного чтения с начала при вводе некорректного значения")
    void readEmployee_RetriesOnInvalidNumber() {
        Scanner scanner = mock(Scanner.class);
        when(scanner.nextLine())
                .thenReturn("Ivan")
                .thenReturn("not_a_number")
                .thenReturn("Ivan")
                .thenReturn("5")
                .thenReturn("wrong_salary")
                .thenReturn("Ivan")
                .thenReturn("5")
                .thenReturn("2000.0");

        EmployeeManualInput input = new EmployeeManualInput(scanner);
        Employee employee = input.readEmployee();

        assertEquals(2000.0, employee.getSalary());
        verify(scanner, atLeast(6)).nextLine();
    }

    @Test
    @DisplayName("Обработка IllegalArgumentException при вводе некорректных данных")
    void readEmployee_HandlesIllegalArgumentException() {
        Scanner scanner = mock(Scanner.class);
        when(scanner.nextLine())
                .thenReturn("Ivan")
                .thenReturn("-1")
                .thenReturn("1000")
                .thenReturn("Ivan")
                .thenReturn("3")
                .thenReturn("1000");

        EmployeeManualInput input = new EmployeeManualInput(scanner);
        Employee employee = input.readEmployee();

        assertEquals(3, employee.getExperienceYears());
    }
}
