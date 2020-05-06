package main;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class Position extends Item {

    JTextField actionType;
    JTextField actionValue;

    public Position(JToggleButton btn, JTextField actionType, JTextField actionValue) {
        this.name = btn.getText();
        this.btn = btn;
        this.actionType = actionType;
        this.actionValue = actionValue;

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
        System.out.println("Hand is: " + db.hand + " " + db.position);
        View.selected(db.position);

        db.hands.forEach((hand) -> {
            if (Random.get(db.getOptions(hand, db.position)).equals("RAISE")) {
                View.changeColor(hand, Color.GREEN);
            } else {
                View.changeColor(hand, Color.GRAY);
            }
        });

        if (null != db.hand && db.position != null) {
            db.positions.forEach((position) -> {
                if (db.position.name.equals(position.name)) {
                    View.changeColor(position, Color.GRAY);
                    View.updateActions(position, null, null);
                } else {
                    String result = Random.get(db.getOptions(db.hand, db.position, position));
                    Log.debug(result, db.hand.name, db.position.name, position.name);

                    switch (result) {
                        case "RAISE":
                            View.changeColor(position, Color.GREEN);
                            View.updateActions(position, db.getOptionResult(db.hand, db.position, position), "RAISE");
                            break;
                        case "CALL":
                            View.changeColor(position, Color.YELLOW);
                            View.updateActions(position, db.getOptionResult(db.hand, db.position, position), "CALL");
                            break;
                        default:
                            View.changeColor(position, Color.RED);
                            View.updateActions(position, db.getOptionResult(db.hand, db.position, position), "FOLD");
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
