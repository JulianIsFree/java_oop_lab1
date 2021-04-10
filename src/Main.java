import lab.CSVStatWriter;
import lab.WordStatCollector;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            WordStatCollector wsc = new WordStatCollector(args[0]);
            CSVStatWriter.generateCSVFile(wsc.getWordTableCounter(), args[1]);
        } catch (IOException ignored) {
            assert false;
        } catch (ArrayIndexOutOfBoundsException ignored) {
            System.out.println("Not enough arguments: input, output");
        }
    }
}
