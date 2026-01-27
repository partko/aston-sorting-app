package sort;

import collection.CustomList;
import model.Employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EvenExperienceSortStrategy implements SortStrategy<Employee> {

    @Override
    public void sort(CustomList<Employee> data, Comparator<Employee> comparator) {
        if (data == null || data.size() < 2) return;

        List<Integer> evenIndexes = new ArrayList<>();
        List<Employee> evenEmployees = new ArrayList<>();

        // собираем сотрудников с чётным стажем
        for (int i = 0; i < data.size(); i++) {
            Employee e = data.get(i);
            if (e.getExperienceYears() % 2 == 0) {
                evenIndexes.add(i);
                evenEmployees.add(e);
            }
        }

        if (evenEmployees.size() < 2) return;

        // сортируем только чётных
        quickSort(evenEmployees, 0, evenEmployees.size() - 1, comparator);

        // возвращаем их обратно на исходные позиции
        for (int i = 0; i < evenIndexes.size(); i++) {
            data.set(evenIndexes.get(i), evenEmployees.get(i));
        }
    }

    private void quickSort(
            List<Employee> list,
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
            List<Employee> list,
            int low,
            int high,
            Comparator<Employee> comparator
    ) {
        Employee pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (comparator.compare(list.get(j), pivot) <= 0) {
                i++;
                swap(list, i, j);
            }
        }

        swap(list, i + 1, high);
        return i + 1;
    }

    private void swap(List<Employee> list, int i, int j) {
        Employee temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}
