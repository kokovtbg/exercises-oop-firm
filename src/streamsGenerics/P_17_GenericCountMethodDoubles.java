package streamsGenerics;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P_17_GenericCountMethodDoubles {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        List<Double> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            double line = Double.parseDouble(scanner.nextLine());
            list.add(line);
        }

        Double element = Double.valueOf(scanner.nextLine());
        System.out.println(P_Box.countGreaterThan(list, element));
    }
}
