import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) {

        String in = "Привет ЮРИК и тебе";
        System.out.println(in);
        String buf;
        buf = in.toLowerCase();

        char[] letter;
        letter = buf.toCharArray();

        String out = "";

        for (int i = 0; i < letter.length; i++) {
            if ((int)letter[i] == 32) {
                out += " || ";
            } else {
                out += ((int)letter[i] - 1071) + " ";
            }

        }

        System.out.println(out);

        Pattern pattern = Pattern.compile("([\\d\\s]+)|([^\\s||\\s])");

        Matcher matcher = pattern.matcher(out);

        List<String> convertWords = new ArrayList<>();

        while (matcher.find()) {
            convertWords.add(matcher.group());
        }

        for (int i = 0; i < convertWords.size(); i++) {
            System.out.println(convertWords.get(i));
        }

        List<String> nums = new ArrayList<>();

        Pattern pattern1 = Pattern.compile("[\\d]+[\\u2000]");

        for (int i = 0; i < convertWords.size(); i++) {

            Matcher matcher1 = pattern1.matcher(convertWords.get(i));

            while (matcher1.find()) {
                nums.add(matcher1.group());
            }
        }

        List<Integer> numLetter = new ArrayList<>();
        for (int i = 0; i < nums.size(); i++) {
            numLetter.add(Integer.parseInt(nums.get(i)));
        }


        for (int i = 0; i < numLetter.size(); i++) {
            System.out.println((char)(numLetter.get(i)+1071));
        }
    }
}
