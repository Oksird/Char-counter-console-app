package ua.foxminded.charcounter.provider;

import ua.foxminded.charcounter.domain.CharsCountResult;

public interface CharacterCountProvider {
    CharsCountResult provideCharsCount(String str);
}
