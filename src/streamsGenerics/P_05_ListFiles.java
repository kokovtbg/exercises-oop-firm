package streamsGenerics;

import java.io.File;

public class P_05_ListFiles {
    public static void main(String[] args) {
        File dir = new File("src/streamsGenerics");
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    System.out.printf("Filename: %s, Size: %d%n", file.getName(), file.length());
                }
            }
        }

    }
}
