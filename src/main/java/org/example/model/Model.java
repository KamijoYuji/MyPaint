package org.example.model;

import org.example.model.fill.Fill;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Model extends Observable {
    private MyShape currentShape;
    private List<MyShape> shapeList = new ArrayList<>();

    public void setMyShape(MyShape myShape) {

        this.currentShape = myShape;
        shapeList.add(currentShape);
    }

    public void changeShape(Point2D x, Point2D y) {
        if(currentShape!=null)
            currentShape.setFrame(x, y);
    }

    public void draw(Graphics2D g) {
        for(var shape : shapeList)
            shape.draw(g);
    }
}
