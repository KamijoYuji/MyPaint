package org.example.model;

import java.util.ArrayList;
import java.util.List;


public class Subject {
    private final List<ModelObserver> observers =  new ArrayList<>();

    public void addObserver(ModelObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ModelObserver observer) {
        observers.remove(observer);
    }

    protected void notifyObservers() {
        for (ModelObserver observer : observers) {
            observer.modelChanged();
        }
    }
}
