package sample;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.effect.SepiaTone;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.regex.Pattern;

public class Controller {
    public TextField arabic,roman;
    public Label exit;
    public Button goButton;

    private int newNumber(int number, int val, int factor, boolean subtract) {
        return (int) (subtract ? number - val * Math.pow(1000, factor) : number + val * Math.pow(1000, factor));
    }

    private StringBuilder addTier(StringBuilder sb, int tier) {
        for (int t = 0; t < tier; t++) {
            sb.insert(0, ")");
            sb.insert(0, "(");
        }
        return sb;
    }

    public void algorithm() {
        String arabicText = arabic.getText();
        String romanText = roman.getText().toUpperCase();

        boolean onlyNumbers = !Pattern.compile("[^\\d]").matcher(arabicText).find();

        if (!arabicText.isBlank() && !arabicText.isEmpty() && !arabicText.contains(" ") && onlyNumbers) {
            System.out.println("works");
            int length = arabicText.length() - 1;
            StringBuilder sb = new StringBuilder();

            for (int i = length; i >= 0; i--) {
                int number = Integer.parseInt(arabicText.substring(i, i + 1));
                int oppositePos = length - i;
                int group = oppositePos % 3;
                int tier = oppositePos / 3;

                sb = addTier(sb, tier);

                switch (group) {
                    case 0 :
                        switch (number) {
                            case 1 :
                                sb.insert(1, "I");
                                break;
                            case 2 :
                                sb.insert(1, "II");
                                break;
                            case 3 :
                                sb.insert(1, "III");
                                break;
                            case 4 :
                                sb.insert(1, "IV");
                                break;
                            case 5 :
                                sb.insert(1, "V");
                                break;
                            case 6 :
                                sb.insert(1, "VI");
                                break;
                            case 7 :
                                sb.insert(1, "VII");
                                break;
                            case 8 :
                                sb.insert(1, "VIII");
                                break;
                            case 9 :
                                sb.insert(1, "IX");
                                break;
                        }
                        break;
                    case 1 :
                        switch (number) {
                            case 1 :
                                sb.insert(1, "X");
                                break;
                            case 2 :
                                sb.insert(1, "XX");
                                break;
                            case 3 :
                                sb.insert(1, "XXX");
                                break;
                            case 4 :
                                sb.insert(1, "XL");
                                break;
                            case 5 :
                                sb.insert(1, "L");
                                break;
                            case 6 :
                                sb.insert(1, "LX");
                                break;
                            case 7 :
                                sb.insert(1, "LXX");
                                break;
                            case 8 :
                                sb.insert(1, "LXXX");
                                break;
                            case 9 :
                                sb.insert(1, "XC");
                                break;
                        }
                        break;
                    case 2 :
                        switch (number) {
                            case 1 :
                                sb.insert(1, "C");
                                break;
                            case 2 :
                                sb.insert(1, "CC");
                                break;
                            case 3 :
                                sb.insert(1, "CCC");
                                break;
                            case 4 :
                                sb.insert(1, "CD");
                                break;
                            case 5 :
                                sb.insert(1, "D");
                                break;
                            case 6 :
                                sb.insert(1, "DC");
                                break;
                            case 7 :
                                sb.insert(1, "DCC");
                                break;
                            case 8 :
                                sb.insert(1, "DCCC");
                                break;
                            case 9 :
                                sb.insert(1, "DM");
                                break;
                        }
                        break;
                    default :
                        throw new IllegalStateException("out of bounds");
                }
            }
            roman.setText(sb.toString());
        } else {
            System.out.println("error");
        }
    }

    public void translateRoman(KeyEvent inputMethodEvent) {

    }

    public void translateArabic(KeyEvent inputMethodEvent) {

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

    /**
     * I =    1
     * V =    5
     * X =   10
     * L =   50
     * C =  100
     * D =  500
     * M = 1000
     *
     *() = *1000
     */
}
