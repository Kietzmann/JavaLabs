package edu.kytsmen.java.collections.lists;

/**
 * Created by dkytsmen on 10/3/16.
 */

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;

/**
 * Created by Dmytro on 07.09.2016.
 */
public class MyLinkedList<E> implements MyList<E>, Cloneable, Serializable {

    private transient Entry<E> header = new Entry<>(null, null, null);
    private transient int size = 0;

    public MyLinkedList() {
        header.next = header.previous = header;
    }


    public MyLinkedList(Collection<? extends E> c) {
        this();
        addAll(c);
    }


    @Override
    public void add(E element) {
        addBefore(element, header);
    }

    @Override
    public void add(int index, E element) {
        addBefore(element, (index == size ? header : entry(index)));
    }

    private Entry<E> addBefore(E e, Entry<E> entry) {
        Entry<E> newEntry = new Entry<E>(e, entry, entry.previous);
        newEntry.previous.next = newEntry;
        newEntry.next.previous = newEntry;
        size++;
        return newEntry;
    }

    @Override
    public void addAll(Collection<? extends E> collection) {
        addAll(size, collection);
        return;
    }

    @Override
    public void addAll(int index, Collection<? extends E> elements) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException(getOutOfBoundsExceptionMessage(index));

        Object[] a = elements.toArray();
        int numNew = a.length;
        if (numNew == 0)
            return;

        Entry<E> successor = (index == size ? header : entry(index));
        Entry<E> predecessor = successor.previous;
        for (int i = 0; i < numNew; i++) {
            Entry<E> e = new Entry<E>((E) a[i], successor, predecessor);
            predecessor.next = e;
            predecessor = e;
        }
        successor.previous = predecessor;

        size += numNew;
        return;
    }

    private String getOutOfBoundsExceptionMessage(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    @Override
    public E get(int index) {
        return entry(index).element;
    }

    @Override
    public E remove(int index) {
        return remove(entry(index));
    }

    @Override
    public void set(int index, E element) {
        Entry<E> e = entry(index);
        e.element = element;
    }

    private Entry<E> entry(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(getOutOfBoundsExceptionMessage(index));

        Entry<E> e = header;
        if (index < (size >> 1)) {
            for (int i = 0; i <= index; i++) {
                e = e.next;
            }
        } else {
            for (int i = size; i > index; i--) {
                e = e.previous;
            }
        }
        return e;
    }

    private E remove(Entry<E> e) {
        if (e == header)
            throw new NoSuchElementException();
        E result = e.element;
        e.previous.next = e.next;
        e.next.previous = e.previous;
        e.next = e.previous = null;
        e.element = null;
        size--;
        return result;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Entry e = header.next; e != header; e = e.next) {
                if (e.element == null) {
                    return index;
                }
                index++;
            }
        } else {
            for (Entry e = header.next; e != header; e = e.next) {
                if (o.equals(e.element)) {
                    return index;
                }
                index++;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Entry<E> e = header.next; e != header; e = e.next) {
            result[i++] = e.element;
        }
        return result;
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Entry<?> entry = (Entry<?>) o;

            if (element != null ? !element.equals(entry.element) : entry.element != null) return false;
            if (next != null ? !next.equals(entry.next) : entry.next != null) return false;
            return previous != null ? previous.equals(entry.previous) : entry.previous == null;

        }

        @Override
        public int hashCode() {
            int result = element != null ? element.hashCode() : 0;
            result = 31 * result + (next != null ? next.hashCode() : 0);
            result = 31 * result + (previous != null ? previous.hashCode() : 0);
            return result;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyLinkedList<?> that = (MyLinkedList<?>) o;

        if (size != that.size) return false;
        return header != null ? header.equals(that.header) : that.header == null;

    }

    @Override
    public int hashCode() {
        int result = header != null ? header.hashCode() : 0;
        result = 31 * result + size;
        return result;
    }
}