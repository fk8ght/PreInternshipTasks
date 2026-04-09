package by.innowise.task.first;

import java.util.NoSuchElementException;

class MyLinkedList<T> implements MyList<T> {
    private MyNode<T> head;
    private MyNode<T> tail;
    private int size;

    MyLinkedList(){
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void addFirst(T element){
        MyNode<T> newNode = new MyNode<>(element, null, null);
        if(size == 0){
            head = newNode;
            tail = newNode;
        } else {
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
        }
        size++;
    }

    @Override
    public void addLast(T element){
        MyNode<T> newNode = new MyNode<>(element, null, null);
        if(size == 0){
            head = newNode;
            tail = newNode;
        } else {
            newNode.setPrev(tail);
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    @Override
    public void add(int index, T element){
        if(index > size || index < 0){
            throw new IndexOutOfBoundsException();
        }

        if(index == 0){
            addFirst(element);
            return;
        } else if(index == size){
            addLast(element);
            return;
        }

        MyNode<T> nodeToFind = findNode(index);

        MyNode<T> newNode = new MyNode<>(element);
        MyNode<T> prevNode = nodeToFind.getPrev();
        newNode.setPrev(prevNode);
        newNode.setNext(nodeToFind);

        prevNode.setNext(newNode);
        nodeToFind.setPrev(newNode);
        size++;
    }

    @Override
    public T getFirst(){
        if(size == 0){
            throw new NoSuchElementException();
        }
        return head.getContent();
    }

    @Override
    public T getLast(){
        if(size == 0){
            throw new NoSuchElementException();
        }
        return tail.getContent();
    }

    @Override
    public T get(int index){
        if(index >= size || index < 0){
            throw new IndexOutOfBoundsException();
        }

        if(index == 0){
            return getFirst();
        } else if(index == size - 1){
            return getLast();
        }

        return findNode(index).getContent();
    }

    @Override
    public void removeFirst(){
        if(size == 0){
            throw new NoSuchElementException();
        } else if(size == 1){
            clearHeadAndTail();
            size--;
        } else {
            head = head.getNext();
            size--;
        }
    }

    @Override
    public void removeLast(){
        if(size == 0){
            throw new NoSuchElementException();
        } else if(size == 1){
            clearHeadAndTail();
            size--;
        } else {
            tail = tail.getPrev();
            size--;
        }
    }

    @Override
    public void remove(int index){
        if(index >= size || index < 0){
            throw new IndexOutOfBoundsException();
        }

        if(index == 0){
            removeFirst();
            return;
        } else if(index == size - 1){
            removeLast();
            return;
        }

        MyNode<T> nodeToRemove = findNode(index);

        MyNode<T> tmpPrevNode = nodeToRemove.getPrev();
        tmpPrevNode.setNext(nodeToRemove.getNext());

        MyNode<T> tmpNextNode = nodeToRemove.getNext();
        tmpNextNode.setPrev(tmpPrevNode);

        size--;
    }

    private void clearHeadAndTail(){
        head = null;
        tail = null;
    }

    private MyNode<T> findNode(int index){
        MyNode<T> node = head;
        for(int i = 0; i < index; i++){
            node = node.getNext();
        }
        return node;
    }
}
