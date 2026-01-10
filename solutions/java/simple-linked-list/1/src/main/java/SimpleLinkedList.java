import java.lang.reflect.Array;
import java.util.NoSuchElementException;

class SimpleLinkedList<T> {

    private static class Node<T> {
        T value;
        Node<T> next;

        Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node<T> head;
    private int size = 0;

    SimpleLinkedList() {}

    SimpleLinkedList(T[] values) {
        for (int i = values.length - 1; i >= 0; i--) {
            push(values[i]);
        }
    }

    void push(T value) {
        head = new Node<>(value, head);
        size++;
    }

    T pop() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }

        T value = head.value;
        head = head.next;
        size--;
        return value;
    }

    void reverse() {
        Node<T> prev = null;
        Node<T> curr = head;

        while (curr != null) {
            Node<T> nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }

        head = prev;
    }

    T[] asArray(Class<T> clazz) {
        @SuppressWarnings("unchecked")
        T[] array = (T[]) Array.newInstance(clazz, size);

        Node<T> curr = head;
        int index = 0;

        while (curr != null) {
            array[index++] = curr.value;
            curr = curr.next;
        }

        return array;
    }

    int size() {
        return size;
    }
}
