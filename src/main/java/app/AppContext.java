package app;

import collection.CustomList;
import model.Employee;
import sort.QuickSortStrategy;
import sort.SortContext;

import java.util.Comparator;

public class AppContext {
    private CustomList<Employee> employees = new CustomList<>();
    private Comparator<Employee> comparator = Employee.GENERAL;
    private final SortContext<Employee> sortContext = new SortContext<>();
    private String strategyName;
    private String comparatorName;

    public AppContext() {
        sortContext.setStrategy(new QuickSortStrategy<>());
        strategyName = "Quick Sort";
        comparatorName = "General";
    }

    public CustomList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(CustomList<Employee> employees) {
        this.employees = employees;
    }

    public Comparator<Employee> getComparator() {
        return comparator;
    }

    public void setComparator(Comparator<Employee> comparator, String comparatorName) {
        this.comparator = comparator;
        this.comparatorName = comparatorName;
    }

    public SortContext<Employee> sortContext() {
        return sortContext;
    }

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public String getComparatorName() {
        return comparatorName;
    }
}
