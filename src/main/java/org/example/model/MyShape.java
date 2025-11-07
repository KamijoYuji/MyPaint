package org.example.model;

import org.example.model.fill.Fill;
import org.example.model.fill.FillBehavior;
import org.example.model.fill.NoFill;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

public class MyShape implements Cloneable{
    private final Color color;
    private RectangularShape shape;
    private FillBehavior fb;

    public MyShape(RectangularShape shape) {
        this.shape = shape;
        color = Color.GRAY;
        fb = new Fill();
        fb.setColor(color);
        fb.setShape(shape);
    }

    public MyShape(Color color, RectangularShape shape) {
        this.shape = shape;
        this.color = color;
        fb = new Fill();
        fb.setColor(color);
        fb.setShape(shape);
    }

    public MyShape() {
        color = Color.BLUE;
        shape = new Rectangle2D.Double();
        fb = new Fill();
        fb.setColor(color);
        fb.setShape(shape);
    }

    public MyShape(Color color, RectangularShape shape, FillBehavior fb) {
        this.color = color;
        this.shape = shape;
        this.fb = fb;
        this.fb.setShape(shape);
        this.fb.setColor(color);
    }

    public void setFb(FillBehavior fb) {
        this.fb = fb;
        fb.setShape(shape);
        fb.setColor(color);
    }

    public void setShape(RectangularShape shape) {
        this.shape = shape;
    }

    public void setFrame(Point2D x, Point2D y) {
        shape.setFrameFromDiagonal(x, y);
    }

    void draw(Graphics2D g) {
        fb.draw(g);

    }

    public MyShape clone() {
        RectangularShape shape1 = (RectangularShape) this.shape.clone();

        FillBehavior fillBehavior;
        if(this.fb instanceof Fill)
            fillBehavior = new Fill();
        else
            fillBehavior = new NoFill();

        fillBehavior.setColor(this.color);
        fillBehavior.setShape(shape1);

        MyShape shape2 = new MyShape(this.color, shape1, fillBehavior);

        return shape2;
    }
}
