package lab;

import java.io.*;
import java.util.*;

public class WordStatCollector {
    private final String inputFileName;
    private Map<String, Integer> wordTableCounter;

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

    }

    /* Public methods and constructors */
    public WordStatCollector(String inputFileName) throws IOException {
        this.inputFileName = inputFileName;
        this.wordTableCounter = new TreeMap<>();
        collectStatistic();
    }

    private void collectStatistic() throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(inputFileName));

        while (inputStreamReader.ready())
            updateStatistic(getWord(inputStreamReader));

        inputStreamReader.close();
    }

    // for tests
    public Map<String, Integer> getWordTableCounter() {
        return wordTableCounter;
    }

    public void setWordTableCounter(Map<String, Integer> wordTableCounter) {
        this.wordTableCounter = wordTableCounter;
    }
}
