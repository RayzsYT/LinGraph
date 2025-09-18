package de.rayzs.lingraph.menu;

public class Camera {

    protected Camera() {}


    protected static int x = 0, y = 0;
    protected static int speed = 20;

    protected static int scale = 1;


    public static void increaseScale() {
        scale = Math.min(20, scale + 1);
    }

    public static void decreaseScale() {
        scale = Math.max(1, scale - 1);
    }

    public static int getScale() {
        return scale;
    }


    public static void setSpeed(int speed) {
        Camera.speed = speed;
    }

    public static void moveX(int step) {
        x += step * speed;
    }

    public static void moveY(int step) {
        y += step * speed;
    }

    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }

    public static int getSpeed() {
        return speed;
    }

    public static boolean inSight(int x, int y) {
        return x >= 0 && x < Menu.getWidth() && y >= 0 && y < Menu.getHeight();
    }
}
