package lab2;

import java.util.Collection;

/**
 * Created by Dmytro on 07.09.2016.
 */
public interface MyList<E> {
    void add(E element);

    void add(int index, E element);

    void addAll(E[] elements);

    void addAll(int index, Collection<? extends E> elements);

    E get(int index);

    E remove(int index);

    void set(int index, E element);

    int indexOf(Object o);

    int size();

    Object[] toArray();
}
