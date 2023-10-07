package streamsGenerics;

import java.util.ArrayDeque;
import java.util.Deque;

public class P_09_JarOfT {
    private static class Jar<T> {
        private Deque<T> elements;

        public Jar() {
            this.elements = new ArrayDeque<>();
        }

        public void add(T element) {
            this.elements.push(element);
        }

        public T remove() {
            return this.elements.pop();
        }
    }
    public static void main(String[] args) {
        Jar<String> deque = new Jar<>();
        deque.add("Hello");
        deque.add("There");
        System.out.println(deque.remove());
        System.out.println(deque.remove());
    }
}
