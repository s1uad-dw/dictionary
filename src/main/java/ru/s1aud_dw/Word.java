package ru.s1aud_dw;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Word {
    private String English;
    private String Russian;

//    Word(Language language, String value) {
//        switch (language){
//            case English -> this.English=value;
//            case Russian -> this.Russian=value;
//        }
//    }

    public Word(String english, String russian) {
        English = english;
        Russian = russian;
    }

    public String getValue(Language language) {
        return switch (language) {
            case English -> English;
            case Russian -> Russian;
        };
    }

    public String[] getValues() {
        return new String[]{English, Russian};
    }
}
