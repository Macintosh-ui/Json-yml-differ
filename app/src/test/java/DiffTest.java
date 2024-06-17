import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import hexlet.code.Differ;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DiffTest {
    private static String expectedStylish;
    private static String expectedPlain;
    private static String expectedJson;

    @BeforeAll
    public static void setUp() throws IOException {
        expectedStylish = readFile("src/test/resources/fixtures/expectedStylish");
        expectedPlain = readFile("src/test/resources/fixtures/expectedPlain");
        expectedJson = String.valueOf(Files.readAllLines(Paths.get("src/test/resources/fixtures/expected.json")));
    }

    private static String readFile(String filepath) throws IOException {
        return Files.readString(Paths.get(filepath)).trim();
    }

    @Test
    public void testYamlStylishDiff() throws Exception {
        String actual = Differ.generate("src/test/resources/fixtures/file1.yml",
                                        "src/test/resources/fixtures/file2.yml", "stylish");
        assertEquals(expectedStylish, actual);
    }

    @Test
    public void testJsonStylishDiff() throws Exception {
        String actual = Differ.generate("src/test/resources/fixtures/file3.JSON",
                                        "src/test/resources/fixtures/file4.JSON", "stylish");
        assertEquals(expectedStylish, actual);
    }

    @Test
    public void testJsonPlainDiff() throws Exception {
        String actual = Differ.generate("src/test/resources/fixtures/file3.JSON",
                                        "src/test/resources/fixtures/file4.JSON", "plain");
        assertEquals(expectedPlain, actual);
    }

    @Test
    public void testYmlPlainDiff() throws Exception {
        String actual = Differ.generate("src/test/resources/fixtures/file1.yml",
                                        "src/test/resources/fixtures/file2.yml", "plain");
        assertEquals(expectedPlain, actual);
    }

    @Test
    public void testJsonToJsonFormat() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String actual = Differ.generate("/home/runner/work/java-project-71/java-project-71/app/src/test/resources"
                        + "/fixtures/file3.json",
                "/home/runner/work/java-project-71/java-project-71/app/src/test/resources/fixtures/file4.json", "json");
        assertEquals(mapper.readTree(expectedJson), mapper.readTree(actual));
    }

    @Test
    public void testYamlToJsonFormat() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String actual = Differ.generate("src/test/resources/fixtures/file1.yml",
                                        "src/test/resources/fixtures/file2.yml", "json");
        assertEquals(mapper.readTree(expectedJson), mapper.readTree(actual));
    }

    @Test
    public void testJsonNoArgs() throws Exception {
        String actual = Differ.generate("/home/runner/work/java-project-71/java-project-71/app/src/test/resources"
                        + "/fixtures/file3.json",
                        "/home/runner/work/java-project-71/java-project-71/app/src/test/resources/fixtures/file4.json");
        assertEquals(expectedStylish, actual);
    }

    @Test
    public void testYamlNoArgs() throws Exception {
        String actual = Differ.generate("src/test/resources/fixtures/file1.yml",
                                        "src/test/resources/fixtures/file2.yml");
        assertEquals(expectedStylish, actual);
    }
}
