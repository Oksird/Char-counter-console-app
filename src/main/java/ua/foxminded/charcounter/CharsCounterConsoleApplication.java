package ua.foxminded.charcounter;

import ua.foxminded.charcounter.cache.CacheProvider;
import ua.foxminded.charcounter.cache.CacheProviderImpl;
import ua.foxminded.charcounter.domain.CharsCountResult;
import ua.foxminded.charcounter.provider.CharacterCountProvider;
import ua.foxminded.charcounter.provider.CharacterCountProviderImpl;
import ua.foxminded.charcounter.provider.CharacterCountProviderWithCache;
import ua.foxminded.charcounter.provider.CharacterStaticsProvider;
import ua.foxminded.charcounter.provider.CharsViewProvider;
import ua.foxminded.charcounter.provider.CharsViewProviderImpl;
import ua.foxminded.charcounter.validator.Validator;
import ua.foxminded.charcounter.validator.ValidatorImpl;

public class CharsCounterConsoleApplication {
    public static void main(String[] args) {

        int cacheCapacity = 100;
        String chars = "hello world!";

        CacheProvider<String, CharsCountResult> cacheProvider = new CacheProviderImpl<>(cacheCapacity);
        CharacterCountProvider characterCountProvider = new CharacterCountProviderWithCache(
            new CharacterCountProviderImpl(), cacheProvider);
        CharsViewProvider charsViewProvider = new CharsViewProviderImpl();
        Validator validator = new ValidatorImpl();

        CharacterStaticsProvider characterStaticsProvider
                = new CharacterStaticsProvider(charsViewProvider, validator, characterCountProvider);


        String result = characterStaticsProvider.provideStatistics(chars);

        System.out.println(result);
    }
}
