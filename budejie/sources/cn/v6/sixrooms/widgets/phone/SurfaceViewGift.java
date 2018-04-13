package cn.v6.sixrooms.widgets.phone;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import cn.v6.sixrooms.animation.GiftAnimationManager;
import cn.v6.sixrooms.animation.GiftAnimationManager$CallBackGiftStatus;
import cn.v6.sixrooms.view.interfaces.RoomTypeable;

public class SurfaceViewGift extends SurfaceView implements Callback {
    private GiftAnimationManager a;
    private GiftAnimationManager$CallBackGiftStatus b;
    private RoomTypeable c;

    public interface InterfaceSurfaceAnimation {
        void addRedPackage();

        void clearAllAnimation();
    }

    public SurfaceViewGift(Context context) {
        super(context);
        a();
    }

    public SurfaceViewGift(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public SurfaceViewGift(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    public void setRoomTypeable(RoomTypeable roomTypeable) {
        this.c = roomTypeable;
        if (this.a != null) {
            this.a.setRoomTypeable(roomTypeable);
        }
    }

    private void a() {
        setZOrderOnTop(true);
        getHolder().setFormat(-3);
        getHolder().addCallback(this);
    }

    public InterfaceSurfaceAnimation getInterfaceSurfaceAnimation() {
        return this.a;
    }

    public void clearGiftAnimations() {
        if (this.a != null) {
            this.a.clearAllAnimation();
        }
    }

    public void closeCurrentAnimation() {
        if (this.a != null) {
            this.a.closeCurrentAnimation();
        }
    }

    public void addAnimationCallBack(GiftAnimationManager$CallBackGiftStatus giftAnimationManager$CallBackGiftStatus) {
        if (this.a != null) {
            this.a.addAnimationCallBack(giftAnimationManager$CallBackGiftStatus);
        } else {
            this.b = giftAnimationManager$CallBackGiftStatus;
        }
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.a = new GiftAnimationManager(getContext(), surfaceHolder);
        if (this.c != null) {
            this.a.setRoomTypeable(this.c);
        }
        if (this.b != null) {
            this.b.onAllAnimationDismiss();
            this.a.addAnimationCallBack(this.b);
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.a.cancleAllAnimation();
        this.a = null;
    }
}
