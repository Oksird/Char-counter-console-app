package ua.foxminded.charcounter.provider;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.foxminded.charcounter.domain.CharsCountResult;
import ua.foxminded.charcounter.validator.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class CharacterStaticsProviderTest {

    @InjectMocks
    private CharacterStaticsProvider characterStaticsProvider;
    @Mock
    private Validator validator;
    @Mock
    private CharacterCountProvider countProvider;
    @Mock
    private CharsViewProvider charsViewProvider;

    @Test
    void countChars_shouldReturnStringWithCorrectStatisticsOfCharsWhenTextIsNotNull() {
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

        CharsCountResult countResult = CharsCountResult.builder()
            .withText(text)
            .withCharactersOfString(new ArrayList<>(List.of('h','e','l','o',' ','w','r','d','!')))
            .withResult(new HashMap<>(Map.of('h',1,'e',1,'l',3,'o',
                2,' ',1,'w',1,'r',1,'d',1,'!',1)))
            .build();

        doNothing().when(validator).validateChars(text);

        given(countProvider.provideCharsCount(text)).willReturn(countResult);

        given(charsViewProvider.provideView(countProvider.provideCharsCount(text)))
            .willReturn(expectedResult);

        String actualResult = characterStaticsProvider.provideStatistics(text);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    void countChars_shouldThrowIllegalArgumentExceptionWhenInputTextIsNull() {

        doThrow(new IllegalArgumentException("String can not be null"))
            .when(validator)
            .validateChars(null);

        Exception exception = assertThrows(
            IllegalArgumentException.class, () -> characterStaticsProvider.provideStatistics(null));

        verifyNoInteractions(charsViewProvider, countProvider);

        String expectedMessage = "String can not be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
