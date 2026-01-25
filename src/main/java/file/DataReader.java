package file;

import collection.CustomList;

public interface DataReader<T> {
    CustomList<T> read(String path, Integer limit);
}
