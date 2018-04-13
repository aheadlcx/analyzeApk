package cz.msebera.android.httpclient;

public class TruncatedChunkException extends MalformedChunkCodingException {
    public TruncatedChunkException(String str) {
        super(str);
    }
}
