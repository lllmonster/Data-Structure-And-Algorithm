package org.example.concurrent;

public class MyMultithread extends Thread {
    private String name;
    public MyMultithread(String name) {
        this.name = name;
    }
    public void run() {
        try {
            System.out.println("Thread " + name + " is running");
            CacheKey.put("key1", String.valueOf(Integer.parseInt(name) * 2));
            System.out.println(CacheKey.get("key1"));
        } catch (Exception e) {
            System.out.println("Exception is caught in MyMultithread");
        }
    }

}
