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

        for (Hand hand : db.hands) {
            if (Random.get(db.getOptions(hand, db.position)).equals("RAISE")) {
                View.changeColor(hand, Color.GREEN);
            } else {
                View.changeColor(hand, Color.GRAY);
            }
        }

        if (null != db.hand && db.position != null) {
            for (Position position : db.positions) {

                if (db.position.name.equals(position.name)) {
                    View.changeColor(position, Color.GRAY);
                    View.updateActions(position, null, null);
                } else {
                    String result = Random.get(db.getOptions(db.hand, db.position, position));
                    Log.debug(result, db.hand.name, db.position.name, position.name);

                    if (result.equals("RAISE")) {
                        View.changeColor(position, Color.GREEN);
                        View.updateActions(position, db.getOptionResult(db.hand, db.position, position), "RAISE");
                    } else if (result.equals("CALL")) {
                        View.changeColor(position, Color.YELLOW);
                        View.updateActions(position, db.getOptionResult(db.hand, db.position, position), "CALL");
                    } else {
                        View.changeColor(position, Color.RED);
                        View.updateActions(position, db.getOptionResult(db.hand, db.position, position), "FOLD");
                    }
                }
            }
        }

    }

    private void btnHovered(java.awt.event.MouseEvent evt) {

    }

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

    @Override
    public String toString() {
        return name;
    }

}
