package ua.foxminded.charcounter.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class CharsCountResult {
    private final String text;
    private final ArrayList<Character> charactersOfString;
    private final HashMap<Character, Integer> result;

    private CharsCountResult(Builder builder) {
        this.text = builder.text;
        this.charactersOfString = builder.charactersOfString;
        this.result = builder.result;
    }

    public String getText() {
        return text;
    }

    public ArrayList<Character> getCharactersOfString() {
        return charactersOfString;
    }

    public HashMap<Character, Integer> getResult() {
        return result;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (o.getClass() != this.getClass()) {
            return false;
        }

        final CharsCountResult other = (CharsCountResult) o;

        return Objects.equals(result, other.result)
            && Objects.equals(text, other.text)
            && Objects.equals(charactersOfString, other.charactersOfString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, charactersOfString, result);
    }

    @Override
    public String toString() {
        return "CharsCountResult{" +
                "text='" + text + '\'' +
                ", charactersOfString=" + charactersOfString +
                ", result=" + result +
                '}';
    }

    public static class Builder {
        private String text;
        private ArrayList<Character> charactersOfString;
        private HashMap<Character, Integer> result;

        private Builder(){

        }

        public Builder withText(String text){
            this.text = text;
            return this;
        }

        public Builder withCharactersOfString(ArrayList<Character> charactersOfString){
            this.charactersOfString = charactersOfString;
            return this;
        }

        public Builder withResult(HashMap<Character, Integer> result){
            this.result = result;
            return this;
        }

        public CharsCountResult build(){
            return new CharsCountResult(this);
        }
    }
}
