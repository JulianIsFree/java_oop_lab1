import lab.WordStatCollector;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            WordStatCollector wsc = new WordStatCollector(args[0], args[1]);
            wsc.collectStatistic();
            wsc.generateCSVFile();
        } catch (IOException ignored) {
            assert false;
        } catch (ArrayIndexOutOfBoundsException ignored) {
            System.out.println("Not enough arguments: input, output");
        }
    }
}
