package file;

import collection.CustomList;
import model.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тестирование чтения сотрудников из формата JSONL")
class EmployeeJsonReaderTest {
    @TempDir
    Path tempDir; // JUnit создаст временную папку

    private final EmployeeJsonReader reader = new EmployeeJsonReader();

    @DisplayName("Проверка создания коллекции сотрудников из корректных данных")
    @Test
    void read_ShouldReturnCorrectListOfEmployees() throws IOException {

        Path file = tempDir.resolve("employees.jsonl");
        String content = """
                {"name": "Ivan", "experienceYears": 2, "salary": 1000}
                {"name": "Anna", "experienceYears": 5, "salary": 2000}
                """;
        Files.writeString(file, content);

        CustomList<Employee> result = reader.read(file.toString(), null);

        assertEquals(2, result.size());
        assertEquals("Ivan", result.get(0).getName());
        assertEquals("Anna", result.get(1).getName());
    }

    @ParameterizedTest
    @DisplayName("Проверка создания коллекции с учётом лимита")
    @ValueSource(ints = {1, 2})
    void read_ShouldRespectLimit(int limit) throws IOException {
        Path file = tempDir.resolve("limit_test.jsonl");
        String content = """
                {"name": "E1", "experienceYears": 1, "salary": 100}
                {"name": "E2", "experienceYears": 1, "salary": 100}
                {"name": "E3", "experienceYears": 1, "salary": 100}
                """;
        Files.writeString(file, content);

        CustomList<Employee> result = reader.read(file.toString(), limit);

        assertEquals(limit, result.size());
    }

    @DisplayName("Проверка создания коллекции сотрудников из данных с пустыми и поврежденными строками")
    @Test
    void read_ShouldSkipInvalidLinesAndEmptyLines() throws IOException {
        Path file = tempDir.resolve("dirty_data.jsonl");
        String content = """
                {"name": "Valid", "experienceYears": 1, "salary": 100}
                
                {broken json line}
                {"name": "Also Valid", "experienceYears": 2, "salary": 200}
                """;
        Files.writeString(file, content);

        CustomList<Employee> result = reader.read(file.toString(), null);

        assertEquals(2, result.size());
        assertEquals("Valid", result.get(0).getName());
        assertEquals("Also Valid", result.get(1).getName());
    }

    @DisplayName("Проверка выброса исключения при несуществующем пути файла")
    @Test
    void read_ShouldThrowExceptionIfFileDoesNotExist() {
        String fakePath = tempDir.resolve("non_existent.jsonl").toString();

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> reader.read(fakePath, null));

        assertTrue(ex.getMessage().contains("does not exist"));
    }

    @DisplayName("Проверка создания пустой коллекции сотрудников, если файл пуст")
    @Test
    void read_ShouldHandleEmptyFile() throws IOException {
        Path file = tempDir.resolve("empty.jsonl");
        Files.createFile(file);

        CustomList<Employee> result = reader.read(file.toString(), null);

        assertNotNull(result);
        assertEquals(0, result.size());
    }

}