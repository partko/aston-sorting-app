package app.context;

import collection.CustomList;
import sort.SortStrategy;

import java.util.Comparator;

public class SortContext<T> {

    private SortStrategy<T> strategy;

    public void setStrategy(SortStrategy<T> strategy) {
        this.strategy = strategy;
    }

    public SortStrategy<T> getStrategy() {
        return strategy;
    }

    public void execute(CustomList<T> data, Comparator<T> comparator) {
        if (strategy == null) {
            throw new IllegalStateException("SortStrategy is not set");
        }
        strategy.sort(data, comparator);
    }
}