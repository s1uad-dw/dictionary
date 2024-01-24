package ru.s1aud_dw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Dictionary extends ArrayList<Word> {

    public void addWord(Word word) {
        this.add(word);
    }

    public void addWord(String english, String russian) {
        this.add(new Word(english, russian));
    }

    public Word getWord(String word, Language language) throws NoSuchElementException {
        for (Word wordInDict : this) {
            if (Arrays.asList(wordInDict.getValues()).contains(word))
                return wordInDict;
        }
        throw new NoSuchElementException();
    }

    public void removeWord(Word word) throws NoSuchElementException {
        if (!this.remove(word)) {
            throw new NoSuchElementException();
        }
    }

    public void removeWord(String word, Language language) {
        Word wordObj = this.getWord(word, language);
        this.removeWord(wordObj);
    }
}
