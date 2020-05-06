package main;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JToggleButton;
import static main.db.records;
import static main.db.hand;

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
        }

    }

    private void btnHovered(java.awt.event.MouseEvent evt) {
//        if (db.rfi == null) {
//            return;
//        }
//
//        db.vs = "";
//        try {
//            String vs = evt.getComponent().getAccessibleContext().getAccessibleName();
//
//            List<Record> handObjs = db.records.stream().filter(record -> record.cards.equals(db.hand.name) && record.position.equals(db.position.name) && record.vs.equals(vs)).collect(Collectors.toList());
//            for (Record handObj : handObjs) {
//                if (handObj != null) {
//                    db.vs += "vs " + vs + " -> " + handObj.result + " : " + handObj.action + " (" + handObj.percentage + ")" + "\n";
//                }
//            }
//            db.result = db.rfi + db.vs;
//        } catch (Exception e) {
//            db.result = db.rfi + db.vs;
//        }
//
//        System.out.println("result: " + db.result);

    }

//    public static void colorPositionsOn(Position position, Position vs) {
//        try {
//            System.out.println("colorPositionsOn");
//            System.out.println("positon: " + position);
//            System.out.println("vs: " + vs);
//            System.out.println("records: " + records);
//            //vs RFI
//            Map<String, Integer> options = new HashMap<>();
//
//            for (Record record : records.stream().filter(record -> record.cards.equals(hand.name)
//                    && record.vs.equals(vs.name)
//                    && record.result.equals("vs RFI")
//                    && record.position.equals(position.getName())).collect(Collectors.toList())) {
//                options.put(record.action, record.percentage);
//            }
//            if (!vs.name.equals(position.name)) {
//                String currentAction = Random.get(options);
//                System.out.println("vs RFI | " + position.name + " - " + vs.name + " : " + currentAction);
//                if (currentAction.equals("RAISE")) {
//                    vs.setStatus(Color.GREEN);
//                } else if (currentAction.equals("CHECK")) {
//                    vs.setStatus(Color.YELLOW);
//                } else if (currentAction.equals("FOLD")) {
//                    vs.setStatus(Color.RED);
//                }
//
//                //RFI vs 3-Bet
//                options = new HashMap<>();
//                for (Record record : records.stream().filter(record -> record.cards.equals(hand.name)
//                        && record.vs.equals(vs.name)
//                        && record.result.equals("RFI vs 3-Bet")
//                        && record.position.equals(position.getName())).collect(Collectors.toList())) {
//                    options.put(record.action, record.percentage);
//                }
//
//                currentAction = Random.get(options);
//                System.out.println("RFI vs 3-Bet | " + position.name + " - " + vs.name + " : " + currentAction);
//                if (currentAction.equals("RAISE")) {
//                    vs.setStatus(Color.GREEN);
//                } else if (currentAction.equals("CHECK")) {
//                    vs.setStatus(Color.YELLOW);
//                } else if (currentAction.equals("FOLD")) {
//                    vs.setStatus(Color.RED);
//                }
//            } else {
//                vs.setStatus(Color.GRAY);
//            }
//
//        } catch (Exception e) {
//            System.out.println("ERROR colorPositionsOn");
//            System.out.println("positon: " + position);
//            System.out.println("vs: " + vs);
//            System.out.println("records: " + records);
//            e.printStackTrace();
//        }
//    }
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
