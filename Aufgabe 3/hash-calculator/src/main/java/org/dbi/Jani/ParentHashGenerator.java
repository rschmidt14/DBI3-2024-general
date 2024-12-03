package org.dbi.Jani;

public abstract class ParentHashGenerator implements HashGenerator {
    public abstract String generateSha256Hex(String source);

    public String generatePlaygroundHash(String source) {
        return "PlaygroundHash: " + source;
    }
}

