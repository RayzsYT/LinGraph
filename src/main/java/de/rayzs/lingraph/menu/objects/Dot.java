package de.rayzs.lingraph.menu.objects;

import de.rayzs.lingraph.menu.Camera;
import de.rayzs.lingraph.menu.Menu;
import java.awt.*;

public class Dot extends ObjectAbstract {

    public Dot(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        final int x = Menu.getBiasX() + (this.x - Camera.getX());
        final int y = Menu.getBiasY() + (this.y - Camera.getY());

        if (Camera.inSight(x, y)) {
            g.fillOval(x, y, 3, 3);
        }

    }
}
