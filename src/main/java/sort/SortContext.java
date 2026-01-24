package sort;

import java.util.List;
import java.util.Comparator;

public class SortContext<T> {

    private SortStrategy<T> strategy;

    public void setStrategy(SortStrategy<T> strategy) {
        this.strategy = strategy;
    }

    public void execute(List<T> list, Comparator<T> comparator) {
        if (strategy == null) {
            throw new IllegalStateException("SortStrategy is not set");
        }
        strategy.sort(list, comparator);
    }
}
