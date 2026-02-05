package app.context;

import model.Employee;
import sort.QuickSortStrategy;

public class AppContext {
    private final CollectionContext<Employee> collectionCtx = new CollectionContext<>();
    private final SortContext<Employee> sortingCtx = new SortContext<>();
    private final IOContext ioContext = new IOContext();

    private boolean evenExperienceMode = false;

    public CollectionContext<Employee> collection() {
        return collectionCtx;
    }

    public SortContext<Employee> sorting() {
        return sortingCtx;
    }

    public IOContext io() {
        return ioContext;
    }

    public AppContext() {
        sortingCtx.setStrategy(new QuickSortStrategy<>());
    }

    public boolean isEvenExperienceMode() {
        return evenExperienceMode;
    }

    public void setEvenExperienceMode(boolean evenExperienceMode) {
        this.evenExperienceMode = evenExperienceMode;
    }
}
