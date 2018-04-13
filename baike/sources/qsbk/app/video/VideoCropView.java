package qsbk.app.video;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import java.util.ArrayList;
import qsbk.app.R;
import qsbk.app.core.AsyncTask;
import qsbk.app.utils.DebugUtil;
import qsbk.app.utils.LogUtil;

public class VideoCropView extends RelativeLayout {
    public static final int MAX_CROP_TIME = 300000;
    public static final int MIN_CROP_TIME = 3000;
    private static final String a = VideoCropView.class.getSimpleName();
    private HorizontalScrollView b;
    private LinearLayout c;
    private HolderImageView d;
    private HolderImageView e;
    private ImageView f;
    private ImageView g;
    private View h;
    private View i;
    private Context j;
    private int k;
    private int l;
    private int m;
    private int n;
    private IVideoCropListener o;
    private TextView p;
    private long q;
    private AsyncTask<Integer, Pair<Integer, Bitmap>, ArrayList<Bitmap>> r;
    private int s;
    private Handler t;

    public static class HolderImageView {
        public int mBottom = 0;
        public ImageView mImageView;
        public int mLeft = 0;
        public int mRight = 0;
        public int mTop = 0;

        public HolderImageView(ImageView imageView) {
            this.mImageView = imageView;
        }

        public void setValue(int i, int i2, int i3, int i4) {
            this.mLeft = i;
            this.mRight = i2;
            this.mTop = i3;
            this.mBottom = i4;
        }
    }

    public interface IVideoCropListener {
        void videoCrop(int i, int i2);
    }

    public VideoCropView(Context context) {
        this(context, null, 0);
    }

    public VideoCropView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VideoCropView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.l = 0;
        this.m = 0;
        this.o = null;
        this.s = 0;
        this.t = new Handler(new q(this));
        a(context);
    }

    private void b() {
        if (this.o != null) {
            this.o.videoCrop(c(), d());
        }
    }

    private void a(Context context) {
        this.j = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.video_frame_crop, this);
        if (Build.MODEL.contains("M032")) {
            ((TextView) inflate.findViewById(R.id.drag_tip)).setVisibility(8);
        }
        this.b = (HorizontalScrollView) inflate.findViewById(R.id.frame_hs);
        this.b.setOnTouchListener(new r(this));
        this.c = (LinearLayout) inflate.findViewById(R.id.frame_ll);
        this.f = (ImageView) inflate.findViewById(R.id.left_drag_bar);
        this.f.setOnTouchListener(new s(this));
        this.d = new HolderImageView(this.f);
        this.g = (ImageView) inflate.findViewById(R.id.right_drag_bar);
        this.g.setOnTouchListener(new t(this));
        this.e = new HolderImageView(this.g);
        this.h = inflate.findViewById(R.id.sharder_left);
        this.i = inflate.findViewById(R.id.sharder_right);
        this.p = (TextView) findViewById(R.id.crop_time);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.k = displayMetrics.widthPixels;
    }

    private void a(View view, MotionEvent motionEvent, int i, int i2, boolean z) {
        DebugUtil.debug(a, "onTouchView :" + motionEvent.getAction());
        int d;
        switch (motionEvent.getAction()) {
            case 0:
                if (z) {
                    this.d.mImageView.setImageResource(R.drawable.holder_left_pressed);
                    return;
                } else {
                    this.e.mImageView.setImageResource(R.drawable.holder_right_pressed);
                    return;
                }
            case 1:
                if (z) {
                    this.d.mImageView.setImageResource(R.drawable.holder_left_normal);
                } else {
                    this.e.mImageView.setImageResource(R.drawable.holder_right_normal);
                }
                if (this.o != null) {
                    int c = c();
                    d = d();
                    LogUtil.d(a + " startTime = " + c + " endTime = " + d);
                    this.o.videoCrop(c, d);
                    return;
                }
                return;
            case 2:
                d = ((int) motionEvent.getRawX()) - (view.getWidth() / 2);
                int top = view.getTop();
                int width = (view.getWidth() / 2) + ((int) motionEvent.getRawX());
                int bottom = view.getBottom();
                DebugUtil.debug(a, "onTouchView :" + d + "  " + width + "  " + top + "  " + bottom);
                if (d < i) {
                    width = i + view.getWidth();
                    d = i;
                }
                if (width > i2) {
                    d = i2 - view.getWidth();
                    width = i2;
                }
                a(d, top, width, bottom, z);
                this.p.setText(String.format("%d", new Object[]{Integer.valueOf(Math.round(((float) e()) / 1000.0f))}) + "s");
                return;
            default:
                return;
        }
    }

    private void a(int i, int i2, int i3, int i4, boolean z) {
        int i5;
        int left = (this.f.getLeft() + this.s) - this.l;
        int right = (this.g.getRight() + this.s) - this.l;
        LayoutParams layoutParams = (LayoutParams) this.d.mImageView.getLayoutParams();
        LayoutParams layoutParams2 = (LayoutParams) this.e.mImageView.getLayoutParams();
        if (z) {
            i5 = (i - layoutParams.leftMargin) + left;
            left = (this.e.mLeft - layoutParams2.leftMargin) + right;
        } else {
            i5 = (this.d.mLeft - layoutParams.leftMargin) + left;
            left = (i - layoutParams2.leftMargin) + right;
        }
        float a = a((float) i5, (float) left);
        if (a > 3000.0f && a < 300000.0f) {
            if (z) {
                this.d.setValue(i, i3, i2, i4);
            } else {
                this.e.setValue(i, i3, i2, i4);
            }
            layoutParams.leftMargin = this.d.mLeft;
            layoutParams2.leftMargin = this.e.mLeft;
            this.d.mImageView.setLayoutParams(layoutParams);
            this.e.mImageView.setLayoutParams(layoutParams2);
            layoutParams = (LayoutParams) this.h.getLayoutParams();
            layoutParams.width = this.d.mLeft;
            this.h.setLayoutParams(layoutParams);
        }
    }

    public void addListener(IVideoCropListener iVideoCropListener) {
        this.o = iVideoCropListener;
    }

    @SuppressLint({"InlinedApi"})
    public void initView(String str) {
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(str);
        this.q = Long.parseLong(mediaMetadataRetriever.extractMetadata(9));
        this.r = new u(this, 5, this.q / ((long) 5), mediaMetadataRetriever);
        this.r.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Integer[5]);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.r != null) {
            this.r.cancel(true);
        }
    }

    private ImageView a(int i) {
        ImageView imageView = new ImageView(this.j);
        imageView.setScaleType(ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new LayoutParams(i, this.j.getResources().getDimensionPixelSize(R.dimen.video_frame_length)));
        return imageView;
    }

    private int c() {
        return Math.round(((((float) ((this.f.getLeft() + this.s) - this.l)) * 1.0f) / ((float) ((this.c.getRight() - this.c.getLeft()) - (this.l * 2)))) * ((float) this.q));
    }

    private int d() {
        return Math.round(((((float) ((this.g.getRight() + this.s) - this.l)) * 1.0f) / ((float) ((this.c.getRight() - this.c.getLeft()) - (this.l * 2)))) * ((float) this.q));
    }

    private int e() {
        return d() - c();
    }

    private float a(float f, float f2) {
        return (((f2 - f) * 1.0f) / ((float) ((this.c.getRight() - this.c.getLeft()) - (this.l * 2)))) * ((float) this.q);
    }
}
