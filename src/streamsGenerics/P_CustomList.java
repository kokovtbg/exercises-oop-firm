package streamsGenerics;

public class P_CustomList<E extends Comparable<E>> {
    private int size = 0;
    private CustomNode<E> first;
    private CustomNode<E> last;

    private static class CustomNode<E> {
        E item;
        CustomNode<E> next;
        CustomNode<E> prev;

        CustomNode(CustomNode<E> prev, E element, CustomNode<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public void add(E element) {
        final CustomNode<E> l = last;
        final CustomNode<E> newNode = new CustomNode<>(l, element, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        return unlink(node(index));
    }

    public boolean contains(E element) {
        return indexOf(element) >= 0;
    }

    public void swap(int index1, int index2) {
        CustomNode<E> node1 = node(index1);
        CustomNode<E> node2 = node(index2);
        E item1 = node1.item;
        node1.item = node2.item;
        node2.item = item1;
    }

    public int countGreaterThan(E element) {
        int count = 0;
        for (CustomNode<E> x = first; x != null; x = x.next) {
            if (x.item.compareTo(element) > 0) {
                count++;
            }
        }

        return count;
    }

    public E getMax() {
        E maxElement = first.item;
        for (CustomNode<E> x = first.next; x != null; x = x.next) {
            if (x.item.compareTo(maxElement) > 0) {
                maxElement = x.item;
            }
        }

        return maxElement;
    }

    public E getMin() {
        E minElement = first.item;
        for (CustomNode<E> x = first.next; x != null; x = x.next) {
            if (x.item.compareTo(minElement) < 0) {
                minElement = x.item;
            }
        }

        return minElement;
    }

    public String getAllJoinedByNewLine() {
        StringBuilder sb = new StringBuilder();
        for (CustomNode<E> x = first; x != null; x = x.next) {
            sb.append(x.item);
            sb.append(System.lineSeparator());
        }

        return sb.toString().trim();
    }

    private CustomNode<E> node(int index) {
        CustomNode<E> x;
        if (index < (size / 2)) {
            x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
        } else {
            x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
        }
        return x;
    }

    private E unlink(CustomNode<E> x) {
        final E element = x.item;
        final CustomNode<E> next = x.next;
        final CustomNode<E> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        return element;
    }

    private int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (CustomNode<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    return index;
                }
                index++;
            }
        } else {
            for (CustomNode<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    return index;
                }
                index++;
            }
        }
        return -1;
    }
}
