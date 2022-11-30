package org.example.concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import com.google.common.cache.*;
public class Cache {

    private static final ConcurrentMap<String, String> map = new ConcurrentHashMap<String, String>();
    public static String get(String key) {
        String s = map.compute(key, (k ,v) -> {
            return v;
        });
        return s;
    }
    public static void put(String k, String v) {
        map.compute(k, (kk,vv) -> v);
    }
//    private static final ConcurrentMap<String, String> map = new ConcurrentHashMap<String, String>();
//    private static final LoadingCache<String,String> cache = CacheBuilder.newBuilder().
//            build(new CacheLoader<String, String>() {
//                @Override
//                public String load(String key) throws Exception {
//                    return getFromMap(key);
//                }
//            });
//
//    public static String get(String key) {
//        String v = "";
//        try {
//            v = cache.get(key);
//        } catch (Exception e) {
//            System.out.println("Failed to get value from cache.");
//        }
//        return v;
//    }
//
//    public static String getFromMap(String k) {
//        return map.get(k);
//    }

//    public static void put(String k, String v) {
//        map.put(k, v);
//    }
}
