package org.example.controller;

import org.example.model.Model;
import org.example.model.MyShape;
import org.example.model.fill.NoFill;
import org.example.view.MyFrame;
import org.example.view.MyPanel;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private static Controller instance;
    private final Model model;
    private final MyFrame frame;
    private final MyPanel panel;
    private Point2D firstPoint;
    private Point2D secondPoint;

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
        MyShape shape = new MyShape(new Rectangle2D.Double());
        shape.setFb(new NoFill());
        model.setMyShape(shape);

        panel = new MyPanel(this);

        frame = new MyFrame();
        frame.setPanel(panel);
    }
    public void getPointOne(Point2D p){
        firstPoint = p;
    }
    public void getPointTwo(Point2D p){
        secondPoint = p;
        model.changeShape(firstPoint, secondPoint);
    }

    public void draw(Graphics2D g2) {
        model.draw(g2); panel.repaint();
    }
}
