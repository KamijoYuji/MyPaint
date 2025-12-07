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
    private final Model model;
    private MyShape drawableShape;

    @Override
    public void setSampleShape(MyShape sampleShape) {
        this.sampleShape = sampleShape;
    }

    @Override
    public void execute() {
        model.createCurrentShape(drawableShape);
        model.update();
    }

    @Override
    public void unexecute() {
        drawableShape = model.getLastShape();
        model.removeLastShape();
        model.update();
    }

    @Override
    public AppAction cloneAction() {
        ActionDraw actionDraw = new ActionDraw(sampleShape,model);
        actionDraw.sampleShape = this.sampleShape.clone();
        actionDraw.drawableShape = this.drawableShape;
        return actionDraw;
    }

    @Override
    public void mousePressed(Point2D point) {
        secondPoint = point;
        shape = sampleShape.clone();
        drawableShape = sampleShape.clone();
        model.createCurrentShape(shape);
    }

    @Override
    public void mouseDragged(Point2D point) {
        firstPoint = point;
        if(shape!=null)
            shape.setFrame(firstPoint,secondPoint);
        if(drawableShape!=null)
            drawableShape.setFrame(firstPoint,secondPoint);
    }

    public ActionDraw(MyShape sampleShape, Model model) {
        this.sampleShape = sampleShape;
        this.model = model;
    }
}
