package org.example.model;

import org.example.model.fill.FillBehavior;

import java.awt.*;

public class ShapeFactory {
    public static MyShape createShape(Color color, ShapeType type, FillBehavior fillBehavior) {
        return type.create(color, fillBehavior);
    }
}
