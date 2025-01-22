package org.dbi.kuea.security;

import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;

public class GuavaHashGenerator extends org.dbi.kuea.security.ParentHashGenerator {
    @Override
    public String generateSha256Hex(String source) {
        return Hashing.sha256()
                .hashString(source, StandardCharsets.UTF_8)
                .toString(); // Gibt den Hash als Hex-String zurück
    }
}
