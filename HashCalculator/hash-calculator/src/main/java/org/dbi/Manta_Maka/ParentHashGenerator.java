package org.dbi.Manta_Maka;

public class ParentHashGenerator implements HashGenerator
{
    public String generatePlaygroundHash(String source) {
        //something simpler than source.hashCode()
        return ""+source.length();
    }

    public String generateSha256Hex(String source)
    {
        return "Method not implemented";
    }
}