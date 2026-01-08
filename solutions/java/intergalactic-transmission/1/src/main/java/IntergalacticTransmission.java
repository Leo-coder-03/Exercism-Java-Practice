import java.util.ArrayList;
import java.util.List;

public class IntergalacticTransmission {
    public static List<Integer> getTransmitSequence(List<Integer> message) {
        List<Integer> result = new ArrayList<>();
        List<Integer> bits = new ArrayList<>();
        for (int b : message) {
            for (int mask = 0x80; mask > 0; mask >>= 1) {
                bits.add((b & mask) != 0 ? 1 : 0);
            }
        }

        int index = 0;
        while (index < bits.size()) {

            List<Integer> chunk = new ArrayList<>();

            int count = 0;
            while (index < bits.size() && count < 7) {
                chunk.add(bits.get(index));
                index++;
                count++;
            }

            while (chunk.size() < 7) {
                chunk.add(0);
            }

            int ones = 0;
            for (int bit : chunk) {
                if (bit == 1) ones++;
            }

            int parity = (ones % 2 == 0) ? 0 : 1;

            int value = 0;
            for (int bit : chunk) {
                value = (value << 1) | bit;
            }
            value = (value << 1) | parity;

            result.add(value);
        }

        return result;
    }

    public static List<Integer> decodeSequence(List<Integer> sequence) {
        List<Integer> dataBits = new ArrayList<>();

        for (int value : sequence) {

            int[] bits = new int[8];
            for (int i = 7; i >= 0; i--) {
                bits[7 - i] = (value >> i) & 1;
            }

            int ones = 0;
            for (int bit : bits) ones += bit;

            if (ones % 2 != 0) {
                throw new IllegalArgumentException("Parity error detected");
            }

            for (int i = 0; i < 7; i++) {
                dataBits.add(bits[i]);
            }
        }

        List<Integer> output = new ArrayList<>();

        int idx = 0;
        while (idx + 7 < dataBits.size()) {

            int byteVal = 0;
            for (int i = 0; i < 8; i++) {
                byteVal = (byteVal << 1) | dataBits.get(idx + i);
            }

            output.add(byteVal);
            idx += 8;
        }

        return output;
    }
}
