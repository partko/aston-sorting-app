package sort;

import collection.CustomList;
import model.Employee;

import java.util.Comparator;

public class EvenExperienceSortStrategy implements SortStrategy<Employee> {

    @Override
    public void sort(CustomList<Employee> data, Comparator<Employee> comparator) {
        if (data == null || data.size() < 2) {
            return;
        }

        CustomList<Integer> evenIndexes = new CustomList<>();
        CustomList<Employee> evenEmployees = new CustomList<>();

        // собираем сотрудников с чётным стажем
        for (int i = 0; i < data.size(); i++) {
            Employee employee = data.get(i);
            if (employee.getExperienceYears() % 2 == 0) {
                evenIndexes.add(i);
                evenEmployees.add(employee);
            }
        }

        if (evenEmployees.size() < 2) {
            return;
        }

        // сортировка чётных сотрудников
        quickSort(evenEmployees, 0, evenEmployees.size() - 1, comparator);

        // возвращаем отсортированных
        for (int i = 0; i < evenIndexes.size(); i++) {
            data.set(evenIndexes.get(i), evenEmployees.get(i));
        }
    }

    private void quickSort(
            CustomList<Employee> list,
            int low,
            int high,
            Comparator<Employee> comparator
    ) {
        if (low < high) {
            int pivotIndex = partition(list, low, high, comparator);
            quickSort(list, low, pivotIndex - 1, comparator);
            quickSort(list, pivotIndex + 1, high, comparator);
        }
    }

    private int partition(
            CustomList<Employee> list,
            int low,
            int high,
            Comparator<Employee> comparator
    ) {
        Employee pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (comparator.compare(list.get(j), pivot) <= 0) {
                i++;
                list.swap(i, j);
            }
        }

        list.swap(i + 1, high);
        return i + 1;
    }
}
