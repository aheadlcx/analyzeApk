package cn.v6.sixrooms.surfaceanim.flybanner.notification;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import cn.v6.sixrooms.surfaceanim.AnimBitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.R;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimEntity;
import cn.v6.sixrooms.surfaceanim.flybanner.utils.touch.SurfaceTouchManager;
import cn.v6.sixrooms.surfaceanim.flybanner.utils.touch.TouchEntity;
import cn.v6.sixrooms.surfaceanim.flybanner.utils.touch.TouchEntity.TouchParameter;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.util.GiftSceneUtil;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialElement;
import cn.v6.sixrooms.surfaceanim.util.AnimIntEvaluator;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import com.budejie.www.R$styleable;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;

public class NotificationElement extends SpecialElement {
    private final NotificationSceneParameter a;
    private AnimSceneResManager b;
    private Paint c;
    private Paint d;
    private int e;
    private AnimBitmap f;
    private int g;
    private int h;
    private String i;
    private float j;
    private float k;
    private AnimIntEvaluator l;
    private Bitmap m;
    private int n;
    private boolean o;
    private int p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private boolean v;
    private TouchEntity w;
    private int x;
    private a y = new a(this);

    class a extends BaseBitmapDataSubscriber {
        final /* synthetic */ NotificationElement a;

        a(NotificationElement notificationElement) {
            this.a = notificationElement;
        }

        protected final void onNewResultImpl(Bitmap bitmap) {
            if (bitmap == null || bitmap.isRecycled()) {
                this.a.a();
                return;
            }
            this.a.m = bitmap;
            this.a.v = true;
        }

        protected final void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
            if (this.a.m == null || this.a.m.isRecycled()) {
                this.a.a();
            }
        }
    }

    public NotificationElement(AnimScene animScene) {
        super(animScene);
        this.a = (NotificationSceneParameter) animScene.getSceneParameter();
        this.b = AnimSceneResManager.getInstance();
        this.x = this.b.getScreenW();
        this.i = this.a.getUserAliasName();
        this.h = this.b.getResources().getDimensionPixelOffset(R.dimen.notification_height);
        this.e = this.b.getResources().getDimensionPixelSize(R.dimen.notification_text_size);
        this.c = new Paint();
        this.d = new Paint();
        this.d.setAntiAlias(true);
        this.c.setAntiAlias(true);
        this.c.setTextSize((float) this.e);
        this.d.setTextSize((float) this.e);
        this.d.setColor(-1);
        this.c.setColor(this.b.getResources().getColor(R.color.anim_become_god_text_color));
        this.j = this.c.measureText(this.i);
        this.k = this.d.measureText("开始直播了!");
        this.p = this.h + this.b.dp2px(5.0f);
        this.q = this.b.dp2px(2.0f);
        this.r = this.b.getResources().getDimensionPixelOffset(R.dimen.anim_upgrade_text_line_spacing);
        this.u = (this.h / 2) + this.b.getScalePx(2);
        this.g = (int) ((this.j > this.k ? this.j : this.k) + ((float) this.b.dp2px(56.0f)));
        this.f = new AnimBitmap(this.b.drawableToBitmap(R.drawable.notification_bg, this.g, this.h));
        this.f.setAlpha(153);
        this.n = this.f.getWidth();
        a();
        this.l = new AnimIntEvaluator(0, 0, 0, 0);
    }

    public void drawElement(Canvas canvas) {
        int i;
        if (!this.o) {
            i = this.mPoint.y;
            this.f.setTranslateX(this.mPoint.x);
            this.f.setTranslateY(i);
            this.o = true;
            this.s = (i + this.q) + this.e;
            this.t = (this.s + this.r) + this.e;
        }
        i = this.f.getTranslateX();
        int translateY = this.f.getTranslateY();
        if (this.m == null || this.m.isRecycled() || !this.v) {
            GiftSceneUtil.scaleBitmap(this.a.getPicuser(), this.y, new a(this));
        }
        this.s = (this.q + translateY) + this.e;
        this.t = (this.s + this.r) + this.e;
        this.f.animTranslate().animAlpha().draw(canvas);
        canvas.drawCircle((float) ((this.h / 2) + i), (float) ((this.h / 2) + translateY), (float) this.u, this.d);
        canvas.drawBitmap(this.m, (float) (this.b.getScalePx(1) + i), (float) (translateY + this.b.getScalePx(1)), null);
        canvas.drawText(this.i, (float) (this.p + i), (float) this.s, this.c);
        canvas.drawText("开始直播了!", (float) (i + this.p), (float) this.t, this.d);
    }

    public boolean frameControl(int i) {
        if (i == 1) {
            this.l.resetEvaluator(1, 5, this.b.getScreenW(), this.b.getScreenW() - this.n);
        } else if (i == 5) {
            this.l.resetEvaluator(5, R$styleable.Theme_Custom_attention_right, this.b.getScreenW() - this.n, this.b.getScreenW() - this.n);
        } else if (5 < i && i < R$styleable.Theme_Custom_attention_right) {
            SurfaceTouchManager surfaceTouchManager = SurfaceTouchManager.getDefault();
            if (this.w == null) {
                this.w = new TouchEntity();
            }
            int translateX = this.f.getTranslateX();
            int i2 = this.g + translateX;
            int i3 = this.a.getPoint().y;
            Rect rect = new Rect(translateX, i3, i2, this.h + i3);
            TouchParameter touchParameter = new TouchParameter();
            touchParameter.setRid(this.a.getRid()).setUid(this.a.getUid());
            this.w.setRect(rect);
            this.w.setWhat(100);
            this.w.setTouchParameter(touchParameter);
            surfaceTouchManager.addTouchEntity(this.w);
        } else if (i == R$styleable.Theme_Custom_attention_right) {
            this.l.resetEvaluator(R$styleable.Theme_Custom_attention_right, 160, this.b.getScreenW() - this.n, this.b.getScreenW());
            SurfaceTouchManager.getDefault().removeTouchEntity(this.w);
        }
        this.f.setTranslateX(this.l.evaluate(i));
        this.f.setTranslateY(this.a.getPoint().y);
        return false;
    }

    public IAnimEntity[] initAnimEntities() {
        return new IAnimEntity[0];
    }

    private void a() {
        this.m = GiftSceneUtil.scaleBitmap(this.b.getBitmap(this.mAnimScene.getSceneType(), R.drawable.default_photo, true), this.h - this.b.getScalePx(2), this.h - this.b.getScalePx(2));
    }
}
