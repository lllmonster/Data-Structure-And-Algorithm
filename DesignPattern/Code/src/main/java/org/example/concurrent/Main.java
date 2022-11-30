package org.example.concurrent;

public class Main {
    public static void main(String[] args) {
        CacheKey.put("key","key");
        int n = 5;
        for (int i = 0; i< n; i++) {
            MyMultithread t = new MyMultithread(String.valueOf(i));
            t.start();
        }
    }
}