package app.context;

import collection.CustomList;

public class CollectionContext<T> {
    private CustomList<T> currentList = new CustomList<>();

    public CustomList<T> get() {
        return currentList;
    }

    public void clear() {
        currentList = new CustomList<>();
    }

    public void addAll(CustomList<T> newList) {
        if (newList == null || newList.size() == 0) return;
        currentList.addAll(newList);
    }

    public boolean isReadyToSort() {
        return currentList != null && currentList.size() > 1;
    }
}
