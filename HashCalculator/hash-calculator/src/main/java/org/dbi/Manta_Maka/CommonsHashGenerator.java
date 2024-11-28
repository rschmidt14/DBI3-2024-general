package org.dbi.Manta_Maka;

import org.apache.commons.codec.digest.DigestUtils;

public class CommonsHashGenerator extends ParentHashGenerator
{
    @Override
    public String generateSha256Hex(String source)
    {
        return DigestUtils.sha256Hex(source);
    }
}