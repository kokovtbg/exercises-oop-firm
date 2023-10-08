package streamsGenerics;

public class P_11_GenericScale {
    private static class Scale<T extends Comparable<T>> {
        private T left;
        private T right;

        public Scale(T left, T right) {
            this.left = left;
            this.right = right;
        }

        public T getHeavier() {
            int compare = this.left.compareTo(this.right);
            if (compare == 0) {
                return null;
            }

            if (compare > 0) {
                return this.left;
            } else {
                return this.right;
            }
        }

    }
    public static void main(String[] args) {
        Scale<Integer> scale = new Scale<>(100,20);
        System.out.println(scale.getHeavier());
    }
}
