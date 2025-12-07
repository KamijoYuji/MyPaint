package org.example.controller.action.menu;

import org.example.controller.Controller;
import org.example.controller.action.ActionDraw;
import org.example.controller.action.ActionMove;
import org.example.controller.action.state.UndoMachine;
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
    private Controller mainController;
    private Model model;
    private UndoMachine undoMachine;

    // Храним ссылки на элементы меню для обновления
    private CommandActionListener undoToolbarAction;
    private CommandActionListener redoToolbarAction;


    public static MenuCreator getInstance(Controller controller) {
        if (instance == null)
            instance = new MenuCreator(controller);
        return instance;
    }

    public MenuState getMenuState() {
        return menuState;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    private MenuCreator(Controller controller) {
        mainController = controller;
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

    public void updateMenuButtons() {
        if (undoToolbarAction != null) {
            undoToolbarAction.setEnabled(undoMachine != null && undoMachine.isEnableUndo());
        }
        if (redoToolbarAction != null) {
            redoToolbarAction.setEnabled(undoMachine != null && undoMachine.isEnableRedo());
        }
    }

    private ArrayList<Action> createToolBarItems() {
        ArrayList<Action> menuItems = new ArrayList<>();

        // Кнопка выбора цвета
        URL colorUrl = getClass().getClassLoader().getResource("ico/color_16x16.png");
        ImageIcon colorIco = colorUrl == null ? null : new ImageIcon(colorUrl);
        JRadioButtonMenuItem rgbButton = new JRadioButtonMenuItem(colorIco);
        AppCommand colorCommand = new SwitchColor(menuState,false, null, rgbButton, mainController);
        menuItems.add(new CommandActionListener("Цвет", colorIco, colorCommand));

        // Кнопка режима рисования
        URL drawUrl = getClass().getClassLoader().getResource("ico/draw_16x16.png");
        ImageIcon drawIco = drawUrl == null ? null : new ImageIcon(drawUrl);
        AppCommand drawToolCommand = new SwitchAction(mainController, new ActionDraw(menuState.getSelectedShape(), model));
        CommandActionListener drawToolAction = new CommandActionListener("Рисование", drawIco, drawToolCommand);
        menuItems.add(drawToolAction);

        // Кнопка режима перемещения
        URL moveUrl = getClass().getClassLoader().getResource("ico/move_16x16.png");
        ImageIcon moveIco = moveUrl == null ? null : new ImageIcon(moveUrl);
        AppCommand moveToolCommand = new SwitchAction(mainController, new ActionMove(menuState.getSelectedShape(), model));
        CommandActionListener moveToolAction = new CommandActionListener("Перемещение", moveIco, moveToolCommand);
        menuItems.add(moveToolAction);

        // Кнопка заливки
        URL fillUrl = getClass().getClassLoader().getResource("ico/fill_16x16.png");
        ImageIcon fillIco = fillUrl == null ? null : new ImageIcon(fillUrl);
        AppCommand fillToolCommand = new SwitchFill(menuState, new Fill());
        CommandActionListener fillToolAction = new CommandActionListener("С заливкой", fillIco, fillToolCommand);
        menuItems.add(fillToolAction);

        // Кнопка без заливки
        URL noFillUrl = getClass().getClassLoader().getResource("ico/no_fill_16x16.png");
        ImageIcon noFillIco = noFillUrl == null ? null : new ImageIcon(noFillUrl);
        AppCommand noFillToolCommand = new SwitchFill(menuState, new NoFill());
        CommandActionListener noFillToolAction = new CommandActionListener("Без заливки",noFillIco, noFillToolCommand);
        menuItems.add(noFillToolAction);

        // Кнопка Undo
        URL undoToolbarUrl = getClass().getClassLoader().getResource("ico/undo_16x16.png");
        ImageIcon undoToolbarIco = undoToolbarUrl == null ? null : new ImageIcon(undoToolbarUrl);
        AppCommand undoToolbarCommand = new SwitchUndo(undoMachine);
        undoToolbarAction = new CommandActionListener("Отменить", undoToolbarIco, undoToolbarCommand);
        undoToolbarAction.setEnabled(false); // Изначально выключена
        menuItems.add(undoToolbarAction);

        // Кнопка Redo
        URL redoToolbarUrl = getClass().getClassLoader().getResource("ico/redo_16x16.png");
        ImageIcon redoToolbarIco = redoToolbarUrl == null ? null : new ImageIcon(redoToolbarUrl);
        AppCommand redoToolbarCommand = new SwitchRedo(undoMachine);
        redoToolbarAction = new CommandActionListener("Повторить", redoToolbarIco, redoToolbarCommand);
        redoToolbarAction.setEnabled(false); // Изначально выключена
        menuItems.add(redoToolbarAction);

        // Передаем слушатели в UndoMachine
        if (undoMachine != null) {
            undoMachine.setUndoActionListener(undoToolbarAction);
            undoMachine.setRedoActionListener(redoToolbarAction);
        }

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
            mainController.setDrawingAction();
        });
        actionMenu.add(draw);
        group.add(draw);

        JRadioButtonMenuItem move = new JRadioButtonMenuItem("Move");
        move.addActionListener(e -> {
            menuState.setAppAction(new ActionMove(menuState.getSelectedShape(), model));
            actionMenu.setText("Move");
            mainController.setMovingAction();
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

    public void setColor(Color color) {
        menuState.setColor(color);
    }

    public void setUndoMachine(UndoMachine undoMachine) {
        this.undoMachine = undoMachine;
    }
}