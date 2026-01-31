package file.parser;

public interface DataParser <T>{
    T fromJson(String jsonLine);
    String toJson (T object);
}
