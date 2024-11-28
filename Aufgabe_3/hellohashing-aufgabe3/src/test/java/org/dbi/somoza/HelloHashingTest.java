package org.dbi.somoza;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.dbi.somoza.security.CommonsHashGenerator;
import org.dbi.somoza.security.GuavaHashGenerator;
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
        //not sure that this is a correct unit test??!
        assertTrue(guava.equals(commons));
    }

}
