package org.dbi.Jani;

public interface HashGenerator {
    String generateSha256Hex(String source);
    String generatePlaygroundHash(String source);
}



