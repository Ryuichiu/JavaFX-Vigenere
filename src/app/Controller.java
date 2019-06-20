package sample;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.effect.SepiaTone;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.Contract;

import javax.swing.*;
import java.io.IOException;
import java.util.regex.Pattern;

public class Controller {
    public TextField arabic,roman;
    public Label exit;
    public Button goButton;

    @Contract("!null, _, _ -> param1")
    private StringBuilder addTier(StringBuilder sb, int tier,int number) {
        if (number > 0){
            for (int t = 0; t < tier; t++) {
                sb.insert(0, ")");
            }
            for (int t = 0; t < tier; t++) {
                sb.insert(0, "(");
            }
        }

        return sb;
    }

    public void initialize() {
        arabic.textProperty().addListener((observe, old, text) -> {
            if (arabic.isFocused())
                translateArabic(text);
        });
        roman.textProperty().addListener((observe, old, text) -> {
            if (roman.isFocused())
                translateRoman(text.toUpperCase());
        });
    }

    public void algorithm() throws IOException {
        String arabicText = arabic.getText();
        String romanText = roman.getText().toUpperCase();

        if (arabicText.isEmpty())
            if (romanText.isEmpty())
                JOptionPane.showMessageDialog(null, "Please enter a Text", "Error", JOptionPane.ERROR_MESSAGE);
            else
                translateRoman(romanText);
        else
            if (romanText.isEmpty())
                translateArabic(arabicText);
    }

    private void translateRoman(String romanText) {
        boolean onlyRoman = !Pattern.compile("[^()IVXLCDM]").matcher(romanText).find();

        if (!romanText.isBlank() && !romanText.isEmpty() && !romanText.contains(" ") && onlyRoman) {
            System.out.println("true");
            int number = 0;
            int tier = 0;
            int size = romanText.length();
            boolean negative = false;
            
            for (int i = 0; i < size; i++) {
                char cha = romanText.charAt(i);
                char chaNext = romanText.charAt(Math.min(size - 1, i + 1));
                if (i == size - 1)
                    negative = true;
            }
        } else if (romanText.isEmpty()) {
            arabic.setText("");
        } else
            System.out.println("false");
    }

    private void translateArabic(String arabicText) {
        boolean onlyNumbers = !Pattern.compile("[^\\d]").matcher(arabicText).find();

        if (!arabicText.isBlank() && !arabicText.isEmpty() && !arabicText.contains(" ") && onlyNumbers) {
            int length = arabicText.length() - 1;
            StringBuilder sb = new StringBuilder();

            for (int i = length; i >= 0; i--) {
                int number = Integer.parseInt(arabicText.substring(i, i + 1));
                int oppositePos = length - i;
                int group = oppositePos % 3;
                int tier = oppositePos / 3;

                sb = addTier(sb, tier, number);

                switch (group) {
                    case 0 :
                        switch (number) {
                            case 1 :
                                sb.insert(tier, "I");
                                break;
                            case 2 :
                                sb.insert(tier, "II");
                                break;
                            case 3 :
                                sb.insert(tier, "III");
                                break;
                            case 4 :
                                sb.insert(tier, "IV");
                                break;
                            case 5 :
                                sb.insert(tier, "V");
                                break;
                            case 6 :
                                sb.insert(tier, "VI");
                                break;
                            case 7 :
                                sb.insert(tier, "VII");
                                break;
                            case 8 :
                                sb.insert(tier, "VIII");
                                break;
                            case 9 :
                                sb.insert(tier, "IX");
                                break;
                        }
                        break;
                    case 1 :
                        switch (number) {
                            case 1 :
                                sb.insert(tier, "X");
                                break;
                            case 2 :
                                sb.insert(tier, "XX");
                                break;
                            case 3 :
                                sb.insert(tier, "XXX");
                                break;
                            case 4 :
                                sb.insert(tier, "XL");
                                break;
                            case 5 :
                                sb.insert(tier, "L");
                                break;
                            case 6 :
                                sb.insert(tier, "LX");
                                break;
                            case 7 :
                                sb.insert(tier, "LXX");
                                break;
                            case 8 :
                                sb.insert(tier, "LXXX");
                                break;
                            case 9 :
                                sb.insert(tier, "XC");
                                break;
                        }
                        break;
                    case 2 :
                        switch (number) {
                            case 1 :
                                sb.insert(tier, "C");
                                break;
                            case 2 :
                                sb.insert(tier, "CC");
                                break;
                            case 3 :
                                sb.insert(tier, "CCC");
                                break;
                            case 4 :
                                sb.insert(tier, "CD");
                                break;
                            case 5 :
                                sb.insert(tier, "D");
                                break;
                            case 6 :
                                sb.insert(tier, "DC");
                                break;
                            case 7 :
                                sb.insert(tier, "DCC");
                                break;
                            case 8 :
                                sb.insert(tier, "DCCC");
                                break;
                            case 9 :
                                sb.insert(tier, "CM");
                                break;
                        }
                        break;
                    default :
                        throw new IllegalStateException("out of bounds");
                }
            }
            roman.setText(sb.toString());
        } else if (arabicText.isEmpty()) {
            roman.setText("");
        } else {
            System.out.println("error");
        }
    }

    public void exitMap(MouseEvent mouseEvent) { System.exit(0); }

    public void unhighlight(MouseEvent mouseEvent) {
        exit.setBackground(new Background(new BackgroundFill(null, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void highlight(MouseEvent mouseEvent) {
        BackgroundFill backgroundFill = new BackgroundFill(new Color(0, 0.8, 0.9098039215686275, 1), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        exit.setBackground(background);
    }
    public void setPressed(MouseEvent mouseEvent) {
        exit.setEffect(new SepiaTone());
    }

    public void setUnpressed(MouseEvent mouseEvent) {
        exit.setEffect(null);
    }
}
