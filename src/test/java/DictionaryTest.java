import org.testng.annotations.Test;
import ru.s1aud_dw.Dictionary;
import ru.s1aud_dw.Word;

import java.util.NoSuchElementException;

import static org.testng.Assert.assertEquals;
import static ru.s1aud_dw.Language.*;


public class DictionaryTest {
    Dictionary dict = new Dictionary();
    Word word = new Word("Hitler", "Гитлер");

    @Test(testName = "Добавление слова в словарь", priority = 0)
    public void addWordTest() {
        dict.addWord(word);
    }

    @Test(testName = "Получение слова, содержащегося в словаре", priority = 1)
    public void getContainedWordTest() {
        assertEquals(word, dict.getWord("Hitler", English));
    }

    @Test(testName = "Получение слова, не содержащегося в словаре", expectedExceptions = {NoSuchElementException.class})
    public void getNotContainedWordTest() {
        assertEquals(word, dict.getWord("Stalin", English));
    }
}
