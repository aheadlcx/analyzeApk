package cn.v6.sixrooms.animation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PorterDuff.Mode;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.SurfaceHolder;
import cn.v6.sixrooms.view.interfaces.RoomTypeable;
import cn.v6.sixrooms.widgets.phone.SurfaceViewGift.InterfaceSurfaceAnimation;
import java.util.Arrays;

public class GiftAnimationManager implements InterfaceSurfaceAnimation {
    public static final long distanceTime = 30;
    public static final int minRedPackageSize = 40;
    boolean a = true;
    Thread b = new c(this);
    private Handler c;
    private a d = null;
    private int e = 0;
    private SurfaceHolder f;
    private Context g;
    private CallBackGiftBitmap h;
    private PaintFlagsDrawFilter i = null;
    private Handler j = null;
    private GiftAnimationManager$CallBackGiftStatus k = null;
    private int l = 60;
    private IAnimation[] m = null;
    private RoomTypeable n = null;

    public interface IAnimation {
        void draw(Context context, Canvas canvas, int i);

        boolean isFinish();
    }

    public interface CallBackGiftBitmap {
        void onBitmapGet(int i, String str, Bitmap bitmap);
    }

    class a extends Thread {
        final /* synthetic */ GiftAnimationManager a;

        a(GiftAnimationManager giftAnimationManager) {
            this.a = giftAnimationManager;
        }

        public final void run() {
            try {
                Looper.prepare();
                this.a.c = new d(this);
                Looper.loop();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                RedPackageAnimation.clearCache();
            }
        }
    }

    static /* synthetic */ void a(GiftAnimationManager giftAnimationManager, int[] iArr) {
        int i = 0;
        if (giftAnimationManager.m != null && iArr != null) {
            int i2;
            int i3 = 0;
            for (i2 = 0; i2 < iArr.length; i2++) {
                int i4;
                if (iArr[i2] == i2) {
                    i4 = 1;
                } else {
                    i4 = 0;
                }
                i3 += i4;
            }
            if (i3 <= 0) {
                return;
            }
            if (giftAnimationManager.m.length == i3) {
                giftAnimationManager.m = null;
                return;
            }
            IAnimation[] iAnimationArr = new IAnimation[(giftAnimationManager.m.length - i3)];
            i2 = 0;
            while (i < giftAnimationManager.m.length) {
                if (i == iArr[i]) {
                    i2++;
                } else {
                    iAnimationArr[i - i2] = giftAnimationManager.m[i];
                }
                i++;
            }
            giftAnimationManager.m = iAnimationArr;
        }
    }

    static /* synthetic */ void h(GiftAnimationManager giftAnimationManager) {
        if (giftAnimationManager.e > 0) {
            int i;
            if (giftAnimationManager.m == null) {
                i = 1;
            } else if (((RedPackageAnimation) giftAnimationManager.m[giftAnimationManager.m.length - 1]).getRunTime() > giftAnimationManager.l) {
                i = 1;
            } else {
                i = 0;
            }
            if (i != 0) {
                if (giftAnimationManager.m == null) {
                    giftAnimationManager.m = new IAnimation[1];
                } else {
                    giftAnimationManager.m = (IAnimation[]) Arrays.copyOf(giftAnimationManager.m, giftAnimationManager.m.length + 1);
                }
                giftAnimationManager.m[giftAnimationManager.m.length - 1] = RedPackageAnimation.getInitRedPackageAnimation(1.0f);
                giftAnimationManager.e--;
            }
        }
    }

    public void setRoomTypeable(RoomTypeable roomTypeable) {
        this.n = roomTypeable;
    }

    public GiftAnimationManager(Context context, SurfaceHolder surfaceHolder) {
        RedPackageAnimation.initAnimationInfo(context);
        this.g = context;
        this.f = surfaceHolder;
        this.h = new a(this);
        this.d = new a(this);
        this.d.start();
    }

    public void cancleAllAnimation() {
        if (this.d != null && !this.d.isInterrupted()) {
            Message message = new Message();
            message.what = 2;
            this.c.sendMessage(message);
        }
    }

    public void addAnimationCallBack(GiftAnimationManager$CallBackGiftStatus giftAnimationManager$CallBackGiftStatus) {
        if (this.j == null) {
            this.j = new b(this);
        }
        this.k = giftAnimationManager$CallBackGiftStatus;
    }

    public void clearRedPackage() {
        this.c.sendEmptyMessage(6);
    }

    private void a() {
        this.e = 0;
        this.m = null;
    }

    public void closeCurrentAnimation() {
        if (this.d != null && !this.d.isInterrupted()) {
            Message message = new Message();
            message.what = 3;
            this.c.sendMessage(message);
        }
    }

    public void clearAllAnimation() {
        if (this.k != null) {
            this.k.onAllAnimationDismiss();
        }
        if (this.d != null && !this.d.isInterrupted()) {
            Message message = new Message();
            message.what = 4;
            this.c.sendMessage(message);
        }
    }

    public void addRedPackage() {
        this.c.sendEmptyMessage(5);
    }

    static /* synthetic */ void c(GiftAnimationManager giftAnimationManager) {
        if (giftAnimationManager.k != null) {
            giftAnimationManager.k.onAnimationShow();
        }
    }

    static /* synthetic */ void d(GiftAnimationManager giftAnimationManager) {
        if (giftAnimationManager.k != null) {
            giftAnimationManager.k.onAllAnimationDismiss();
        }
    }

    static /* synthetic */ void e(GiftAnimationManager giftAnimationManager) {
        try {
            if (giftAnimationManager.f != null) {
                Canvas lockCanvas = giftAnimationManager.f.lockCanvas();
                lockCanvas.drawColor(0, Mode.CLEAR);
                giftAnimationManager.f.unlockCanvasAndPost(lockCanvas);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static /* synthetic */ void f(GiftAnimationManager giftAnimationManager) {
        giftAnimationManager.a = true;
        giftAnimationManager.a();
        giftAnimationManager.c.removeCallbacks(giftAnimationManager.b);
    }

    static /* synthetic */ void i(GiftAnimationManager giftAnimationManager) {
        if (giftAnimationManager.a) {
            giftAnimationManager.a = false;
            giftAnimationManager.c.removeCallbacks(giftAnimationManager.b);
            giftAnimationManager.c.post(giftAnimationManager.b);
        }
    }
}
