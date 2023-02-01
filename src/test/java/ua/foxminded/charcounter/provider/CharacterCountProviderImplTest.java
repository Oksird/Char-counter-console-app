package ua.foxminded.charcounter.provider;

import org.junit.jupiter.api.Test;
import ua.foxminded.charcounter.domain.CharsCountResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


class CharacterCountProviderImplTest {

    CharacterCountProvider countProvider = new CharacterCountProviderImpl();
    @Test
    void provideCharsCount_shouldReturnCharsCountResultWithCorrectFieldsWhenTextContainsCharacters() {
        String text = "hello world!";

        CharsCountResult expectedResult = CharsCountResult.builder()
            .withText(text)
            .withCharactersOfString(new ArrayList<>(List.of('h','e','l','o',' ','w','r','d','!')))
            .withResult(new HashMap<>(Map.of('h',1,'e',1,'l',3,'o',
                2,' ',1,'w',1,'r',1,'d',1,'!',1)))
            .build();

        assertEquals(expectedResult, countProvider.provideCharsCount(text));
    }

    @Test
    void provideCharsCount_shouldReturnCharsCountResultWithEmptyFieldsWhenTextIsEmpty() {
        String text = "";

        CharsCountResult expectedResult = CharsCountResult.builder()
            .withResult(new HashMap<>())
            .withText("")
            .withCharactersOfString(new ArrayList<>())
            .build();

        assertEquals(expectedResult, countProvider.provideCharsCount(text));
    }
}
