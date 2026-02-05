package app;

import app.commands.*;
import app.ui.ConsoleIO;
import app.ui.Menu;
import app.ui.MenuItem;
import app.ui.header.FillMenuHeader;
import app.ui.header.MainMenuHeader;
import app.ui.header.SortMenuHeader;
import file.EmployeeJsonReader;
import model.Employee;
import sort.BubbleSortStrategy;
import sort.EvenExperienceSortStrategy;
import sort.MergeSortStrategy;
import sort.QuickSortStrategy;

public class ConsoleApplication {
    private boolean isRunning = true;

    public void run() {
        AppContext context = new AppContext();
        ConsoleIO io = new ConsoleIO();
        EmployeeJsonReader reader = new EmployeeJsonReader();

        Menu fillMenu = createFillMenu(context, io, reader);
        fillMenu.setHeader(new FillMenuHeader(context));
        Menu strategyMenu = createStrategyMenu(context, io);
        Menu comparatorMenu = createComparatorMenu(context, io);
        Menu sortMenu = createSortMenu(context, io, strategyMenu, comparatorMenu);
        sortMenu.setHeader(new SortMenuHeader(context));

        Menu mainMenu = new Menu("Main menu");
        mainMenu.add(new MenuItem("1", "Show collection", new ShowCollectionCommand(context, io)));
        mainMenu.add(new MenuItem("2", "Fill collection", new OpenSubMenuCommand(fillMenu, io, false)));
        mainMenu.add(new MenuItem("3", "Sort collection", new OpenSubMenuCommand(sortMenu, io, false)));
        mainMenu.add(new MenuItem("4", "Save to file", new SaveToFileCommand(context, io)));
        mainMenu.add(new MenuItem("5", "Count occurrences", new CountOccurrencesCommand(context, io)));
        mainMenu.add(new MenuItem("0", "Exit", new ExitCommand(this)));
        mainMenu.setHeader(new MainMenuHeader(context));

        while (isRunning) {
            mainMenu.show(io);
            mainMenu.handle(io);
        }
    }

    private Menu createFillMenu(AppContext context, ConsoleIO io, EmployeeJsonReader reader) {
        Menu menu = new Menu("Fill collection");
        menu.add(new MenuItem("1", "Show collection", new ShowCollectionCommand(context, io)));
        menu.add(new MenuItem("2", "Load from JSONL file", new LoadFromJSONCommand(context, io, reader)));
        menu.add(new MenuItem("3", "Generate random", new GenerateRandomCommand(context, io)));
        menu.add(new MenuItem("4", "Manual input", new ManualInputCommand(context, io)));
        menu.add(new MenuItem("5", "Clear collection", new ClearCollectionCommand(context, io)));
        menu.add(new MenuItem("6", "Change replace mode", new ChangeReplaceModeCommand(context)));
        menu.add(new MenuItem("0", "Back", new BackCommand()));
        return menu;
    }

    private Menu createSortMenu(AppContext context, ConsoleIO io, Menu strategy, Menu comparator) {
        Menu menu = new Menu("Sorting options");
        menu.add(new MenuItem("1", "Show collection", new ShowCollectionCommand(context, io)));
        menu.add(new MenuItem("2", "Select sort strategy", new OpenSubMenuCommand(strategy, io, true)));
        menu.add(new MenuItem("3", "Select comparator", new OpenSubMenuCommand(comparator, io, true)));
        menu.add(new MenuItem("4", "Execute sort", new ExecuteSortCommand(context, io)));
        menu.add(new MenuItem("0", "Back", new BackCommand()));
        return menu;
    }

    private Menu createStrategyMenu(AppContext context, ConsoleIO io) {
        Menu menu = new Menu("Sort strategies");
        menu.add(new MenuItem("1", "Bubble Sort", new SelectSortStrategyCommand(context, new BubbleSortStrategy<>(), "Bubble Sort")));
        menu.add(new MenuItem("2", "Quick Sort", new SelectSortStrategyCommand(context, new QuickSortStrategy<>(), "Quick Sort")));
        menu.add(new MenuItem("3", "Merge Sort", new SelectSortStrategyCommand(context, new MergeSortStrategy<>(), "Merge Sort")));
        menu.add(new MenuItem("4", "Even Experience Sort", new SelectSortStrategyCommand(context, new EvenExperienceSortStrategy(), "Even Experience Sort")));
        menu.add(new MenuItem("0", "Back", new BackCommand()));
        return menu;
    }

    private Menu createComparatorMenu(AppContext context, ConsoleIO io) {
        Menu menu = new Menu("Comparators");
        menu.add(new MenuItem("1", "General", new SelectComparatorCommand(context, Employee.GENERAL, "General")));
        menu.add(new MenuItem("2", "Name ASC", new SelectComparatorCommand(context, Employee.BY_NAME_ASC, "Name ASC")));
        menu.add(new MenuItem("3", "Name DESC", new SelectComparatorCommand(context, Employee.BY_NAME_DESC, "Name DESC")));
        menu.add(new MenuItem("4", "Salary ASC", new SelectComparatorCommand(context, Employee.BY_SALARY_ASC, "Salary ASC")));
        menu.add(new MenuItem("5", "Salary DESC", new SelectComparatorCommand(context, Employee.BY_SALARY_DESC, "Salary DESC")));
        menu.add(new MenuItem("6", "Experience ASC", new SelectComparatorCommand(context, Employee.BY_EXPERIENCE_ASC, "Experience ASC")));
        menu.add(new MenuItem("7", "Experience DESC", new SelectComparatorCommand(context, Employee.BY_EXPERIENCE_DESC, "Experience DESC")));
        menu.add(new MenuItem("0", "Back", new BackCommand()));
        return menu;
    }

    public void stop() {
        isRunning = false;
    }
}
