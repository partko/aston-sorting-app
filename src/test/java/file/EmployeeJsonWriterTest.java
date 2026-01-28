package file;

import static org.junit.jupiter.api.Assertions.*;

import collection.CustomList;
import model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@DisplayName("Тестирование записи сотрудников в формат JSONL")
class EmployeeJsonWriterTest {

    @TempDir
    Path tempDir;

    private EmployeeJsonWriter writer;
    private CustomList<Employee> employees;

    @BeforeEach
    void setUp() {
        writer = new EmployeeJsonWriter();
        employees = new CustomList<>();
        employees.add(Employee.of("Ivan", 2, 1000.0));
    }

    @Test
    @DisplayName("Проверка создания файла и записи корректных данных")
    void write_ShouldCreateFileAndWriteCorrectContent() throws IOException {
        Path filePath = tempDir.resolve("new_file.jsonl");

        writer.write(filePath.toString(), employees);

        assertTrue(Files.exists(filePath));
        List<String> lines = Files.readAllLines(filePath);
        assertEquals(1, lines.size());
        assertTrue(lines.get(0).contains("\"name\": \"Ivan\""));
    }

    @Test
    @DisplayName("Проверка добавления записи корректных данных без изменения уже существующих")
    void write_ShouldAppendDataToExistingFile() throws IOException {
        Path filePath = tempDir.resolve("append_test.jsonl");

        writer.write(filePath.toString(), employees);

        CustomList<Employee> moreEmployees = new CustomList<>();
        moreEmployees.add(Employee.of("Anna", 5, 2000.0));
        writer.write(filePath.toString(), moreEmployees);

        List<String> lines = Files.readAllLines(filePath);
        assertEquals(2, lines.size(), "Файл должен содержать 2 строки (дозапись)");
        assertTrue(lines.get(0).contains("Ivan"));
        assertTrue(lines.get(1).contains("Anna"));
    }

    @Test
    @DisplayName("Проверка создания файла и родительских директорий")
    void write_ShouldCreateParentDirectoriesAutomatically() {
        Path deepPath = tempDir.resolve("subfolder/nested/employees.jsonl");

        assertDoesNotThrow(() -> writer.write(deepPath.toString(), employees));
        assertTrue(Files.exists(deepPath), "Файл должен быть создан вместе с родительскими папками");
    }

    @Test
    @DisplayName("Проверка пропуска пустых значений при записи в файл")
    void write_ShouldSkipNullElements() throws IOException {
        Path filePath = tempDir.resolve("null_test.jsonl");
        employees.add(null); // Добавляем null в коллекцию
        employees.add(Employee.of("Petr", 1, 500.0));

        writer.write(filePath.toString(), employees);

        List<String> lines = Files.readAllLines(filePath);
        assertEquals(2, lines.size(), "Null-элементы должны игнорироваться");
        assertTrue(lines.get(0).contains("Ivan"));
        assertTrue(lines.get(1).contains("Petr"));
    }

    @Test
    @DisplayName("Проверка отсутствия записи при аргументе - пустой коллекции")
    void write_ShouldDoNothingIfListIsEmpty() throws IOException {
        Path filePath = tempDir.resolve("empty_test.jsonl");

        writer.write(filePath.toString(), new CustomList<>());

        if (Files.exists(filePath)) {
            assertEquals(0, Files.size(filePath), "Файл должен быть пустым для пустого списка");
        }
    }
}
