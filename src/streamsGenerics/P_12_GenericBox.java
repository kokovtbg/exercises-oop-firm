package streamsGenerics;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P_12_GenericBox {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        List<String> boxesString = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            boxesString.add(line);
        }

        boxesString.stream().map(P_Box::new).forEach(System.out::println);
    }
}
