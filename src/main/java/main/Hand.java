package main;

import java.awt.Color;
import javax.swing.JToggleButton;

public class Hand extends Item {

    public Hand(JToggleButton btn) {
        this.name = btn.getText();
        this.btn = btn;
        btn.addActionListener(this::btnClicked);
    }

    public Hand getHand(String name) {
        for (Hand hand : db.hands) {
            if (hand.name.equals(name)) {
                return hand;
            }
        }
        return null;
    }

    private void btnClicked(java.awt.event.ActionEvent evt) {
        db.hand = db.getHand(evt.getActionCommand());
        View.selected(db.hand);

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

    @Override
    public String toString() {
        return name;
    }

}
