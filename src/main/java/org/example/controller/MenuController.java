package org.example.controller;

import org.example.model.Model;
import org.example.model.ShapeType;
import org.example.model.fill.Fill;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class MenuController {
    private static MenuController instance;
    JMenuBar menu;
    MenuState menuState;
    Model model;

    public static MenuController getInstance(Model model) {
        if(instance == null)
            instance = new MenuController(model);
        return instance;
    }

    private MenuController(Model model){
        this.model = model;
        menuState = new MenuState(model,ShapeType.RECTANGLE, Color.RED,new Fill());
        JMenu colorMenu = createShapeMenu();
        menu = new JMenuBar();
        menu.add(colorMenu);
    }

    private JMenu createShapeMenu(){
        JMenu shapeMenu = new JMenu("Figure");

        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem square = new JRadioButtonMenuItem("Rectangle");
        square.addActionListener(e -> menuState = new MenuState(model, ShapeType.RECTANGLE, Color.RED,new Fill()));
        shapeMenu.add(square);
        group.add(square);

        JRadioButtonMenuItem ellipse = new JRadioButtonMenuItem("Ellipse");
        ellipse.addActionListener(e -> menuState = new MenuState(model, ShapeType.ELLIPSE, Color.RED,new Fill()));
        shapeMenu.add(ellipse);
        group.add(ellipse);

        return shapeMenu;
    }

    public void createShape(Point2D point) {
        menuState.createShape(point);
    }

    public void stretchShape(Point2D point) {
        menuState.stretchShape(point);
    }

    public JMenuBar getMenuBar() {
        return menu;
    }
}