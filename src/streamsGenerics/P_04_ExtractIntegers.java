package streamsGenerics;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class P_04_ExtractIntegers {
    public static void main(String[] args) {
        String inPath = "src/streamsGenerics/input.txt";
        String outPath = "src/streamsGenerics/output_p4.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outPath));
             BufferedReader br = new BufferedReader(new FileReader(inPath))) {

            Pattern pattern = Pattern.compile("(?<a>\\b[0-9]+\\b)");

            String line = br.readLine();
            while (line != null) {
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    bw.write(matcher.group("a"));
                    bw.newLine();
                }
                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
