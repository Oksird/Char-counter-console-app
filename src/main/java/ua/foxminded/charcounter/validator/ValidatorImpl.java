package ua.foxminded.charcounter.validator;

public class ValidatorImpl implements Validator {
    @Override
    public void validateChars(String text) {
        if (text == null){
            throw new IllegalArgumentException("String can not be null");
        }
    }
}
