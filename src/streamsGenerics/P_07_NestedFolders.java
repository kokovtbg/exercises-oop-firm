package streamsGenerics;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P_07_NestedFolders {
    public static void main(String[] args) {
        File dir = new File("src");
        List<File> files = findAllSubdirs(dir).stream().toList();
        files.forEach(e -> System.out.println(e.getName()));
        System.out.println(files.size());
    }

    private static List<File> findAllSubdirs(File file) {
        List<File> subdirs = Arrays.asList(file.listFiles(File::isDirectory));
        subdirs = new ArrayList<>(subdirs);

        List<File> deepSubdirs = new ArrayList<>();
        for(File subdir : subdirs) {
            deepSubdirs.addAll(findAllSubdirs(subdir));
        }
        subdirs.addAll(deepSubdirs);
        return subdirs;
    }
}
