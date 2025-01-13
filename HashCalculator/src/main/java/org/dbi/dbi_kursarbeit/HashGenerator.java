package org.dbi.dbi_kursarbeit;

/**
 * Interface for Hash Generators.
 */
public interface HashGenerator {
    /**
     * Generates a SHA256 hash for the given input string and returns it as a hex-encoded string.
     *
     * @param input The input string to hash.
     * @return The hex-encoded SHA256 hash.
     */
    String generateHash256Hex(String input);
}
