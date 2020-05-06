package main;

import java.awt.Color;
import javax.swing.JToggleButton;

public class Item {

    String name;
    JToggleButton btn;
    Color color;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JToggleButton getBtn() {
        return btn;
    }

    public void setBtn(JToggleButton btn) {
        this.btn = btn;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
