package app.context;

import app.options.EmployeeComparator;
import model.Employee;

import java.util.Comparator;

public class IOContext {
    private boolean replaceMode;
    private EmployeeComparator comparator;

    public IOContext() {
        comparator = EmployeeComparator.GENERAL;
        replaceMode = true;
    }

    public boolean isReplaceMode() {
        return replaceMode;
    }

    public void changeReplaceMode() {
        this.replaceMode = !replaceMode;
    }

    public Comparator<Employee> getComparator() {
        if (comparator == null) {
            throw new IllegalStateException("Comparator is not selected");
        }
        return comparator.comparator();
    }

    public void setComparator(EmployeeComparator option) {
        if (option == null) throw new IllegalArgumentException("Comparator option must not be null");
        this.comparator = option;
    }

    public String getComparatorName() {
        return comparator.label();
    }
}
