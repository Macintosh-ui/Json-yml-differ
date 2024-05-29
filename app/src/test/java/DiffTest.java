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
    public void testYamlDiff() throws Exception {
        var expected = """
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                """;
        String actual = Differ.generate("./testFiles/file1.yml", "./testFiles/file2.yml");
        assertEquals(expected, actual);
    }

    @Test
    public void testIntegratedDiff() throws Exception {
        var expected = """
 {
    chars1: [a, b, c]
  - chars2: [d, e, f]
  + chars2: false
  - checked: false
  + checked: true
  - default: null
  + default: [value1, value2]
  - id: 45
  + id: null
  - key1: value1
  + key2: value2
    numbers1: [1, 2, 3, 4]
  - numbers2: [2, 3, 4, 5]
  + numbers2: [22, 33, 44, 55]
  - numbers3: [3, 4, 5]
  + numbers4: [4, 5, 6]
  + obj1: {nestedKey=value, isNested=true}
  - setting1: Some value
  + setting1: Another value
  - setting2: 200
  + setting2: 300
  - setting3: true
  + setting3: none
}
                """;

    }
}
