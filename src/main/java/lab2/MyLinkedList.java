package lab2;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Dmytro on 07.09.2016.
 */
public class MyLinkedList<E> implements MyList<E>, Cloneable, Serializable {

    private transient Entry<E> header = new Entry<>(null, null, null);
    private transient int size = 0;

    public MyLinkedList() {
        header.next = header.previous = header;
    }



    @Override
    public void add(E element) {

    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public void addAll(E[] elements) {

    }

    @Override
    public void addAll(int index, Collection<? extends E> elements) {

    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public void set(int index, E element) {

    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    private static class Entry<E> {
        E element;
        Entry<E> next;
        Entry<E> previous;

        public Entry(E element, Entry<E> next, Entry<E> previous) {
            this.element = element;
            this.next = next;
            this.previous = previous;
        }
    }
}
