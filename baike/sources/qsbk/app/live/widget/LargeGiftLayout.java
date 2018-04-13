package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import java.io.File;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.core.model.GiftData;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.ConfigInfoUtil;
import qsbk.app.core.utils.GiftResSync;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.core.widget.FrameAnimationView;
import qsbk.app.live.R;
import qsbk.app.live.animation.BalloonAnimation;
import qsbk.app.live.animation.BaseLargeAnimation;
import qsbk.app.live.animation.CarAnimation;
import qsbk.app.live.animation.ChangEAnimation;
import qsbk.app.live.animation.ChristmasAnimation;
import qsbk.app.live.animation.EvilAnimation;
import qsbk.app.live.animation.PlaneAnimation;
import qsbk.app.live.animation.RocketAnimation;
import qsbk.app.live.animation.ShipAnimation;
import qsbk.app.live.animation.UnknownLiquidAnimation;
import qsbk.app.live.model.LiveGiftMessage;
import qsbk.app.live.ui.helper.LiveMessageListener;

public class LargeGiftLayout extends RelativeLayout {
    private static final String a = LargeGiftLayout.class.getSimpleName();
    private Context b;
    private ArrayList<LiveGiftMessage> c;
    private HashMap<Integer, SoftReference<Bitmap>> d;
    private boolean e;
    private int f;
    private int g;
    private FamilyLevelView h;
    private LiveMessageListener i;
    public ImageView ivAvatar;
    private Map<Long, BaseLargeAnimation> j;
    private BaseLargeAnimation k;
    private FrameAnimationView l;
    public Handler mHandler;
    public RelativeLayout mUserInfoLayout;
    public TextView tvGiftName;
    public TextView tvUserName;

    public LargeGiftLayout(Context context) {
        this(context, null);
    }

    public LargeGiftLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LargeGiftLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = new ArrayList();
        this.d = new HashMap();
        this.e = true;
        this.j = new HashMap();
        this.mHandler = new Handler();
        this.b = context;
        setWillNotDraw(false);
        b();
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.f = getMeasuredWidth();
        this.g = getMeasuredHeight();
        if (this.j == null || this.j.isEmpty()) {
            a();
        }
    }

    public void addGift(LiveGiftMessage liveGiftMessage) {
        if (this.e) {
            this.e = false;
            a(liveGiftMessage);
            return;
        }
        this.c.add(liveGiftMessage);
    }

    private void a() {
        a(new CarAnimation(), new ShipAnimation(), new BalloonAnimation(), new PlaneAnimation(), new RocketAnimation(), new ChangEAnimation(), new EvilAnimation(), new UnknownLiquidAnimation(), new ChristmasAnimation());
    }

    private void a(BaseLargeAnimation... baseLargeAnimationArr) {
        if (this.j == null) {
            this.j = new HashMap();
        }
        for (BaseLargeAnimation baseLargeAnimation : baseLargeAnimationArr) {
            this.j.put(Long.valueOf(baseLargeAnimation.getGiftId()), baseLargeAnimation);
        }
    }

    private void a(LiveGiftMessage liveGiftMessage) {
        if (e(liveGiftMessage)) {
            loadFrameAnimation(liveGiftMessage);
        } else if (d(liveGiftMessage)) {
            loadWebpAnim(liveGiftMessage);
        } else if (c(liveGiftMessage)) {
            b(liveGiftMessage);
        } else {
            showSmallGiftAnim(liveGiftMessage);
        }
    }

    private void b(LiveGiftMessage liveGiftMessage) {
        this.k = a(liveGiftMessage.getGiftId());
        if (this.k != null) {
            this.k.attach(this.b, this);
            try {
                this.k.loadAnim(liveGiftMessage);
                return;
            } catch (Exception e) {
            } catch (Error e2) {
            }
        } else {
            return;
        }
        showSmallGiftAnim(liveGiftMessage);
        postNextAnim();
    }

    private BaseLargeAnimation a(long j) {
        return (BaseLargeAnimation) this.j.get(Long.valueOf(j));
    }

    public boolean isSupportLargeAnim(LiveGiftMessage liveGiftMessage) {
        return e(liveGiftMessage) || c(liveGiftMessage) || d(liveGiftMessage);
    }

    private boolean c(LiveGiftMessage liveGiftMessage) {
        return this.j.containsKey(Long.valueOf(liveGiftMessage.getGiftId()));
    }

    private boolean d(LiveGiftMessage liveGiftMessage) {
        return liveGiftMessage.isWebpAnim();
    }

    private boolean e(LiveGiftMessage liveGiftMessage) {
        File file = new File(Environment.getExternalStorageDirectory() + "/Remix/.Animation/" + liveGiftMessage.getGiftId());
        String[] list = file.list();
        GiftData giftDataById = ConfigInfoUtil.instance().getGiftDataById(liveGiftMessage.getGiftId());
        if (giftDataById == null) {
            if (!file.exists() || list == null || list.length <= 0) {
                return false;
            }
            return true;
        } else if (!file.exists() || list == null || list.length <= 0 || giftDataById.ga == null || giftDataById.ga.length <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public void loadWebpAnim(LiveGiftMessage liveGiftMessage) {
        Object obj = "file://" + GiftResSync.getDownloadedGiftResPath(liveGiftMessage.getWebpAnimUrl());
        if (TextUtils.isEmpty(obj)) {
            termAnimIfBitmapInvalid(liveGiftMessage, (Bitmap) null);
            return;
        }
        View inflate = View.inflate(getContext(), R.layout.imageview_live_webp, null);
        addView(inflate, 0, new LayoutParams(-1, -1));
        AppUtils.getInstance().getImageProvider().loadWebpImage((ImageView) inflate.findViewById(R.id.iv_background), obj);
        f(liveGiftMessage);
        setWebpUserPosition(this.mUserInfoLayout);
        this.mHandler.postDelayed(new fr(this, inflate), liveGiftMessage.getWebpAnimDuration());
    }

    public void removeAnimView(ImageView imageView) {
        imageView.clearAnimation();
        imageView.clearFocus();
        imageView.setImageDrawable(null);
        imageView.setImageBitmap(null);
        removeView(imageView);
    }

    protected void onDraw(Canvas canvas) {
        if (this.k != null) {
            this.k.onDraw(canvas);
        }
    }

    public boolean termAnimIfBitmapInvalid(LiveGiftMessage liveGiftMessage, Bitmap... bitmapArr) {
        boolean z = false;
        for (Bitmap bitmap : bitmapArr) {
            if (bitmap == null || bitmap.isRecycled()) {
                z = true;
                break;
            }
        }
        if (z) {
            showSmallGiftAnim(liveGiftMessage);
            postNextAnim();
        }
        return z;
    }

    public void showSmallGiftAnim(LiveGiftMessage liveGiftMessage) {
        if (this.i != null) {
            this.i.onShowSmallGiftAnim(liveGiftMessage);
        }
        postNextAnim();
    }

    private void setWebpUserPosition(View view) {
        ViewGroup.LayoutParams layoutParams = new LayoutParams(view.getWidth(), view.getHeight());
        layoutParams.topMargin = ((-((int) view.getY())) + view.getTop()) + WindowUtils.dp2Px(90);
        layoutParams.leftMargin = ((-((int) view.getX())) + view.getLeft()) + ((this.f - view.getWidth()) / 2);
        view.setLayoutParams(layoutParams);
        view.setVisibility(0);
    }

    public void postNextAnim() {
        ViewCompat.setTranslationX(this.mUserInfoLayout, 0.0f);
        ViewCompat.setTranslationY(this.mUserInfoLayout, 0.0f);
        this.e = true;
        this.k = null;
        this.mUserInfoLayout.setVisibility(4);
        if (this.c != null && this.c.size() > 0) {
            LiveGiftMessage liveGiftMessage;
            synchronized (this) {
                liveGiftMessage = (LiveGiftMessage) this.c.remove(0);
            }
            if (liveGiftMessage != null) {
                addGift(liveGiftMessage);
            }
        }
    }

    private void b() {
        View inflate = View.inflate(this.b, R.layout.live_large_gift_user_item, this);
        this.mUserInfoLayout = (RelativeLayout) inflate.findViewById(R.id.live_large_part_without_text);
        this.ivAvatar = (ImageView) inflate.findViewById(R.id.live_gift_avatar);
        this.tvUserName = (TextView) inflate.findViewById(R.id.live_large_gift_username);
        this.tvGiftName = (TextView) inflate.findViewById(R.id.live_large_gift_name);
        this.h = (FamilyLevelView) inflate.findViewById(R.id.fl_level);
        this.mUserInfoLayout.setVisibility(4);
    }

    public Bitmap getGiftBitmap(int i) {
        Bitmap bitmap;
        SoftReference softReference = (SoftReference) this.d.get(Integer.valueOf(i));
        if (softReference != null) {
            bitmap = (Bitmap) softReference.get();
        } else {
            bitmap = null;
        }
        if (bitmap == null || bitmap.isRecycled()) {
            try {
                bitmap = BitmapFactory.decodeResource(getResources(), i);
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
                System.gc();
            }
            if (bitmap != null) {
                this.d.put(Integer.valueOf(i), new SoftReference(bitmap));
            }
        }
        return bitmap;
    }

    private void f(LiveGiftMessage liveGiftMessage) {
        AppUtils.getInstance().getImageProvider().loadAvatar(this.ivAvatar, liveGiftMessage.getUserAvatar(), true);
        this.tvUserName.setText(liveGiftMessage.getUserName());
        this.tvGiftName.setText(liveGiftMessage.getGiftShowContent());
        this.ivAvatar.setOnClickListener(new ga(this, liveGiftMessage));
        if (TextUtils.isEmpty(liveGiftMessage.getMessageBadge())) {
            this.h.setVisibility(8);
            return;
        }
        this.h.setVisibility(0);
        this.h.setLevelAndName(liveGiftMessage.getMessageFamilyLevel(), liveGiftMessage.getMessageBadge());
    }

    public void releaseResource() {
        this.i = null;
        this.c.clear();
        this.j.clear();
        this.mHandler.removeCallbacksAndMessages(null);
        if (this.k != null) {
            this.k.releaseResource();
        }
        removeAllViews();
        if (this.d != null && this.d.size() > 0) {
            for (SoftReference softReference : this.d.values()) {
                if (softReference != null) {
                    Bitmap bitmap = (Bitmap) softReference.get();
                    if (!(bitmap == null || bitmap.isRecycled())) {
                        bitmap.recycle();
                    }
                }
            }
            this.d.clear();
        }
        if (this.l != null) {
            this.l.release();
        }
    }

    public void setLiveMessageListener(LiveMessageListener liveMessageListener) {
        this.i = liveMessageListener;
    }

    public LiveMessageListener getLiveMessageListener() {
        return this.i;
    }

    public void spreadFlowers(View view, int i) {
        this.mHandler.post(new gb(this, new int[]{R.drawable.live_petal, R.drawable.live_petal1, R.drawable.live_petal2, R.drawable.live_petal3, R.drawable.live_petal4, R.drawable.live_petal5, R.drawable.live_petal6, R.drawable.live_petal7, R.drawable.live_petal8, R.drawable.live_petal9}, i, view));
    }

    public void spreadKiss(View view, int i) {
        this.mHandler.postDelayed(new gc(this, i, view), 300);
    }

    private void a(View view, int i) {
        this.mHandler.postDelayed(new gd(this, i, view), 400);
    }

    private void b(View view, int i) {
        this.mHandler.postDelayed(new ge(this, new int[]{R.drawable.live_music_note_1, R.drawable.live_music_note_2, R.drawable.live_music_note_3, R.drawable.live_music_note_4, R.drawable.live_music_note_5}, i, view), 300);
    }

    public void showSpecialAnimation(int i, int i2, long j) {
        if (this.e) {
            View view = new View(this.b);
            addView(view);
            ViewGroup.LayoutParams layoutParams = new LayoutParams(0, 0);
            layoutParams.topMargin = i;
            layoutParams.leftMargin = i2;
            view.setLayoutParams(layoutParams);
            if (j == 9) {
                spreadFlowers(view, 1000);
            } else if (j == 11) {
                spreadKiss(view, 1000);
            } else if (j == 26) {
                a(view, 1000);
            } else if (j == 32) {
                b(view, 1000);
            }
            this.mHandler.postDelayed(new gf(this, view), (long) 1000);
        }
    }

    public void showFullScreenAnimation() {
        View inflate = View.inflate(this.b, R.layout.imageview_live_webp, null);
        addView(inflate, this.f, this.g);
        AppUtils.getInstance().getImageProvider().loadWebpImage((ImageView) inflate.findViewById(R.id.iv_background), "res://raw/" + R.raw.live_rose_full_screen);
        this.mHandler.postDelayed(new gg(this, inflate), 3000);
    }

    public void loadFrameAnimation(LiveGiftMessage liveGiftMessage) {
        ImageView imageView = new ImageView(this.b);
        imageView.setAlpha(0);
        a(imageView);
        f(liveGiftMessage);
        setWebpUserPosition(this.mUserInfoLayout);
        this.l = new FrameAnimationView(getContext());
        this.l.setLayoutParams(new LayoutParams(-1, -1));
        this.l.setScaleType(ScaleType.CENTER_CROP);
        this.l.setFramesInSdCard(Environment.getExternalStorageDirectory() + "/Remix/.Animation/" + liveGiftMessage.getGiftId());
        this.l.setAnimationListener(new gh(this, imageView));
        addView(this.l);
        this.l.play();
        if (liveGiftMessage.getGiftId() == 100) {
            postDelayed(new fs(this, liveGiftMessage), 4000);
            postDelayed(new ft(this, liveGiftMessage), 8000);
        }
    }

    private void g(LiveGiftMessage liveGiftMessage) {
        View imageView = new ImageView(getContext());
        addView(imageView);
        imageView.setImageResource(R.drawable.live_luckyegg_diamond);
        a(liveGiftMessage, imageView, liveGiftMessage.getLuckyEggDiamondCount(), false);
    }

    private void a(LiveGiftMessage liveGiftMessage, ImageView imageView, long j, boolean z) {
        int width = getWidth();
        int height = getHeight();
        int i = width / 5;
        imageView.setLayoutParams(new LayoutParams(i, i));
        if (imageView instanceof SimpleDraweeView) {
            AppUtils.getInstance().getImageProvider().loadGift(imageView, ConfigInfoUtil.instance().getGiftUrlById(liveGiftMessage.getLuckyEggGiftId()));
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(imageView, Y, new float[]{(float) height, (float) ((height - i) / 2)});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(imageView, X, new float[]{(float) ((width - i) / 2), (float) ((width - i) / 2)});
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(imageView, SCALE_X, new float[]{0.0f, 0.3f});
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(imageView, SCALE_Y, new float[]{0.0f, 0.3f});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2, ofFloat3, ofFloat4});
        animatorSet.setDuration(500);
        ofFloat = ObjectAnimator.ofFloat(imageView, SCALE_X, new float[]{0.3f, 1.2f});
        ofFloat2 = ObjectAnimator.ofFloat(imageView, SCALE_Y, new float[]{0.3f, 1.2f});
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet2.setDuration(200);
        ofFloat = ObjectAnimator.ofFloat(imageView, SCALE_X, new float[]{1.2f, 0.9f});
        ofFloat2 = ObjectAnimator.ofFloat(imageView, SCALE_Y, new float[]{1.2f, 0.9f});
        AnimatorSet animatorSet3 = new AnimatorSet();
        animatorSet3.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet3.setDuration(200);
        ofFloat = ObjectAnimator.ofFloat(imageView, SCALE_X, new float[]{0.9f, 1.1f});
        ofFloat2 = ObjectAnimator.ofFloat(imageView, SCALE_Y, new float[]{0.9f, 1.1f});
        AnimatorSet animatorSet4 = new AnimatorSet();
        animatorSet4.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet4.setDuration(200);
        ofFloat = ObjectAnimator.ofFloat(imageView, SCALE_X, new float[]{1.1f, 1.0f});
        ofFloat2 = ObjectAnimator.ofFloat(imageView, SCALE_Y, new float[]{1.1f, 1.0f});
        AnimatorSet animatorSet5 = new AnimatorSet();
        animatorSet5.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet5.setDuration(200);
        ObjectAnimator.ofFloat(imageView, SCALE_X, new float[]{1.0f, 1.0f}).setDuration(2500);
        View frameAnimationView = new FrameAnimationView(getContext());
        frameAnimationView.setFramesInAssets("luckyegg_flash");
        frameAnimationView.setFillAfter(true);
        addView(frameAnimationView);
        int i2 = width / 3;
        ViewGroup.LayoutParams layoutParams = new LayoutParams(i2, i2);
        layoutParams.topMargin = (height - i2) / 2;
        layoutParams.leftMargin = (width - i2) / 2;
        frameAnimationView.setLayoutParams(layoutParams);
        postDelayed(new fu(this, frameAnimationView), 600);
        ofFloat2 = ObjectAnimator.ofFloat(imageView, Y, new float[]{(float) ((height - i) / 2), (float) WindowUtils.dp2Px(10)});
        ofFloat = ObjectAnimator.ofFloat(imageView, X, new float[]{(float) ((width - i) / 2), (float) WindowUtils.dp2Px(10)});
        if (!z) {
            ofFloat2 = ObjectAnimator.ofFloat(imageView, Y, new float[]{(float) ((height - i) / 2), (float) WindowUtils.dp2Px(90)});
            ofFloat = ObjectAnimator.ofFloat(imageView, X, new float[]{(float) ((width - i) / 2), (float) ((width - this.mUserInfoLayout.getWidth()) / 2)});
        }
        ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(imageView, SCALE_X, new float[]{1.0f, 0.0f});
        ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(imageView, SCALE_Y, new float[]{1.0f, 0.0f});
        AnimatorSet animatorSet6 = new AnimatorSet();
        animatorSet6.playTogether(new Animator[]{ofFloat2, ofFloat5, ofFloat6, ofFloat});
        animatorSet6.setDuration(800);
        AnimatorSet animatorSet7 = new AnimatorSet();
        animatorSet7.playSequentially(new Animator[]{animatorSet, animatorSet2, animatorSet3, animatorSet4, animatorSet5, r13, animatorSet6});
        animatorSet7.start();
        animatorSet7.addListener(new fv(this, imageView));
        postDelayed(new fw(this, height, i, j, frameAnimationView), 1460);
    }

    private void h(LiveGiftMessage liveGiftMessage) {
        View simpleDraweeView = new SimpleDraweeView(getContext());
        addView(simpleDraweeView);
        a(liveGiftMessage, simpleDraweeView, liveGiftMessage.getLuckyEggGiftCount(), true);
    }

    private void a(ImageView imageView) {
        imageView.setBackgroundColor(this.b.getResources().getColor(R.color.black));
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -1);
        imageView.setVisibility(0);
        imageView.setLayoutParams(layoutParams);
        addView(imageView);
    }

    private void a(View view) {
        Animator ofFloat = ObjectAnimator.ofFloat(view, ALPHA, new float[]{0.0f, 0.3f});
        ofFloat.setDuration(500);
        view.setVisibility(0);
        ofFloat.start();
    }

    private void b(View view) {
        Animator ofFloat = ObjectAnimator.ofFloat(view, ALPHA, new float[]{0.3f, 0.0f});
        ofFloat.setDuration(500);
        ofFloat.start();
    }
}
