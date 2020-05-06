package main;

public class Log {

    public static void debug(String... vars) {

        for (String s : vars) {
            System.out.print(s + " ");
        }

        System.out.println("");
    }

    public static void debug(Item item, String info) {
        System.out.println("Item: " + item);
        System.out.println("Info: " + info);
        System.out.println("");
    }

    public static void info(Item item, String info) {

    }

}
