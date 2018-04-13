package cn.xiaochuan.media.av;

import android.media.AudioTrack;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;

public class XPlayer {
    static final int COMMAND_NATIVE_EXIT = 6;
    static final int COMMAND_NATIVE_NOTIFY = 5;
    static final int COMMAND_RUN = 4;
    static final int COMMAND_TEXTEDIT_HIDE = 3;
    static final int COMMAND_UNUSED = 2;
    protected static final int COMMAND_USER = 32768;
    private static final String TAG = "XPlayer";
    private static final boolean enable_log = true;
    public static int serial = 0;
    public static int state_none = 0;
    public static int state_pause = 2;
    public static int state_play = 1;
    public static int state_stopped = 3;
    Handler commandHandler = new SDLCommandHandler(this);
    protected AudioTrack mAudioTrack;
    private long mContext = 0;
    public boolean mIsSurfaceReady;
    private XPlayerObserver mObserver;
    private String mPath;
    protected Thread mSDLThread;
    public int mSerial;
    private Integer mState = Integer.valueOf(state_none);
    private TextureView mTextureView;

    class MsgInfo {
        public static final int msg_begin_render = 0;
        public static final int msg_download_progress = 1;
        public static final int msg_loop = 5;
        public static final int msg_pause = 2;
        public static final int msg_play_progress = 4;
        public static final int msg_resume = 3;
        double arg;
        int msg;

        public MsgInfo(int i, double d) {
            this.msg = i;
            this.arg = d;
        }
    }

    protected class SDLCommandHandler extends Handler {
        private XPlayer mHost;

        public SDLCommandHandler(XPlayer xPlayer) {
            this.mHost = xPlayer;
        }

        public void handleMessage(Message message) {
            switch (message.arg1) {
                case 4:
                    ((Runnable) message.obj).run();
                    return;
                case 5:
                    if (XPlayer.this.mObserver != null) {
                        MsgInfo msgInfo = (MsgInfo) message.obj;
                        if (msgInfo.msg == 0) {
                            XPlayer.this.mObserver.onPlayerBeginRend(XPlayer.this);
                            return;
                        } else if (msgInfo.msg == 1) {
                            XPlayer.this.mObserver.onDownloadProgress(msgInfo.arg, XPlayer.this);
                            return;
                        } else if (msgInfo.msg == 4) {
                            XPlayer.this.mObserver.onPlayProgress(msgInfo.arg, XPlayer.this);
                            return;
                        } else if (msgInfo.msg == 5) {
                            XPlayer.this.mObserver.onPlayerLoop(XPlayer.this);
                            return;
                        } else {
                            return;
                        }
                    }
                    return;
                case 6:
                    if (XPlayer.this.mObserver != null) {
                        XPlayer.this.mObserver.onPlayerStop(XPlayer.this);
                        XPlayer.this.mObserver = null;
                        return;
                    }
                    return;
                default:
                    if (!this.mHost.onUnhandledMessage(message.arg1, message.obj)) {
                        XPlayer.log("error handling message, command is " + message.arg1);
                        return;
                    }
                    return;
            }
        }
    }

    public interface XPlayerObserver {
        void onDownloadProgress(double d, XPlayer xPlayer);

        void onPlayProgress(double d, XPlayer xPlayer);

        void onPlayerBeginRend(XPlayer xPlayer);

        void onPlayerLoop(XPlayer xPlayer);

        void onPlayerPause(XPlayer xPlayer);

        void onPlayerResume(XPlayer xPlayer);

        void onPlayerStart(XPlayer xPlayer);

        void onPlayerStop(XPlayer xPlayer);
    }

    public native int nativeAddJoystick(int i, String str, int i2, int i3, int i4, int i5, int i6);

    public native void nativeEnableRotate(boolean z);

    public native void nativeFlipBuffers();

    public native void nativeLowMemory();

    public native void nativePause();

    public native void nativeQuit();

    public native int nativeRemoveJoystick(int i);

    public native void nativeResume();

    public native void nativeSeek(long j);

    public native void nativeSetLoop(boolean z);

    public native void nativeSetPath(String str);

    public native void nativeSetRendered(int i);

    public native void nativeSetSeekExact(boolean z);

    public native void nativeSetSupportPlayProgress(int i);

    public native void nativeSetVFilters(String str);

    public native void nativeSetVolume(float f);

    public native void nativeStart();

    public native void onNativeAccel(float f, float f2, float f3);

    public native void onNativeHat(int i, int i2, int i3, int i4);

    public native void onNativeJoy(int i, int i2, float f);

    public native void onNativeKeyDown(int i);

    public native void onNativeKeyUp(int i);

    public native void onNativeKeyboardFocusLost();

    public native int onNativePadDown(int i, int i2);

    public native int onNativePadUp(int i, int i2);

    public native void onNativeResize(int i, int i2, int i3);

    public native void onNativeSurfaceChanged();

    public native void onNativeSurfaceDestroyed();

    public native void onNativeTouch(int i, int i2, int i3, float f, float f2, float f3);

    static {
        System.loadLibrary("avmedia");
    }

    public XPlayer() {
        int i = serial;
        serial = i + 1;
        this.mSerial = i;
        init();
    }

    public static void log(String str) {
        Log.e(TAG, str);
    }

    public void setObserver(XPlayerObserver xPlayerObserver) {
        this.mObserver = xPlayerObserver;
    }

    public void setTextureView(TextureView textureView) {
        this.mTextureView = textureView;
    }

    public void setSurfaceTextureAvailable(int i, int i2) {
        if (getState() != state_stopped) {
            onNativeResize(i, i2, 353701890);
            this.mIsSurfaceReady = true;
            onNativeSurfaceChanged();
        }
    }

    public boolean setSurfaceTextureDestroyed() {
        this.mIsSurfaceReady = false;
        onNativeSurfaceDestroyed();
        return false;
    }

    private void init() {
        this.mSDLThread = null;
        this.mAudioTrack = null;
        this.mIsSurfaceReady = false;
    }

    public void start() {
        log("XPlayer.start begin serial=" + this.mSerial + " path=" + this.mPath);
        if (this.mPath != null && getState() == state_none) {
            startSDLMainThread();
            log("XPlayer.start end serial=" + this.mSerial);
        }
    }

    public void player_notify(int i, double d) {
        sendCommand(5, new MsgInfo(i, d));
    }

    public void stop() {
        Log.e(TAG, "xplayer stop begin serial=" + this.mSerial);
        if (getState() != state_none && getState() != state_stopped) {
            this.mTextureView = null;
            nativeQuit();
            if (this.mSDLThread != null) {
                this.mSDLThread = null;
            }
            setState(state_stopped);
            Log.e(TAG, "xplayer stop end serial=" + this.mSerial);
        }
    }

    public void seek(long j) {
        nativeSeek(j);
    }

    public void setVolume(float f) {
        float f2 = 1.0f;
        float f3 = 0.0f;
        if (f >= 0.0f) {
            f3 = f;
        }
        if (f3 <= 1.0f) {
            f2 = f3;
        }
        nativeSetVolume(f2);
    }

    public void setLoop(boolean z) {
        nativeSetLoop(z);
    }

    public void setSeekExact(boolean z) {
        nativeSetSeekExact(z);
    }

    public void setEnableRotate(boolean z) {
        nativeEnableRotate(z);
    }

    public void setSupportPlayProgress(boolean z) {
        nativeSetSupportPlayProgress(z ? 1 : 0);
    }

    public void setPath(String str) {
        log("XPlayer.setPath begin serial=" + this.mSerial + str);
        this.mPath = str;
        nativeSetPath(str);
        log("XPlayer.setPath end serial=" + this.mSerial);
    }

    public void setVFilters(String str) {
        nativeSetVFilters(str);
    }

    public String getPath() {
        return this.mPath;
    }

    private void setState(int i) {
        synchronized (this.mState) {
            this.mState = Integer.valueOf(i);
        }
    }

    public int getState() {
        int intValue;
        synchronized (this.mState) {
            intValue = this.mState.intValue();
        }
        return intValue;
    }

    public boolean isPlaying() {
        return this.mState.intValue() == state_play;
    }

    public boolean isPause() {
        return this.mState.intValue() == state_pause;
    }

    public void pause() {
        if (getState() == state_play) {
            nativePause();
            setState(state_pause);
            if (this.mObserver != null) {
                this.mObserver.onPlayerPause(this);
            }
        }
    }

    public void resume() {
        log("handleResume begin");
        if (getState() == state_pause) {
            nativeResume();
            setState(state_play);
            if (this.mObserver != null) {
                this.mObserver.onPlayerResume(this);
            }
        }
        log("handleResume end");
    }

    public void handleNativeExit() {
        this.mSDLThread = null;
        log("handleNativeExit serial=" + this.mSerial);
        sendCommand(6, null);
        setState(state_stopped);
    }

    protected boolean onUnhandledMessage(int i, Object obj) {
        return false;
    }

    boolean sendCommand(int i, Object obj) {
        Message obtainMessage = this.commandHandler.obtainMessage();
        obtainMessage.arg1 = i;
        obtainMessage.obj = obj;
        return this.commandHandler.sendMessage(obtainMessage);
    }

    private void onBeginRender() {
        if (this.mObserver != null) {
            this.mObserver.onPlayerBeginRend(this);
        }
    }

    public void flipBuffers() {
        nativeFlipBuffers();
    }

    public boolean setActivityTitle(String str) {
        return true;
    }

    public boolean sendMessage(int i, int i2) {
        return sendCommand(i, Integer.valueOf(i2));
    }

    public Object getSystemServiceFromUiThread(String str) {
        return null;
    }

    public boolean showTextInput(int i, int i2, int i3, int i4) {
        return true;
    }

    public Object getNativeSurface() {
        if (!this.mIsSurfaceReady || this.mTextureView == null) {
            return null;
        }
        return new Surface(this.mTextureView.getSurfaceTexture());
    }

    public int audioInit(int i, boolean z, boolean z2, int i2) {
        int i3;
        int i4;
        String str;
        int i5 = z2 ? 3 : 2;
        if (z) {
            i3 = 2;
        } else {
            i3 = 3;
        }
        if (z2) {
            i4 = 2;
        } else {
            i4 = 1;
        }
        i4 *= z ? 2 : 1;
        String str2 = "SDL";
        StringBuilder append = new StringBuilder().append("SDL audio: wanted ").append(z2 ? "stereo" : "mono").append(" ");
        if (z) {
            str = "16-bit";
        } else {
            str = "8-bit";
        }
        Log.v(str2, append.append(str).append(" ").append(((float) i) / 1000.0f).append("kHz, ").append(i2).append(" frames buffer").toString());
        int max = Math.max(i2, ((AudioTrack.getMinBufferSize(i, i5, i3) + i4) - 1) / i4);
        if (this.mAudioTrack == null) {
            this.mAudioTrack = new AudioTrack(3, i, i5, i3, max * i4, 1);
            if (this.mAudioTrack.getState() != 1) {
                log("Failed during initialization of Audio Track");
                this.mAudioTrack = null;
                return -1;
            }
            this.mAudioTrack.play();
        }
        Log.v("SDL", "SDL audio: got " + (this.mAudioTrack.getChannelCount() >= 2 ? "stereo" : "mono") + " " + (this.mAudioTrack.getAudioFormat() == 2 ? "16-bit" : "8-bit") + " " + (((float) this.mAudioTrack.getSampleRate()) / 1000.0f) + "kHz, " + max + " frames buffer");
        return 0;
    }

    public void audioWriteShortBuffer(short[] sArr) {
        int i = 0;
        while (i < sArr.length) {
            int write = this.mAudioTrack.write(sArr, i, sArr.length - i);
            if (write > 0) {
                i += write;
            } else if (write == 0) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                }
            } else {
                Log.w("SDL", "SDL audio: error return from write(short)");
                return;
            }
        }
    }

    public void audioWriteByteBuffer(byte[] bArr) {
        int i = 0;
        while (i < bArr.length) {
            int write = this.mAudioTrack.write(bArr, i, bArr.length - i);
            if (write > 0) {
                i += write;
            } else if (write == 0) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                }
            } else {
                Log.w("SDL", "SDL audio: error return from write(byte)");
                return;
            }
        }
    }

    public void audioQuit() {
        if (this.mAudioTrack != null) {
            this.mAudioTrack.release();
            this.mAudioTrack = null;
        }
    }

    public void startSDLMainThread() {
        if (this.mSDLThread == null) {
            setState(state_play);
            if (this.mObserver != null) {
                this.mObserver.onPlayerStart(this);
            }
            this.mSDLThread = new Thread(new SDLMain(this), "SDLThread_" + this.mSerial);
            this.mSDLThread.start();
            new Thread(new Runnable() {
                public void run() {
                    try {
                        XPlayer.this.mSDLThread.join();
                    } catch (Exception e) {
                    } finally {
                        XPlayer.this.handleNativeExit();
                    }
                }
            }).start();
        }
    }
}
