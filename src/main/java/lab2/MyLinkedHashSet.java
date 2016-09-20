package lab2;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dkytsmen on 9/20/16.
 */
public class MyLinkedHashSet<E> extends HashSet<E> implements Set<E>, Cloneable, Serializable {
    public MyLinkedHashSet(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public MyLinkedHashSet(int initialCapacity) {
        super(initialCapacity, .75f);
    }

    public MyLinkedHashSet() {
        super(16, .75f);
    }

    public MyLinkedHashSet(Collection<? extends E> c) {
        super(Math.max(2 * c.size(), 11), .75f);
        addAll(c);
    }
}
