package lab;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

public class CSVStatWriter {
    private static int countWords(Map<String, Integer> wordTableCounter) {
        int result = 0;
        for (Map.Entry<?, Integer> entry : wordTableCounter.entrySet()) {
            result += entry.getValue();
        }
        return result;
    }

    public static void generateCSVFile(Map<String, Integer> wordTableCounter, String outputFileName) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(outputFileName));
        List<Map.Entry<String, Integer>> list = new ArrayList<>(wordTableCounter.entrySet());
        list.sort(HashMap.Entry.comparingByValue(Comparator.reverseOrder()));

        int totalWords = countWords(wordTableCounter);
        for(Map.Entry<String, Integer> pair : list) {
            // IDEA suggested to replace StringBuilder with String, be so
            String CSVLine = pair.getKey() + "," +
                    pair.getValue().toString() + "," +
                    100.0 * pair.getValue() / totalWords + "%" + "\n";

            outputStreamWriter.write(CSVLine);
        }

        outputStreamWriter.close();
    }
}
