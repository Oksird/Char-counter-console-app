package ua.foxminded.charcounter.provider;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.foxminded.charcounter.cache.CacheProvider;
import ua.foxminded.charcounter.domain.CharsCountResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class CharacterCountProviderWithCacheTest {

    @InjectMocks
    private CharacterCountProviderWithCache countProviderWithCache;
    @Mock
    private CacheProvider<String, CharsCountResult> cache;
    @Mock
    private CharacterCountProvider countProvider;

    @Test
    void provideCharsCount_shouldReturnCharacterCountResultAndPutResultInCacheWhenCacheDoNotContainThisResult() {
        String text = "hello world!";

        CharsCountResult countResult = CharsCountResult.builder()
            .withText(text)
            .withCharactersOfString(new ArrayList<>(List.of('h','e','l','o',' ','w','r','d','!')))
            .withResult(new HashMap<>(Map.of('h',1,'e',1,'l',3,'o',
                2,' ',1,'w',1,'r',1,'d',1,'!',1)))
            .build();

        given(cache.isPresent(text)).willReturn(false);
        doNothing().when(cache).put(text, countResult);

        given(countProvider.provideCharsCount(anyString())).willReturn(countResult);

        CharsCountResult actualResult = countProviderWithCache.provideCharsCount(text);

        verify(cache).isPresent(anyString());
        verify(cache).put(anyString(), any());
        verify(countProvider, times(2)).provideCharsCount(anyString());

        assertEquals(countResult, actualResult);
    }

    @Test
    void provideCharsCount_shouldGetResultFromCacheAndReturnItWhenMethodWasCalledBeforeWithTheSameText() {
        String text = "hello world!";

        CharsCountResult countResult = CharsCountResult.builder()
            .withText(text)
            .withCharactersOfString(new ArrayList<>(List.of('h','e','l','o',' ','w','r','d','!')))
            .withResult(new HashMap<>(Map.of('h',1,'e',1,'l',3,'o',
                2,' ',1,'w',1,'r',1,'d',1,'!',1)))
            .build();

        given(cache.isPresent(text)).willReturn(true);
        given(cache.getValue(text)).willReturn(countResult);

        CharsCountResult actualResult = countProviderWithCache.provideCharsCount(text);

        verify(cache).isPresent(anyString());
        verify(cache).getValue(anyString());
        verifyNoInteractions(countProvider);
        assertEquals(countResult, actualResult);
    }
}
