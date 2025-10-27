package org.example.model;

import org.example.model.fill.FillBehavior;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

public enum ShapeType {
    ELLIPSE{
        @Override
        MyShape create(Color color) {
            return new MyShape(color, new Ellipse2D.Double());
        }
    },
    RECTANGLE{
        @Override
        MyShape create(Color color) {
            return new MyShape(color, new Rectangle2D.Double());
        }
    };

    abstract MyShape create(Color color);
}
