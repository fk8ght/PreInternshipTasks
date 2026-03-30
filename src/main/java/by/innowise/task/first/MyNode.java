package by.innowise.task.first;

public class MyNode<T> {
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

    public void setNext(MyNode<T> next){
        this.next = next;
    }

    public void setPrev(MyNode<T> prev){
        this.prev = prev;
    }

    public void setContent(T content){
        this.content = content;
    }

    public T getContent(){
        return content;
    }

    public MyNode<T> getPrev(){
        return prev;
    }

    public MyNode<T> getNext(){
        return next;
    }
}
