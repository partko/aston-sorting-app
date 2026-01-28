package file.parser;

import model.Employee;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeJsonParser {

    public static Employee toEmployee(String jsonLine) {
        if (jsonLine == null || jsonLine.isBlank()) return null;
        String name = extractStringField(jsonLine, "name");
        String expStr = extractNumberField(jsonLine, "experienceYears");
        String salStr = extractNumberField(jsonLine, "salary");

        if (name == null || expStr == null || salStr == null) return null;

        return Employee.of(name, Integer.parseInt(expStr), Double.parseDouble(salStr));
    }

    public static String toJson(Employee employee) {
        if (employee == null) return "null";
        return String.format(java.util.Locale.US,
                "{\"name\": \"%s\", \"experienceYears\": %d, \"salary\": %.2f}",
                escapeJson(employee.getName()),
                employee.getExperienceYears(),
                employee.getSalary());
    }

    private static String extractStringField(String json, String fieldName) {
        String pattern = "\"" + fieldName + "\":\\s*\"((?:[^\"\\\\]|\\\\.)*)\"";
        return extractField(json, pattern);
    }

    private static String extractNumberField(String json, String fieldName) {
        String pattern = "\"" + fieldName + "\":\\s*([0-9]+\\.?[0-9]*)";
        return extractField(json, pattern);
    }

    private static String extractField(String json, String regexPattern) {
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(json);

        if (matcher.find()) {
            String value = matcher.group(1);
            return unescapeJson(value).trim();
        }
        return null;
    }

    private static String unescapeJson(String escaped) {
        return escaped.replace("\\\\", "\\")
                .replace("\\\"", "\"")
                .replace("\\n", "\n")
                .replace("\\r", "\r")
                .replace("\\t", "\t");
    }

    private static String escapeJson(String input) {
        if (input == null) return "";
        return input.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}
