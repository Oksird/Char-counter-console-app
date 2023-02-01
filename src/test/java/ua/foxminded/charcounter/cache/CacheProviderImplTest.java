package ua.foxminded.charcounter.cache;

import org.junit.jupiter.api.Test;
import ua.foxminded.charcounter.domain.CharsCountResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CacheProviderImplTest {

    private final CacheProvider<String, CharsCountResult> cache = new CacheProviderImpl<>(2);

    @Test
    void getValue_shouldReturnValueWhenCacheContainsIt() {
        cache.put("key1", CharsCountResult.builder().withText("text1").build());
        cache.put("key2", CharsCountResult.builder().withText("text2").build());

        assertEquals(CharsCountResult.builder().withText("text1").build(), cache.getValue("key1"));
        assertEquals(CharsCountResult.builder().withText("text2").build(), cache.getValue("key2"));
    }

    @Test
    void getValue_shouldReturnNullWhenCacheDoesNotContainIt() {
        cache.put("key0", CharsCountResult.builder().withText("some text").build());
        assertNull(cache.getValue("text1"));
    }

    @Test
    void put_shouldPutKeyValuePairInCacheWhenCacheDoesNotContainIt() {
        cache.put("key1", CharsCountResult.builder().withText("text1").build());
        cache.put("key2", CharsCountResult.builder().withText("text2").build());

        assertTrue(cache.isPresent("key1"));
        assertTrue(cache.isPresent("key2"));
    }

    @Test
    void put_shouldRemoveTheLeastRecentlyUsedValueWhileAddingThisValue() {
        cache.put("key1", CharsCountResult.builder().withText("text1").build());
        cache.put("key2", CharsCountResult.builder().withText("text2").build());
        cache.put("key3", CharsCountResult.builder().withText("text3").build());

        assertNull(cache.getValue("key1"));
        assertTrue(cache.isPresent("key2"));
        assertTrue(cache.isPresent("key3"));
    }

    @Test
    void isPresent_shouldReturnTrueWhenCacheContainsValue() {
        cache.put("key1", CharsCountResult.builder().withText("text1").build());
        assertTrue(cache.isPresent("key1"));
    }

    @Test
    void isPresent_shouldReturnFalseWhenCacheDoesNotContainValue() {
        assertFalse(cache.isPresent("any key that we don't have"));
    }
}
