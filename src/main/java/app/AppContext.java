package app;

import collection.CustomList;
import model.Employee;
import sort.QuickSortStrategy;
import sort.SortContext;
import sort.SortStrategy;

import java.util.Comparator;

public class AppContext {
    public static final String DEFAULT_STRATEGY_NAME = "Quick Sort";
    public static final String DEFAULT_COMPARATOR_NAME = "General";

    private CustomList<Employee> employees = new CustomList<>();
    private Comparator<Employee> comparator = Employee.GENERAL;

    private final SortContext<Employee> sortContext = new SortContext<>();

    private String strategyName = DEFAULT_STRATEGY_NAME;
    private String comparatorName = DEFAULT_COMPARATOR_NAME;

    public AppContext() {
        setStrategy(new QuickSortStrategy<>(), DEFAULT_STRATEGY_NAME);
        setComparator(Employee.GENERAL, DEFAULT_COMPARATOR_NAME);
    }

    public CustomList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(CustomList<Employee> employees) {
        this.employees = (employees == null) ? new CustomList<>() : employees;
    }

    public Comparator<Employee> getComparator() {
        return comparator;
    }

    public String getComparatorName() {
        return comparatorName;
    }

    public void setComparator(Comparator<Employee> comparator, String name) {
        if (comparator == null) throw new IllegalArgumentException("Comparator must not be null");
        this.comparator = comparator;
        this.comparatorName = (name == null || name.isBlank()) ? "Custom" : name;
    }

    public SortContext<Employee> getSortContext() {
        return sortContext;
    }

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategy(SortStrategy<Employee> strategy, String name) {
        if (strategy == null) throw new IllegalArgumentException("Strategy must not be null");
        sortContext.setStrategy(strategy);
        this.strategyName = (name == null || name.isBlank()) ? "Custom" : name;
    }
}
