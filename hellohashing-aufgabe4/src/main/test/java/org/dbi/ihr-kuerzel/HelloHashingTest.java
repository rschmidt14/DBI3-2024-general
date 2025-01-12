package org.dbi.Dizdarevic;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.dbi.Dizdarevic.security.CommonsHashGenerator;
import org.dbi.Dizdarevic.security.GuavaHashGenerator;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class HelloHashingTest {

    /**
     * Rigorous Test :-)
     */
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
