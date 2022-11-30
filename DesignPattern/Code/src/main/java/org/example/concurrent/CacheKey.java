package org.example.concurrent;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CacheKey {

    private static final Set<String> lockedKeys = new HashSet<>();
    private static final ConcurrentMap<String, String> cache = new ConcurrentHashMap<String, String>();

    private static void lock(String key) {
        synchronized (lockedKeys) {
            while (!lockedKeys.add(key)) {
                try {
                    lockedKeys.wait();
                } catch (InterruptedException e) {
                    System.out.println("Exception in lock key");
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static void unlock(String key) {
        synchronized (lockedKeys) {
            lockedKeys.remove(key);
            lockedKeys.notifyAll();
        }
    }

    public static String get(String key) {
        try {
            lock(key);

            String result = cache.get(key);
            return result;
            //For different keys it is executed in parallel.
            //For the same key it is executed synchronously.

        } finally {
            unlock(key);
        }
    }

    public static void put(String key, String value) {
        try {
            lock(key);

            cache.put(key, value);

        } finally {
            unlock(key);
        }
    }

}
