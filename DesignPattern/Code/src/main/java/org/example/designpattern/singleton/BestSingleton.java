package org.example.designpattern.singleton;

public class BestSingleton {

    private BestSingleton() {}

    private static class LazyHolder {
        private static final BestSingleton INSTANCE = new BestSingleton();
    }

    public static BestSingleton getInstance() {
        return LazyHolder.INSTANCE;
    }

    /*
    As you can see, until we need an instance, the LazyHolder class will not be initialized until required
    and you can still use other static members of BestSingleton class.
    This is the solution, i will recommend to use. I have used it in my all projects.
     */
}
