package hexlet.code.formatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Stylish {
    private static final String MINUS = "-";
    private static final String PLUS = "+";
    private static final String OLD = "old";
    private static final String NEW = "new";
    private static final String INTACT = "intact";
    private static final Integer COUNT_EMPTY_LINE = 2;

    public static String getStylish(Map<String, Map<String, Object>> list) {
        List<String> result = new ArrayList<>();
        var emptyString = " ".repeat(COUNT_EMPTY_LINE);

        result.add("{");
        list.forEach((key, value) -> {
            var plus = PLUS + emptyString + key + ": " + value.get(NEW);
            var minus = MINUS + emptyString + key + ": " + value.get(OLD);

            if (value.containsKey(NEW) && value.containsKey(OLD)) {
                result.add(minus);
                result.add(plus);
            } else if (value.containsKey(OLD)) {
                result.add(minus);
            } else if (value.containsKey(NEW)) {
                result.add(plus);
            } else if (value.containsKey(INTACT)) {
                result.add(" " + emptyString + key + ": " + value.get(INTACT));
            }

        });
        result.add("}");

        return String.join("\n", result);
    }
}