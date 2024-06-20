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
    private static String filepathPart = "src/test/resources/fixtures/";

    @BeforeAll
    public static void setUp() throws IOException {
        expectedStylish = readFile(filepathPart + "expectedStylish");
        expectedPlain = readFile(filepathPart + "expectedPlain");
        expectedJson = String.valueOf(Files.readAllLines(Paths.get(filepathPart + "expected.json")));
    }

    private static String readFile(String filepath) throws IOException {
        return Files.readString(Paths.get(filepath)).trim();
    }

    @Test
    public void testYamlStylishDiff() throws Exception {
        String actual = Differ.generate(filepathPart + "file1.yml", filepathPart + "file2.yml", "stylish");
        assertEquals(expectedStylish, actual);
    }

    @Test
    public void testJsonStylishDiff() throws Exception {
        String actual = Differ.generate(filepathPart + "file3.json", filepathPart + "file4.json", "stylish");
        assertEquals(expectedStylish, actual);
    }

    @Test
    public void testJsonPlainDiff() throws Exception {
        String actual = Differ.generate(filepathPart + "file3.json", filepathPart + "file4.json", "plain");
        assertEquals(expectedPlain, actual);
    }

    @Test
    public void testYmlPlainDiff() throws Exception {
        String actual = Differ.generate(filepathPart + "file1.yml", filepathPart + "file2.yml", "plain");
        assertEquals(expectedPlain, actual);
    }

    @Test
    public void testJsonToJsonFormat() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String actual = Differ.generate(filepathPart + "file3.json", filepathPart + "file4.json", "json");
        assertEquals(mapper.readTree(expectedJson), mapper.readTree(actual));
    }

    @Test
    public void testYamlToJsonFormat() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String actual = Differ.generate(filepathPart + "file1.yml", filepathPart + "file2.yml", "json");
        assertEquals(mapper.readTree(expectedJson), mapper.readTree(actual));
    }

    @Test
    public void testJsonNoArgs() throws Exception {
        String actual = Differ.generate(filepathPart + "file3.json", filepathPart + "file4.json");
        assertEquals(expectedStylish, actual);
    }

    @Test
    public void testYamlNoArgs() throws Exception {
        String actual = Differ.generate(filepathPart + "file1.yml", filepathPart + "file2.yml");
        assertEquals(expectedStylish, actual);
    }
}
