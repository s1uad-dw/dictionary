import org.testng.annotations.Test;
import ru.s1aud_dw.Dictionary;
import ru.s1aud_dw.Language;
import ru.s1aud_dw.Word;

import java.io.IOException;
import java.util.NoSuchElementException;

import static org.testng.Assert.*;


public class DictionaryTest {
    Dictionary dict = new Dictionary();

    Word word = new Word("Hitler", "Гитлер");
    Word notContainedWord = new Word("Holocaust", "Холокост");
    String filePath = "data.json";
    Language english = Language.language1;
    Language russian = Language.language2;

    @Test(testName = "Установка языков словаря")
    public void setLanguagesTest() {
        dict.setTitle("Eng-Rus");
        dict.setLanguage1("English");
        dict.setLanguage2("Russian");

    }

    @Test(testName = "Добавление слова в словарь", priority = 1)
    public void addWordTest() {
        dict.addWord(word);
    }

    @Test(testName = "Получение слова, содержащегося в словаре", priority = 2)
    public void getContainedWordTest() {
        assertEquals(word, dict.getWord("Hitler"));
    }

    @Test(testName = "Получение слова, не содержащегося в словаре", priority = 3, expectedExceptions = {NoSuchElementException.class})
    public void getNotContainedWordTest() {
        dict.getWord(notContainedWord.getValue(russian));
    }

    @Test(testName = "Удаление слова, содержащегося в словаре", priority = 4, expectedExceptions = {NoSuchElementException.class})
    public void removeContainedWordTest() {
        dict.removeWord(word);
        dict.getWord(word.getValue(english));
    }

    @Test(testName = "Удаление слова, не содержащегося в словаре", priority = 5, expectedExceptions = {NoSuchElementException.class})
    public void removeNotContainedWordTest() {
        dict.removeWord(notContainedWord);
    }

    @Test(testName = "Удаление слова, содержащегося в словаре по значению", priority = 6, expectedExceptions = {NoSuchElementException.class})
    public void removeContainedWordByValueTest() {
        addWordTest();
        dict.removeWord(word.getValue(english));
        dict.getWord(word.getValue(english));
    }

    @Test(testName = "Удаление слова, не содержащегося в словаре по значению", priority = 7, expectedExceptions = {NoSuchElementException.class})
    public void removeNotContainedWordByValueTest() {
        dict.removeWord(notContainedWord.getValue(english));
    }

    @Test(testName = "Сохранение данных в файл", priority = 8)
    public void saveToFileTest() throws IOException {
        addWordTest();
        setLanguagesTest();
        dict.saveToFile(filePath);
        dict.removeWord(word);
    }

    @Test(testName = "Загрузка данных из файла", priority = 9)
    public void loadFromFileTest() throws IOException {
        addWordTest();
        setLanguagesTest();
        Dictionary loadedDict = Dictionary.loadFromFile(filePath);
        assertEquals(loadedDict.toString(), dict.toString());
        dict.removeWord(word);
    }
}
