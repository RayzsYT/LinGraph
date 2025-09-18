package de.rayzs.lingraph.menu.objects;

import de.rayzs.lingraph.menu.Camera;
import de.rayzs.lingraph.menu.Menu;
import java.awt.*;

public class Line extends ObjectAbstract {

    private int toX, toY;
    private int slopeX, slopeY;

    public Line(int x, int y, int toX, int toY) {
        super(x, y);

        this.toX = toX;
        this.toY = toY;
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        final int x = this.x + Menu.getBiasX() - Camera.getX();
        final int y = this.y + Menu.getBiasY() - Camera.getY();

        final int toX = this.toX + Menu.getBiasX() - Camera.getX();
        final int toY = this.toY + Menu.getBiasY() - Camera.getY();

        g.drawLine(x + 1, y + 1, toX + 1, toY + 1);
    }

    public int getSlopeX() {
        return slopeX;
    }

    public int getSlopeY() {
        return slopeY;
    }

    public void setToX(int x) {
        toX = x;
        calcSlope();
    }

    public void setToY(int y) {
        toY = y;
        calcSlope();
    }

    public void moveToX(int step) {
        toX += step;
        calcSlope();
    }

    public void moveToY(int step) {
        toY += step;
        calcSlope();
    }

    public void calcSlope() {
        slopeX = toX - x;
        slopeY = toY - y;
    }
}
