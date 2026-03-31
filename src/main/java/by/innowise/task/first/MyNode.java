package by.innowise.task.first;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
class MyNode<T> {
    private T content;
    private MyNode<T> next;
    private MyNode<T> prev;

    MyNode(T content){
        this.content = content;
        this.next = null;
        this.prev = null;
    }

    MyNode(T content, MyNode<T> next, MyNode<T> prev){
        this.content = content;
        this.next = next;
        this.prev = prev;
    }
}
