package app.ui;

public interface UserIO {
    void println(String text);
    String readLine();
    int readInt(String message);
    double readDouble(String message);
    String readNonBlank(String message);
}
