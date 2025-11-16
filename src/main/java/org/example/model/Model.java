package org.example.model;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Model extends Subject {
    private MyShape currentShape;
    private final List<MyShape> shapeList = new ArrayList<>();

    public void setMyShape(MyShape myShape)
    {
        this.currentShape = myShape;
    }

    public void changeShape(Point2D x, Point2D y) {
        if(currentShape!=null)
            currentShape.setFrame(x, y);
        notifyObservers();
    }

    public void draw(Graphics2D g) {
        for(var shape : shapeList)
            shape.draw(g);
    }

    public void createCurrentShape(MyShape shape) {
        currentShape = shape;
        shapeList.add(shape);
        notifyObservers();
    }
}
