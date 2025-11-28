package org.example.controller.action;

import org.example.controller.action.menu.MenuState;
import org.example.model.Model;
import org.example.model.shape.MyShape;
import org.example.model.shape.factory.ShapeFactory;

import java.awt.*;
import java.awt.geom.Point2D;

public class ActionDraw implements AppAction{
    private MyShape sampleShape;
    private MyShape shape;
    private Point2D firstPoint;
    private Point2D secondPoint;
    private Model model;

    @Override
    public void setSampleShape(MyShape sampleShape) {
        this.sampleShape = sampleShape;
    }

    @Override
    public void mousePressed(Point2D point) {
        secondPoint = point;
        shape = sampleShape.clone();
        model.createCurrentShape(shape);
    }

    @Override
    public void mouseDragged(Point2D point) {
        firstPoint = point;
        shape.setFrame(firstPoint,secondPoint);
    }

    public ActionDraw(MyShape sampleShape, Model model) {
        this.sampleShape = sampleShape;
        this.model = model;
    }
}
