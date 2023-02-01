package ua.foxminded.charcounter.provider;

import ua.foxminded.charcounter.cache.CacheProvider;
import ua.foxminded.charcounter.domain.CharsCountResult;

public class CharacterCountProviderWithCache implements CharacterCountProvider{

    private final CharacterCountProvider characterCountProvider;
    private final CacheProvider<String, CharsCountResult> cacheProvider;

    public CharacterCountProviderWithCache(CharacterCountProvider characterCountProvider,
                                           CacheProvider<String, CharsCountResult> cacheProvider) {
        this.cacheProvider = cacheProvider;
        this.characterCountProvider = characterCountProvider;
    }

    @Override
    public CharsCountResult provideCharsCount(String text) {
        if (!cacheProvider.isPresent(text)){
            cacheProvider.put(text, characterCountProvider.provideCharsCount(text));
            return  characterCountProvider.provideCharsCount(text);
        }
        return cacheProvider.getValue(text);
    }
}
