package main;

import java.util.Map;

public class Random {

    public static String get(Map<String, Integer> options) {
        int randomNumber = (int) Math.round(Math.random() * 100);
        int RAISE;
        int CALL;
        if (options.containsKey("RAISE") && options.containsKey("CALL")) {
            RAISE = options.get("RAISE");
            CALL = options.get("CALL");
            if (randomNumber <= RAISE) {
                return "RAISE";
            } else if (randomNumber <= RAISE + CALL) {
                return "CALL";
            }
        } else if (options.containsKey("RAISE")) {
            RAISE = options.get("RAISE");
            if (randomNumber <= RAISE) {
                return "RAISE";
            }
        } else if (options.containsKey("CALL")) {
            CALL = options.get("CALL");
            if (randomNumber <= CALL) {
                return "CALL";
            }
        }
        return "FOLD";
    }
}
