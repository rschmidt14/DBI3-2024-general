package org.dbi.kuea.security;

public abstract class ParentHashGenerator implements org.dbi.kuea.security.HashGenerator {
    public String generatePlaygroundHash(String source) {
        // Liefert die Länge des Eingabestrings zurück
        return String.valueOf(source.length());
    }
}
