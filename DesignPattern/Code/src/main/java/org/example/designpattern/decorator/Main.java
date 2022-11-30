package org.example.designpattern.decorator;

public class Main {

    public static void main(String[] args) {
        ChristmasTree tree1 = new Garland(new ChristmansTreeImpl());
        System.out.println(tree1.decorate());

        ChristmasTree tree2 = new BubbleLights(new Garland(new ChristmansTreeImpl()));
        System.out.println(tree2.decorate());
    }

    /*
    Decorator: add additional behavior at run time, the new behavior is totally independent
    Inheritance: change behavior at compile time
    When to use inheritance and when to use a Decorator pattern?
    Well, one of the most important factors to consider is whether or not polymorphic behavior is needed.
    If you don’t need polymorphism, you probably should not be using inheritance.
     */
}
