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
}
