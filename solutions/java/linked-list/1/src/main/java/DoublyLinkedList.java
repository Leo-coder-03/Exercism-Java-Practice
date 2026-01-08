class DoublyLinkedList<T> {

    private Element<T> head;
    private Element<T> tail;

    void push(T value) {
        Element<T> newNode = new Element<>(value, tail, null);
        if (tail != null) {
            tail.next = newNode;
        }
        tail = newNode;

        if (head == null) {
            head = newNode;
        }
    }

    T pop() {
        if (tail == null) {
            return null;
        }

        T value = tail.value;

        if (tail.prev != null) {
            tail = tail.prev;
            tail.next = null;
        } else {
            head = null;
            tail = null;
        }

        return value;
    }

    void unshift(T value) {
        Element<T> newNode = new Element<>(value, null, head);
        if (head != null) {
            head.prev = newNode;
        }
        head = newNode;

        if (tail == null) {
            tail = newNode;
        }
    }

    T shift() {
        if (head == null) {
            return null;
        }

        T value = head.value;

        if (head.next != null) {
            head = head.next;
            head.prev = null;
        } else {
            head = null;
            tail = null;
        }

        return value;
    }

    private static final class Element<T> {
        private final T value;
        private Element<T> prev;
        private Element<T> next;

        Element(T value, Element<T> prev, Element<T> next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }
}
