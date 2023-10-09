package streamsGenerics;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P_16_GenericCountMethodStrings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            list.add(line);
        }

        String element = scanner.nextLine();
        System.out.println(P_Box.countGreaterThan(list, element));
    }
}
