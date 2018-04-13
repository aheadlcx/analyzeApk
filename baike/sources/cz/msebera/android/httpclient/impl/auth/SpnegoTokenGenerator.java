package cz.msebera.android.httpclient.impl.auth;

import java.io.IOException;

@Deprecated
public interface SpnegoTokenGenerator {
    byte[] generateSpnegoDERObject(byte[] bArr) throws IOException;
}
