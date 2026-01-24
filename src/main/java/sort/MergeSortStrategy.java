package sort;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class MergeSortStrategy<T> implements SortStrategy<T> {

    @Override
    public void sort(List<T> list, Comparator<T> comparator) {
        if (list.size() < 2) return;
        mergeSort(list, 0, list.size() - 1, comparator);
    }

    private void mergeSort(List<T> list, int left, int right, Comparator<T> comp) {
        if (left >= right) return;
        int mid = (left + right) / 2;
        mergeSort(list, left, mid, comp);
        mergeSort(list, mid + 1, right, comp);
        merge(list, left, mid, right, comp);
    }

    private void merge(List<T> list, int left, int mid, int right, Comparator<T> comp) {
        List<T> temp = new ArrayList<>(list.subList(left, right + 1));

        int i = 0;
        int j = mid - left + 1;
        int k = left;

        while (i <= mid - left && j <= right - left) {
            if (comp.compare(temp.get(i), temp.get(j)) <= 0) {
                list.set(k++, temp.get(i++));
            } else {
                list.set(k++, temp.get(j++));
            }
        }

        while (i <= mid - left) {
            list.set(k++, temp.get(i++));
        }
    }
}
