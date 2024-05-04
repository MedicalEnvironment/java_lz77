import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java Main <input_file> <output_file>");
            return;
        }

        String inputFile = args[0];
        String outputFile = args[1];

        try {
            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = inputStream.readAllBytes();
            inputStream.close();

            LZ77 lz77 = new LZ77();
            byte[] encodedBytes = lz77.encode(inputBytes);

            writeToFile(outputFile, encodedBytes);

            System.out.println("Encoding successful. Output written to " + outputFile);
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
