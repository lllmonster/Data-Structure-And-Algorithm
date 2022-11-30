package org.example.designpattern.singleton;

public class EagerSingleton {

    private static volatile EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() {

    }

    public static EagerSingleton getInstance() {
        return instance;
    }

    /*
    The instance is created irrespective of it is required in runtime or not.
    If this instance is not a big object and you can live with it being unused, this is the best approach.
     */
}
