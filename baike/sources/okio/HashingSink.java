package okio;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.annotation.Nullable;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public final class HashingSink extends ForwardingSink {
    @Nullable
    private final MessageDigest a;
    @Nullable
    private final Mac b;

    public static HashingSink md5(Sink sink) {
        return new HashingSink(sink, "MD5");
    }

    public static HashingSink sha1(Sink sink) {
        return new HashingSink(sink, "SHA-1");
    }

    public static HashingSink sha256(Sink sink) {
        return new HashingSink(sink, "SHA-256");
    }

    public static HashingSink sha512(Sink sink) {
        return new HashingSink(sink, "SHA-512");
    }

    public static HashingSink hmacSha1(Sink sink, ByteString byteString) {
        return new HashingSink(sink, byteString, "HmacSHA1");
    }

    public static HashingSink hmacSha256(Sink sink, ByteString byteString) {
        return new HashingSink(sink, byteString, "HmacSHA256");
    }

    public static HashingSink hmacSha512(Sink sink, ByteString byteString) {
        return new HashingSink(sink, byteString, "HmacSHA512");
    }

    private HashingSink(Sink sink, String str) {
        super(sink);
        try {
            this.a = MessageDigest.getInstance(str);
            this.b = null;
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError();
        }
    }

    private HashingSink(Sink sink, ByteString byteString, String str) {
        super(sink);
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

    public void write(Buffer buffer, long j) throws IOException {
        long j2 = 0;
        r.checkOffsetAndCount(buffer.b, 0, j);
        n nVar = buffer.a;
        while (j2 < j) {
            int min = (int) Math.min(j - j2, (long) (nVar.c - nVar.b));
            if (this.a != null) {
                this.a.update(nVar.a, nVar.b, min);
            } else {
                this.b.update(nVar.a, nVar.b, min);
            }
            j2 += (long) min;
            nVar = nVar.f;
        }
        super.write(buffer, j);
    }

    public ByteString hash() {
        return ByteString.of(this.a != null ? this.a.digest() : this.b.doFinal());
    }
}
