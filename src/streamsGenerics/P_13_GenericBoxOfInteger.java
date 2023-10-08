package streamsGenerics;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P_13_GenericBoxOfInteger {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        List<Integer> boxesString = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(scanner.nextLine());
            boxesString.add(number);
        }

        boxesString.stream().map(P_Box::new).forEach(System.out::println);
    }
}
