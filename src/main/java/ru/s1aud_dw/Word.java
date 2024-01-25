package ru.s1aud_dw;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Word {
    private String english;
    private String russian;

    public Word() {}
    public Word(String english, String russian) {
        this.english = english;
        this.russian = russian;
    }
    @JsonIgnore
    public String getValue(Language language) {
        return switch (language) {
            case English -> english;
            case Russian -> russian;
        };
    }
    @JsonIgnore
    public String[] getValues() {
        return new String[]{english, russian};
    }

    @Override
    public String toString(){
        return english + " = " + russian;
    }
}
