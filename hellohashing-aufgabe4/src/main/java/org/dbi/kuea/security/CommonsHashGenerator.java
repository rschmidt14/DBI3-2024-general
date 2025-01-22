package org.dbi.kuea.security;

import org.apache.commons.codec.digest.DigestUtils;

public class CommonsHashGenerator extends org.dbi.kuea.security.ParentHashGenerator {
    @Override
    public String generateSha256Hex(String source) {
        return DigestUtils.sha256Hex(source); // Gibt den Hash als Hex-String zurück
    }
}
