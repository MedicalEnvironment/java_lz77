import java.util.ArrayList;
import java.util.List;

public class LZ77 {
    private static final int WINDOW_SIZE = 4096;
    private static final int LOOKAHEAD_BUFFER_SIZE = 15;

    public byte[] encode(byte[] text) {
        List<Byte> encodedBytes = new ArrayList<>();
        int currentIndex = 0;

        while (currentIndex < text.length) {
            int maxMatchLength = 0;
            int maxMatchDistance = 0;

            for (int i = Math.max(0, currentIndex - WINDOW_SIZE); i < currentIndex; i++) {
                int matchLength = 0;
                while (matchLength < LOOKAHEAD_BUFFER_SIZE && currentIndex + matchLength < text.length &&
                        text[i + matchLength] == text[currentIndex + matchLength]) {
                    matchLength++;
                }
                if (matchLength > maxMatchLength) {
                    maxMatchLength = matchLength;
                    maxMatchDistance = currentIndex - i;
                }
            }

            if (maxMatchLength > 0) {
                encodedBytes.add((byte) maxMatchDistance);
                encodedBytes.add((byte) maxMatchLength);
                currentIndex += maxMatchLength;
            } else {
                encodedBytes.add(text[currentIndex]);
                currentIndex++;
            }
        }

        byte[] result = new byte[encodedBytes.size()];
        for (int i = 0; i < encodedBytes.size(); i++) {
            result[i] = encodedBytes.get(i);
        }
        return result;
    }

    public byte[] decode(byte[] information) {
        // Implementation of LZ77 decoding goes here
        return information;
    }
}
