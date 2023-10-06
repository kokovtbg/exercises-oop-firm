package streamsGenerics;

import java.io.FileInputStream;
import java.io.IOException;

public class P_01_ReadFile {
    public static void main(String[] args) {
        String path = "src/streamsGenerics/input.txt";

        try (FileInputStream fis = new FileInputStream(path)) {
            int oneByte = fis.read();
            while (oneByte > -1) {
                System.out.printf("%d,", oneByte);
                oneByte = fis.read();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
