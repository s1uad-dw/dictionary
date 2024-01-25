package ru.s1aud_dw;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.*;
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

    public Word getWord(String word) throws NoSuchElementException {
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
        Word wordObj = this.getWord(word);
        this.removeWord(wordObj);
    }

    public void saveToFile(String filepath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(filepath), this);
    }

    public static Dictionary loadFromFile(String filepath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filepath), Dictionary.class);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for(Word wordInDict : this){
            string.append(wordInDict.toString()).append("\n");
        }
        return string.toString();
    }
}
