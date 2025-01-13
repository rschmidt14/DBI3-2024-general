package org.dbi.dbi_kursarbeit;

import org.apache.commons.codec.digest.DigestUtils;

public class CommonsHashGenerator extends ParentHashGenerator {
    @Override
    public String generateHash256Hex(String input) {
        return DigestUtils.sha256Hex(input); // Returns the hash as a hex-encoded string
    }
}

