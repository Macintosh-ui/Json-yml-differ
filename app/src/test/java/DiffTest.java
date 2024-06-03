import static org.junit.jupiter.api.Assertions.assertEquals;

import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

public class DiffTest {

    @Test
    public void testFilepath() {
        String expected = "/Users/denis/java-project-71/app/src/test/resources/fixtures/file1.json";
        String actual = String.valueOf(Differ.getFilePath("src/test/resources/fixtures/file1.json"));
        assertEquals(expected, actual);
    }

    @Test
    public void testYamlDiff() throws Exception {
        var expected = Files.readString(Paths.get("src/test/resources/fixtures/expectedStylish"));
        String actual = Differ.generate("src/test/resources/fixtures/file1.yml",
                                        "src/test/resources/fixtures/file2.yml");
        assertEquals(expected, actual);
    }

    @Test
    public void testIntegratedDiff() throws Exception {
        var expected = Files.readString(Paths.get("src/test/resources/fixtures/expectedStylish"));
        String actual = Differ.generate("src/test/resources/fixtures/file3.JSON",
                                        "src/test/resources/fixtures/file4.JSON");
        assertEquals(expected, actual);
    }
}
