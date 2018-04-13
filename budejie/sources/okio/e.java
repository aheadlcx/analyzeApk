package okio;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public interface e extends r {
    long a(byte b) throws IOException;

    long a(q qVar) throws IOException;

    String a(Charset charset) throws IOException;

    void a(long j) throws IOException;

    boolean a(long j, ByteString byteString) throws IOException;

    ByteString c(long j) throws IOException;

    c c();

    boolean f() throws IOException;

    byte[] f(long j) throws IOException;

    InputStream g();

    void g(long j) throws IOException;

    byte i() throws IOException;

    short j() throws IOException;

    int k() throws IOException;

    short l() throws IOException;

    int m() throws IOException;

    long n() throws IOException;

    long o() throws IOException;

    String r() throws IOException;
}
