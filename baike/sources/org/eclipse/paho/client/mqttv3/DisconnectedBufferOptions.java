package org.eclipse.paho.client.mqttv3;

public class DisconnectedBufferOptions {
    public static final boolean DELETE_OLDEST_MESSAGES_DEFAULT = false;
    public static final boolean DISCONNECTED_BUFFER_ENABLED_DEFAULT = false;
    public static final int DISCONNECTED_BUFFER_SIZE_DEFAULT = 5000;
    public static final boolean PERSIST_DISCONNECTED_BUFFER_DEFAULT = false;
    private int a = 5000;
    private boolean b = false;
    private boolean c = false;
    private boolean d = false;

    public int getBufferSize() {
        return this.a;
    }

    public void setBufferSize(int i) {
        if (i < 1) {
            throw new IllegalArgumentException();
        }
        this.a = i;
    }

    public boolean isBufferEnabled() {
        return this.b;
    }

    public void setBufferEnabled(boolean z) {
        this.b = z;
    }

    public boolean isPersistBuffer() {
        return this.c;
    }

    public void setPersistBuffer(boolean z) {
        this.c = z;
    }

    public boolean isDeleteOldestMessages() {
        return this.d;
    }

    public void setDeleteOldestMessages(boolean z) {
        this.d = z;
    }
}
