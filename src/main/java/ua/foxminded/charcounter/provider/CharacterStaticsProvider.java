package ua.foxminded.charcounter.provider;

import ua.foxminded.charcounter.domain.CharsCountResult;
import ua.foxminded.charcounter.validator.Validator;

public class CharacterStaticsProvider {
    private final CharsViewProvider charsViewProvider;
    private final Validator validator;
    private final CharacterCountProvider countProvider;

    public CharacterStaticsProvider(CharsViewProvider charsViewProvider, Validator validator, CharacterCountProvider countProvider) {
        this.charsViewProvider = charsViewProvider;
        this.validator = validator;
        this.countProvider = countProvider;
    }

    public String provideStatistics(String text){
        validator.validateChars(text);

        CharsCountResult charsCountResult = countProvider.provideCharsCount(text);

        return charsViewProvider.provideView(charsCountResult);
    }
}
