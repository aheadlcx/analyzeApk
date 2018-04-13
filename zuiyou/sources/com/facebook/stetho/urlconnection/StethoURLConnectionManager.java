package com.facebook.stetho.urlconnection;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class StethoURLConnectionManager {
    private static final boolean sIsStethoPresent;
    @Nullable
    private final StethoURLConnectionManager$Holder mHolder;

    static {
        boolean z = false;
        try {
            Class.forName("com.facebook.stetho.Stetho");
            z = true;
        } catch (ClassNotFoundException e) {
        }
        sIsStethoPresent = z;
    }

    public StethoURLConnectionManager(@Nullable String str) {
        if (sIsStethoPresent) {
            this.mHolder = new StethoURLConnectionManager$Holder(str);
        } else {
            this.mHolder = null;
        }
    }

    public boolean isStethoEnabled() {
        return this.mHolder != null && StethoURLConnectionManager$Holder.access$000(this.mHolder).isStethoActive();
    }

    public void preConnect(HttpURLConnection httpURLConnection, @Nullable SimpleRequestEntity simpleRequestEntity) {
        if (this.mHolder != null) {
            StethoURLConnectionManager$Holder.access$000(this.mHolder).preConnect(httpURLConnection, simpleRequestEntity);
        }
    }

    public void postConnect() throws IOException {
        if (this.mHolder != null) {
            StethoURLConnectionManager$Holder.access$000(this.mHolder).postConnect();
        }
    }

    public void httpExchangeFailed(IOException iOException) {
        if (this.mHolder != null) {
            StethoURLConnectionManager$Holder.access$000(this.mHolder).httpExchangeFailed(iOException);
        }
    }

    public InputStream interpretResponseStream(@Nullable InputStream inputStream) {
        if (this.mHolder != null) {
            return StethoURLConnectionManager$Holder.access$000(this.mHolder).interpretResponseStream(inputStream);
        }
        return inputStream;
    }

    @Deprecated
    @Nullable
    public Object getStethoHook() {
        if (this.mHolder != null) {
            return StethoURLConnectionManager$Holder.access$000(this.mHolder).getStethoHook();
        }
        return null;
    }

    @Nullable
    public String getStethoRequestId() {
        if (this.mHolder != null) {
            return StethoURLConnectionManager$Holder.access$000(this.mHolder).getStethoRequestId();
        }
        return null;
    }
}
