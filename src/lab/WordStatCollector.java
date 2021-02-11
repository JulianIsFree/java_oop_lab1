package lab;

import java.io.*;
import java.util.*;

public class WordStatCollector {
    private final String inputFileName;
    private final String outputFileName;
    private HashMap<String, Integer> wordTableCounter;
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
        this.wordTableCounter = new HashMap<>();
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

        for(Map.Entry<String, Integer> pair : wordTableCounter.entrySet()) {
            // IDEA suggested to replace StringBuilder with String, be so
            String CSVLine = pair.getKey() + "," +
                    pair.getValue().toString() + "," +
                    (double) pair.getValue() / totalWords + "\n";

            outputStreamWriter.write(CSVLine);
        }

        outputStreamWriter.close();
    }

    // for tests
    public HashMap<String, Integer> getWordTableCounter() {
        return wordTableCounter;
    }
}
