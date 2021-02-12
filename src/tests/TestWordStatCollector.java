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

    private static final String testFileName = "TestWordStatCollector_collectStatistic()";;
    private static final String CSVFileName = "CSVStatistic";
    private static List<String> fileContent = Arrays.asList("test.test#test!test test", "second Second line", "third Third line", "five five five five five");;

    private static HashMap<String, Integer> expectedTable;

    @Before
    public void initTestData() {
        expectedTable = new HashMap<>();
        expectedTable.put("test", 5);
        expectedTable.put("second", 2);
        expectedTable.put("line", 2);
        expectedTable.put("third", 2);
        expectedTable.put("five", 5);

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
            WordStatCollector wsc = new WordStatCollector(testFileName, CSVFileName);
            wsc.collectStatistic();

            Map<String, Integer> wordTableCounter = wsc.getWordTableCounter();
            for(Map.Entry<String, Integer> pair : wordTableCounter.entrySet()) {
                assertEquals(expectedTable.get(pair.getKey()), pair.getValue());
            }
        } catch (IOException ignored) {
            fail();
        }
    }

    @Test
    public void generateCSV() {
        try {
            WordStatCollector wsc = new WordStatCollector(testFileName, CSVFileName);
            wsc.setWordTableCounter(expectedTable);

            wsc.generateCSVFile();
            TestCSVFileReader testCSVFileReader = new TestCSVFileReader(CSVFileName);

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
