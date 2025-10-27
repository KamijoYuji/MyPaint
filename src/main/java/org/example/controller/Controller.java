package org.example.controller;

import org.example.ActionDraw;
import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.ShapeFactory;
import org.example.model.ShapeType;
import org.example.model.fill.NoFill;
import org.example.view.MyFrame;
import org.example.view.MyPanel;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Controller {
    private static Controller instance;
    private final Model model;
    private final MyFrame frame;
    private final MyPanel panel;
    private Point2D firstPoint;
    private Point2D secondPoint;
    private ActionDraw actionDraw;

    public static Controller getInstance() {
        synchronized (Controller.class) {
            if (instance == null) {
                instance = new Controller();
            }
            return instance;
        }
    }
    private Controller() {
        model = new Model();
        MyShape shape = ShapeFactory.createShape(Color.gray, ShapeType.RECTANGLE);
        actionDraw = new ActionDraw(shape,model);
        shape.setFb(new NoFill());

        panel = new MyPanel(this);
        // TODO: Поменять наблюдатель на более современную реализацию

        frame = new MyFrame();
        frame.setPanel(panel);
    }

    public void stretchShape(Point2D point){
        firstPoint = point;
        actionDraw.stretchShape(point);
    }
    public void createShape(Point2D point){
        secondPoint = point;
        actionDraw.createShape(point);
    }

    public void draw(Graphics2D g2) {
        model.draw(g2);
    }
}
