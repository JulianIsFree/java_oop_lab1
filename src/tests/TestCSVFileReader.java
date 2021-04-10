package tests;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

class TestCSVFileReader {
    private BufferedReader bufferedReader;
    static class TestTrio {
        final String first;
        final int second;
        final double third;

        TestTrio(List<String> csvLine) {
            assert csvLine.size() == 3;

            this.first = csvLine.get(0);
            this.second = Integer.valueOf(csvLine.get(1));

            String third = csvLine.get(2);
            third = third.replace("%","");
            this.third = Double.valueOf(third);
            }
    }

    TestCSVFileReader(String inputFileName) throws IOException{
        this.bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFileName)));
    }

    List<String> getCSVLine() throws IOException {
        List<String> line;

        String unseparatedLine = bufferedReader.readLine();
        line = Arrays.asList(unseparatedLine.split(","));

        return line;
    }

    boolean ready() throws IOException{
        return this.bufferedReader.ready();
    }

    static TestTrio fromCSVLineToTestTrio(List<String> splitedLine) {
        return new TestTrio(splitedLine);
    }
}
