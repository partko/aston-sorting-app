package file;

import collection.CustomList;

public interface DataWriter<T> {
    void write(String path, CustomList<T> data);
}