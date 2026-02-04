package sort;

import collection.CustomList;
import java.util.Comparator;

public class BubbleSortStrategy<T> implements SortStrategy<T> {

    @Override
    public void sort(CustomList<T> data, Comparator<T> comparator) {
        int n = data.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(data.get(j), data.get(j + 1)) > 0) {
                    swap(data, j, j + 1);
                }
            }
        }
    }

    private void swap(CustomList<T> data, int i, int j) {
        T tmp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, tmp);
    }
}