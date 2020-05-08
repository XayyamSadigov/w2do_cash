package main;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class Position extends Item {

    public Position(JToggleButton btn) {
        this.name = btn.getText();
        this.btn = btn;

        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHovered(evt);
            }
        });

        btn.addActionListener(this::btnClicked);
    }

    private void btnClicked(java.awt.event.ActionEvent evt) {
        db.position = db.getPosition(evt.getActionCommand());
        View.selected(db.position);

        if (db.hand != null && db.position != null) {
            db.mainFrame.setTitle("w2do | " + db.hand.name + " on " + " " + db.position.name);
        }

        db.hands.forEach((hand) -> {
            if (Random.get(db.getOptions(hand, db.position)).equals("RAISE")) {
                View.changeColor(hand, Color.GREEN);
                View.updateToolTip(hand, "RAISE");
            } else {
                View.changeColor(hand, Color.GRAY);
                View.updateToolTip(hand, "FOLD");
            }
        });

        if (null != db.hand && db.position != null) {
            db.positions.forEach((position) -> {
                if (db.position.name.equals(position.name)) {
                    View.changeColor(position, Color.GRAY);
                    View.updateToolTip(position, null);
                } else {
                    String result = Random.get(db.getOptions(db.hand, db.position, position));
                    Log.debug(result, db.hand.name, db.position.name, position.name);

                    switch (result) {
                        case "RAISE":
                            View.changeColor(position, Color.GREEN);
                            View.updateToolTip(position, "RAISE");
                            break;
                        case "CALL":
                            View.changeColor(position, Color.YELLOW);
                            View.updateToolTip(position, "CALL");
                            break;
                        default:
                            View.changeColor(position, Color.RED);
                            View.updateToolTip(position, "FOLD");
                            break;
                    }
                }
            });
        }

    }

    private void btnHovered(java.awt.event.MouseEvent evt) {

    }

    @Override
    public String toString() {
        return name;
    }

}
