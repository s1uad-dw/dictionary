import org.testng.annotations.Test;
import ru.s1aud_dw.Dictionary;
import ru.s1aud_dw.Language;
import ru.s1aud_dw.Word;

import java.util.NoSuchElementException;

import static org.testng.Assert.assertEquals;


public class DictionaryTest {
    Dictionary dict = new Dictionary();
    Word word = new Word("Hitler", "Гитлер");
    Word notContainedWord = new Word("Holocaust", "Холокост");

    @Test(testName = "Добавление слова в словарь", priority = 0)
    public void addWordTest() {
        dict.addWord(word);
    }

    @Test(testName = "Получение слова, содержащегося в словаре", priority = 1)
    public void getContainedWordTest() {
        assertEquals(word, dict.getWord("Hitler", Language.English));
    }

    @Test(testName = "Получение слова, не содержащегося в словаре", priority = 2, expectedExceptions = {NoSuchElementException.class})
    public void getNotContainedWordTest() {
        dict.getWord(notContainedWord.getValue(Language.English), Language.English);
    }

    @Test(testName = "Удаление слова, содержащегося в словаре", priority = 3, expectedExceptions = {NoSuchElementException.class})
    public void removeContainedWordTest(){
        dict.removeWord(word);
        dict.getWord(word.getValue(Language.English), Language.English);
    }

    @Test(testName = "Удаление слова, не содержащегося в словаре", priority = 4, expectedExceptions = {NoSuchElementException.class})
    public void removeNotContainedWordTest(){
        dict.removeWord(notContainedWord);
    }

    @Test(testName = "Удаление слова, содержащегося в словаре по значению", priority = 5, expectedExceptions = {NoSuchElementException.class})
    public void removeContainedWordByValueTest(){
        addWordTest();
        dict.removeWord(word.getValue(Language.English), Language.English);
        dict.getWord(word.getValue(Language.English), Language.English);
    }
    @Test(testName = "Удаление слова, не содержащегося в словаре по значению", priority = 6, expectedExceptions = {NoSuchElementException.class})
    public void removeNotContainedWordByValueTest(){
        addWordTest();
        dict.removeWord(notContainedWord.getValue(Language.English), Language.English);
    }
}
