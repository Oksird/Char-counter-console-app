package ua.foxminded.charcounter.provider;

import ua.foxminded.charcounter.domain.CharsCountResult;

public interface CharsViewProvider {
    String provideView(CharsCountResult charsCountResult);
}
