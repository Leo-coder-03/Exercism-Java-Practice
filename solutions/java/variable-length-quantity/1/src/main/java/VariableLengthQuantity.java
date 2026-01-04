import java.util.ArrayList;
import java.util.List;

class VariableLengthQuantity {

    List<String> encode(List<Long> numbers) {
        List<String> result = new ArrayList<>();

        for (long number : numbers) {
            List<Integer> chunks = new ArrayList<>();

            // extract 7-bit groups
            do {
                chunks.add((int) (number & 0x7F));
                number >>>= 7;
            } while (number > 0);

            // write from most-significant to least
            for (int i = chunks.size() - 1; i >= 0; i--) {
                int val = chunks.get(i);
                if (i != 0) val |= 0x80;        // set continuation bit
                result.add(toOutHex(val));
            }
        }

        return result;
    }

    List<String> decode(List<Long> bytes) {
        List<String> result = new ArrayList<>();

        long value = 0;
        boolean waiting = false;

        for (long b : bytes) {
            int v = (int)(b & 0xFF);

            value = (value << 7) | (v & 0x7F);

            if ((v & 0x80) == 0) {
                // end of one number
                result.add("0x" + Long.toHexString(value));
                value = 0;
                waiting = false;
            } else {
                waiting = true;
            }
        }

        if (waiting) {
            throw new IllegalArgumentException("Invalid variable-length quantity encoding");
        }

        return result;
    }

    private String toOutHex(int val) {
        return "0x" + Integer.toHexString(val);
    }
}
