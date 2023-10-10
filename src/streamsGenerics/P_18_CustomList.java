package streamsGenerics;

import java.util.Scanner;

public class P_18_CustomList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        P_CustomList<String> list = new P_CustomList<>();
        String commandLine = scanner.nextLine();
        while (!commandLine.equals("end")) {
            String[] data = commandLine.split("\\s+");
            String command = data[0];
            switch (command) {
                case "Add" -> {
                    String element = data[1];
                    list.add(element);
                }
                case "Remove" -> {
                    int index = Integer.parseInt(data[1]);
                    list.remove(index);
                }
                case "Contains" -> {
                    String element = data[1];
                    System.out.println(list.contains(element));
                }
                case "Swap" -> {
                    int index1 = Integer.parseInt(data[1]);
                    int index2 = Integer.parseInt(data[2]);
                    list.swap(index1, index2);
                }
                case "Greater" -> {
                    String element = data[1];
                    System.out.println(list.countGreaterThan(element));
                }
                case "Max" -> {
                    System.out.println(list.getMax());
                }
                case "Min" -> {
                    System.out.println(list.getMin());
                }
                case "Print" -> {
                    System.out.println(list.getAllJoinedByNewLine());
                }
            }

            commandLine = scanner.nextLine();
        }
    }
}
