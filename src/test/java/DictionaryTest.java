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

    @Test(testName = "Добавление слова в словарь")
    public void addWordTest() {
        dict.addWord(word);
    }

    @Test(testName = "Получение слова, содержащегося в словаре", priority = 1)
    public void getContainedWordTest() {
        assertEquals(word, dict.getWord("Hitler"));
    }

    @Test(testName = "Получение слова, не содержащегося в словаре", priority = 2, expectedExceptions = {NoSuchElementException.class})
    public void getNotContainedWordTest() {
        dict.getWord(notContainedWord.getValue(Language.English));
    }

    @Test(testName = "Удаление слова, содержащегося в словаре", priority = 3, expectedExceptions = {NoSuchElementException.class})
    public void removeContainedWordTest(){
        dict.removeWord(word);
        dict.getWord(word.getValue(Language.English));
    }

    @Test(testName = "Удаление слова, не содержащегося в словаре", priority = 4, expectedExceptions = {NoSuchElementException.class})
    public void removeNotContainedWordTest(){
        dict.removeWord(notContainedWord);
    }

    @Test(testName = "Удаление слова, содержащегося в словаре по значению", priority = 5, expectedExceptions = {NoSuchElementException.class})
    public void removeContainedWordByValueTest(){
        addWordTest();
        dict.removeWord(word.getValue(Language.English));
        dict.getWord(word.getValue(Language.English));
    }
    @Test(testName = "Удаление слова, не содержащегося в словаре по значению", priority = 6, expectedExceptions = {NoSuchElementException.class})
    public void removeNotContainedWordByValueTest(){
        dict.removeWord(notContainedWord.getValue(Language.English));
    }

    @Test(testName = "Сохранение данных в файл", priority = 7)
    public void saveToFileTest() throws IOException {
        addWordTest();
        dict.saveToFile(filePath);
        dict.removeWord(word);
    }
    @Test(testName = "Загрузка данных из файла", priority = 8)
    public void loadFromFileTest() throws IOException {
        addWordTest();
        Dictionary loadedDict = Dictionary.loadFromFile(filePath);
        assertEquals(loadedDict.toString(), dict.toString());
        dict.removeWord(word);
    }
}
