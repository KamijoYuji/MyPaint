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
    private FillBehavior fill = new Fill();
    private Color color = Color.GRAY;
    private ShapeType shapeType = ShapeType.RECTANGLE;
    private final ActionDraw actionDraw;

    public MenuState(Model model){
        MyShape shape = ShapeFactory.createShape(color, shapeType);
        actionDraw = new ActionDraw(shape,model);
        shape.setFb(fill);
    }

    public void stretchShape(Point2D point){
        actionDraw.stretchShape(point);
    }
    public void createShape(Point2D point){
        actionDraw.createShape(point);
    }
}
