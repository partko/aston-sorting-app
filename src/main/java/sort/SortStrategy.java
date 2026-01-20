package sort;

import collection.CustomList;

import java.util.ArrayList;
import java.util.Comparator;

public interface SortStrategy<T> {
    void sort(CustomList<T> data, Comparator<T> comparator);
}
