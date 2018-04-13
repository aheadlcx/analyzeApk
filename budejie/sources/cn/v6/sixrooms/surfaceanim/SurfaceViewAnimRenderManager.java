package cn.v6.sixrooms.surfaceanim;

import android.graphics.Canvas;
import android.graphics.PorterDuff.Mode;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;

public class SurfaceViewAnimRenderManager extends AnimRenderManager implements Callback {
    private a a;
    private SurfaceHolder b;
    private boolean c;

    private class a extends Thread {
        final /* synthetic */ SurfaceViewAnimRenderManager a;

        private a(SurfaceViewAnimRenderManager surfaceViewAnimRenderManager) {
            this.a = surfaceViewAnimRenderManager;
        }

        public final void run() {
            try {
                Looper.prepare();
                this.a.initRenderHandler();
                this.a.mRenderHandler.sendEmptyMessage(4);
                this.a.mRenderHandler.sendEmptyMessage(1);
                Looper.loop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.b = surfaceHolder;
        synchronized (this.b) {
            this.c = false;
        }
        this.a = new a();
        this.a.start();
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        setRenderRect(i2, i3);
        Log.d("setRenderRect", "setRenderRect1111---" + i3 + "====" + i2);
        if (this.mRenderHandler != null) {
            Message obtain = Message.obtain();
            obtain.what = 4;
            obtain.arg1 = i2;
            obtain.arg2 = i3;
            this.mRenderHandler.sendMessage(obtain);
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        synchronized (this.b) {
            this.c = true;
            if (this.mRenderHandler != null) {
                this.mRenderHandler.sendEmptyMessage(3);
                this.mRenderHandler.sendEmptyMessage(2);
            }
        }
    }

    protected void render() {
        super.render();
        if (this.b != null) {
            try {
                synchronized (this.b) {
                    if (!this.c && this.b.getSurface().isValid()) {
                        Canvas lockCanvas = this.b.lockCanvas();
                        if (lockCanvas != null) {
                            try {
                                lockCanvas.drawColor(0, Mode.CLEAR);
                                onDraw(lockCanvas);
                                this.b.unlockCanvasAndPost(lockCanvas);
                            } catch (Exception e) {
                                e.printStackTrace();
                                this.b.unlockCanvasAndPost(lockCanvas);
                            } catch (Throwable th) {
                                this.b.unlockCanvasAndPost(lockCanvas);
                            }
                        }
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    protected void renderPause() {
        super.renderPause();
        if (this.b != null) {
            try {
                synchronized (this.b) {
                    if (!this.c && this.b.getSurface().isValid()) {
                        Canvas lockCanvas = this.b.lockCanvas();
                        if (lockCanvas != null) {
                            try {
                                lockCanvas.drawColor(0, Mode.CLEAR);
                                this.b.unlockCanvasAndPost(lockCanvas);
                            } catch (Exception e) {
                                e.printStackTrace();
                                this.b.unlockCanvasAndPost(lockCanvas);
                            } catch (Throwable th) {
                                this.b.unlockCanvasAndPost(lockCanvas);
                            }
                        }
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
