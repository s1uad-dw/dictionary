package ru.s1uad_dw;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Word {
    private String language1;
    private String language2;

    public Word() {}
    public Word(String language1, String language2) {
        this.language1 = language1;
        this.language2 = language2;
    }
    @JsonIgnore
    public String getValue(Language language) {
        return switch (language) {
            case language1 -> language1;
            case language2 -> language2;
        };
    }
    @JsonIgnore
    public String[] getValues() {
        return new String[]{language1, language2};
    }

    @Override
    public String toString(){
        return String.format("%-15s -> %-15s", language1, language2);
    }
}
