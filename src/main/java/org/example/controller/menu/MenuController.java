package org.example.controller.menu;

import org.example.model.Model;
import org.example.model.shape.factory.ShapeType;
import org.example.model.shape.fill.Fill;
import org.example.model.shape.fill.NoFill;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class MenuController {
    private static MenuController instance;
    JMenuBar menu;
    MenuState menuState;

    public static MenuController getInstance(Model model) {
        if(instance == null)
            instance = new MenuController(model);
        return instance;
    }

    private MenuController(Model model){
        menuState = new MenuState(model,ShapeType.RECTANGLE, Color.RED, new Fill());
        JMenu shapeMenu = createShapeMenu();
        JMenu colorMenu = createColorMenu();
        JMenu fillMenu = createFillMenu();
        menu = new JMenuBar();
        menu.add(shapeMenu);
        menu.add(colorMenu);
        menu.add(fillMenu);
    }

    private JMenu createShapeMenu(){
        JMenu shapeMenu = new JMenu("Rectangle");

        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem rectangle = new JRadioButtonMenuItem("Rectangle");
        rectangle.addActionListener(e -> {menuState.setSelectedShape(ShapeType.RECTANGLE); shapeMenu.setText("Rectangle");});
        shapeMenu.add(rectangle);
        group.add(rectangle);

        JRadioButtonMenuItem ellipse = new JRadioButtonMenuItem("Ellipse");
        ellipse.addActionListener(e -> {menuState.setSelectedShape(ShapeType.ELLIPSE); shapeMenu.setText("Ellipse");});
        shapeMenu.add(ellipse);
        group.add(ellipse);

        return shapeMenu;
    }

    private JMenu createColorMenu(){

        JMenu colorMenu = new JMenu("Red");

        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem red = new JRadioButtonMenuItem("Red");
        red.addActionListener(e -> {menuState.setColor(Color.RED); colorMenu.setText("Red");});
        colorMenu.add(red);
        group.add(red);

        JRadioButtonMenuItem blue = new JRadioButtonMenuItem("Blue");
        blue.addActionListener(e -> {menuState.setColor(Color.BLUE); colorMenu.setText("Blue");});
        colorMenu.add(blue);
        group.add(blue);

        JRadioButtonMenuItem green = new JRadioButtonMenuItem("Green");
        green.addActionListener(e -> {menuState.setColor(Color.GREEN); colorMenu.setText("Green");});
        colorMenu.add(green);
        group.add(green);

        JRadioButtonMenuItem black = new JRadioButtonMenuItem("Black");
        black.addActionListener(e -> {menuState.setColor(Color.BLACK); colorMenu.setText("Black");});
        colorMenu.add(black);
        group.add(black);

        JRadioButtonMenuItem white = new JRadioButtonMenuItem("White");
        white.addActionListener(e -> {menuState.setColor(Color.WHITE); colorMenu.setText("White");});
        colorMenu.add(white);
        group.add(white);

        return colorMenu;
    }

    private JMenu createFillMenu(){

        JMenu fillMenu = new JMenu("Fill");

        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem fill = new JRadioButtonMenuItem("Fill");
        fill.addActionListener(e -> {menuState.setFill(new Fill()); fillMenu.setText("Fill");});
        fillMenu.add(fill);
        group.add(fill);

        JRadioButtonMenuItem noFill = new JRadioButtonMenuItem("NoFill");
        noFill.addActionListener(e -> {menuState.setFill(new NoFill()); fillMenu.setText("NoFill");});
        fillMenu.add(noFill);
        group.add(noFill);

        return fillMenu;
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