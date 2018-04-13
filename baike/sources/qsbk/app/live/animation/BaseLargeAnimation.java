package qsbk.app.live.animation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;
import android.widget.ImageView;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.live.model.LiveGiftMessage;
import qsbk.app.live.ui.helper.LiveMessageListener;
import qsbk.app.live.widget.LargeGiftLayout;

public abstract class BaseLargeAnimation {
    protected Context a;
    protected LargeGiftLayout b;
    protected long c;
    protected int d;
    protected int e;

    protected abstract void a(View view);

    public abstract long getGiftId();

    protected abstract void onLoadAnim(LiveGiftMessage liveGiftMessage);

    public void attach(Context context, LargeGiftLayout largeGiftLayout) {
        this.a = context;
        this.b = largeGiftLayout;
        this.d = largeGiftLayout.getWidth();
        this.e = largeGiftLayout.getHeight();
    }

    protected void a() {
        this.b.postNextAnim();
    }

    protected void b(View view) {
        this.b.addView(view);
    }

    protected void a(Runnable runnable, long j) {
        this.b.mHandler.postDelayed(runnable, j);
    }

    protected void a(Runnable runnable) {
        this.b.mHandler.removeCallbacks(runnable);
    }

    protected void b() {
        this.b.invalidate();
    }

    protected void a(ImageView imageView) {
        this.b.removeAnimView(imageView);
    }

    protected Bitmap a(int i) {
        return this.b.getGiftBitmap(i);
    }

    protected boolean a(LiveGiftMessage liveGiftMessage, Bitmap... bitmapArr) {
        return this.b.termAnimIfBitmapInvalid(liveGiftMessage, bitmapArr);
    }

    protected boolean c() {
        LiveMessageListener d = d();
        return d != null && d.isMessageOverloadOrLowDevice();
    }

    protected LiveMessageListener d() {
        return this.b.getLiveMessageListener();
    }

    protected void a(LiveGiftMessage liveGiftMessage) {
        AppUtils.getInstance().getImageProvider().loadAvatar(this.b.ivAvatar, liveGiftMessage.getUserAvatar(), true);
        this.b.tvUserName.setText(liveGiftMessage.getUserName());
        this.b.tvGiftName.setText(liveGiftMessage.getGiftShowContent());
        this.b.ivAvatar.setOnClickListener(new b(this, liveGiftMessage));
        this.c = liveGiftMessage.getCreatedAt();
    }

    public void loadAnim(LiveGiftMessage liveGiftMessage) {
        a(liveGiftMessage);
        a(this.b.mUserInfoLayout);
        onLoadAnim(liveGiftMessage);
    }

    public void onDraw(Canvas canvas) {
    }

    public void releaseResource() {
    }
}
