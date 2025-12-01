package org.example.view;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public interface MyMouseDraggedAdapter extends MouseMotionListener {
    @Override
    void mouseDragged(MouseEvent e);

    @Override
    default void mouseMoved(MouseEvent e) {

    }
}
