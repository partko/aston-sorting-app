package input;

import app.ui.ConsoleIO;
import model.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeManualInputTest {

    @Test
    @DisplayName("Успешное чтение сотрудника с корректными данными")
    void readEmployee_Success() {
        ConsoleIO io = mock(ConsoleIO.class);
        when (io.readNonBlank(anyString())).thenReturn("Ivan");
        when (io.readInt(anyString())).thenReturn(5);
        when (io.readDouble(anyString())).thenReturn(1500.50);

        EmployeeManualInput input = new EmployeeManualInput();
        Employee employee = input.readEmployee(io);

        assertNotNull(employee);
        assertEquals("Ivan", employee.getName());
        assertEquals(5, employee.getExperienceYears());
        assertEquals(1500.50, employee.getSalary());

        verify(io, times(1)).readNonBlank(anyString());
        verify(io, times(1)).readInt(anyString());
        verify(io, times(1)).readDouble(anyString());

        // ошибок быть не должно
        verify(io, never()).println(startsWith("Input error:"));
    }

    @Test
    @DisplayName("Обработка IllegalArgumentException при вводе некорректных данных")
    void readEmployee_HandlesIllegalArgumentException() {
        ConsoleIO io = mock(ConsoleIO.class);

        // 1-я попытка: exp = -1 (должно упасть в Employee Builder)
        // 2-я попытка: всё корректно
        when(io.readNonBlank(anyString())).thenReturn("Ivan", "Ivan");
        when(io.readInt(anyString())).thenReturn(-1, 3);
        when(io.readDouble(anyString())).thenReturn(1000.0, 1000.0);

        EmployeeManualInput input = new EmployeeManualInput();
        Employee employee = input.readEmployee(io);

        assertEquals(3, employee.getExperienceYears());

        // Ввод был запрошен 2 раза по кругу
        verify(io, times(2)).readNonBlank(anyString());
        verify(io, times(2)).readInt(anyString());
        verify(io, times(2)).readDouble(anyString());

        // Сообщение об ошибке должно быть напечатано хотя бы раз
        verify(io, atLeastOnce()).println(startsWith("Input error:"));
    }
}
