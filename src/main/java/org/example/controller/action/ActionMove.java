package org.example.controller.action;

import org.example.model.Model;
import org.example.model.shape.MyShape;

import java.awt.geom.Point2D;

public class ActionMove implements AppAction{
    private MyShape sampleShape;
    private MyShape shape;
    private Point2D firstPoint;
    private Point2D secondPoint;
    private final Model model;

    public ActionMove(MyShape sampleShape, Model model) {
        this.sampleShape = sampleShape;
        this.model = model;
    }

    @Override
    public void setSampleShape(MyShape sampleShape) {
        this.sampleShape = sampleShape;
    }

    @Override
    public void mousePressed(Point2D point) {
        firstPoint = point;
        shape = model.getShapeList()
                .stream()
                .filter(myShape -> myShape.getShape().contains(point))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void mouseDragged(Point2D point) {
        if (shape == null){
            return;
        }
        double deltaX = point.getX() - firstPoint.getX();
        double deltaY = point.getY() - firstPoint.getY();
        Point2D newShapeFirstPoint = new Point2D.Double();
        newShapeFirstPoint.setLocation(shape.getShape().getMaxX() + deltaX,
                shape.getShape().getMaxY() + deltaY);
        Point2D newShapeSecondPoint = new Point2D.Double();
        newShapeSecondPoint.setLocation(shape.getShape().getMinX() + deltaX,
                shape.getShape().getMinY() + deltaY);
        shape.getShape().setFrameFromDiagonal(newShapeFirstPoint,
                newShapeSecondPoint);
        firstPoint = point;
        model.update();
    }
}
