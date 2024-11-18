package org.dbi.Manta-Maka;

import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;

public class GuavaHashGenerator extends ParentHashGenerator
{
    @Override
    public String generateSha256Hex(String source)
    {
        String sha256hex = Hashing.sha256()
                .hashString(source, StandardCharsets.UTF_8)
                .toString();
        return sha256hex;
    }
}
