package app;

import app.commands.*;
import app.context.AppContext;
import app.options.EmployeeComparator;
import app.ui.ConsoleIO;
import app.ui.Menu;
import app.ui.MenuItem;
import app.ui.UserIO;
import app.ui.header.FillMenuHeader;
import app.ui.header.MainMenuHeader;
import app.ui.header.SortMenuHeader;
import file.EmployeeJsonReader;
import file.EmployeeJsonWriter;
import input.EmployeeManualInput;
import sort.BubbleSortStrategy;
import sort.EvenExperienceSortStrategy;
import sort.MergeSortStrategy;
import sort.QuickSortStrategy;

public class ConsoleApplication {

    public void run() {
        AppContext ctx = new AppContext();
        UserIO io = new ConsoleIO();

        EmployeeJsonReader reader = new EmployeeJsonReader();
        EmployeeJsonWriter writer = new EmployeeJsonWriter();
        EmployeeManualInput input = new EmployeeManualInput();

        Menu mainMenu = createMainMenu(ctx, io, reader, writer, input);

        mainMenu.run();
    }

    private Menu createMainMenu(AppContext ctx, UserIO io, EmployeeJsonReader reader, EmployeeJsonWriter writer, EmployeeManualInput input) {
        Menu fillMenu = createFillMenu(ctx, io, reader, input);
        fillMenu.setHeader(new FillMenuHeader(ctx));

        Menu strategyMenu = createStrategyMenu(ctx, io);
        Menu comparatorMenu = createComparatorMenu(ctx, io);

        Menu sortMenu = createSortMenu(ctx, io, strategyMenu, comparatorMenu);
        sortMenu.setHeader(new SortMenuHeader(ctx));

        Menu menu = new Menu("Main menu", io, false);
        menu.add(new MenuItem("1", "Show collection", new ShowCollectionCommand(ctx, io)));
        menu.add(new MenuItem("2", "Fill collection", new OpenSubMenuCommand(fillMenu)));
        menu.add(new MenuItem("3", "Sort collection", new OpenSubMenuCommand(sortMenu)));
        menu.add(new MenuItem("4", "Save to file", new SaveToFileCommand(ctx, io, writer)));
        menu.add(new MenuItem("5", "Count occurrences", new CountOccurrencesCommand(ctx, io, input)));
        menu.add(new MenuItem("0", "Exit", new EmptyCommand()));
        menu.setHeader(new MainMenuHeader(ctx));
        return menu;
    }

    private Menu createFillMenu(AppContext ctx, UserIO io, EmployeeJsonReader reader, EmployeeManualInput input) {
        Menu menu = new Menu("Fill collection", io, false);
        menu.add(new MenuItem("1", "Show collection", new ShowCollectionCommand(ctx, io)));
        menu.add(new MenuItem("2", "Load from JSONL file", new LoadFromJSONCommand(ctx, io, reader)));
        menu.add(new MenuItem("3", "Generate random", new GenerateRandomCommand(ctx, io)));
        menu.add(new MenuItem("4", "Manual input", new ManualInputCommand(ctx, io, input)));
        menu.add(new MenuItem("5", "Clear collection", new ClearCollectionCommand(ctx, io)));
        menu.add(new MenuItem("6", "Change replace mode", new ChangeReplaceModeCommand(ctx)));
        menu.add(new MenuItem("0", "Back", new EmptyCommand()));
        return menu;
    }

    private Menu createSortMenu(AppContext ctx, UserIO io, Menu strategy, Menu comparator) {
        Command specialMenu = new OpenSubMenuCommand(comparator, new ToggleExperienceComparatorCommand(ctx, io), ctx::isEvenExperienceMode);
        Menu menu = new Menu("Sorting options", io, false);
        menu.add(new MenuItem("1", "Show collection", new ShowCollectionCommand(ctx, io)));
        menu.add(new MenuItem("2", "Select sort strategy", new OpenSubMenuCommand(strategy)));
        menu.add(new MenuItem("3", "Select comparator", specialMenu));
        menu.add(new MenuItem("4", "Execute sort", new ExecuteSortCommand(ctx, io)));
        menu.add(new MenuItem("0", "Back", new EmptyCommand()));
        return menu;
    }

    private Menu createStrategyMenu(AppContext ctx, UserIO io) {
        Menu menu = new Menu("Sort strategies", io, true);
        Command bubbleSort = new SelectSortStrategyCommand(ctx, new BubbleSortStrategy<>());
        menu.add(new MenuItem("1", "Bubble Sort", bubbleSort));
        Command quickSort = new SelectSortStrategyCommand(ctx, new QuickSortStrategy<>());
        menu.add(new MenuItem("2", "Quick Sort", quickSort));
        Command mergeSort = new SelectSortStrategyCommand(ctx, new MergeSortStrategy<>());
        menu.add(new MenuItem("3", "Merge Sort", mergeSort));
        Command evenExperienceSort = new SelectSortStrategyCommand(ctx, new EvenExperienceSortStrategy(), true);
        menu.add(new MenuItem("4", "Even Experience Sort",evenExperienceSort));
        menu.add(new MenuItem("0", "Back", new EmptyCommand()));
        return menu;
    }

    private Menu createComparatorMenu(AppContext ctx, UserIO io) {
        Menu menu = new Menu("Comparators", io, true);
        int i = 1;
        for (EmployeeComparator comp : EmployeeComparator.values()) {
            menu.add(new MenuItem(String.valueOf(i), comp.label(),
                    new SelectComparatorCommand(ctx, comp)));
            i++;
        }
        menu.add(new MenuItem("0", "Back", new EmptyCommand()));
        return menu;
    }

}
