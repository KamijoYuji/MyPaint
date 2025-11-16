package org.example.controller;

import org.example.controller.menu.MenuController;
import org.example.model.Model;
import org.example.view.MyFrame;
import org.example.view.MyPanel;

import java.awt.*;
import java.awt.geom.Point2D;

public class Controller {
    private static Controller instance;
    private final Model model;
    private final MyFrame frame;
    private final MyPanel panel;
    private final MenuController menuController;
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

        panel = new MyPanel(this);
        menuController = MenuController.getInstance(model);
        frame = new MyFrame();
        frame.setJMenuBar(menuController.getMenuBar());
        frame.setPanel(panel);
    }

    public void stretchShape(Point2D point){
        menuController.stretchShape(point);
    }
    public void createShape(Point2D point){
        menuController.createShape(point);
    }

    public void draw(Graphics2D g2) {
        model.draw(g2);
    }
}
