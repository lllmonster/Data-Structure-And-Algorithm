package org.example.designpattern.singleton;

public enum EnumSingleton {
    INSTANCE;

    public void someMethod(String param) {
        // some class member
    }

    /*
    This type of implementation employs the use of enum.
    Enum, as written in the java docs, provided implicit support for thread safety and only one instance is guaranteed.
    Java enum singleton is also a good way to have singleton with minimal effort.
     */
}
