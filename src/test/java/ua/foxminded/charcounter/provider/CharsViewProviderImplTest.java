package ua.foxminded.charcounter.provider;

import org.junit.jupiter.api.Test;
import ua.foxminded.charcounter.domain.CharsCountResult;

import static org.junit.jupiter.api.Assertions.*;

class CharsViewProviderImplTest {

    private final CharsViewProvider charsViewProvider = new CharsViewProviderImpl();

    @Test
    void provideView_shouldReturnCorrectCountOfEachUniqueChar() {
        String text = "hello world!";
        String expectedResult = """
            hello world!
            "h" - 1
            "e" - 1
            "l" - 3
            "o" - 2
            " " - 1
            "w" - 1
            "r" - 1
            "d" - 1
            "!" - 1
            """;

        CharsCountResult countResult = new CharacterCountProviderImpl().provideCharsCount(text);

        assertEquals(expectedResult, charsViewProvider.provideView(countResult));
    }

    @Test
    void provideView_shouldReturnEmptyStringWhenTextIsEmpty() {
        String text = "";
        String expectedResult = "\n";

        CharsCountResult countResult = new CharacterCountProviderImpl().provideCharsCount(text);

        assertEquals(expectedResult, charsViewProvider.provideView(countResult));
    }
}
