package sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;


public class EvenExperienceSortStrategy<T> implements SortStrategy<T> {

    private final ExperienceExtractor<T> extractor;
    private final SortStrategy<T> baseSort;

    public EvenExperienceSortStrategy(ExperienceExtractor<T> extractor,
                                      SortStrategy<T> baseSort) {
        this.extractor = Objects.requireNonNull(extractor);
        this.baseSort = Objects.requireNonNull(baseSort);
    }

    @Override
    public void sort(List<T> list, Comparator<T> comparator) {

        List<Integer> evenIndexes = new ArrayList<>();
        List<T> evenElements = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (extractor.getExperience(list.get(i)) % 2 == 0) {
                evenIndexes.add(i);
                evenElements.add(list.get(i));
            }
        }

        baseSort.sort(evenElements, comparator);

        for (int i = 0; i < evenIndexes.size(); i++) {
            list.set(evenIndexes.get(i), evenElements.get(i));
        }
    }
}
