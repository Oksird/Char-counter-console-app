package ua.foxminded.charcounter.provider;

import ua.foxminded.charcounter.domain.CharsCountResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;

public class CharacterCountProviderImpl implements CharacterCountProvider {
    public CharsCountResult provideCharsCount(String str) {

        TreeSet<Character> charactersOfString = new TreeSet<>();
        HashMap<Character, Integer> result = new HashMap<>();
        ArrayList<Character> characterArrayList = new ArrayList<>();

            for (int i = 0; i < str.length(); i++) {
                if (!charactersOfString.contains(str.charAt(i))) {
                    characterArrayList.add(str.charAt(i));
                    charactersOfString.add(str.charAt(i));
                    result.put(str.charAt(i), 1);
                } else {
                    result.merge(str.charAt(i), 1, Integer::sum);
                }
            }

        return CharsCountResult.builder()
                .withText(str)
                .withResult(result)
                .withCharactersOfString(characterArrayList)
                .build();
    }
}
