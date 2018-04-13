package qsbk.app.activity.publish;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import java.util.Random;
import qsbk.app.R;
import qsbk.app.utils.DeviceUtils;
import qsbk.app.utils.ImageUtils;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.widget.ButtonBar;
import qsbk.app.widget.barcode.BarcodeView;
import qsbk.app.widget.barcode.BarcodeView.OnCloseListener;
import qsbk.app.widget.barcode.Layer;
import qsbk.app.widget.barcode.SimpleZoomListener;
import qsbk.app.widget.barcode.ZoomState;

public class Publish_Image extends FragmentActivity implements OnCloseListener {
    public static final int MIN_IMG_HEIGHT = 960;
    public static final int MIN_IMG_WIDTH = 540;
    public static final String NEED_WATER_MARK = "need_water_mark";
    public static final String OUTPUT_FILE_NAME = "output_file_name";
    private static int h = 0;
    ImageView a;
    FrameLayout b;
    DisplayMetrics c;
    Bitmap d;
    RelativeLayout e = null;
    ButtonBar f = null;
    ButtonBar g = null;
    private final Handler i = new Handler();
    private Bitmap j;
    private Bitmap k;
    private Bitmap l;
    private Bitmap m;
    private BarcodeView n;
    private ZoomState o;
    private SimpleZoomListener p;
    private Paint q = new Paint(2);
    private int r = 0;
    private int s = 0;
    private int t = 0;
    private ImageView u;
    private String v;
    private boolean w = true;
    private BarcodeView x;
    private int y = 0;

    public static String getSendImagePath() {
        return getSendImagePath(null);
    }

    public static String getSendImagePath(String str) {
        StringBuilder append = new StringBuilder().append(DeviceUtils.getSDPath()).append("/qsbk/send/");
        if (str == null) {
            str = "send.jpg";
        }
        return append.append(str).toString();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_publish_image);
        this.c = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(this.c);
        this.v = getIntent().getStringExtra(OUTPUT_FILE_NAME);
        initView();
        f();
        g();
        a(needWaterMark());
    }

    public boolean needWaterMark() {
        return getIntent().getBooleanExtra(NEED_WATER_MARK, true);
    }

    protected void a(boolean z) {
        this.e = (RelativeLayout) findViewById(R.id.id_water_mark_layout);
        if (z) {
            this.u = (ImageView) findViewById(R.id.id_warter_mark);
            this.u.setOnTouchListener(new bu(this));
            return;
        }
        this.e.setVisibility(8);
    }

    private void a(Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            LogUtil.d("bitmapWidth:" + bitmap.getWidth() + " bitmapHeight:" + bitmap.getHeight());
        }
    }

    private void e() {
        if (this.d == null || this.d.getWidth() < 240 || this.d.getHeight() < 240) {
            this.w = false;
        }
    }

    protected void a() {
        Object stringExtra = getIntent().getStringExtra("picpath");
        int max = Math.max(this.t, MIN_IMG_WIDTH);
        int max2 = Math.max(this.s, MIN_IMG_HEIGHT);
        if (TextUtils.isEmpty(stringExtra)) {
            setResult(0);
            finish();
            return;
        }
        this.j = ImageUtils.decodeBitmap(this, stringExtra, max, max2, null);
        b(this.j);
        if (this.j != null) {
            this.d = ImageUtils.scaleBitmapIfNecessary(this.j, max, max2, false);
        }
        b(this.d);
    }

    private void f() {
        this.k = BitmapFactory.decodeResource(getResources(), R.drawable.qb_mask);
        this.l = BitmapFactory.decodeResource(getResources(), R.drawable.icon_cancel);
        this.m = BitmapFactory.decodeResource(getResources(), R.drawable.icon_zoom);
    }

    private void b(Bitmap bitmap) {
        if (bitmap == null) {
            ToastAndDialog.makeNegativeToast(this, "获取图片失败,请重试", Integer.valueOf(1)).show();
            setResult(0);
            finish();
        }
    }

    public void initView() {
        this.a = (ImageView) findViewById(R.id.target_image);
        this.f = (ButtonBar) findViewById(R.id.id_top_bar);
        this.g = (ButtonBar) findViewById(R.id.id_bottom_bar);
        this.f.setOnClickListener(new bv(this), new bw(this));
        this.g.setOnClickListener(new bx(this), new by(this));
        this.b = (Layer) findViewById(R.id.imageLayout);
        this.r = this.b.getChildCount();
    }

    private void g() {
        this.i.postDelayed(new bz(this), 100);
    }

    protected void onDestroy() {
        super.onDestroy();
        b();
    }

    protected void b() {
        if (!(this.k == null || this.k.isRecycled())) {
            this.k.recycle();
            this.k = null;
        }
        if (!(this.l == null || this.l.isRecycled())) {
            this.l.recycle();
            this.l = null;
        }
        if (!(this.m == null || this.m.isRecycled())) {
            this.m.recycle();
            this.m = null;
        }
        for (int childCount = this.b.getChildCount() - 1; childCount >= this.r; childCount--) {
            View childAt = this.b.getChildAt(childCount);
            if (childAt instanceof BarcodeView) {
                childAt.setOnTouchListener(null);
                ((BarcodeView) childAt).getZoomState().deleteObservers();
                ((BarcodeView) childAt).setOnCloseListener(null);
                this.b.removeView(childAt);
            }
        }
    }

    private void a(ZoomState zoomState) {
        Random random = new Random(System.currentTimeMillis());
        zoomState.setPanX((random.nextFloat() + 0.8f) / 2.0f);
        zoomState.setPanY((random.nextFloat() + 0.8f) / 2.0f);
        zoomState.setZoom(0.3f);
        zoomState.notifyObservers();
    }

    private Bitmap h() {
        Bitmap fitRotate = ImageUtils.fitRotate(Bitmap.createBitmap(this.d.copy(Config.ARGB_8888, true)), this.y, Math.max(this.t, MIN_IMG_WIDTH), Math.max(this.s, MIN_IMG_HEIGHT), true);
        LogUtil.d("waterImage width:" + this.d.getWidth());
        LogUtil.d("waterImage height:" + this.d.getHeight());
        Canvas canvas = new Canvas(fitRotate);
        if (needWaterMark()) {
            a(canvas);
        }
        int childCount = this.b.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = this.b.getChildAt(i);
            if (childAt instanceof BarcodeView) {
                Rect innerRect = ((BarcodeView) childAt).getInnerRect();
                Rect rect = new Rect();
                rect.left = innerRect.left - this.a.getLeft();
                rect.top = innerRect.top - this.a.getTop();
                rect.right = (innerRect.right - innerRect.left) + rect.left;
                rect.bottom = (innerRect.bottom - innerRect.top) + rect.top;
                canvas.drawBitmap(this.k, null, rect, this.q);
            }
        }
        canvas.save(31);
        canvas.restore();
        return fitRotate;
    }

    private void a(Canvas canvas) {
        if (this.u != null) {
            LayoutParams layoutParams = (LayoutParams) this.u.getLayoutParams();
            int i = layoutParams.leftMargin;
            int i2 = layoutParams.topMargin;
            int i3 = layoutParams.rightMargin;
            int i4 = layoutParams.bottomMargin;
            Bitmap bitmap = ((BitmapDrawable) this.u.getBackground()).getBitmap();
            LogUtil.d("leftMargin:" + i);
            LogUtil.d("topMargin:" + i2);
            LogUtil.d("waterbmWidth:" + bitmap.getWidth());
            LogUtil.d("waterbmHeight:" + bitmap.getHeight());
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            i4 = this.a.getLeft() - i;
            i4 = this.a.getTop() - i2;
            i2 = canvas.getWidth();
            i = canvas.getHeight();
            if (i2 >= 200 && i >= 200) {
                double d;
                if (((double) ((((float) i2) * 1.0f) / ((float) (i == 0 ? 1 : i)))) > 1.0d) {
                    d = (double) ((((float) i2) * 1.0f) / 720.0f);
                } else {
                    d = (double) ((((float) i) * 1.0f) / 1080.0f);
                }
                i2 = (int) (((double) width) * d);
                width = (int) (((double) height) * d);
                i = (this.a.getWidth() - ((int) (30.0d * d))) - i2;
                i4 = (this.a.getHeight() - ((int) (d * 20.0d))) - width;
                canvas.drawBitmap(bitmap, null, new Rect(i, i4, i2 + i, width + i4), this.q);
            }
        }
    }

    protected void c() {
        if (this.w) {
            this.o = new ZoomState();
            this.p = new SimpleZoomListener();
            this.p.setZoomState(this.o);
            View inflate = LayoutInflater.from(this).inflate(R.layout.barcode_image, null);
            this.n = (BarcodeView) inflate.findViewById(R.id.zoomview);
            this.n.setZoomState(this.o);
            this.n.setImage(this.k);
            this.n.setLeftTopImage(this.l);
            this.n.setRightBottomImage(this.m);
            this.n.setOnCloseListener(this);
            this.n.setBoundsRect(new Rect(this.a.getLeft(), this.a.getTop(), this.a.getRight(), this.a.getBottom()));
            this.n.setOnTouchListener(this.p);
            a(this.o);
            this.b.addView(inflate);
        }
    }

    public void onClose() {
        this.b.getChildCount();
    }

    private void i() {
        int childCount = this.b.getChildCount();
        if (childCount <= this.r || (this.x != null && childCount <= this.r + 1)) {
            d();
        } else {
            ToastAndDialog.makeNegativeToast(this, "打码之后就不能旋转了，亲~", Integer.valueOf(0)).show();
        }
    }

    protected void d() {
        int max = Math.max(this.t, MIN_IMG_WIDTH);
        int max2 = Math.max(this.s, MIN_IMG_HEIGHT);
        this.y = (this.y + 90) % 360;
        this.a.setImageBitmap(ImageUtils.fitRotate(this.d, this.y, max, max2, false));
        this.i.postDelayed(new ca(this), 100);
    }
}
