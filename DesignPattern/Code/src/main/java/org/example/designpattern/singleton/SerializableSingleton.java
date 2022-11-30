package org.example.designpattern.singleton;


import java.io.Serializable;

public class SerializableSingleton implements Serializable {
    private static final long serialVersionUID = 1L;

    private SerializableSingleton() {}

    private static class Holder {
        public static final SerializableSingleton INSTANCE = new SerializableSingleton();
    }

    public static SerializableSingleton getInstance() {
        return Holder.INSTANCE;
    }

    protected Object readResolve() {
        return getInstance();
    }

    /*
    Good Reference
    https://howtodoinjava.com/design-patterns/creational/singleton-design-pattern-in-java/
     */
}