import java.util.Arrays;

class CircularBuffer<T> {

    private final T[] buffer;
    private final int capacity;
    private int readPos = 0;
    private int writePos = 0;
    private int size = 0;

    @SuppressWarnings("unchecked")
    CircularBuffer(final int size) {
        this.capacity = size;
        this.buffer = (T[]) new Object[size];
    }

    T read() throws BufferIOException {
        if (size == 0) {
            throw new BufferIOException("Tried to read from empty buffer");
        }
        T value = buffer[readPos];
        buffer[readPos] = null;
        readPos = (readPos + 1) % capacity;
        size--;
        return value;
    }

    void write(T data) throws BufferIOException {
        if (size == capacity) {
            throw new BufferIOException("Tried to write to full buffer");
        }
        buffer[writePos] = data;
        writePos = (writePos + 1) % capacity;
        size++;
    }

    void overwrite(T data) {
        if (size == capacity) {
            buffer[readPos] = data;
            readPos = (readPos + 1) % capacity;
            writePos = (writePos + 1) % capacity;
        } else {
            buffer[writePos] = data;
            writePos = (writePos + 1) % capacity;
            size++;
        }
    }

    void clear() {
        Arrays.fill(buffer, null);
        readPos = 0;
        writePos = 0;
        size = 0;
    }
}
