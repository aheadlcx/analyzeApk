package io.agora.videoprp;

import android.content.Context;
import java.nio.ByteBuffer;

public class AgoraYuvPreProcessor {
    private ByteBuffer mPrPRawUBuffer = null;
    private ByteBuffer mPrPRawVBuffer = null;
    private ByteBuffer mPrPRawYBuffer = null;
    private PreProcessHandler mProcessHandler;

    public interface PreProcessHandler {
        void handleYuvData(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, ByteBuffer byteBuffer3, int i, int i2, int i3, int i4, int i5);
    }

    private native boolean doSetUp();

    private native boolean doTearDown();

    public native int StartPreProcess();

    public native int StopPreProcess();

    static {
        SoHolder.load();
    }

    public AgoraYuvPreProcessor(Context context) {
    }

    public void finalize() {
        tearDown();
    }

    public void addProcessHandler(PreProcessHandler preProcessHandler) {
        this.mProcessHandler = preProcessHandler;
    }

    public void removeProcessHandler() {
        this.mProcessHandler = null;
    }

    public boolean setUp() {
        return doSetUp();
    }

    public boolean tearDown() {
        this.mPrPRawYBuffer = null;
        return doTearDown();
    }

    private void VM_ProcessOneFrame(int i, int i2, int i3, int i4, int i5) {
        int i6 = i * i5;
        if (this.mPrPRawYBuffer == null || this.mPrPRawYBuffer.capacity() != i6) {
            this.mPrPRawYBuffer = ByteBuffer.allocateDirect(i6);
        }
        i6 = (i2 * i5) / 2;
        if (this.mPrPRawUBuffer == null || this.mPrPRawUBuffer.capacity() != i6) {
            this.mPrPRawUBuffer = ByteBuffer.allocateDirect(i6);
        }
        i6 = (i3 * i5) / 2;
        if (this.mPrPRawVBuffer == null || this.mPrPRawVBuffer.capacity() != i6) {
            this.mPrPRawVBuffer = ByteBuffer.allocateDirect(i6);
        }
        if (this.mProcessHandler != null) {
            this.mProcessHandler.handleYuvData(this.mPrPRawYBuffer, this.mPrPRawUBuffer, this.mPrPRawVBuffer, i, i2, i3, i4, i5);
        }
    }
}
