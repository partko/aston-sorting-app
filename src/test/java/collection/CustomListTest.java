package collection;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

class CustomListTest {

    private CustomList<String> list;

    @BeforeEach
    void setUp() {
        list = new CustomList<>();
    }

    // <editor-fold desc="--- Создание ---">
    @Test
    @DisplayName("Создание и добавление: Должен корректно работать при превышении дефолтной емкости (10)")
    void add_shouldHandleMoreThanDefaultCapacity() {
        CustomList<Integer> list = new CustomList<>();

        for (int i = 0; i < 100; i++) {
            list.add(i);
        }

        assertEquals(100, list.size());
        assertEquals(0, list.get(0));
        assertEquals(99, list.get(99));
    }

    @Test
    @DisplayName("Создание и добавление: Должен работать корректно при превышении заданной начальной емкости (1)")
    void add_boundaryCapacityTest() {
        CustomList<String> smallList = new CustomList<>(1);
        smallList.add("1");
        smallList.add("Edge Case");

        assertEquals(2, smallList.size());
        assertEquals("Edge Case", smallList.get(1));
    }

    @Test
    @DisplayName("Создание: Должен кидать исключение на отрицательную емкость")
    void negativeCapacityTest() {
        assertThrows(IllegalArgumentException.class, () -> new CustomList<>(-1));
    }

    @Test
    @DisplayName("Получение: Должен выбрасывать исключение при неверном индексе")
    void get_checkIndexTest() {
        list.add("First");
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    }
// </editor-fold>

    @Test
    @DisplayName("Удаление: по индексу со сдвигом элементов")
    void removeByIndexTest() {
        list.add("A");
        list.add("B");
        list.add("C");

        String removed = list.remove(1);

        assertEquals("B", removed);
        assertEquals(2, list.size());
        assertEquals("C", list.get(1));
    }

    @Test
    @DisplayName("Удаление: по значению")
    void removeByValueTest() {
        list.add("Apple");
        list.add("Banana");

        assertTrue(list.remove("Apple"));
        assertFalse(list.remove("Orange"));
        assertEquals(1, list.size());
    }

    @Test
    @DisplayName("Установка и смена значения: set и swap")
    void setAndSwapTest() {
        list.add("A");
        list.add("B");

        list.set(0, "Z");
        assertEquals("Z", list.get(0));

        list.swap(0, 1);
        assertEquals("B", list.get(0));
        assertEquals("Z", list.get(1));
    }

    @Test
    @DisplayName("Добавление: все элементы из другого CustomList")
    void addAllCustomListTest() {
        CustomList<String> other = new CustomList<>();
        other.add("X");
        other.add("Y");

        list.add("A");
        list.addAll(other);

        assertEquals(3, list.size());
        assertEquals("X", list.get(1));
    }

    @Test
    @DisplayName("Итератор: проверка возврата элементов и исключений")
    void iteratorTest() {
        list.add("1");
        list.add("2");

        var iterator = list.iterator();

        assertTrue(iterator.hasNext());
        assertEquals("1", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("2", iterator.next());
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    @DisplayName("Очищение: clear должен обнулять список")
    void clearTest() {
        list.add("Data");
        list.clear();
        assertEquals(0, list.size());
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }

    @Test
    @DisplayName("Содержание: contains должен корректно определять наличие элемента")
    void containsTest() {
        list.add("Hello");
        list.add("Java");
        assertTrue(list.contains("Hello"));
        assertFalse(list.contains("World"));
    }
}
