package file;

import collection.CustomList;
import file.parser.EmployeeJsonParser;
import model.Employee;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * Implementation of {@link DataWriter} designed to persist {@link Employee} objects
 * using JSONL (JSON Lines) format. Each object is stored on a new line.
 */
public class EmployeeJsonWriter implements DataWriter<Employee> {
    EmployeeJsonParser parser = new EmployeeJsonParser();

    @Override
    public void write(String userPath, CustomList<Employee> data) {
        Path path = Path.of(userPath);

        try {
            Path parent = path.getParent();
            if (parent != null && !Files.exists(parent)) {
                Files.createDirectories(parent);
            }
            String stringToWrite = parseToJsonLString(data);
            Files.writeString(path, stringToWrite,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);

        } catch (Exception e) {
            throw new RuntimeException("Error writing to JSONL file: " + path, e);
        }
    }

    private String parseToJsonLString(CustomList<Employee> data) {
        if (data == null || data.size() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for (Employee employee : data) {
            if (employee != null) {
                String json = parser.toJson(employee);
                sb.append(json).append("\n");
            }
        }

        return sb.toString();
    }
}
