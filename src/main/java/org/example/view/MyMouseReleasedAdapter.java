package org.example.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public interface MyMouseReleasedAdapter extends MouseListener {
    @Override
    default void mouseClicked(MouseEvent e) {
    }

    @Override
    void mouseReleased(MouseEvent e);

    @Override
    default void mouseEntered(MouseEvent e) {

    }

    @Override
    default void mouseExited(MouseEvent e) {

    }

    @Override
    default void mousePressed(MouseEvent e){

    }
}
