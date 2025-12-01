package org.example.controller.action;

import org.example.model.shape.MyShape;

import java.awt.*;
import java.awt.geom.Point2D;

public interface AppAction {
    void execute();
    void unexecute();
    AppAction cloneAction();

    void mousePressed(Point2D point);

    void mouseDragged(Point2D point);

    void setSampleShape(MyShape sampleShape);
}
