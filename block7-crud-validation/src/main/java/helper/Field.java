package helper;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Field {
    public static Boolean validateName(String str) {
        Pattern pat = Pattern.compile("^[a-zA-ZÀ-ÿ0-9\\u00f1\\u00d1]+(\\s*[a-zA-ZÀ-ÿ0-9\\u00f1\\u00d1]*)*[a-zA-ZÀ-ÿ0-9\\u00f1\\u00d1]{3,16}+$");
        Matcher mat = pat.matcher(str);

        return mat.matches();
    }

    public static Boolean validatePlace(String str) {
        Pattern pat = Pattern.compile("^[a-zA-ZÀ-ÿ\\u00f1\\u00d1]+(\\s*[a-zA-ZÀ-ÿ\\u00f1\\u00d1]*)*[a-zA-ZÀ-ÿ\\u00f1\\u00d1]{3,16}+$");
        Matcher mat = pat.matcher(str);

        return mat.matches();
    }
}
