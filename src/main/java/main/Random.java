package main;

import java.util.Map;

public class Random {

    public static String get(Map<String, Integer> options) {
        int randomNumber = (int) Math.round(Math.random() * 100);
//        System.out.println("options: " + options);
//        System.out.println("Random: " + randomNumber);
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
            return "RAISE";
        } else if (options.containsKey("CALL")) {
            return "CALL";
        }
        return "FOLD";
    }

}
