package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {
    private static Map<String, Object> fileExpecteds = new HashMap<>();

    @BeforeAll
    public static void main() {
        Map<String, Object> expecteds = Map.of("host", "hexlet.io",
                "timeout", 50,
                "proxy", "123.234.53.22",
                "follow", false);
        fileExpecteds.putAll(expecteds);
    }

    @ParameterizedTest
    @ValueSource(strings = {"empty.json", "empty.yml"})
    void runTest(String file) throws Exception {
        var output = Parser.run(file);

        assertTrue(output.isEmpty());
        assertFalse(output.containsKey("hexlet"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"file1.json", "file1.yml"})
    void runTest2(String file) throws Exception {
        var actual = fileExpecteds;
        var output = Parser.run(file);
        output.forEach((key, value) -> {
            assertEquals(output.get(key), actual.get(key));
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"file1.json", "file1.yml"})
    void runTest3(String file) throws Exception {
        var output = Parser.run(file);

        assertFalse(output.isEmpty());
        assertTrue(output.containsKey("timeout"));
        assertFalse(output.containsKey("hexlet"));
    }
}