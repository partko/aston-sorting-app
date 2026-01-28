package file.parser;

import model.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тестирование парсера EmployeeJsonParser (формат JSONL)")
class EmployeeJsonParserTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "{\"name\": \"Ivan\", \"experienceYears\": 5, \"salary\": 1000.0}",
            "{\"name\": \"Ivan\", \"salary\": 1000.0, \"experienceYears\": 5}", // другой порядок полей
            "  {\"name\":\"Ivan\"  ,  \"experienceYears\":5, \"salary\":1000}  "  // лишние пробелы
    })
    @DisplayName("Десериализация: корректная обработка валидных JSON-объектов")
    void toEmployee_ShouldParseValidJson(String json) {
        Employee employee = EmployeeJsonParser.toEmployee(json);
        assertNotNull(employee);
        assertEquals("Ivan", employee.getName());
        assertEquals(5, employee.getExperienceYears());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "", "   ", "{\"wrong\": \"field\"}", "broken-json"
    })
    @DisplayName("Десериализация: возврат null при пустом или некорректном вводе")
    void toEmployee_ShouldReturnNullOnInvalidInput(String json) {
        assertNull(EmployeeJsonParser.toEmployee(json));
    }

    static Stream<Arguments> provideFormatEdgeCases() {
        return Stream.of(
                Arguments.of(Employee.of("Zero", 0, 0.0), "0", "0.00"),
                // Огромная зарплата — проверка, что не уходит в экспоненциальную нотацию (1.0E9)
                Arguments.of(Employee.of("Rich", 10, 1000000000.0), "10", "1000000000.00"),
                // Маленькая дробная часть — проверка округления/форматирования %.2f
                Arguments.of(Employee.of("Penny", 1, 0.01), "1", "0.01"),
                // Имя со всеми возможными спецсимволами JSON
                Arguments.of(Employee.of("Quotes \" \\ / \n \r \t", 5, 500.0), "5", "500.00")
        );
    }

    @ParameterizedTest
    @DisplayName("Десериализация: поддержка различных числовых форматов JSON")
    @CsvSource({
            "'{\"name\": \"N\", \"experienceYears\": 0, \"salary\": 0}', 0, 0.0",
            "'{\"name\": \"N\", \"experienceYears\": 5, \"salary\": 1234.5}', 5, 1234.5",
            "'{\"name\": \"N\", \"experienceYears\": 1, \"salary\": 100.000}', 1, 100.0" // Лишние нули в конце
    })
    void toEmployee_ShouldParseVariousNumericFormats(String json, int expectedExp, double expectedSal) {
        Employee employee = EmployeeJsonParser.toEmployee(json);
        assertNotNull(employee);
        assertEquals(expectedExp, employee.getExperienceYears());
        assertEquals(expectedSal, employee.getSalary(), 0.001);
    }

    @Test
    @DisplayName("Десериализация: поддержка кириллицы и многобайтовых Unicode-символов (эмодзи)")
    void toEmployee_ShouldHandleUnicodeAndEmoji() {
        String json = "{\"name\": \"Иван 👨‍💻\", \"experienceYears\": 1, \"salary\": 5000.0}";
        Employee emp = EmployeeJsonParser.toEmployee(json);

        assertNotNull(emp);
        assertEquals("Иван 👨‍💻", emp.getName());
    }

    @ParameterizedTest
    @DisplayName("Десериализация: обработка данных, нарушающих бизнес-логику или типы")
    @ValueSource(strings = {
            "{\"name\": \"NoExp\", \"salary\": 100}",                       // Пропущено поле
            "{\"name\": \"WrongType\", \"experienceYears\": \"five\", \"salary\": 100}" // Строка вместо числа
    })
    void toEmployee_ShouldReturnNullForInvalidData(String json) {
        assertNull(EmployeeJsonParser.toEmployee(json));
    }

    @Test
    @DisplayName("Сериализация: корректное экранирование кавычек и спецсимволов")
    void toJson_ShouldEscapeSpecialCharacters() {
        Employee employee = Employee.of("John \"Junior\" O'Neil", 1, 1000.0);
        String json = EmployeeJsonParser.toJson(employee);
        assertTrue(json.contains("\"name\": \"John \\\"Junior\\\" O'Neil\""));
    }

    @ParameterizedTest
    @DisplayName("Сериализация: соблюдение формата для краевых значений (0, большие числа, спецсимволы)")
    @MethodSource("provideFormatEdgeCases")
    void toJson_ShouldMaintainCorrectFormat(Employee emp, String expectedExp, String expectedSal) {
        String json = EmployeeJsonParser.toJson(emp);
        assertTrue(json.startsWith("{") && json.endsWith("}"));
        assertTrue(json.contains("\"experienceYears\": " + expectedExp));
        assertTrue(json.contains("\"salary\": " + expectedSal));
    }

    @Test
    @DisplayName("Сериализация: использование точки как разделителя независимо от системной локали")
    void toJson_ShouldUseDotAsDecimalSeparator() {
        Employee employee = Employee.of("Ivan", 1, 1234.56);
        String json = EmployeeJsonParser.toJson(employee);

        assertTrue(json.contains("1234.56"));
        assertFalse(json.contains("1234,56"));
    }

    @Test
    @DisplayName("Симметричность: проверка полного цикла (Object -> JSON -> Object)")
    void roundTrip_ShouldPreserveData() {
        Employee original = Employee.of("Test Employee\nNew Line", 10, 500.25);

        String json = EmployeeJsonParser.toJson(original);
        Employee parsed = EmployeeJsonParser.toEmployee(json);

        assertEquals(original, parsed);
    }

}