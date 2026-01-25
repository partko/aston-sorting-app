package sort;

import collection.CustomList;
import java.util.Comparator;

public class QuickSortStrategy<T> implements SortStrategy<T> {

    @Override
    public void sort(CustomList<T> data, Comparator<T> comparator) {
        quickSort(data, 0, data.size() - 1, comparator);
    }

    private void quickSort(CustomList<T> data, int low, int high, Comparator<T> comp) {
        if (low < high) {
            int pi = partition(data, low, high, comp);
            quickSort(data, low, pi - 1, comp);
            quickSort(data, pi + 1, high, comp);
        }
    }

    private int partition(CustomList<T> data, int low, int high, Comparator<T> comp) {
        T pivot = data.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (comp.compare(data.get(j), pivot) <= 0) {
                i++;
                swap(data, i, j);
            }
        }
        swap(data, i + 1, high);
        return i + 1;
    }

    private void swap(CustomList<T> data, int i, int j) {
        T tmp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, tmp);
    }
}