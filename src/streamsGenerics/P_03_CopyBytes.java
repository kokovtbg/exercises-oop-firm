package streamsGenerics;

import java.io.*;

public class P_03_CopyBytes {
    public static void main(String[] args) {
        String path = "src/streamsGenerics/input.txt";
        String outPath = "src/streamsGenerics/output_p03.txt";
        try (FileInputStream fis = new FileInputStream(path);
             FileOutputStream fos = new FileOutputStream(outPath)) {
            int oneByte = fis.read();
            while (oneByte > -1) {
                if (oneByte == 32 || oneByte == 9) {
                    fos.write(oneByte);
                } else if (oneByte == 13) {
                    oneByte = fis.read();
                    if (oneByte == 10) {
                        fos.write(13);
                        fos.write(oneByte);
                    }
                } else {
                    fos.write(String.valueOf(oneByte).getBytes());
                }

                oneByte = fis.read();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
