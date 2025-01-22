package org.dbi.kuea;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.dbi.kuea.security.CommonsHashGenerator;
import org.dbi.kuea.security.GuavaHashGenerator;
import org.junit.jupiter.api.Test;

public class HelloHashingTest {

    @Test
    public void SHA256equalityTest() {
        String input = "test123";

        String guavaHash = (new GuavaHashGenerator()).generateSha256Hex(input);
        String commonsHash = (new CommonsHashGenerator()).generateSha256Hex(input);

        System.out.println("Guava Hash: " + guavaHash);
        System.out.println("Commons Hash: " + commonsHash);

        assertEquals(guavaHash, commonsHash, "Fehler");
    }
}
