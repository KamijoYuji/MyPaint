package org.example.controller.action;

import org.example.model.Model;
import org.example.model.shape.MyShape;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class ActionMove implements AppAction {
    private MyShape sampleShape;
    private MyShape shape;
    private Point2D startPoint;
    private Point2D endPoint;
    private final Model model;
    private Rectangle2D startBounds;
    double totalDeltaX;
    double totalDeltaY;
    private boolean hasMoved = false;

    public ActionMove(MyShape sampleShape, Model model) {
        this.sampleShape = sampleShape;
        this.model = model;
    }

    @Override
    public void setSampleShape(MyShape sampleShape) {
        this.sampleShape = sampleShape;
    }

    @Override
    public void execute() {
        if (shape != null && startBounds != null && hasMoved) {
            double newX = startBounds.getX() + totalDeltaX;
            double newY = startBounds.getY() + totalDeltaY;

            shape.getShape().setFrame(
                    newX,
                    newY,
                    startBounds.getWidth(),
                    startBounds.getHeight()
            );
            model.update();
        }
    }

    @Override
    public void unexecute() {
        if (shape != null && startBounds != null && hasMoved) {
            shape.getShape().setFrame(
                    startBounds.getX(),
                    startBounds.getY(),
                    startBounds.getWidth(),
                    startBounds.getHeight()
            );
            model.update();
        }
    }

    @Override
    public AppAction cloneAction() {
        ActionMove clone = new ActionMove(sampleShape, model);
        clone.shape = this.shape;
        clone.startPoint = this.startPoint != null ?
                new Point2D.Double(this.startPoint.getX(), this.startPoint.getY()) : null;
        clone.endPoint = this.endPoint != null ?
                new Point2D.Double(this.endPoint.getX(), this.endPoint.getY()) : null;
        clone.startBounds = this.startBounds != null ?
                new Rectangle2D.Double(
                        this.startBounds.getX(),
                        this.startBounds.getY(),
                        this.startBounds.getWidth(),
                        this.startBounds.getHeight()
                ) : null;
        clone.totalDeltaX = this.totalDeltaX;
        clone.totalDeltaY = this.totalDeltaY;
        clone.hasMoved = this.hasMoved;
        return hasMoved?clone:null;
    }

    @Override
    public void mousePressed(Point2D point) {
        startPoint = point;
        shape = model.getShapeList()
                .stream()
                .filter(myShape -> myShape.getShape().contains(point))
                .findFirst()
                .orElse(null);
        if (shape != null) {
            startBounds = shape.getShape().getBounds2D();
            totalDeltaX = 0;
            totalDeltaY = 0;
        }
        hasMoved = false;
    }

    @Override
    public void mouseDragged(Point2D point) {
        if (shape == null || startBounds == null) {
            return;
        }

        double deltaX = point.getX() - startPoint.getX();
        double deltaY = point.getY() - startPoint.getY();

        double newX = startBounds.getX() + deltaX;
        double newY = startBounds.getY() + deltaY;

        shape.getShape().setFrame(
                newX,
                newY,
                startBounds.getWidth(),
                startBounds.getHeight()
        );

        totalDeltaX = deltaX;
        totalDeltaY = deltaY;
        hasMoved = true;

        endPoint = point;
        model.update();
    }

    public double getTotalDeltaX() {
        return totalDeltaX;
    }

    public double getTotalDeltaY() {
        return totalDeltaY;
    }
}