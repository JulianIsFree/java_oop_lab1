package tests;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class TestCSVFileReader {
    public final String inputFileName;
    private BufferedReader bufferedReader;
    public static class TestTrio {
        public final String first;
        public final int second;
        public final double third;

        public TestTrio(List<String> csvLine) {
            assert csvLine.size() == 3;

            this.first = csvLine.get(0);
            this.second = Integer.valueOf(csvLine.get(1));
            this.third = Double.valueOf(csvLine.get(2));
            }
    }

    TestCSVFileReader(String inputFileName) throws IOException{
        this.inputFileName = inputFileName;
        this.bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFileName)));
    }

    public List<String> getCSVLine() throws IOException {
        List<String> line;

        String unseparatedLine = bufferedReader.readLine();
        line = Arrays.asList(unseparatedLine.split(","));

        return line;
    }

    public boolean ready() throws IOException{
        return this.bufferedReader.ready();
    }

    public static TestTrio fromCSVLineToTestTrio(List<String> splitedLine) {
        return new TestTrio(splitedLine);
    }
}
