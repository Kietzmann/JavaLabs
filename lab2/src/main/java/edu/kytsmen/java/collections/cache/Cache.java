package edu.kytsmen.java.collections.cache;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * Created by dkytsmen on 9/20/16.
 */
public class Cache<K, V> {
    //    private long timeToLive;
//    private HashMap<K, T> cacheMap;
//
//    protected class CacheObject {
//        public long lastAccessed = System.currentTimeMillis();
//        public String value;
//
//        private CacheObject(String value) {
//            this.value = value;
//        }
//    }
//
//    public void put(K key, T value){
//        synchronized (cacheMap)
//    }
    private volatile ConcurrentHashMap<Key, V> globalMap = new ConcurrentHashMap<Key, V>();
    private long timeToLive;
    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            return thread;
        }
    });

    public Cache(long timeToLive) {
        if (timeToLive < 10)
            throw new IllegalArgumentException("Too little time for storing in the cache.");
        this.timeToLive = timeToLive;
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                long current = System.currentTimeMillis();
                for (Key k : globalMap.keySet()) {
                    if (!k.isAlive(current)) {
                        globalMap.remove(k);
                    }
                }
            }
        }, 1, timeToLive / 5, TimeUnit.MILLISECONDS);
    }

    public void setTimeToLive(long timeToLive) throws IllegalArgumentException {
        if (timeToLive < 100) {
            throw new IllegalArgumentException("Too little time for storing in the cache.");
        }
        this.timeToLive = timeToLive;
    }

    public void put(K key, V value) {
        verifyKey(key);
        globalMap.put(new Key(key, timeToLive), value);
    }

    private void verifyKey(K key) {
        if (key == null)
            throw new IllegalArgumentException("Key can't be null!");
    }

    public void put(K key, V value, long timeToLive) {
        verifyKey(key);
        globalMap.put(new Key(key, timeToLive), value);
    }

    public V get(K key) {
        verifyKey(key);
        return globalMap.get(new Key(key));
    }

    public void remove(K key) {
        verifyKey(key);
        globalMap.remove(new Key(key));
    }

    public void removeAll() {
        globalMap.clear();
    }

    public void setAll(Map<K, V> map) {
        ConcurrentHashMap tempMap = new ConcurrentHashMap<Key, V>();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            verifyKey(entry.getKey());
            tempMap.put(new Key(entry.getKey(), timeToLive), entry.getValue());
        }
        globalMap = tempMap;
    }

    public void addAll(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            verifyKey(entry.getKey());
            put(entry.getKey(), entry.getValue());
        }
    }


    private static class Key {
        private final Object key;
        private final long timeToLive;
        private static long DEFAULT_TIME = 10000000;

        public Key(Object key, long timeToLive) {
            this.key = key;
            this.timeToLive = System.currentTimeMillis() + timeToLive;
        }

        public Key(Object key) {
            this.key = key;
            timeToLive = DEFAULT_TIME;
        }

        public Object getKey() {
            return key;
        }

        public boolean isAlive(long currentTimeMillis) {
            return currentTimeMillis < timeToLive;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key1 = (Key) o;
            return Objects.equals(key, key1.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }

        @Override
        public String toString() {
            return "Key{" +
                    "key=" + key +
                    '}';
        }
    }
}
