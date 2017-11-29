import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App extends JFrame{

    private JTextArea inOut;
    private JButton encoder, decoder;
    private String inString, outString;


    public App() {
        setTitle("Шифровщик сообщений");
        setSize(620,200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setLayout(new FlowLayout(FlowLayout.CENTER));

        inOut = new JTextArea(10,30);
        inOut.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(inOut);
        encoder = new JButton("Зашифровать");
        encoder.addActionListener(new EncoderActionListener());

        decoder = new JButton("Расшифровать");
        decoder.addActionListener(new DecoderActionListener());

        //add(inOut);
        add(scrollPane);
        add(encoder);
        add(decoder);

        setVisible(true);
    }

    public static void main(String[] args) {

        new App();
    }

    private class EncoderActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            inString = inOut.getText();

            String buf;
            buf = inString.toLowerCase();

            char[] letter;
            letter = buf.toCharArray();

            String outString = "";

            for (int i = 0; i < letter.length; i++) {
                outString += ((int)letter[i]) + " ";
            }

            inOut.setText(outString);
        }
    }

    private class DecoderActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            outString = inOut.getText();

            Pattern pattern = Pattern.compile("([\\d\\s]+)|([^\\n])");

            Matcher matcher = pattern.matcher(outString);

            List<String> convertWords = new ArrayList<>();

            while (matcher.find()) {
                convertWords.add(matcher.group());
            }

            List<String> nums = new ArrayList<>();

            Pattern pattern1 = Pattern.compile("[\\d]+");

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

            String temp = "";

            for (int i = 0; i < convertWords.size(); i++) {
                Matcher matcher1 = pattern1.matcher(convertWords.get(i));
                while (matcher1.find()) {
                    temp += ( String.valueOf((char) (Integer.parseInt(matcher1.group()))));
                }
            }

            inOut.setText(temp);
        }
    }
}
