package by.innowise.task.first;

public interface MyList<T> {
    int size();
    void addFirst(T element);
    void addLast(T element);
    void add(int index, T element);
    T getFirst();
    T getLast();
    T get(int index);
    void removeFirst();
    void removeLast();
    void remove(int index);
}