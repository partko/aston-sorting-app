package app.commands;

import app.AppContext;
import sort.SortStrategy;

public class SelectSortStrategyCommand implements Command {
    private final AppContext context;
    private final SortStrategy strategy;
    private final String sortStrategyName;

    public SelectSortStrategyCommand(AppContext context, SortStrategy strategy, String sortStrategyName) {
        this.context = context;
        this.strategy = strategy;
        this.sortStrategyName = sortStrategyName;
    }

    @Override
    public void execute() {
        context.setStrategy(strategy, sortStrategyName);
    }
}
