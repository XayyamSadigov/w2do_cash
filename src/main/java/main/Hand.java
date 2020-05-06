package main;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JToggleButton;

public class Hand extends Item {

    List<Record> records;
    String message;

    public Hand(JToggleButton btn) {
        this.name = btn.getText();
        this.btn = btn;
        this.records = db.records.stream().filter(hand -> hand.cards.equals(name)).collect(Collectors.toList());

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                btnClicked(evt);
            }
        });

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
        System.out.println("Hand is: " + db.hand + " " + db.position);
        View.selected(db.hand);

        System.out.println(db.positions);

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
