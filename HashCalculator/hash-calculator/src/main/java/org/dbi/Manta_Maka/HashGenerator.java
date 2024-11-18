package org.dbi.Manta_Maka;

public interface HashGenerator
{
    String generateSha256Hex(String source);
    String generatePlaygroundHash(String source);
}
