package qsbk.app.live.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.opensource.svgaplayer.SVGAImageView;
import com.opensource.svgaplayer.SVGAParser;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import qsbk.app.api.BigCoverHelper;
import qsbk.app.core.model.MarketData;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.ConfigInfoUtil;
import qsbk.app.core.utils.WindowUtils;
import qsbk.app.core.widget.FrameAnimationView;
import qsbk.app.live.R;
import qsbk.app.live.model.LiveEnterFrameUserinfo;
import qsbk.app.live.model.LiveEnterMessage;
import qsbk.app.live.ui.helper.LiveMessageListener;

public class SuperUserEnterAnimLayout extends RelativeLayout {
    private static final String a = SuperUserEnterAnimLayout.class.getSimpleName();
    private Context b;
    private int c;
    private int d;
    private ArrayList<LiveEnterMessage> e;
    private volatile boolean f;
    private LiveEnterMessage g;
    private SimpleDraweeView h;
    private SimpleDraweeView i;
    private TextView j;
    private ImageView k;
    private FrameAnimationView l;
    private LinearLayout m;
    public Handler mHandler;
    private SimpleDraweeView n;
    private TextView o;
    private SVGAImageView p;
    private TextView q;
    private LiveMessageListener r;
    private Runnable s;

    public SuperUserEnterAnimLayout(Context context) {
        this(context, null);
    }

    public SuperUserEnterAnimLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SuperUserEnterAnimLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = new ArrayList();
        this.f = true;
        this.mHandler = new Handler();
        this.s = new iu(this);
        this.b = context;
        setWillNotDraw(false);
        a();
    }

    private void a() {
        setVisibility(4);
        View inflate = View.inflate(getContext(), R.layout.live_special_user_enter_view, this);
        this.h = (SimpleDraweeView) inflate.findViewById(R.id.iv_anim);
        this.i = (SimpleDraweeView) inflate.findViewById(R.id.iv_avatar);
        this.j = (TextView) inflate.findViewById(R.id.tv_desc);
        this.k = (ImageView) inflate.findViewById(R.id.bg);
        this.m = (LinearLayout) inflate.findViewById(R.id.ll_enter_info);
        this.n = (SimpleDraweeView) inflate.findViewById(R.id.iv_market_avatar);
        this.o = (TextView) inflate.findViewById(R.id.tv_market_name);
        this.i.setOnClickListener(new is(this));
        this.l = (FrameAnimationView) inflate.findViewById(R.id.iv_frame_anim);
        this.p = (SVGAImageView) inflate.findViewById(R.id.iv_svga);
        this.p.setLoops(1);
        this.q = (TextView) inflate.findViewById(R.id.tv_market_desc);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.c = getMeasuredWidth();
        this.d = getMeasuredHeight();
    }

    public void addEnterMessage(LiveEnterMessage liveEnterMessage) {
        a(liveEnterMessage);
        b();
    }

    private void a(LiveEnterMessage liveEnterMessage) {
        this.e.add(liveEnterMessage);
    }

    private void b() {
        if (this.e.size() > 0 && this.f) {
            post(this.s);
        }
    }

    public void setViewContentAndStartAnim(LiveEnterMessage liveEnterMessage) {
        if ((this.b instanceof FragmentActivity) && !((FragmentActivity) this.b).isFinishing()) {
            this.f = false;
            this.g = liveEnterMessage;
            MarketData marketDatById = ConfigInfoUtil.instance().getMarketDatById(liveEnterMessage.getEnterAnimId());
            if (marketDatById != null) {
                if (marketDatById.y == 1) {
                    setFrameEnterAnimContentandStartAnim(liveEnterMessage);
                } else if (marketDatById.y == 2) {
                    setSVGAEnterAnimContentandStart(liveEnterMessage);
                } else {
                    this.f = true;
                }
            } else if (liveEnterMessage.getUserId() == 578888 && liveEnterMessage.getOrigin() == 1) {
                setJiamingContentAndStartAnim(liveEnterMessage);
            } else {
                setYelangContentAndStartAnim(liveEnterMessage);
            }
        }
    }

    private void setFrameEnterAnimContentandStartAnimV2(LiveEnterMessage liveEnterMessage) {
        setVisibility(0);
        List arrayList = new ArrayList();
        LiveEnterFrameUserinfo liveEnterFrameUserinfo = new LiveEnterFrameUserinfo();
        liveEnterFrameUserinfo.index = 20;
        liveEnterFrameUserinfo.left = 0.261f;
        liveEnterFrameUserinfo.top = 0.2f;
        liveEnterFrameUserinfo.width = WindowUtils.dp2Px(BigCoverHelper.REQCODE_CAREMA);
        liveEnterFrameUserinfo.height = WindowUtils.dp2Px(20);
        liveEnterFrameUserinfo.op = 0;
        LiveEnterFrameUserinfo liveEnterFrameUserinfo2 = new LiveEnterFrameUserinfo();
        liveEnterFrameUserinfo2.index = 28;
        liveEnterFrameUserinfo2.left = 0.261f;
        liveEnterFrameUserinfo2.top = 0.2f;
        liveEnterFrameUserinfo2.width = WindowUtils.dp2Px(BigCoverHelper.REQCODE_CAREMA);
        liveEnterFrameUserinfo2.height = WindowUtils.dp2Px(20);
        liveEnterFrameUserinfo2.op = 100;
        LiveEnterFrameUserinfo liveEnterFrameUserinfo3 = new LiveEnterFrameUserinfo();
        liveEnterFrameUserinfo3.index = 78;
        liveEnterFrameUserinfo3.left = 0.261f;
        liveEnterFrameUserinfo3.top = 0.2f;
        liveEnterFrameUserinfo3.width = WindowUtils.dp2Px(BigCoverHelper.REQCODE_CAREMA);
        liveEnterFrameUserinfo3.height = WindowUtils.dp2Px(20);
        liveEnterFrameUserinfo3.op = 100;
        LiveEnterFrameUserinfo liveEnterFrameUserinfo4 = new LiveEnterFrameUserinfo();
        liveEnterFrameUserinfo4.index = 86;
        liveEnterFrameUserinfo4.left = 0.261f;
        liveEnterFrameUserinfo4.top = 0.2f;
        liveEnterFrameUserinfo4.width = WindowUtils.dp2Px(BigCoverHelper.REQCODE_CAREMA);
        liveEnterFrameUserinfo4.height = WindowUtils.dp2Px(20);
        liveEnterFrameUserinfo4.op = 0;
        arrayList.add(liveEnterFrameUserinfo);
        arrayList.add(liveEnterFrameUserinfo2);
        arrayList.add(liveEnterFrameUserinfo3);
        arrayList.add(liveEnterFrameUserinfo4);
        a(liveEnterMessage, arrayList, 750, 400, "开着法拉利来了");
    }

    private void a(LiveEnterMessage liveEnterMessage, List<LiveEnterFrameUserinfo> list, int i, int i2, String str) {
        this.m.setVisibility(0);
        this.o.setText(liveEnterMessage.getUserName());
        AppUtils.getInstance().getImageProvider().loadAvatar(this.n, liveEnterMessage.getUserAvatar(), true);
        this.q.setText(str);
        LayoutParams layoutParams = (LayoutParams) this.l.getLayoutParams();
        layoutParams.width = WindowUtils.getScreenWidth();
        layoutParams.height = (int) (((float) WindowUtils.getScreenWidth()) * (((float) i2) / ((float) i)));
        layoutParams.gravity = 17;
        this.l.setLayoutParams(layoutParams);
        this.l.play();
        this.l.setAnimationListener(new iv(this));
        if (list != null && list.size() > 0) {
            for (int i3 = 0; i3 < list.size() - 1; i3++) {
                LiveEnterFrameUserinfo liveEnterFrameUserinfo = (LiveEnterFrameUserinfo) list.get(i3);
                this.mHandler.postDelayed(new iw(this, liveEnterFrameUserinfo, layoutParams, (LiveEnterFrameUserinfo) list.get(i3 + 1)), (long) (liveEnterFrameUserinfo.index * 40));
            }
        }
    }

    private void setFrameEnterAnimContentandStartAnim(LiveEnterMessage liveEnterMessage) {
        setVisibility(0);
        MarketData marketDatById = ConfigInfoUtil.instance().getMarketDatById(liveEnterMessage.getEnterAnimId());
        LayoutParams layoutParams = (LayoutParams) this.m.getLayoutParams();
        layoutParams.topMargin = (int) (((double) this.d) * 0.219d);
        layoutParams.gravity = 1;
        this.m.setLayoutParams(layoutParams);
        this.mHandler.postDelayed(new ix(this, liveEnterMessage), 1480);
        layoutParams = (LayoutParams) this.l.getLayoutParams();
        layoutParams.height = this.d;
        layoutParams.width = this.c;
        this.l.setLayoutParams(layoutParams);
        this.l.setFramesInSdCard(Environment.getExternalStorageDirectory() + "/Remix/.Market/" + marketDatById.i);
        this.l.play();
        c();
        this.l.setAnimationListener(new iy(this));
    }

    private void setSVGAEnterAnimContentandStart(LiveEnterMessage liveEnterMessage) {
        setVisibility(0);
        this.p.setVisibility(0);
        MarketData marketDatById = ConfigInfoUtil.instance().getMarketDatById(liveEnterMessage.getEnterAnimId());
        if (marketDatById != null) {
            try {
                new SVGAParser(getContext()).parse(new URL(marketDatById.r), new iz(this, liveEnterMessage, marketDatById));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setJiamingContentAndStartAnim(LiveEnterMessage liveEnterMessage) {
        int i = (int) (((double) (this.d / 2)) * 1.18d);
        int i2 = (int) (((double) i) * 0.81d);
        int i3 = (int) (((double) i2) * 0.25d);
        int i4 = (int) (((double) i) * 0.91d);
        LayoutParams layoutParams = (LayoutParams) this.h.getLayoutParams();
        layoutParams.height = i;
        layoutParams.width = i2;
        this.h.setLayoutParams(layoutParams);
        layoutParams = (LayoutParams) this.i.getLayoutParams();
        layoutParams.height = i3;
        layoutParams.width = i3;
        this.i.setLayoutParams(layoutParams);
        layoutParams = (LayoutParams) this.j.getLayoutParams();
        layoutParams.topMargin = i4;
        this.j.setLayoutParams(layoutParams);
        startJiamingEnterAnim(liveEnterMessage);
    }

    public void setYelangContentAndStartAnim(LiveEnterMessage liveEnterMessage) {
        int i = (int) (((double) (this.d / 2)) * 1.2d);
        int i2 = (int) (((double) i) * 0.93d);
        int i3 = (int) (((double) i2) * 0.19d);
        int dp2Px = WindowUtils.dp2Px(3) + ((int) (((double) i) * 0.91d));
        this.h.setVisibility(8);
        this.l.setVisibility(0);
        LayoutParams layoutParams = (LayoutParams) this.l.getLayoutParams();
        layoutParams.height = i;
        layoutParams.width = i2;
        layoutParams.gravity = 14;
        this.l.setLayoutParams(layoutParams);
        layoutParams = (LayoutParams) this.i.getLayoutParams();
        layoutParams.height = i3;
        layoutParams.width = i3;
        this.i.setLayoutParams(layoutParams);
        layoutParams = (LayoutParams) this.j.getLayoutParams();
        layoutParams.topMargin = dp2Px;
        this.j.setLayoutParams(layoutParams);
        startYelangEnterAnim(liveEnterMessage);
    }

    public void startJiamingEnterAnim(LiveEnterMessage liveEnterMessage) {
        setVisibility(0);
        AppUtils.getInstance().getImageProvider().loadWebpImage(this.h, "res://raw/" + R.raw.jm);
        AppUtils.getInstance().getImageProvider().loadAvatar(this.i, liveEnterMessage.getUserAvatar(), true);
        this.j.setText("Lv" + liveEnterMessage.getUserLevel() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + a(liveEnterMessage.getUserName()) + "\n" + "降临直播间");
        a(this.i);
        a(this.j);
        e();
    }

    public void startYelangEnterAnim(LiveEnterMessage liveEnterMessage) {
        setVisibility(0);
        AppUtils.getInstance().getImageProvider().loadAvatar(this.i, liveEnterMessage.getUserAvatar(), true);
        this.j.setText("Lv" + liveEnterMessage.getUserLevel() + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + a(liveEnterMessage.getUserName()) + "\n" + "降临直播间");
        b(this.i);
        b(this.j);
        f();
        this.l.setAnimationListener(new jd(this));
        this.l.setFramesInAssets("yelang");
        this.l.play();
    }

    private String a(String str) {
        return str.length() <= 5 ? str : str.substring(0, 5) + "...";
    }

    private void a(View view) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.SCALE_X, new float[]{0.0f, 1.1f});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{0.0f, 1.1f});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet.setDuration(120);
        ofFloat = ObjectAnimator.ofFloat(view, View.SCALE_X, new float[]{1.1f, 0.9f});
        ofFloat2 = ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{1.1f, 0.9f});
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet2.setDuration(120);
        ofFloat = ObjectAnimator.ofFloat(view, View.SCALE_X, new float[]{0.9f, 1.0f});
        ofFloat2 = ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{0.9f, 1.0f});
        AnimatorSet animatorSet3 = new AnimatorSet();
        animatorSet3.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet3.setDuration(120);
        ofFloat = ObjectAnimator.ofFloat(view, View.SCALE_X, new float[]{1.0f, 1.0f});
        ofFloat2 = ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{1.0f, 1.0f});
        AnimatorSet animatorSet4 = new AnimatorSet();
        animatorSet4.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet4.setDuration(2040);
        ofFloat = ObjectAnimator.ofFloat(view, View.SCALE_X, new float[]{1.0f, 1.1f});
        ofFloat2 = ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{1.0f, 1.1f});
        AnimatorSet animatorSet5 = new AnimatorSet();
        animatorSet5.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet5.setDuration(320);
        ofFloat = ObjectAnimator.ofFloat(view, View.SCALE_X, new float[]{1.1f, 0.0f});
        ofFloat2 = ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{1.1f, 0.0f});
        AnimatorSet animatorSet6 = new AnimatorSet();
        animatorSet6.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet6.setDuration(120);
        AnimatorSet animatorSet7 = new AnimatorSet();
        animatorSet7.playSequentially(new Animator[]{animatorSet, animatorSet2, animatorSet3, animatorSet4, animatorSet5, animatorSet6});
        animatorSet7.start();
    }

    private void b(View view) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.SCALE_X, new float[]{0.0f, 0.0f});
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{0.0f, 0.0f});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet.setDuration(160);
        ofFloat = ObjectAnimator.ofFloat(view, View.SCALE_X, new float[]{0.0f, 1.1f});
        ofFloat2 = ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{0.0f, 1.1f});
        animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet.setDuration(160);
        ofFloat = ObjectAnimator.ofFloat(view, View.SCALE_X, new float[]{1.1f, 0.95f});
        ofFloat2 = ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{1.1f, 0.95f});
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet2.setDuration(80);
        ofFloat = ObjectAnimator.ofFloat(view, View.SCALE_X, new float[]{0.95f, 1.0f});
        ofFloat2 = ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{0.95f, 1.0f});
        AnimatorSet animatorSet3 = new AnimatorSet();
        animatorSet3.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet3.setDuration(80);
        ofFloat = ObjectAnimator.ofFloat(view, View.SCALE_X, new float[]{1.0f, 1.0f});
        ofFloat2 = ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{1.0f, 1.0f});
        AnimatorSet animatorSet4 = new AnimatorSet();
        animatorSet4.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet4.setDuration(2120);
        ofFloat = ObjectAnimator.ofFloat(view, View.SCALE_X, new float[]{1.0f, 0.95f});
        ofFloat2 = ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{1.0f, 0.95f});
        AnimatorSet animatorSet5 = new AnimatorSet();
        animatorSet5.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet5.setDuration(80);
        ofFloat = ObjectAnimator.ofFloat(view, View.SCALE_X, new float[]{0.95f, 1.1f});
        ofFloat2 = ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{0.95f, 1.1f});
        AnimatorSet animatorSet6 = new AnimatorSet();
        animatorSet6.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet6.setDuration(80);
        ofFloat = ObjectAnimator.ofFloat(view, View.SCALE_X, new float[]{1.1f, 0.0f});
        ofFloat2 = ObjectAnimator.ofFloat(view, View.SCALE_Y, new float[]{1.1f, 0.0f});
        AnimatorSet animatorSet7 = new AnimatorSet();
        animatorSet7.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet7.setDuration(120);
        AnimatorSet animatorSet8 = new AnimatorSet();
        animatorSet8.playSequentially(new Animator[]{animatorSet, animatorSet2, animatorSet3, animatorSet4, animatorSet5, animatorSet6, animatorSet7});
        animatorSet8.start();
    }

    public void setLiveMessageListener(LiveMessageListener liveMessageListener) {
        this.r = liveMessageListener;
    }

    public void terminateAllAnim() {
        this.e.clear();
        removeCallbacks(this.s);
    }

    public void releaseResource() {
        this.r = null;
        terminateAllAnim();
        removeAllViews();
    }

    private void c() {
        Animator ofFloat = ObjectAnimator.ofFloat(this.k, ALPHA, new float[]{0.0f, 0.3f});
        ofFloat.setDuration(80);
        ofFloat.start();
        this.k.setVisibility(0);
    }

    private void d() {
        this.k.setVisibility(8);
    }

    private void e() {
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet animatorSet2 = new AnimatorSet();
        AnimatorSet animatorSet3 = new AnimatorSet();
        AnimatorSet animatorSet4 = new AnimatorSet();
        this.k.setVisibility(0);
        Animator ofFloat = ObjectAnimator.ofFloat(this.k, ALPHA, new float[]{0.0f, 0.3f});
        animatorSet.setDuration(80);
        animatorSet.play(ofFloat);
        ofFloat = ObjectAnimator.ofFloat(this.k, ALPHA, new float[]{0.3f, 0.3f});
        animatorSet2.setDuration(2800);
        animatorSet2.play(ofFloat);
        ofFloat = ObjectAnimator.ofFloat(this.k, ALPHA, new float[]{0.3f, 0.0f});
        animatorSet3.setDuration(80);
        animatorSet3.play(ofFloat);
        animatorSet4.playSequentially(new Animator[]{animatorSet, animatorSet2, animatorSet3});
        animatorSet4.addListener(new je(this));
        animatorSet4.start();
    }

    private void f() {
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSet animatorSet2 = new AnimatorSet();
        AnimatorSet animatorSet3 = new AnimatorSet();
        AnimatorSet animatorSet4 = new AnimatorSet();
        this.k.setVisibility(0);
        Animator ofFloat = ObjectAnimator.ofFloat(this.k, ALPHA, new float[]{0.0f, 0.3f});
        animatorSet.setDuration(80);
        animatorSet.play(ofFloat);
        ofFloat = ObjectAnimator.ofFloat(this.k, ALPHA, new float[]{0.3f, 0.3f});
        animatorSet2.setDuration(2800);
        animatorSet2.play(ofFloat);
        ofFloat = ObjectAnimator.ofFloat(this.k, ALPHA, new float[]{0.3f, 0.0f});
        animatorSet3.setDuration(80);
        animatorSet3.play(ofFloat);
        animatorSet4.playSequentially(new Animator[]{animatorSet, animatorSet2, animatorSet3});
        animatorSet4.addListener(new it(this));
        animatorSet4.start();
    }

    public static Bitmap getbitmap(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            return decodeStream;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static Bitmap cropBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width < height) {
            height = width;
        }
        return Bitmap.createBitmap(bitmap, (bitmap.getWidth() - height) / 2, (bitmap.getHeight() - height) / 2, height, height);
    }

    public static Bitmap getCircleBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        Bitmap cropBitmap = cropBitmap(bitmap);
        try {
            Bitmap createBitmap = Bitmap.createBitmap(cropBitmap.getWidth(), cropBitmap.getHeight(), Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            Paint paint = new Paint();
            Rect rect = new Rect(0, 0, cropBitmap.getWidth(), cropBitmap.getHeight());
            RectF rectF = new RectF(new Rect(0, 0, cropBitmap.getWidth(), cropBitmap.getHeight()));
            float width = (float) cropBitmap.getWidth();
            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(-1);
            canvas.drawRoundRect(rectF, width, width, paint);
            paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
            canvas.drawBitmap(cropBitmap, new Rect(0, 0, cropBitmap.getWidth(), cropBitmap.getHeight()), rect, paint);
            return createBitmap;
        } catch (Exception e) {
            return cropBitmap;
        }
    }
}
