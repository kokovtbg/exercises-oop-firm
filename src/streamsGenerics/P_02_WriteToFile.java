package streamsGenerics;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class P_02_WriteToFile {
    public static void main(String[] args) {
        String path = "src/streamsGenerics/input.txt";
        String outPath = "src/streamsGenerics/output_p02.txt";
        try (FileInputStream fis = new FileInputStream(path);
             FileOutputStream fos = new FileOutputStream(outPath)) {
            int oneByte = fis.read();
            while (oneByte > -1) {
                if (oneByte != 44 && oneByte != 46 && oneByte != 33 && oneByte != 63 && oneByte != 59) {
                    fos.write(oneByte);
                }

                oneByte = fis.read();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
