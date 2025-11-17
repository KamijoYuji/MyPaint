package org.example.controller.action.menu;

import org.example.controller.action.ActionDraw;
import org.example.controller.action.ActionMove;
import org.example.controller.action.AppAction;
import org.example.model.Model;
import org.example.model.shape.MyShape;
import org.example.model.shape.factory.ShapeFactory;
import org.example.model.shape.factory.ShapeType;
import org.example.model.shape.fill.FillBehavior;
import java.awt.*;
import java.awt.geom.Point2D;

public class MenuState {
    private FillBehavior fill;
    private Color color;
    private AppAction appAction;
    private boolean appActionType;
    private MyShape selectedShape;
    private final Model model;
    private ShapeType type;

    public MenuState(Model model, ShapeType type, Color color, FillBehavior fill){
        this.type = type;
        this.fill = fill;
        this.model = model;
        this.color = color;
        selectedShape = ShapeFactory.createShape(color, type, fill);
        appAction = new ActionDraw(selectedShape, model);
        appActionType = true;
    }

    public void setAppAction(AppAction appAction){
        this.appAction = appAction;
        appActionType = appAction instanceof ActionDraw;
    }
    public MyShape getSelectedShape() {
        return selectedShape;
    }

    public void createShape(Point2D point){
        appAction.mousePressed(point);
    }
    public void stretchShape(Point2D point){ appAction.mouseDragged(point); }

    public void setFill(FillBehavior fill) {
        this.fill = fill;
        selectedShape = ShapeFactory.createShape(color, type, fill);
        appAction = !appActionType ? new ActionMove(selectedShape, model) : (new ActionDraw(selectedShape, model));
    }

    public void setColor(Color color) {
        this.color = color;
        selectedShape = ShapeFactory.createShape(color, type, fill);
        appAction = !appActionType ? new ActionMove(selectedShape, model) : (new ActionDraw(selectedShape, model));
    }

    public void setSelectedShape(ShapeType type) {
        this.type = type;
        selectedShape = ShapeFactory.createShape(color, type, fill);
        appAction = !appActionType ? new ActionMove(selectedShape, model) : (new ActionDraw(selectedShape, model));
    }
}
