package streamsGenerics;

import java.io.File;
import java.util.ArrayDeque;
import java.util.Deque;

public class P_06_NestedFolders {
    public static void main(String[] args) {
        File dir = new File("src");

        Deque<File> dirs = new ArrayDeque<>();
        dirs.offer(dir);
        int count = 0;
        while (!dirs.isEmpty()) {
            File current = dirs.poll();
            File[] nestedFiles = current.listFiles();
            for (File nestedFile : nestedFiles) {
                if (nestedFile.isDirectory()) {
                    dirs.offer(nestedFile);
                }
            }

            count++;
            System.out.println(current.getName());
        }
        System.out.println(count + " folders");

    }
}
