package qsbk.app.utils.image.issue;

import java.io.IOException;

public class IOExceptionWrapper extends IOException {
    private final int a;
    private final IOException b;

    public IOExceptionWrapper(int i, IOException iOException) {
        this.a = i;
        this.b = iOException;
    }

    public int getResponseCode() {
        return this.a;
    }

    public IOException getOriginException() {
        return this.b;
    }

    public String toString() {
        return String.format("%s&responseCode=%s", new Object[]{this.b.toString(), Integer.valueOf(this.a)});
    }
}
