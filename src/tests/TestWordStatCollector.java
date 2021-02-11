package tests;

import lab.WordStatCollector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;


import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestWordStatCollector {

    private static String CSVFileName;
    private static List<String> fileContent;
    private static HashMap<String, Integer> expectedTable;
    private static WordStatCollector wsc;

    @Before
    public void initTestData() {

        String testFileName = "TestWordStatCollector_collectStatistic()";
        CSVFileName = "CSVStatistic";
        fileContent = Arrays.asList("test.test#test!test test", "second Second line", "third Third line", "five five five five five");

        expectedTable = new HashMap<>();
        expectedTable.put("test", 5);
        expectedTable.put("second", 2);
        expectedTable.put("line", 2);
        expectedTable.put("third", 2);
        expectedTable.put("five", 5);

        wsc = new WordStatCollector(testFileName, CSVFileName);

        try{
            TestFileWriter tfw = new TestFileWriter(fileContent, testFileName);
            tfw.write();
        } catch (IOException ignored){
            assert false;
        }
    }

    @After
    public void cleanTestData() {
        fileContent.clear();
    }

    @Test
    public void noAssert() {
        assertEquals(1,1);
    }

    @Test
    public void collectStatistic() {
        try {
            wsc.collectStatistic();

            HashMap<String, Integer> wordTableCounter = wsc.getWordTableCounter();
            for(Map.Entry<String, Integer> pair : wordTableCounter.entrySet()) {
                assertEquals(expectedTable.get(pair.getKey()), pair.getValue());
            }
            generateCSV();
        } catch (IOException ignored) {
            fail();
        }
    }

    //TODO: install JUnit5 and make order for running tests
    private void generateCSV() {
        try {
            wsc.generateCSVFile();
            TestCSVFileReader testCSVFileReader = new TestCSVFileReader(CSVFileName);

//            for(Map.Entry<String, Integer> pair : expectedTable.entrySet()) {
//                TestCSVFileReader.TestTrio trio = TestCSVFileReader.fromCSVLineToTestTrio(testCSVFileReader.getCSVLine());
//                assertEquals(trio.first, pair.getKey());
//                assertEquals(Integer.valueOf(trio.second), pair.getValue());
//            }

            while(testCSVFileReader.ready()) {
                TestCSVFileReader.TestTrio trio = TestCSVFileReader.fromCSVLineToTestTrio(testCSVFileReader.getCSVLine());

                int expectedFrequency = expectedTable.get(trio.first);
                assertEquals(expectedFrequency, trio.second);
            }

        } catch (IOException ignored) {
            fail();
        }
    }
}
