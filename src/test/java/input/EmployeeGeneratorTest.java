package input;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class EmployeeGeneratorTest {

    @Test
    @DisplayName("Создание заданного количества сотрудников")
    void generateEmployees_ShouldMakeCorrectNumberOfNotNullEmployees() {
        int count = 10;
        var employees = EmployeeGenerator.generateEmployees(count);
        assertEquals(count, employees.size());
        employees.forEach(Assertions::assertNotNull);
    }

    @Test
    @DisplayName("count == 0 возвращает пустой список")
    void generateEmployees_ZeroCountReturnsEmpty() {
        var employees = EmployeeGenerator.generateEmployees(0);
        assertEquals(0, employees.size());
    }

    @Test
    @DisplayName("Отрицательный count должен бросать исключение")
    void generateEmployees_NegativeCountThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> EmployeeGenerator.generateEmployees(-1));
    }

}