package sample;

import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.effect.SepiaTone;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.regex.Pattern;

public class Controller {
    public TextField arabic,roman;
    public Label exit;

    private int newNumber(int number, int val, int factor, boolean subtract) { 
        return (int) (subtract ? number - val * Math.pow(1000, factor) : number + val * Math.pow(1000, factor));
    }

    public void translateRoman(InputMethodEvent inputMethodEvent) {
        String text = roman.getText();
        if (text != null && !text.isBlank() && !text.contains(" ") && !Pattern.compile("^[\\d]").matcher(text).find()) {
            int number = 0;
            int factor = 0;
            for (int i = 0; i < text.length(); i++) {
                char cha = text.toUpperCase().charAt(i);
                char chaPre = text.toUpperCase().charAt(i - 1);
                switch (cha) {
                    case 'I' :
                        number = newNumber(number,1,factor,false);
                        break;
                    case 'V' :
                        number = newNumber(number,5,factor,false);
                        if (chaPre == 'I') number = newNumber(number, 1, factor, true);
                        break;
                    case 'X' :
                        number = newNumber(number,10,factor,false);
                        switch (chaPre) {
                            case 'V' :
                                number = newNumber(number,5,factor,true);
                                break;
                            case 'I' :
                                number = newNumber(number,1,factor,true);
                                break;
                        }
                        break;
                    case 'L' :
                        number = newNumber(number,50,factor,false);
                        switch (chaPre) {
                            case 'X' :
                                number = newNumber(number,10,factor,true);
                                break;
                            case 'V' :
                                number = newNumber(number,5,factor,true);
                                break;
                            case 'I' :
                                number = newNumber(number,1,factor,true);
                                break;
                        }
                        break;
                    case 'C' :
                        number = newNumber(number,100,factor,false);
                        switch (chaPre) {
                            case 'L' :
                                number = newNumber(number,50,factor,true);
                                break;
                            case 'X' :
                                number = newNumber(number,10,factor,true);
                                break;
                            case 'V' :
                                number = newNumber(number,5,factor,true);
                                break;
                            case 'I' :
                                number = newNumber(number,1,factor,true);
                                break;
                        }
                        break;
                    case 'D' :
                        number = newNumber(number,500,factor,false);
                        switch (chaPre) {
                            case 'C' :
                                number = newNumber(number,100,factor,true);
                                break;
                            case 'L' :
                                number = newNumber(number,50,factor,true);
                                break;
                            case 'X' :
                                number = newNumber(number,10,factor,true);
                                break;
                            case 'V' :
                                number = newNumber(number,5,factor,true);
                                break;
                            case 'I' :
                                number = newNumber(number,1,factor,true);
                                break;
                        }
                        break;
                    case 'M' :
                        number = newNumber(number,1000,factor,false);
                        switch (chaPre) {
                            case 'D' :
                                number = newNumber(number,500,factor,true);
                                break;
                            case 'C' :
                                number = newNumber(number,100,factor,true);
                                break;
                            case 'L' :
                                number = newNumber(number,50,factor,true);
                                break;
                            case 'X' :
                                number = newNumber(number,10,factor,true);
                                break;
                            case 'V' :
                                number = newNumber(number,5,factor,true);
                                break;
                            case 'I' :
                                number = newNumber(number,1,factor,true);
                                break;
                        }
                        break;
                    case '(' :
                        factor ++;
                        break;
                    case ')' :
                        factor = Math.max(0, --factor);
                        break;
                }
            }
            arabic.setText(Integer.toString(number));
        }
    }

    public void translateArabic(InputMethodEvent inputMethodEvent) {
     
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
