package by.innowise.task.first;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class MyLinkedListTest {

    @Test
    void size_shouldReturnZeroWhenListIsEmpty() {
        MyList<String> list = new MyLinkedList<>();
        assertEquals(0, list.size());
    }

    @Test
    void addFirst_shouldAddElementToTheHead() {
        MyList<Integer> list = new MyLinkedList<>();
        list.addFirst(10);
        assertEquals(10, list.getFirst());
        assertEquals(1, list.size());
    }

    @Test
    void addLast_shouldAddElementToTheEnd() {
        MyList<Integer> list = new MyLinkedList<>();
        list.addLast(10);
        assertEquals(10, list.getLast());
        assertEquals(1, list.size());
    }

    @Test
    void addAtIndex_shouldInsertElementCorrectly() {
        MyList<Integer> list = new MyLinkedList<>();
        list.addLast(1);
        list.addLast(3);
        list.add(1, 2);

        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));
    }

    @Test
    void get_shouldThrowExceptionForInvalidIndex() {
        MyList<String> list = new MyLinkedList<>();
        list.addLast("A");
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(5));
    }

    @Test
    void removeFirst_shouldUpdateHead() {
        MyList<Integer> list = new MyLinkedList<>();
        list.addLast(1);
        list.addLast(2);

        list.removeFirst();
        assertEquals(1, list.size());
        assertEquals(2, list.getFirst());
    }

    @Test
    void removeLast_shouldUpdateTail() {
        MyList<Integer> list = new MyLinkedList<>();
        list.addLast(1);
        list.addLast(2);

        list.removeLast();
        assertEquals(1, list.size());
        assertEquals(1, list.getLast());
    }

    @Test
    void remove_shouldThrowExceptionForInvalidIndex() {
        MyList<Integer> list = new MyLinkedList<>();
        list.addLast(1);
        list.addLast(2);

        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(5));
    }

    @Test
    void remove_shouldThrowExceptionForInvalidList() {
        MyList<Integer> list = new MyLinkedList<>();
        assertThrows(NoSuchElementException.class, list::removeFirst);
    }

    @Test
    void getFirst_shouldThrowExceptionWhenListEmpty() {
        MyList<Integer> list = new MyLinkedList<>();
        assertThrows(NoSuchElementException.class, list::getFirst);
    }

    @Test
    void getLast_shouldThrowExceptionWhenListEmpty() {
        MyList<Integer> list = new MyLinkedList<>();
        assertThrows(NoSuchElementException.class, list::getLast);
    }

    @Test
    void removeFirst_and_removeLast_onSingleElementList() {
        MyList<Integer> list = new MyLinkedList<>();
        list.addFirst(100);
        list.removeFirst();
        assertEquals(0, list.size());

        list.addLast(200);
        list.removeLast();
        assertEquals(0, list.size());
    }

    @Test
    void addAndRemoveMultipleElementsMaintainsCorrectOrder() {
        MyList<Integer> list = new MyLinkedList<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.remove(1);
        list.add(1, 4);

        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(4, list.get(1));
        assertEquals(3, list.get(2));
    }
}