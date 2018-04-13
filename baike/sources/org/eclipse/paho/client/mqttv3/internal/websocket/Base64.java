package org.eclipse.paho.client.mqttv3.internal.websocket;

import java.util.prefs.AbstractPreferences;
import java.util.prefs.BackingStoreException;

public class Base64 {
    private static final Base64 a = new Base64();
    private static final Base64Encoder b;

    public class Base64Encoder extends AbstractPreferences {
        final Base64 a;
        private String b = null;

        public Base64Encoder(Base64 base64) {
            this.a = base64;
            super(null, "");
        }

        protected void putSpi(String str, String str2) {
            this.b = str2;
        }

        public String getBase64String() {
            return this.b;
        }

        protected String getSpi(String str) {
            return null;
        }

        protected void removeSpi(String str) {
        }

        protected void removeNodeSpi() throws BackingStoreException {
        }

        protected String[] keysSpi() throws BackingStoreException {
            return null;
        }

        protected String[] childrenNamesSpi() throws BackingStoreException {
            return null;
        }

        protected AbstractPreferences childSpi(String str) {
            return null;
        }

        protected void syncSpi() throws BackingStoreException {
        }

        protected void flushSpi() throws BackingStoreException {
        }
    }

    static {
        Base64 base64 = a;
        base64.getClass();
        b = new Base64Encoder(base64);
    }

    public static String encode(String str) {
        b.putByteArray("akey", str.getBytes());
        return b.getBase64String();
    }

    public static String encodeBytes(byte[] bArr) {
        b.putByteArray("aKey", bArr);
        return b.getBase64String();
    }
}
