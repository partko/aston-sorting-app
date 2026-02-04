package collection;


import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class CustomList<T> implements Iterable<T> {

    private Object[] data;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public CustomList() {
        this.data = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public CustomList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Initial capacity cannot be negative: " + initialCapacity);
        }
        this.data = new Object[initialCapacity];
        this.size = 0;
    }

    public void add(T element) {
        if (size == data.length) {
            ensureCapacity(size + 1);
        }
        data[size] = element;
        size++;
    }

    public void addAll(CustomList<? extends T> otherList) {
        if(otherList == null) {
            throw new IllegalArgumentException("Other list must not be null");
        }

        int otherSize = otherList.size();
        if(otherSize == 0) {
            return;
        }

        ensureCapacity(size + otherSize);
        for (int i = 0; i < otherSize; i++) {
            data[size + i] = otherList.get(i);
        }
        size = size + otherSize;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) data[index];
    }

    @SuppressWarnings("unchecked")
    public T set(int index, T element) {
        checkIndex(index);
        T oldValue = (T) data[index];
        data[index] = element;
        return oldValue;
    }

    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    public T remove(int index) {
        checkIndex(index);
        T removedElement = (T) data[index];
        int numToMove = size - index - 1;
        if (numToMove > 0) {
            System.arraycopy(data, index + 1, data, index, numToMove);
        }
        data[size - 1] = null;
        size--;
        return removedElement;
    }

    public boolean remove(T element) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(data[i], element)) {
                 remove(i);
                 return true;
            }
        }
        return  false;
    }

    public boolean contains(T element) {
        for (int i = 0; i < size ; i++) {
            if (Objects.equals(data[i], element)) {
                return true;
            }
        }
        return false;
    }

    private void ensureCapacity(int minCapacity) {
        if (data.length < minCapacity) {
            int newCapacity = Math.max(minCapacity, data.length * 2);
            data = Arrays.copyOf(data, newCapacity);
        }
    }

    public void swap(int i, int j) {
        checkIndex(i);
        checkIndex(j);
        Object temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public void clear() {
        for(int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    public CustomList<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException(
                    "fromIndex=" + fromIndex + ", toIndex=" + toIndex + ", size=" + size
            );
        }

        CustomList<T> result = new CustomList<>(toIndex - fromIndex);

        for (int i = fromIndex; i < toIndex; i++) {
            result.add(get(i));
        }

        return result;
    }

    public Iterator<T> iterator() {

        return new Iterator<T>() {

            private int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @SuppressWarnings("unchecked")
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Iterator has no more elements");
                }
                return (T) data[cursor++];
            }
        };
    }
}
