package sort;

import collection.CustomList;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MergeSortStrategy<T> implements SortStrategy<T> {

    @Override
    public void sort(CustomList<T> data, Comparator<T> comparator) {
        if (data.size() < 2) return;
        mergeSort(data, 0, data.size() - 1, comparator);
    }

    private void mergeSort(CustomList<T> data, int left, int right, Comparator<T> comp) {
        if (left >= right) return;
        int mid = (left + right) / 2;
        mergeSort(data, left, mid, comp);
        mergeSort(data, mid + 1, right, comp);
        merge(data, left, mid, right, comp);
    }

    private void merge(CustomList<T> data, int left, int mid, int right, Comparator<T> comp) {
        CustomList<T> temp = data.subList(left, right + 1);

        int i = 0;
        int j = mid - left + 1;
        int k = left;

        while (i <= mid - left && j <= right - left) {
            if (comp.compare(temp.get(i), temp.get(j)) <= 0) {
                data.set(k++, temp.get(i++));
            } else {
                data.set(k++, temp.get(j++));
            }
        }

        while (i <= mid - left) {
            data.set(k++, temp.get(i++));
        }
    }
}