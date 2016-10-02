package main.edu.kytsmen.java.collections.lists;

import java.util.Collection;
import java.util.Objects;

/**
 * Created by dkytsmen on 9/20/16.
 */
public interface MySet<E> extends Collection<E> {
    int size();

    boolean isEmpty();

    boolean contains(Object o);

    Object[] toArray();

    boolean add(E e);

    boolean remove(Object o);

    boolean containsAll(Collection<?> c);

    boolean addAll(Collection<? extends E> c);

    int hashCode();

    boolean equals(Object o);

    void clear();

    boolean removeAll(Collection<?> c);

    boolean retainAll(Collection<?> c);
}
