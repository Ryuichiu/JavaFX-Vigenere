package sample;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

public class ControllerPattern {
    public static void main(String[] args) {
        isValidRomanPattern("");
    }

    public static boolean isValidRomanPattern(@NotNull String romanText) {
        boolean onlyRoman = !Pattern.compile("[^()IVXLCDM]").matcher(romanText).find();
        if (romanText.isEmpty() || !onlyRoman) return false;

        AtomicBoolean noIllegalPattern = new AtomicBoolean(true);
        List<String> subStringList = getSubStrings(romanText);

        subStringList.forEach(s -> {
            if (s.matches("[IXC]+") && s.length() > 3) noIllegalPattern.set(false);
            if (s.matches("[VLDM]+") && s.length() > 1) noIllegalPattern.set(false);
        });

        String brackets = romanText.replaceAll("[^()]","");
        return noIllegalPattern.get() && validBrackets(brackets);
    }

    private static boolean validBrackets (@NotNull String brackets) {
        if (brackets.isEmpty()) return true;
        int openBrackets = brackets.replaceAll("[(]","").length();
        int closingBrackets = brackets.replaceAll("[)]","").length();
        AtomicInteger countBrackets = new AtomicInteger();

        for (int i = 0; i < brackets.length(); i++) {
            if (brackets.charAt(i) == '(') countBrackets.getAndIncrement();
            if (brackets.charAt(i) == ')') {
                if (countBrackets.decrementAndGet() < 0) return false;
            }
        }

        return openBrackets == closingBrackets && countBrackets.get() == 0;
    }

    private static List<String> getSubStrings(@NotNull String text) {
        List<String> list = new ArrayList<>();

        int left = 0;
        char ch = 0;

        for (int i = 0; i < text.length(); i++) {
            char chNew = text.charAt(i);
            if (ch != 0 && chNew != ch) {
                list.add(text.substring(left, i));
                left = i;
            }
            if (i == text.length() - 1)
                list.add(text.substring(left));
            ch = chNew;
        }

        return list;
    }
}
