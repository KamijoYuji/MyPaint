package org.example.model.shape.factory;

import org.example.model.shape.MyShape;
import org.example.model.shape.fill.FillBehavior;

import java.awt.*;

public class ShapeFactory {
    public static MyShape createShape(Color color, ShapeType type, FillBehavior fillBehavior) {
        return type.create(color, fillBehavior);
    }
}
