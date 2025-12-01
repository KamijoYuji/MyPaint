package org.example.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public interface MyMousePressedAdapter extends MouseListener {
    @Override
    default void mouseClicked(MouseEvent e) {
    }

    @Override
    default void mouseReleased(MouseEvent e) {

    }

    @Override
    default void mouseEntered(MouseEvent e) {

    }

    @Override
    default void mouseExited(MouseEvent e) {

    }

    @Override
    void mousePressed(MouseEvent e);
}
