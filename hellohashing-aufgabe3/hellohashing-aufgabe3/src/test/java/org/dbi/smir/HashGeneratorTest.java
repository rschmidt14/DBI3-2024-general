package org.dbi.security;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HashGeneratorTest {
    @Test
    public void testSha256HashConsistency() {
        String input = "testPassword";

        GuavaHashGenerator guavaGenerator = new GuavaHashGenerator();
        CommonsHashGenerator commonsGenerator = new CommonsHashGenerator();

        assertEquals(guavaGenerator.generateSha256Hash(input), commonsGenerator.generateSha256Hash(input));
    }
}



