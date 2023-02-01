package ua.foxminded.charcounter.provider;

import ua.foxminded.charcounter.domain.CharsCountResult;

import java.util.ArrayList;
import java.util.HashMap;

public class CharsViewProviderImpl implements CharsViewProvider {
    @Override
    public String provideView(CharsCountResult charsCountResult) {
        StringBuilder resultToPrint = new StringBuilder();
        HashMap<Character, Integer> countOfEachChar = charsCountResult.getResult();
        ArrayList<Character> charactersOfString = charsCountResult.getCharactersOfString();
        String originalString = charsCountResult.getText();

        resultToPrint.append(originalString).append("\n");

        for (Character character : charactersOfString) {
            resultToPrint.append("\"").append(character).append("\"").append(" - ")
                    .append(countOfEachChar.get(character)).append("\n");
        }

        return resultToPrint.toString();
    }
}
