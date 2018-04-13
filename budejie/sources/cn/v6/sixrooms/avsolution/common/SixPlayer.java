package cn.v6.sixrooms.avsolution.common;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.SurfaceView;
import java.util.ArrayList;

public class SixPlayer extends SurfaceView {
    private static boolean isLoadLibrary = false;
    private boolean isExit = false;
    private boolean isRelease = true;
    private ArrayList<PlayerCallBack> listCallBack = new ArrayList();
    private Object lock = new Object();
    private Handler mHandler = new SixPlayer$1(this);
    private final int msg1 = 1;
    private final int msg2 = 2;
    private final int msg3 = 3;
    private final int msg4 = 4;
    private final int msg5 = 5;
    private Handler playerHandler = null;
    private final int playerMsg1 = 1;
    private final int playerMsg2 = 2;
    private final int playerMsg3 = 3;
    private Thread playerThread = null;

    public static native void closeRender();

    public static native int getState();

    public static native float getVersion();

    public static native int isSupport();

    public static native int openRender(Surface surface);

    private static native int play(SixPlayer sixPlayer, Surface surface, String str);

    private static native void release();

    public SixPlayer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initPlayThread();
    }

    public SixPlayer(Context context) {
        super(context);
        initPlayThread();
    }

    public SixPlayer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initPlayThread();
    }

    private void initPlayThread() {
        if (this.playerThread == null) {
            synchronized (this.lock) {
                this.playerThread = new SixPlayer$2(this);
                this.playerThread.start();
                try {
                    this.lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean loadLibrary() {
        boolean z = true;
        synchronized (SixPlayer.class) {
            if (!isLoadLibrary) {
                isLoadLibrary = true;
                try {
                    System.loadLibrary("sixroomsplayer");
                } catch (UnsatisfiedLinkError e) {
                    e.printStackTrace();
                    z = false;
                } catch (SecurityException e2) {
                    e2.printStackTrace();
                    z = false;
                }
            }
        }
        return z;
    }

    public void addCallBack(PlayerCallBack playerCallBack) {
        synchronized (this.lock) {
            this.listCallBack.remove(playerCallBack);
            this.listCallBack.add(playerCallBack);
        }
    }

    public void removeCallback(PlayerCallBack playerCallBack) {
        synchronized (this.lock) {
            this.listCallBack.remove(playerCallBack);
        }
    }

    public int play(String str) throws NullPointerException {
        if (getHolder().getSurface() == null) {
            throw new NullPointerException("Surface is Null");
        }
        Message obtain = Message.obtain();
        obtain.what = 1;
        obtain.obj = str;
        this.playerHandler.sendMessage(obtain);
        return 1;
    }

    private void onVideoSizeChange(int i, int i2) {
        Message obtain = Message.obtain();
        obtain.what = 4;
        obtain.arg1 = i;
        obtain.arg2 = i2;
        this.mHandler.sendMessage(obtain);
    }

    private void onBufferEmpty() {
        this.mHandler.sendEmptyMessage(3);
    }

    private void onBufferLoad() {
        this.mHandler.sendEmptyMessage(2);
    }

    private void onVideoEnd() {
        this.mHandler.sendEmptyMessage(5);
    }

    private void onError(int i) {
        if (!this.isExit) {
            Message obtain = Message.obtain();
            obtain.what = 1;
            obtain.arg1 = i;
            this.playerHandler.sendMessage(obtain);
        }
    }

    public void releasePlayer() {
        if (!this.isExit) {
            this.playerHandler.sendEmptyMessage(2);
        }
    }

    public void exit() {
        if (!this.isExit) {
            this.playerHandler.sendEmptyMessage(3);
            this.isExit = true;
        }
    }
}
