package org.example.controller.action.menu;

import org.example.controller.action.ActionDraw;
import org.example.controller.action.ActionMove;
import org.example.model.Model;
import org.example.model.shape.factory.ShapeType;
import org.example.model.shape.fill.Fill;
import org.example.model.shape.fill.NoFill;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.net.URL;
import java.util.ArrayList;

public class MenuCreator {
    private static MenuCreator instance;
    private JMenuBar menu;
    private MenuState menuState;
    private Model model;

    public static MenuCreator getInstance() {
        if (instance == null)
            instance = new MenuCreator();
        return instance;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    private MenuCreator() {
    }

    public JMenuBar createMenuBar() {
        menuState = new MenuState(model, ShapeType.RECTANGLE, Color.RED, new Fill());
        JMenu shapeMenu = createShapeMenu();
        JMenu colorMenu = createColorMenu();
        JMenu fillMenu = createFillMenu();
        JMenu actionMenu = createActionMenu();
        menu = new JMenuBar();
        menu.add(shapeMenu);
        menu.add(colorMenu);
        menu.add(fillMenu);
        menu.add(actionMenu);

        return menu;
    }

    public JToolBar createToolBar() {
        ArrayList<Action> subMenuItems = createToolBarItems();
        JToolBar jToolBar = new JToolBar();
        subMenuItems.forEach(jToolBar::add);

        return jToolBar;
    }

    private ArrayList<Action> createToolBarItems() {
        ArrayList<Action> menuItems = new ArrayList<>();
        URL colorUrl = getClass().getClassLoader().getResource("ico/color_16x16.png");
        ImageIcon colorIco = colorUrl == null ? null : new ImageIcon(colorUrl);
        JRadioButtonMenuItem rgbButton = new JRadioButtonMenuItem(colorIco);
        AppCommand colorCommand = new SwitchColor(menuState, false, Color.BLUE, rgbButton);
        menuItems.add(new CommandActionListener("Color", colorIco, colorCommand));

        URL shapeUrl = getClass().getClassLoader().getResource("ico/rectangular_16x16.png");
        ImageIcon shapeIco = shapeUrl == null ? null : new ImageIcon(shapeUrl);
        AppCommand shapeCommand = new SwitchShape(menuState, ShapeType.RECTANGLE, null);
        menuItems.add(new CommandActionListener("Rectangular", shapeIco, shapeCommand));

        URL shapeUrl1 = getClass().getClassLoader().getResource("ico/ellipse_16x16.png");
        ImageIcon shapeIco1 = shapeUrl1 == null ? null : new ImageIcon(shapeUrl1);
        AppCommand shapeCommand1 = new SwitchShape(menuState, ShapeType.ELLIPSE, null);
        menuItems.add(new CommandActionListener("Ellipse", shapeIco1, shapeCommand1));

        URL fillUrl = getClass().getClassLoader().getResource("ico/fill_16x16.png");
        ImageIcon fillIco = fillUrl == null ? null : new ImageIcon(fillUrl);
        AppCommand fillCommand = new SwitchFill(menuState, new Fill());
        menuItems.add(new CommandActionListener("Fill", fillIco, fillCommand));

        URL noFillUrl = getClass().getClassLoader().getResource("ico/no_fill_16x16.png");
        ImageIcon noFillIco = fillUrl == null ? null : new ImageIcon(noFillUrl);
        AppCommand noFillCommand = new SwitchFill(menuState, new NoFill());
        menuItems.add(new CommandActionListener("Fill", noFillIco, noFillCommand));

        URL actionUrl = getClass().getClassLoader().getResource("ico/draw_16x16.png");
        ImageIcon actionIco = actionUrl == null ? null : new ImageIcon(actionUrl);
        AppCommand actionCommand = new SwitchAction(menuState, new ActionDraw(menuState.getSelectedShape(), model));
        menuItems.add(new CommandActionListener("Action", actionIco, actionCommand));

        URL moveUrl = getClass().getClassLoader().getResource("ico/move_16x16.png");
        ImageIcon moveIco = actionUrl == null ? null : new ImageIcon(moveUrl);
        AppCommand moveCommand = new SwitchAction(menuState, new ActionMove(menuState.getSelectedShape(), model));
        menuItems.add(new CommandActionListener("Action", moveIco, moveCommand));


        return menuItems;
    }

    private JMenu createShapeMenu() {
        JMenu shapeMenu = new JMenu("Rectangle");

        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem rectangle = new JRadioButtonMenuItem("Rectangle");

        AppCommand shapeCommand = new SwitchShape(menuState, ShapeType.RECTANGLE, rectangle);
        rectangle.addActionListener(new CommandActionListener(shapeCommand));
        shapeMenu.add(rectangle);
        group.add(rectangle);

        JRadioButtonMenuItem ellipse = new JRadioButtonMenuItem("Ellipse");

        AppCommand ellipseCommand = new SwitchShape(menuState, ShapeType.ELLIPSE, ellipse);
        ellipse.addActionListener(new CommandActionListener(ellipseCommand));
        shapeMenu.setText("Switch shape");
        shapeMenu.add(ellipse);
        group.add(ellipse);

        return shapeMenu;
    }

    private JMenu createColorMenu() {

        JMenu colorMenu = new JMenu("Red");

        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem red = new JRadioButtonMenuItem("Red");
        red.addActionListener(e -> {
            menuState.setColor(Color.RED);
            colorMenu.setText("Red");
        });
        colorMenu.add(red);
        group.add(red);

        JRadioButtonMenuItem blue = new JRadioButtonMenuItem("Blue");
        blue.addActionListener(e -> {
            menuState.setColor(Color.BLUE);
            colorMenu.setText("Blue");
        });
        colorMenu.add(blue);
        group.add(blue);

        JRadioButtonMenuItem green = new JRadioButtonMenuItem("Green");
        green.addActionListener(e -> {
            menuState.setColor(Color.GREEN);
            colorMenu.setText("Green");
        });
        colorMenu.add(green);
        group.add(green);

        JRadioButtonMenuItem black = new JRadioButtonMenuItem("Black");
        black.addActionListener(e -> {
            menuState.setColor(Color.BLACK);
            colorMenu.setText("Black");
        });
        colorMenu.add(black);
        group.add(black);

        JRadioButtonMenuItem white = new JRadioButtonMenuItem("White");
        white.addActionListener(e -> {
            menuState.setColor(Color.WHITE);
            colorMenu.setText("White");
        });
        colorMenu.add(white);
        group.add(white);

        return colorMenu;
    }

    private JMenu createFillMenu() {

        JMenu fillMenu = new JMenu("Fill");

        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem fill = new JRadioButtonMenuItem("Fill");
        fill.addActionListener(e -> {
            menuState.setFill(new Fill());
            fillMenu.setText("Fill");
        });
        fillMenu.add(fill);
        group.add(fill);

        JRadioButtonMenuItem noFill = new JRadioButtonMenuItem("NoFill");
        noFill.addActionListener(e -> {
            menuState.setFill(new NoFill());
            fillMenu.setText("NoFill");
        });
        fillMenu.add(noFill);
        group.add(noFill);

        return fillMenu;
    }

    private JMenu createActionMenu() {

        JMenu actionMenu = new JMenu("Draw");

        ButtonGroup group = new ButtonGroup();
        JRadioButtonMenuItem draw = new JRadioButtonMenuItem("Draw");
        draw.addActionListener(e -> {
            menuState.setAppAction(new ActionDraw(menuState.getSelectedShape(), model));
            actionMenu.setText("Draw");
        });
        actionMenu.add(draw);
        group.add(draw);

        JRadioButtonMenuItem move = new JRadioButtonMenuItem("Move");
        move.addActionListener(e -> {
            menuState.setAppAction(new ActionMove(menuState.getSelectedShape(), model));
            actionMenu.setText("Move");
        });
        actionMenu.add(move);
        group.add(move);

        return actionMenu;
    }

    public void createShape(Point2D point) {
        menuState.createShape(point);
    }

    public void stretchShape(Point2D point) {
        menuState.stretchShape(point);
    }
}