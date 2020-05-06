package main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class db {

    public static final List<Record> records = Database.getData();
    public static List<Hand> hands;
    public static List<Position> positions;

    public static Hand hand;
    public static Position position;

    // info
    public static String rfi;
    public static String vs;
    public static String result;

    public static List<Record> getRfis() {
        return records.stream().filter(record -> record.result.equals("RFI")).collect(Collectors.toList());
    }

    public static List<Record> getRfis(Position position) {
        return records.stream().filter(record -> record.result.equals("RFI") && record.position.equals(position.name)).collect(Collectors.toList());
    }

    public static Position getPosition(String name) {
        return positions.stream().filter(i -> i.name.equals(name)).findFirst().get();
    }

    public static Hand getHand(String name) {
        return hands.stream().filter(i -> i.name.equals(name)).findFirst().get();
    }

    public static Map<String, Integer> getOptions(Hand hand, Position position, Position vs) {
        Map<String, Integer> options = new HashMap<>();
        try {
            for (Record record : records) {
                if (record.cards.equals(hand.name)
                        && record.position.equals(position.name)
                        && record.vs.equals(vs.name)) {
                    options.put(record.action, record.percentage);
                }
            }
            return options;
        } catch (Exception e) {
            return null;
        }
    }

    public static String getOptionResult(Hand hand, Position position, Position vs) {
        for (Record record : records) {
            if (record.cards.equals(hand.name)
                    && record.position.equals(position.name)
                    && record.vs.equals(vs.name)) {
                return record.result;
            }
        }
        return null;
    }

    public static Map<String, Integer> getOptions(Hand hand, Position position) {
        Map<String, Integer> options = new HashMap<>();
        for (Record record : records) {
            if (record.cards.equals(hand.name)
                    && record.position.equals(position.name)
                    && record.result.equals("RFI")) {
                options.put(record.action, record.percentage);
            }
        }
        return options;
    }

}
