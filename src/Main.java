import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java Main <encode/decode> <input_file> <output_file>");
            return;
        }

        String operation = args[0];
        String inputFile = args[1];
        String outputFile = args[2];

        try {
            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = inputStream.readAllBytes();
            inputStream.close();

            LZ77 lz77 = new LZ77();
            byte[] resultBytes;
            if (operation.equalsIgnoreCase("encode")) {
                resultBytes = lz77.encode(inputBytes);
                System.out.println("Encoding successful.");
            } else if (operation.equalsIgnoreCase("decode")) {
                resultBytes = lz77.decode(inputBytes);
                System.out.println("Decoding successful.");

                // Debug: Print decoded data
                System.out.println("Decoded data: " + new String(resultBytes));
            } else {
                System.out.println("Invalid operation. Please specify 'encode' or 'decode'.");
                return;
            }

            writeToFile(outputFile, resultBytes);
            System.out.println("Output written to " + outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFile(String name, byte[] data) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(name);
        outputStream.write(data);
        outputStream.close();
    }

    public static void exit() {
        System.exit(0);
    }
}
