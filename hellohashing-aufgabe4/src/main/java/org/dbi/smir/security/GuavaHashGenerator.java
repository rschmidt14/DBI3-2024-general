package org.dbi.smir.security;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;

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
