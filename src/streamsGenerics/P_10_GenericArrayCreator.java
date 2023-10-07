package streamsGenerics;

import java.util.Arrays;

public class P_10_GenericArrayCreator {

    private static class ArrayCreator {
        public static <T>T[] create(int length, T item) {
            T[] array = (T[]) new Object[length];
            Arrays.fill(array, item);
            return array;
        }

        public static <T>T[] create(Class<T> classNeeded, int length, T item) {
            T[] array = (T[]) new Object[length];
            Arrays.fill(array, item);
            return array;
        }
    }

    public static void main(String[] args) {
        Object[] strings = ArrayCreator.create(10, "Hello");
        System.out.println(strings.length);
        for (int i = 0; i < strings.length; i++) {
            System.out.println(strings[i]);
        }

        Object[] objects = ArrayCreator.create(String.class, 10, "Hello again");
        System.out.println(objects.length);
        for (int i = 0; i < objects.length; i++) {
            System.out.println(objects[i]);
        }
    }
}
