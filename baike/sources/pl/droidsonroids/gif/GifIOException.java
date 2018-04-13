package pl.droidsonroids.gif;

import java.io.IOException;

public class GifIOException extends IOException {
    public final GifError reason;

    private GifIOException(GifError gifError) {
        super(gifError.a());
        this.reason = gifError;
    }

    GifIOException(int i) {
        this(GifError.a(i));
    }
}
