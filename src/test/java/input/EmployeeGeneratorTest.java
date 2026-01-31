package input;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

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

//    todo: выбрать вариант теста ДЛЯ КОНСТАНТ
//    @RepeatedTest(5)
//    @DisplayName("Поля в допустимых диапазонах")
//    void generateEmployees_FieldsWithinRanges() {
//        int count = 100;
//        var employees = EmployeeGenerator.generateEmployees(count);
//
//        assertEquals(count, employees.size());
//        employees.forEach(e -> {
//            assertNotNull(e.getName());
//            assertTrue(Arrays.asList(EmployeeGenerator.NAMES).contains(e.getName()));
//            assertTrue(e.getExperienceYears() >= 0);
//            assertTrue(e.getExperienceYears() < EmployeeGenerator.MAX_EXPERIENCE);
//            assertTrue(e.getSalary() >= EmployeeGenerator.MIN_SALARY);
//            assertTrue(e.getSalary() <
//                    EmployeeGenerator.MIN_SALARY
//                            + (long) EmployeeGenerator.MAX_SALARY_STEPS * EmployeeGenerator.SALARY_STEP);
//            assertEquals(0,
//                    ((long) e.getSalary() - EmployeeGenerator.MIN_SALARY) % EmployeeGenerator.SALARY_STEP);
//        });
//    }

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