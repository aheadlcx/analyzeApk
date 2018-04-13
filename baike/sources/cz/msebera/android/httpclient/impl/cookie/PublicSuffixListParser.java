package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.conn.util.PublicSuffixList;
import java.io.IOException;
import java.io.Reader;

@Immutable
@Deprecated
public class PublicSuffixListParser {
    private final PublicSuffixFilter a;
    private final cz.msebera.android.httpclient.conn.util.PublicSuffixListParser b;

    public void parse(Reader reader) throws IOException {
        PublicSuffixList parse = this.b.parse(reader);
        this.a.setPublicSuffixes(parse.getRules());
        this.a.setExceptions(parse.getExceptions());
    }
}
