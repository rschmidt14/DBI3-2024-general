package org.dbi.hess.security;

import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;

public class GuavaHashGenerator extends ParentHashGenerator
{
    @Override
    public String generateSha256Hex(String source)
    {
        return Hashing.sha256()
            .hashString(source, StandardCharsets.UTF_8)
            .toString();
    }
}
