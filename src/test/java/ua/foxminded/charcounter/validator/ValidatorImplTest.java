package ua.foxminded.charcounter.validator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


class ValidatorImplTest {

    private final Validator validator = new ValidatorImpl();

    @Test
    void validate_shouldThrowIllegalArgumentExceptionWhenTextIsNull(){
        Exception exception =assertThrows(
            IllegalArgumentException.class, () -> validator.validateChars(null));

        String exceptionMessage = "String can not be null";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(exceptionMessage));
    }

    @Test
    void validate_shouldNotThrowExceptionWhenArgumentsAreCorrect() {
        assertDoesNotThrow(() -> validator.validateChars("hello!"));
    }
}
