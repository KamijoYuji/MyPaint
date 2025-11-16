package org.example.model;

import java.util.ArrayList;
import java.util.List;


public class MyObservable {
    private final List<MyObserver> observers =  new ArrayList<>();

    public void addObserver(MyObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(MyObserver observer) {
        observers.remove(observer);
    }

    protected void notifyObservers() {
        for (MyObserver observer : observers) {
            observer.changed();
        }
    }
}
