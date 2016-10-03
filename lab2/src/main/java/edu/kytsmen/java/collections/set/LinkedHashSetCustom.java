package edu.kytsmen.java.collections.set;

/**
 * Created by dkytsmen on 10/3/16.
 */
public class LinkedHashSetCustom<E> {
    private LinkedHashMapCustom<E, Object> linkedHashMapCustom;

    public LinkedHashSetCustom() {
        this.linkedHashMapCustom = new LinkedHashMapCustom<>();
    }

    public void add(E value) {
        linkedHashMapCustom.put(value, null);
    }

    public boolean contains(E obj) {
        return linkedHashMapCustom.contains(obj) != null ? true : false;
    }

    public void display() {
        linkedHashMapCustom.display();
    }

    public boolean remove(E obj) {
        return linkedHashMapCustom.remove(obj);
    }
}

class LinkedHashMapCustom<K, V> {
    private Entry<K, V>[] table;
    private int capacity = 4;
    private Entry<K, V> header;
    private Entry<K, V> lastElement;

    static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;
        Entry<K, V> before;
        Entry<K, V> after;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    @SuppressWarnings("unchecked")
    public LinkedHashMapCustom() {
        table = new Entry[capacity];
    }


    private int hash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public K contains(K key) {
        int hash = hash(key);
        if (table[hash] == null) {
            return null;
        } else {
            Entry<K, V> temp = table[hash];
            while (temp != null) {
                if (temp.key.equals(key))
                    return key;
                temp = temp.next;
            }
            return null;
        }
    }

    public void displaySet() {
        Entry<K, V> currentEntry = header;
        while (currentEntry != null) {
            System.out.println(currentEntry.key + " ");
            currentEntry = currentEntry.after;
        }
    }

    public void display() {
        Entry<K, V> currentEntry = header;
        while (currentEntry != null) {
            System.out.println("{" + currentEntry.key + "=" + currentEntry.value + "}" + " ");
            currentEntry = currentEntry.after;
        }
    }

    public boolean remove(K deleteKey) {
        int hash = hash(deleteKey);

        if (table[hash] == null) {
            return false;
        } else {
            Entry<K, V> previous = null;
            Entry<K, V> current = table[hash];

            while (current != null) {
                if (current.key.equals(deleteKey)) {
                    maintainOrderAfterDeletion(current);
                    if (previous == null) {
                        table[hash] = table[hash].next;
                        return true;
                    } else {
                        previous.next = current.next;
                        return true;
                    }
                }
                previous = current;
                current = current.next;
            }
            return false;
        }
    }

    public V get(K key) {
        int hash = hash(key);
        if (table[hash] == null) {
            return null;
        } else {
            Entry<K, V> temp = table[hash];
            while (temp != null) {
                if (temp.key.equals(key)) {
                    return temp.value;
                }
                temp = temp.next;
            }
            return null;
        }
    }

    public Entry<K, V> deleteSpecificEntry(Entry<K, V> entryForDeletion) {
        Entry<K, V> current = header;
        while (!current.key.equals(entryForDeletion.key)) {
            if (current.after == null) {
                return null;
            }
            current = current.after;
        }
        Entry<K, V> beforeDeletionEntry = current.before;
        current.before.after = current.after;
        current.after.before = current.before;
        return beforeDeletionEntry;
    }

    public void deleteLast() {
        if (header == lastElement) {
            header = lastElement = null;
            return;
        }
        lastElement = lastElement.before;
        lastElement.after = null;
    }

    public void insertLast(Entry<K, V> newEntry) {
        if (header == null) {
            header = newEntry;
            lastElement = newEntry;
            return;
        }
        lastElement.after = newEntry;
        newEntry.before = lastElement;
        lastElement = newEntry;
    }

    public void insertFirst(Entry<K, V> newEntry) {
        if (header == null) {
            header = newEntry;
            lastElement = newEntry;
            return;
        }
        newEntry.after = header;
        header.before = newEntry;
        header = newEntry;
    }

    public void deleteFirst() {
        if (header == null) {
            header = lastElement = null;
            return;
        }

        header = header.after;
        header.before = null;
    }

    private void insertAfter(Entry<K, V> beforeEntry, Entry<K, V> newEntry) {
        Entry<K, V> current = header;
        while (current != beforeEntry) {
            current = current.after;
        }

        newEntry.after = beforeEntry.after;
        beforeEntry.after.before = newEntry;
        newEntry.before = beforeEntry;
        beforeEntry.after = newEntry;
    }

    private void maintainOrderAfterDeletion(Entry<K, V> deleteEntry) {
        if (header.key.equals(deleteEntry.key)) {
            deleteFirst();
            return;
        }

        if (lastElement.key.equals(deleteEntry.key)) {
            deleteLast();
            return;
        }
        deleteSpecificEntry(deleteEntry);
    }

    private void maintainOrderAfterInsert(Entry<K, V> newEntry) {
        if (header == null) {
            header = newEntry;
            lastElement = newEntry;
            return;
        }

        if (header.key.equals(newEntry.key)) {
            deleteFirst();
            insertFirst(newEntry);
            return;
        }

        if (lastElement.key.equals(newEntry.key)) {
            deleteLast();
            insertLast(newEntry);
            return;
        }

        Entry<K, V> beforeDeleteEntry = deleteSpecificEntry(newEntry);
        if (beforeDeleteEntry == null) {
            insertLast(newEntry);
        } else {
            insertAfter(beforeDeleteEntry, newEntry);
        }
    }

    public void put(K newKey, V data) {
        if (newKey == null) {
            return;
        }

        int hash = hash(newKey);

        Entry<K, V> newEntry = new Entry<K, V>(newKey, data, null);
        maintainOrderAfterInsert(newEntry);
        if (table[hash] == null) {
            table[hash] = newEntry;
        } else {
            Entry<K, V> previous = null;
            Entry<K, V> current = table[hash];
            while (current != null) {
                if (current.key.equals(newKey)) {
                    if (previous == null) {
                        newEntry.next = current.next;
                        table[hash] = newEntry;
                        return;
                    } else {
                        newEntry.next = current.next;
                        previous.next = newEntry;
                        return;
                    }
                }
                previous = current;
                current = current.next;
            }
            previous.next = newEntry;
        }
    }
}
