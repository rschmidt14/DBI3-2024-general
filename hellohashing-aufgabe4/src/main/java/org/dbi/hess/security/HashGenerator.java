package org.dbi.hess.security;

public interface HashGenerator
{
    String generateSha256Hex(String source);

    String generatePlaygroundHash(String source);
}
