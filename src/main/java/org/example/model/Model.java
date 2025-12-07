package org.example.model;

import org.example.model.shape.MyShape;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Model extends MyObservable {
    private MyShape currentShape;
    private final List<MyShape> shapeList = new ArrayList<>();

    public List<MyShape> getShapeList() {
        return shapeList;
    }

    public void changeShape(Point2D x, Point2D y) {
        if(currentShape!=null)
            currentShape.setFrame(x, y);
        notifyObservers();
    }

    public void draw(Graphics2D g) {
        for(var shape : shapeList)
            shape.draw(g);
        notifyObservers();
    }

    public void createCurrentShape(MyShape shape) {
        currentShape = shape;
        shapeList.add(shape);
        notifyObservers();
    }

    public void update(){
        notifyObservers();
    }

    public MyShape getLastShape() {
        return shapeList.get(shapeList.size()-1);
    }

    public void removeLastShape() {
        shapeList.remove(shapeList.size()-1);
    }
}
