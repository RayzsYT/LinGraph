package de.rayzs.lingraph;

import de.rayzs.lingraph.menu.Function;
import de.rayzs.lingraph.menu.Menu;

public class Bootstrap {

    public static void main(String[] args) {
        Menu.init();

        Menu.setGraphInfo(new Function() {
            @Override
            public int calc(int x) {
                return (int) (Math.pow(x, 3));
            }
        }, 1000);

        Menu.drawGraph();
    }
}
