package util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static String match(String regex, String input) {
        Matcher m = Pattern.compile(regex).matcher(input);

        return m.find() ? m.group() : null;
    }

    public static List<String> matchAll(String regex, String input) {
        Matcher m = Pattern.compile(regex).matcher(input);
        List<String> list = new ArrayList<>();

        while (m.find()) {
            list.add(m.group());
        }

        return list;
    }

    public static boolean matches(String regex, String input) {
        return Pattern.compile(regex).matcher(input).matches();
    }
}