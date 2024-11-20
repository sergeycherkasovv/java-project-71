package hexlet.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FileDifferences {
    public static final String STATUS = "status";
    public static final String CHANGED = "changed";
    public static final String NEW = "new";
    public static final String SAME = "same";
    public static final String DELETED = "deleted";

    public static final String KEY = "key";
    public static final String VALUE_ONE = "valueOne";
    public static final String VALUE_SECOND = "valueSecond";



    public static List<Map<String, Object>> getDifferences(Map<String, Object> fileParse1,
                                                           Map<String, Object> fileParse2) {

        List<String> listKey = new ArrayList<>();
        listKey.addAll(fileParse1.keySet());
        listKey.addAll(fileParse2.keySet());

        return listKey.stream()
                .distinct()
                .sorted()
                .map(key -> {
                    Map<String, Object> map = new HashMap<>();
                    if (Objects.equals(fileParse1.get(key), fileParse2.get(key))) {
                        map.put(STATUS, SAME);
                        map.put(KEY, key);
                        map.put(VALUE_SECOND, fileParse2.get(key));
                    } else if ((fileParse1.containsKey(key) && fileParse2.containsKey(key))) {
                        map.put(STATUS, CHANGED);
                        map.put(KEY, key);
                        map.put(VALUE_ONE, fileParse1.get(key));
                        map.put(VALUE_SECOND, fileParse2.get(key));
                    } else if (fileParse1.containsKey(key) && !fileParse2.containsKey(key)) {
                        map.put(STATUS, DELETED);
                        map.put(KEY, key);
                        map.put(VALUE_ONE, fileParse1.get(key));
                    } else if (fileParse2.containsKey(key) && !fileParse1.containsKey(key)) {
                        map.put(STATUS, NEW);
                        map.put(KEY, key);
                        map.put(VALUE_SECOND, fileParse2.get(key));
                    }
                    return map;
                })
                .toList();
    }
}