package util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {

    public static List<String> matchAll(String regex, String input) {
        Matcher m = Pattern.compile(regex).matcher(input);
        List<String> list = new ArrayList<>();

        while (m.find()) {
            list.add(m.group());
        }

        return list;
    }

    public static List<String> matchGroups(String regex, String input) {
        Matcher m = Pattern.compile(regex).matcher(input);
        List<String> list = new ArrayList<>();

        if (!m.find()) {
            return null;
        }

        for (var i = 1; i <= m.groupCount(); i++) {
            var val = m.group(i);

            if (val != null) {
                list.add(val);
            }
        }

        return list;
    }
}
