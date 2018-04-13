package okio;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public final class HashingSource extends ForwardingSource {
    private final MessageDigest a;
    private final Mac b;

    public static HashingSource md5(Source source) {
        return new HashingSource(source, "MD5");
    }

    public static HashingSource sha1(Source source) {
        return new HashingSource(source, "SHA-1");
    }

    public static HashingSource sha256(Source source) {
        return new HashingSource(source, "SHA-256");
    }

    public static HashingSource hmacSha1(Source source, ByteString byteString) {
        return new HashingSource(source, byteString, "HmacSHA1");
    }

    public static HashingSource hmacSha256(Source source, ByteString byteString) {
        return new HashingSource(source, byteString, "HmacSHA256");
    }

    private HashingSource(Source source, String str) {
        super(source);
        try {
            this.a = MessageDigest.getInstance(str);
            this.b = null;
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError();
        }
    }

    private HashingSource(Source source, ByteString byteString, String str) {
        super(source);
        try {
            this.b = Mac.getInstance(str);
            this.b.init(new SecretKeySpec(byteString.toByteArray(), str));
            this.a = null;
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError();
        } catch (Throwable e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public long read(Buffer buffer, long j) throws IOException {
        long read = super.read(buffer, j);
        if (read != -1) {
            long j2 = buffer.b - read;
            long j3 = buffer.b;
            n nVar = buffer.a;
            while (j3 > j2) {
                nVar = nVar.g;
                j3 -= (long) (nVar.c - nVar.b);
            }
            while (j3 < buffer.b) {
                int i = (int) ((j2 + ((long) nVar.b)) - j3);
                if (this.a != null) {
                    this.a.update(nVar.a, i, nVar.c - i);
                } else {
                    this.b.update(nVar.a, i, nVar.c - i);
                }
                j3 += (long) (nVar.c - nVar.b);
                nVar = nVar.f;
                j2 = j3;
            }
        }
        return read;
    }

    public ByteString hash() {
        return ByteString.of(this.a != null ? this.a.digest() : this.b.doFinal());
    }
}
