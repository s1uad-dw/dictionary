package ru.s1aud_dw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Dictionary extends ArrayList<Word> {

    public void addWord(Word word) {
        this.add(word);
    }

    public Word getWord(String word, Language language) throws NoSuchElementException {
        for (Word wordInDict : this) {
            if (Arrays.asList(wordInDict.getValues()).contains(word))
                return wordInDict;
        }
        throw new NoSuchElementException();
    }
}
