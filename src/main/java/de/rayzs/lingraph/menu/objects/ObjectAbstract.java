package de.rayzs.lingraph.menu.objects;

import java.awt.*;

public class ObjectAbstract {

    protected Color color = Color.black;
    protected int x, y;

    ObjectAbstract(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public ObjectAbstract setColor(Color color) {
        this.color = color;
        return this;
    }

    public void draw(Graphics g) {
        g.setColor(color);
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void moveX(int step) {
        x += step;
    }

    public void moveY(int step) {
        y += step;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
