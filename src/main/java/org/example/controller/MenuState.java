package org.example.controller;

import org.example.ActionDraw;
import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.ShapeFactory;
import org.example.model.ShapeType;
import org.example.model.fill.Fill;
import org.example.model.fill.FillBehavior;
import java.awt.*;
import java.awt.geom.Point2D;

public class MenuState {
    private FillBehavior fill;
    private Color color;
    private final ActionDraw actionDraw;
    private MyShape selectedShape;

    public MenuState(Model model, ShapeType type, Color color, FillBehavior fill){
        selectedShape = ShapeFactory.createShape(color, type);
        actionDraw = new ActionDraw(selectedShape, model);
        selectedShape.setFb(fill);
    }

    public MyShape getSelectedShape() {
        return selectedShape;
    }

    public void stretchShape(Point2D point){
        actionDraw.stretchShape(point);
    }
    public void createShape(Point2D point){ actionDraw.createShape(point); }
}
