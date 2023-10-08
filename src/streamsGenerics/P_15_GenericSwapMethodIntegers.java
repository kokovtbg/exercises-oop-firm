package streamsGenerics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P_15_GenericSwapMethodIntegers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        List<Integer> boxesString = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(scanner.nextLine());
            boxesString.add(number);
        }

        int[] indices = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        List<P_Box<Integer>> list = boxesString.stream().map(P_Box::new).collect(Collectors.toList());
        P_Box.swap(list, indices[0], indices[1]);
        list.forEach(System.out::println);
    }
}
