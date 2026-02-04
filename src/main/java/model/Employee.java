package model;

import java.util.Comparator;
import java.util.Objects;

/**
 * Immutable Employee object.
 */
public final class Employee {
    private final String name;
    private final int experienceYears;
    private final double salary;

    private Employee(Builder builder) {
        this.name = builder.name;
        this.experienceYears = builder.experienceYears;
        this.salary = builder.salary;
    }

    @Override
    public String toString() {
        return String.format("%-12s | %4d | %12.2f", name, experienceYears, salary);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee e)) return false;
        return experienceYears == e.experienceYears
                && Double.compare(e.salary, salary) == 0
                && Objects.equals(name, e.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, experienceYears, salary);
    }

    // <editor-fold desc="--- Comparators ---">
    public static final Comparator<Employee> BY_NAME_ASC = Comparator.comparing(Employee::getName, String.CASE_INSENSITIVE_ORDER);
    public static final Comparator<Employee> BY_NAME_DESC = BY_NAME_ASC.reversed();
    public static final Comparator<Employee> BY_SALARY_ASC = Comparator.comparingDouble(Employee::getSalary);
    public static final Comparator<Employee> BY_SALARY_DESC = BY_SALARY_ASC.reversed();
    public static final Comparator<Employee> BY_EXPERIENCE_ASC = Comparator.comparingInt(Employee::getExperienceYears);
    public static final Comparator<Employee> BY_EXPERIENCE_DESC = BY_EXPERIENCE_ASC.reversed();
    public static final Comparator<Employee> GENERAL = BY_EXPERIENCE_DESC.thenComparing(BY_SALARY_DESC).thenComparing(BY_NAME_ASC);
    // </editor-fold>

    // <editor-fold desc="--- Getters ---">
    public String getName() {return name;}
    public int getExperienceYears() {return experienceYears;}
    public double getSalary() {return salary;}
    // </editor-fold>

    // <editor-fold desc="--- Builder ---">
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String name;
        private int experienceYears;
        private double salary;

        private Builder() {}

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder experienceYears(int years) {
            this.experienceYears = years;
            return this;
        }

        public Builder salary(double salary) {
            this.salary = salary;
            return this;
        }

        public Employee build() {
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("Employee-name must be provided and non-blank");
            }
            if (experienceYears < 0) {
                throw new IllegalArgumentException("Employee-experience years cannot be negative");
            }
            if (salary < 0) {
                throw new IllegalArgumentException("Employee-salary cannot be negative");
            }
            return new Employee(this);}
    }

    /**
     * Simplified static-factory builder.
     * @param name              the name of the Employee
     * @param experienceYears   the experience value of the Employee
     * @param salary            the salary value of the Employee
     * @throws IllegalArgumentException if provided with invalid data
     */
    public static Employee of(String name, int experienceYears, double salary) {
        return Employee.builder()
                .name(name)
                .experienceYears(experienceYears)
                .salary(salary)
                .build();
    }
    // </editor-fold>
}
