package streamsGenerics;

public class P_Box<T> {
    private T content;

    public P_Box(T content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", this.content.getClass().getName(), this.content);
    }
}
