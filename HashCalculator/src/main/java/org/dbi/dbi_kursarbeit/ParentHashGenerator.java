package org.dbi.dbi_kursarbeit;

public abstract class ParentHashGenerator implements HashGenerator {
    public String generatePlaygroundHash(String input) {
        return "Length of input string: " + input.length();
    }
}