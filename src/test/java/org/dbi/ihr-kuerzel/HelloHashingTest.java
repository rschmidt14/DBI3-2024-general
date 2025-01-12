package org.dbi.hess;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.dbi.hess.security.CommonsHashGenerator;
import org.dbi.hess.security.GuavaHashGenerator;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class HelloHashingTest {

    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void SHA256equalityTest() {
        String guava = (new GuavaHashGenerator()).generateSha256Hex("myPassword");
        String commons = (new CommonsHashGenerator()).generateSha256Hex("myPassword");

        assertTrue(guava.equals(commons));
    }
}
