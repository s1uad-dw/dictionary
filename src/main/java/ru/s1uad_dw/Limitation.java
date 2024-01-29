package ru.s1uad_dw;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Limitation {
    private boolean allowedDigits;
    private boolean allowedLetters;
    private boolean allowedSpaces;
    private boolean allowedSymbols;
    private int maxSize;
    public Limitation(){
        allowedDigits = true;
        allowedLetters = true;
        allowedSpaces = true;
        allowedSymbols = true;
        maxSize = Integer.MAX_VALUE;
    }
    public Limitation(boolean allowedDigits, boolean allowedLetters, boolean allowedSpaces,
                      boolean allowedSymbols){
        this.allowedDigits = allowedDigits;
        this.allowedLetters = allowedLetters;
        this.allowedSpaces = allowedSpaces;
        this.allowedSymbols = allowedSymbols;
        this.maxSize = Integer.MAX_VALUE;
    }
    public Limitation(boolean allowedDigits, boolean allowedLetters, boolean allowedSpaces,
                      boolean allowedSymbols, int maxSize){
        this.allowedDigits = allowedDigits;
        this.allowedLetters = allowedLetters;
        this.allowedSpaces = allowedSpaces;
        this.allowedSymbols = allowedSymbols;
        this.maxSize = maxSize;
    }
    public boolean check(String word){
        if (word.length()>maxSize) return false;
        for (char c : word.toCharArray()){
            boolean isDigit = Character.isDigit(c);
            boolean isLetter = Character.isLetter(c);
            boolean isSpace =Character.isSpaceChar(c);
            if (isDigit && !allowedDigits) return false;
            else if (isLetter && !allowedLetters) return false;
            else if (isSpace && !allowedSpaces) return false;
            else if (!isDigit && !isLetter && !isSpace && !allowedSymbols) return false;
        }
        return true;
    }
    @Override
    public String toString(){
        return "Максимально-допустимая длинна слова: " + maxSize +
                "\nМожно использовать буквы в словах: " + (allowedLetters ? "+" : "-") +
                "\nМожно использовать цифры в словах: " + (allowedDigits ? "+" : "-") +
                "\nМожно использовать пробелы в словах: " + (allowedSpaces ? "+" : "-") +
                "\nМожно использовать символы в словах: " + (allowedSymbols ? "+" : "-");
    }
}
