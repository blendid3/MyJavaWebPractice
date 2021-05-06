package smallPracticeModule;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regex {
    public static void main(String[] args) {
//        Pattern pattern = Pattern.compile("^\\d{10}$");
//        Matcher matcher = pattern.matcher("9517725656");
//        if (matcher.matches()) {
//            System.out.println("matches");
//        } else {
//            System.out.println("doesn't match");
//        }
        try {
            int id = Integer.parseInt("a 12");

        } catch (Exception e) {
            System.out.println("Yes");
        }

    }
}
