package main;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;

public class View {

    public static void changeColor(Item item, Color color) {
        item.btn.setBackground(color);
        item.btn.setContentAreaFilled(false);
        item.btn.setOpaque(true);
    }

    public static void selected(Item item) {
        if (item instanceof Position) {
            db.positions.forEach((i) -> {
                if (item.name.equals(i.name)) {
                    i.btn.setSelected(true);
                    i.btn.setFont(new Font("Tahoma", Font.BOLD, 18));
                    i.btn.setBorder(BorderFactory.createSoftBevelBorder(1));
                } else {
                    i.btn.setSelected(false);
                    i.btn.setFont(new Font("Tahoma", Font.PLAIN, 14));
                    i.btn.setBorder(BorderFactory.createSoftBevelBorder(0));
                }
            });
        } else {
            db.hands.forEach((i) -> {
                if (item.name.equals(i.name)) {
                    i.btn.setSelected(true);
                    i.btn.setFont(new Font("Tahoma", Font.BOLD, 16));
                    i.btn.setBorder(BorderFactory.createSoftBevelBorder(1));
                } else {
                    i.btn.setSelected(false);
                    i.btn.setFont(new Font("Tahoma", Font.PLAIN, 14));
                    i.btn.setBorder(BorderFactory.createSoftBevelBorder(0));
                }
            });
        }
    }

    public static void updateActions(Position position, String actionType, String actionValue) {
        position.actionType.setText(actionType);
        position.actionValue.setText(actionValue);
    }

}
