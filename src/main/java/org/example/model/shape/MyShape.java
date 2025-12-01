package org.example.model.shape;

import org.example.model.shape.fill.Fill;
import org.example.model.shape.fill.FillBehavior;
import org.example.model.shape.fill.NoFill;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

public class MyShape implements Cloneable {
    private final Color color;
    private RectangularShape shape;
    private final FillBehavior fb;

    public MyShape(Color color, RectangularShape shape, FillBehavior fb) {
        this.color = color;
        this.shape = shape;
        this.fb = fb;
        this.fb.setShape(shape);
        this.fb.setColor(color);
    }

    public void setShape(RectangularShape shape) {
        this.shape = shape;
    }

    public void setFrame(Point2D x, Point2D y) {
        shape.setFrameFromDiagonal(x, y);
    }

    public void draw(Graphics2D g) {
        fb.draw(g);
    }

    public MyShape clone() {
        RectangularShape shape1 = (RectangularShape) this.shape.clone();

        FillBehavior fillBehavior;
        if (this.fb instanceof Fill)
            fillBehavior = new Fill();
        else
            fillBehavior = new NoFill();

        fillBehavior.setColor(this.color);
        fillBehavior.setShape(shape1);

        return new MyShape(this.color, shape1, fillBehavior);
    }

    public RectangularShape getShape() {
        return shape;
    }
}
