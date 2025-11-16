package org.example.controller.action;

import org.example.model.Model;
import org.example.model.MyShape;

import java.awt.geom.Point2D;

public class ActionDraw {
    private MyShape sampleShape;
    private MyShape shape;
    private Point2D firstPoint;
    private Point2D secondPoint;
    private Model model;

    public ActionDraw(MyShape sampleShape, Model model) {
        this.sampleShape = sampleShape;
        this.model = model;
    }

    public void stretchShape(Point2D point){
        firstPoint = point;
        shape.setFrame(firstPoint,secondPoint);
    }
    public void createShape(Point2D point){
        secondPoint = point;
        shape = sampleShape.clone();
        model.createCurrentShape(shape);
    }
}
