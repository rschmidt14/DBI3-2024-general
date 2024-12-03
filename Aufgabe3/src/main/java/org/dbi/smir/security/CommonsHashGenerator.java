package org.dbi.smir.security;


import org.apache.commons.codec.digest.DigestUtils;

public class CommonsHashGenerator extends ParentHashGenerator
{
    @Override
    public String generateSha256Hex(String source)
    {

        String sha256hex = DigestUtils.sha256Hex(source);
        return sha256hex;
    }
}
