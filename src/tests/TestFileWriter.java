package tests;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

class TestFileWriter {
    private final List<String> fileContent;
    private final String outputFileName;

    TestFileWriter(List<String> fileContent, String fileName) {
        this.fileContent = fileContent;
        this.outputFileName = fileName;
    }

    void write() throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(this.outputFileName));

        for(String line : fileContent) {
            outputStreamWriter.write(line + "\n");
        }

        outputStreamWriter.close();
    }
}
