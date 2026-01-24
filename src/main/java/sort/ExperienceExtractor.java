package sort;


@FunctionalInterface
public interface ExperienceExtractor<T> {
    int getExperience(T object);
}
