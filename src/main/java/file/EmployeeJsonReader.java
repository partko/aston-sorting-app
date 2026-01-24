package file;

import collection.CustomList;
import file.parser.EmployeeJsonParser;
import model.Employee;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Implementation of DataReader that parses Employee objects from a JSONL file.
 */
public class EmployeeJsonReader implements DataReader<Employee> {

    @Override
    public CustomList<Employee> read(String userPath, Integer limit) {
        Path path = Path.of(userPath);
        if (!Files.exists(path)) {
            throw new IllegalArgumentException("File %s does not exist".formatted(path));
        }

        long maxElements = (limit == null || limit <= 0) ? Long.MAX_VALUE : limit;
        CustomList<Employee> employees = new CustomList<>();

        try (Stream<String> lines = Files.lines(path)) {
            lines.map(String::trim)
                    .filter(line -> !line.isEmpty())
                    .map(EmployeeJsonParser::toEmployee)
                    .filter(Objects::nonNull)
                    .limit(maxElements)
                    .forEach(employees::add);
        } catch (Exception e) {
            throw new RuntimeException("Error reading JSONL file: " + path, e);
        }

        return employees;
    }
}
