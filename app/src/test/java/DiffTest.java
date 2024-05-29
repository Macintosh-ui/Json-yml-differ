import static org.junit.jupiter.api.Assertions.*;

import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

public class DiffTest {

    @Test
    public void testDiff() {
        String expected = """
                  - proxy: 123.234.53.22
                    host: hexlet.io
                  - follow: false
                  - timeout: 50
                  + timeout: 20
                  + verbose: true\
                """;
        String actual;
        try {
            actual = Differ.generate("./testFiles/file1.JSON", "./testFiles/file2.JSON");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertEquals(expected, actual);
    }
    @Test
    public void testFilepath() {
        String expected = "./testFiles/file1.JSON";
        String actual = String.valueOf(Differ.getFilePath("./testFiles/file1.JSON"));
        assertEquals(expected, actual);
    }

    @Test
    public void testYamlDiff() {
        var expected = """
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                """;
    }
}
