package org.example.controller;

import org.example.controller.action.menu.MenuCreator;
import org.example.controller.action.menu.MenuState;
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
    MenuState menuState;
    private MenuCreator menuCreator;
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

        frame = new MyFrame();

        menuCreator = MenuCreator.getInstance();
        menuCreator.setState(menuState);
        menuCreator.setModel(model);
        frame.setJMenuBar(menuCreator.createMenuBar());
        frame.add(menuCreator.createToolBar(), BorderLayout.SOUTH);

        frame.setPanel(panel);
        model.addObserver(panel);
        frame.revalidate();
    }

    public void stretchShape(Point2D point){
        menuCreator.stretchShape(point);
    }
    public void createShape(Point2D point){
        menuCreator.createShape(point);
    }

    public void draw(Graphics2D g2) {
        model.draw(g2);
    }
}
