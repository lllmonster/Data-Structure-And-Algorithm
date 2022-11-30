package org.example.designpattern.observer;

public class Main {

    public static void main(String[] args) {
        Observer o1 = new MessageSubscriberOne();
        Observer o2 = new MessageSubscriberTwo();

        Subject p = new MessagePublisher();
        p.add(o1);
        p.add(o2);

        p.notifyUpdate(new Message("First Message"));
        p.remove(o1);

        p.notifyUpdate(new Message("Second Message"));
    }

    /*
    Benefits of the observer pattern:
    The subject and observers make a loosely coupled system.
    They do not need to know each other explicitly. We can independently add or remove observers at any time.
     */
}
