package org.dbi.kuea.security;

public interface HashGenerator {
    String generateSha256Hex(String source);
    String generatePlaygroundHash(String source); // Hinzufügen der Methode
}
