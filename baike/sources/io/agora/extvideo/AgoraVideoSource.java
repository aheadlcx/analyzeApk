package io.agora.extvideo;

public class AgoraVideoSource {
    private native void AttachToEngine();

    private native void DetachFromEngine();

    private native void SendFrame(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8);

    static {
        System.loadLibrary("videoprp");
    }

    public void finalize() {
    }

    public synchronized void Attach() {
        AttachToEngine();
    }

    public synchronized void Detach() {
        DetachFromEngine();
    }

    public synchronized void DeliverFrame(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8) {
        SendFrame(bArr, i, i2, i3, i4, i5, i6, i7, j, i8);
    }
}
