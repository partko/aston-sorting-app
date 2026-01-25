package sort;

import collection.CustomList;
import model.Employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EvenExperienceSortStrategy implements SortStrategy<Employee> {

    private final SortStrategy<Employee> baseSort;

    public EvenExperienceSortStrategy(SortStrategy<Employee> baseSort) {
        this.baseSort = baseSort;
    }

    @Override
    public void sort(CustomList<Employee> data, Comparator<Employee> comparator) {
        List<Integer> evenIndexes = new ArrayList<>();
        CustomList<Employee> evenEmployees = new CustomList<>();

        // Collect employees with even experience
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getExperienceYears() % 2 == 0) {
                evenIndexes.add(i);
                evenEmployees.add(data.get(i));
            }
        }

        // Sort only even experience employees
        baseSort.sort(evenEmployees, comparator);

        // Put sorted employees back to their positions
        for (int i = 0; i < evenIndexes.size(); i++) {
            data.set(evenIndexes.get(i), evenEmployees.get(i));
        }
    }
}