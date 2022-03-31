package model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class WordHistoryTest {
    WordHistory history;
    Word word;
    Guessing guess;

    @BeforeEach
    public void runBefore() {
        history = new WordHistory();
        guess = new Guessing(history);
        word = new Word("tests", guess, history);
    }

    @Test
    public void testAddWord() {
        history.addToHistory(word);
        assertEquals(1, history.getImmutableWordHistory().size());
    }

    @Test
    public void testAddWordTwice() {
        history.addToHistory(word);
        history.addToHistory(word);
        assertEquals(2, history.getImmutableWordHistory().size());
    }

    // clears the history
    @Test
    public void testClearHistory() {
        history.addToHistory(word);
        history.clear();
        assertEquals(0, history.getImmutableWordHistory().size());
    }

    // test setWordHistory
    @Test
    public void testSetWordHistory() {
        ArrayList<Word> wordList = new ArrayList<>();
        wordList.add(word);
        history.setWordHistory(wordList);
        assertEquals(1, history.getImmutableWordHistory().size());
    }

    // test toJson
    @Test
    public void testToJson() {
        history.addToHistory(word);
        // get the word history currently
        ArrayList<Word> originalWordList = history.getImmutableWordHistory();
        String jsonString = history.toJson();

        // convert the json string back to a WordHistory object
        Gson gson = new Gson();
        Type words = new TypeToken<Collection<Word>>(){}.getType();
        Collection<Word> enums = gson.fromJson(jsonString, words);
        WordHistory newHistory = new WordHistory();
        newHistory.setWordHistory(enums);

        ArrayList<Word> newWordList = history.getImmutableWordHistory();

        assertEquals(originalWordList, newWordList);
    }

    // add 11 words to the history for testTrimHistory
    @Test
    public void testTrimHistory() {
        for (int i = 0; i < 11; i++) {
            history.addToHistory(word);
        }
        // trimHistory should have limited the history to 10
        assertEquals(10, history.getImmutableWordHistory().size());
    }

}