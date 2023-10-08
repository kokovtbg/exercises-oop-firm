package streamsGenerics;

import java.util.List;

public class P_Box<T> {
    private T content;

    public P_Box(T content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", this.content.getClass().getName(), this.content);
    }

    public static <T>void swap(List<T> list, int index1, int index2) {
        T tempElement = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, tempElement);
    }
}
