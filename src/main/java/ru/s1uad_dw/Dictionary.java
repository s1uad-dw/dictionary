package ru.s1uad_dw;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
@Getter@Setter
public class Dictionary{
    @JsonProperty
    private String title;
    @JsonProperty
    private ArrayList<Word> words = new ArrayList<>();
    @JsonProperty
    private String language1;
    @JsonProperty
    private String language2;
    public Dictionary(){}
    public Dictionary(String title, String language1, String language2){
        this.title = title;
        this.language1 = language1;
        this.language2 = language2;
    }

    public void addWord(Word word) {
        words.add(word);
    }

    public void addWord(String language1, String language2) {
        words.add(new Word(language1, language2));
    }

    public Word getWord(String word) throws NoSuchElementException {
        for (Word wordInDict : words) {
            if (Arrays.asList(wordInDict.getValues()).contains(word))
                return wordInDict;
        }
        throw new NoSuchElementException();
    }

    public void removeWord(Word word) throws NoSuchElementException {
        if (!words.remove(word)) {
            throw new NoSuchElementException();
        }
    }
    public void removeWord(String word) {
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
        for(Word wordInDict : words){
            string.append(wordInDict.toString()).append("\n");
        }
        return string.toString();
    }
}
