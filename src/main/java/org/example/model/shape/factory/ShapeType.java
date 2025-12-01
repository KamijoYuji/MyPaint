package org.example.model.shape.factory;

import org.example.model.shape.MyShape;
import org.example.model.shape.fill.FillBehavior;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public enum ShapeType {
    ELLIPSE {
        @Override
        MyShape create(Color color, FillBehavior fillBehavior) {
            return new MyShape(color, new Ellipse2D.Double(), fillBehavior);
        }
    },
    RECTANGLE {
        @Override
        MyShape create(Color color, FillBehavior fillBehavior) {
            return new MyShape(color, new Rectangle2D.Double(), fillBehavior);
        }
    };

    abstract MyShape create(Color color, FillBehavior fillBehavior);
}
