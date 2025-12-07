package org.example.view;

import org.example.controller.Controller;
import org.example.model.MyObserver;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;


public class MyPanel extends JPanel implements MyObserver {
    private final Controller controller;

    @Override
    public void changed() {
        repaint();
        controller.updateUndoRedoButtons();
    }

    public MyPanel(Controller controller) {
        this.controller = controller;
        addMouseListener((MyMousePressedAdapter) arg0 ->
                controller.createShape(arg0.getPoint()));
        addMouseListener((MyMouseReleasedAdapter) arg0 -> {
            controller.finishDrawing(arg0.getPoint());
            controller.updateUndoRedoButtons();
        });
                addMouseMotionListener((MyMouseDraggedAdapter) arg0 ->
                        controller.stretchShape(arg0.getPoint()));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        controller.draw(g2);
    }
}
