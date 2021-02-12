package lab;

import java.io.*;
import java.util.*;

public class WordStatCollector {
    private final String inputFileName;
    private final String outputFileName;
    private Map<String, Integer> wordTableCounter;
    private int totalWords;

    private static String getWord(InputStreamReader inputStreamReader) throws IOException {
        StringBuilder wordBuilder = new StringBuilder();

        while(inputStreamReader.ready()) {
            char ch = (char) inputStreamReader.read();
            if(!Character.isLetterOrDigit(ch))
                break;

            // method must match "Word" and "word" as they are same
            ch = Character.toLowerCase(ch);

            wordBuilder.append(ch);
        }
        return wordBuilder.toString();
    }

    /* Private methods */
    private void updateStatistic(String word) {
        if(word.equals(""))
            return;

        wordTableCounter.merge(word, 1, (a, b) -> a + b);

        totalWords++;
    }

    /* Public methods and constructors */
    public WordStatCollector(String inputFileName, String outputFileName) {
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
        this.wordTableCounter = new TreeMap<>();
        this.totalWords = 0;
    }

    public void collectStatistic() throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(inputFileName));
        totalWords = 0;

        while (inputStreamReader.ready())
            updateStatistic(getWord(inputStreamReader));

        inputStreamReader.close();
    }

    public void generateCSVFile() throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(outputFileName));

        List<Map.Entry<String, Integer>> list = new ArrayList<>(wordTableCounter.entrySet());
        list.sort(HashMap.Entry.comparingByValue(Comparator.reverseOrder()));

        for(Map.Entry<String, Integer> pair : list) {
            // IDEA suggested to replace StringBuilder with String, be so
            String CSVLine = pair.getKey() + "," +
                    pair.getValue().toString() + "," +
                    100.0 * pair.getValue() / totalWords + "%" + "\n";

            outputStreamWriter.write(CSVLine);
        }

        outputStreamWriter.close();
    }

    // for tests
    public Map<String, Integer> getWordTableCounter() {
        return wordTableCounter;
    }

    public void setWordTableCounter(Map<String, Integer> wordTableCounter) {
        totalWords = 0;
        this.wordTableCounter = wordTableCounter;

        for(Map.Entry<String, Integer> pair : wordTableCounter.entrySet()) {
            totalWords += pair.getValue();
        }
    }
}
