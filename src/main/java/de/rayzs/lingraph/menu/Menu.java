package de.rayzs.lingraph.menu;

import de.rayzs.lingraph.menu.objects.Dot;
import de.rayzs.lingraph.menu.objects.Line;
import de.rayzs.lingraph.menu.objects.ObjectAbstract;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Menu {

    private static final Frame frame;

    private static Function func;
    private static int iterations;

    static {
        frame = new Frame();
        iterations = 5000;
        func = new Function() {
            @Override
            public int calc(int x) {
                return x;
            }
        };
    }

    protected Menu() {}

    public static void init() {}

    public static int getWidth() {
        return frame.getWidth();
    }

    public static int getHeight() {
        return frame.getHeight();
    }

    public static int getBiasX() {
        return frame.getBiasX();
    }

    public static int getBiasY() {
        return frame.getBiasY();
    }

    public static void addObject(final ObjectAbstract object) {
        frame.addObject(object);
    }

    public static void addTmpObject(final ObjectAbstract object) {
        frame.addTmpObject(object);
    }

    public static void setGraphInfo(Function func, int iterations) {
        Menu.iterations = iterations;
        Menu.func = func;
    }

    public static void drawGraph() {
        frame.clearTmpObjects();
        Dot priorDot = null;

        for (int i = -1 * iterations; i < iterations; i++) {
            final int x = i * Camera.getScale();
            final int y = func.calc(i) * Camera.getScale() * -1;
            final Dot dot = new Dot(x, y);

            if (priorDot != null) {
                final ObjectAbstract line = new Line(priorDot.getX(), priorDot.getY(), x, y).setColor(Color.blue);
                Menu.addTmpObject(line);
            }

            priorDot = dot;
        }
    }


    protected static class Frame extends JFrame {

        private final int VAL = 1000000;


        private final ObjectAbstract VERTICAL_GRAPH_LINE = new Line(0, -1 * VAL, 0, VAL);
        private final ObjectAbstract HORIZONTAL_GRAPH_LINE = new Line(-1 * VAL, 0, VAL, 0);


        private int width = 800, height = 800;
        private int biasX = width / 2, biasY = height / 2;

        public void resize(int width, int height) {
            this.width = width;
            this.height = height;

            this.biasX = width / 2;
            this.biasY = height / 2;
        }

        private final List<ObjectAbstract> objects = new ArrayList<>();
        private final List<ObjectAbstract> tmpObjects = new ArrayList<>();

        public Frame() {
            updateTitle();
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(width, height);

            Paint paint = new Paint();
            paint.setSize(width, height);

            add(paint);
            pack();

            setResizable(false);
            setLocationRelativeTo(null);
            setVisible(true);

            addObject(HORIZONTAL_GRAPH_LINE);
            addObject(VERTICAL_GRAPH_LINE);

            paint.addMouseWheelListener(new MouseAdapter() {
                @Override
                public void mouseWheelMoved(MouseWheelEvent e) {
                    if (e.getWheelRotation() == -1) {
                        Camera.increaseScale();
                    } if (e.getWheelRotation() == 1) {
                        Camera.decreaseScale();
                    }

                    updateTitle();
                    drawGraph();
                    paint.repaint();
                }
            });

            paint.addKeyListener(new KeyAdapter() {

                @Override
                public void keyPressed(KeyEvent e) {
                    char c = e.getKeyChar();

                    switch (c) {
                        case 'w' -> { Camera.moveY(-1); }
                        case 's' -> { Camera.moveY(1); }
                        case 'a' -> { Camera.moveX(-1); }
                        case 'd' -> { Camera.moveX(1); }
                        default -> {
                            return;
                        }
                    }

                    updateTitle();
                    paint.repaint();

                }
            });

            paint.repaint();
        }

        public void updateTitle() {
            setTitle("LinGraph (x=" + Camera.getX() + ", y=" + Camera.getY() + ", scale=" + Camera.getScale() + ")");
        }

        @Override
        public int getWidth() {
            return width;
        }

        @Override
        public int getHeight() {
            return height;
        }

        public int getBiasX() {
            return biasX;
        }

        public int getBiasY() {
            return biasY;
        }

        public void addObject(final ObjectAbstract object) {
            objects.add(object);
        }

        public void addTmpObject(final ObjectAbstract object) {
            tmpObjects.add(object);
        }

        public void clearTmpObjects() {
            tmpObjects.clear();
        }

        protected class Paint extends Canvas {

            @Override
            public void paint(Graphics g) {
                g.setColor(Color.red);
                g.fillRect(biasX, biasY, 3, 3);

                for (ObjectAbstract object : objects) {
                    object.draw(g);
                }

                for (ObjectAbstract object : tmpObjects) {
                    object.draw(g);
                }
            }

            @Override
            public void repaint() {
                super.repaint();
            }
        }
    }
}
