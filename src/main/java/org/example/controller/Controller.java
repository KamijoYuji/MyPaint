package org.example.controller;

import org.example.controller.action.ActionMove;
import org.example.controller.action.AppAction;
import org.example.controller.action.menu.MenuCreator;

import org.example.controller.action.state.UndoMachine;
import org.example.model.Model;
import org.example.view.MyFrame;
import org.example.view.MyPanel;

import java.awt.*;
import java.awt.geom.Point2D;

public class Controller {
    private static Controller instance;
    private final Model model;
    private final MenuCreator menuCreator;
    private AppAction currentAction;
    private final UndoMachine undoMachine;

    public static Controller getInstance() {
        synchronized (Controller.class) {
            if (instance == null) {
                instance = new Controller();
            }
            return instance;
        }
    }

    public MenuCreator getMenuCreator() {
        return menuCreator;
    }

    private Controller() {
        model = new Model();
        MyPanel panel = new MyPanel(this);
        MyFrame frame = new MyFrame();

        menuCreator = MenuCreator.getInstance(this);
        menuCreator.setModel(model);
        undoMachine = new UndoMachine();
        menuCreator.setUndoMachine(undoMachine);
        frame.setJMenuBar(menuCreator.createMenuBar());
        frame.add(menuCreator.createToolBar(), BorderLayout.SOUTH);

        frame.setPanel(panel);
        model.addObserver(panel);
        frame.revalidate();
        currentAction = menuCreator.getMenuState().getAppAction();
    }

    public void stretchShape(Point2D point){
        menuCreator.stretchShape(point);
    }

    public void createShape(Point2D point){
        menuCreator.createShape(point);
    }

    public void setDrawingAction() {
        currentAction = menuCreator.getMenuState().getAppAction();
    }

    public void setMovingAction() {
        currentAction = menuCreator.getMenuState().getAppAction();
    }

    public void finishDrawing(Point2D point) {
        if (currentAction instanceof ActionMove) {
            ActionMove moveAction = (ActionMove) currentAction;
            if (moveAction.getTotalDeltaX() != 0 || moveAction.getTotalDeltaY() != 0) {
                AppAction completedAction = currentAction.cloneAction();
                if(completedAction != null)
                    undoMachine.add(completedAction);
            }
        } else undoMachine.add(currentAction.cloneAction());

        updateUndoRedoButtons();
    }

    public void draw(Graphics2D g2) {
        model.draw(g2);
    }

    public void setCurrentColor(Color color) {
        this.menuCreator.setColor(color);
    }

    public void updateUndoRedoButtons() {
        undoMachine.updateButtons();
        if (menuCreator != null) {
            menuCreator.updateMenuButtons();
        }
    }

    public Model getModel() {
        return model;
    }
}