package org.dbi.dbi_kursarbeit;


import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;

public class GuavaHashGenerator extends ParentHashGenerator {
    @Override
    public String generateHash256Hex(String input) {
        return Hashing.sha256()
                .hashString(input, StandardCharsets.UTF_8)
                .toString();
    }
}
