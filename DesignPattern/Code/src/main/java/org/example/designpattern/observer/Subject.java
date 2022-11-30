package org.example.designpattern.observer;

public interface Subject {

    public void add(Observer o);
    public void remove(Observer o);
    public void notifyUpdate(Message m);
}
