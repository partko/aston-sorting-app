package app.options;

import model.Employee;

import java.util.Comparator;

public enum EmployeeComparator {
    GENERAL("General", Employee.GENERAL),
    BY_NAME_ASC("Name ASC", Employee.BY_NAME_ASC),
    BY_NAME_DESC("Name DESC", Employee.BY_NAME_DESC),
    BY_SALARY_ASC("Salary ASC", Employee.BY_SALARY_ASC),
    BY_SALARY_DESC("Salary DESC", Employee.BY_SALARY_DESC),
    BY_EXPERIENCE_ASC("Experience ASC", Employee.BY_EXPERIENCE_ASC),
    BY_EXPERIENCE_DESC("Experience DESC", Employee.BY_EXPERIENCE_DESC);

    private final String label;
    private final Comparator<Employee> comparator;

    EmployeeComparator(String label, Comparator<Employee> comparator) {
        this.label = label;
        this.comparator = comparator;
    }

    public String label() {
        return label;
    }

    public Comparator<Employee> comparator() {
        return comparator;
    }
}