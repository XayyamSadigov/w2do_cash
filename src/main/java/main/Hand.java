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
            } else {
                String result = Random.get(db.getOptions(db.hand, db.position, position));
                Log.debug(result, db.hand.name, db.position.name, position.name);

                if (result.equals("RAISE")) {
                    View.changeColor(position, Color.GREEN);
                } else if (result.equals("CALL")) {
                    View.changeColor(position, Color.YELLOW);
                } else {
                    View.changeColor(position, Color.RED);
                }
            }
        }

//        for (Position position : db.positions) {
//            db.colorPositionsOn(db.position, position);
//        }
//
//        System.out.println(name + " btnClicked");
//        db.handBtnClicked(getHand(evt.getActionCommand()));
//        db.rfi = "";
//        try {
//            String hand = evt.getActionCommand();
//            if (db.position.btn == null) {
//                db.result = "Select the position !";
//                return;
//            }
//            db.rfi += hand + " from " + db.position.name + "\n";
//            Record handObj = db.records.stream().filter(hh -> hh.result.equals("RFI") && hh.position.equals(db.position.name)).findFirst().get();
//            db.rfi += handObj.result + " : " + handObj.action + " (" + handObj.percentage + ")" + "\n";
//            db.result = db.rfi;
//        } catch (Exception e) {
//            db.result = "";
//        }
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
