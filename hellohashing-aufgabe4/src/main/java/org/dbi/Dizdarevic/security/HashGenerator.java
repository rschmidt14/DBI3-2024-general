package org.dbi.smir.security;

public interface HashGenerator
{
    String generateSha256Hex(String source);

    String generatePlaygroundHash(String source);
}
