package cn.v6.sixrooms.surfaceanim.flybanner.confession;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;
import cn.v6.sixrooms.surfaceanim.AnimBitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.R;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimEntity;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.util.GiftSceneUtil;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialElement;
import cn.v6.sixrooms.surfaceanim.util.AnimIntEvaluator;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import com.budejie.www.R$styleable;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;

public class ConfessionElement extends SpecialElement {
    private int A;
    private AnimSceneResManager a;
    private Paint b;
    private Paint c;
    private AnimBitmap d;
    private String e;
    private String f;
    private String g;
    private int h;
    private int i;
    private int j;
    private int k;
    private AnimIntEvaluator l;
    private int m;
    private ConfessionSceneParameter n;
    private float o;
    private int p;
    private float q;
    private float r;
    private float s;
    private float t;
    private boolean u;
    private float v;
    private float w;
    private float x;
    private boolean y;
    private a z = new a(this);

    class a extends BaseBitmapDataSubscriber {
        final /* synthetic */ ConfessionElement a;

        a(ConfessionElement confessionElement) {
            this.a = confessionElement;
        }

        protected final void onNewResultImpl(Bitmap bitmap) {
            if (bitmap == null || bitmap.isRecycled()) {
                this.a.d.setBitmap(this.a.a.drawableToBitmap(R.drawable.become_god_bg));
                this.a.d.setAlpha(153);
                return;
            }
            this.a.d.setBitmap(bitmap);
            this.a.d.setAlpha(255);
            this.a.y = true;
        }

        protected final void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
            this.a.d.setBitmap(this.a.a.drawableToBitmap(R.drawable.become_god_bg));
            this.a.d.setAlpha(153);
        }
    }

    public ConfessionElement(AnimScene animScene) {
        super(animScene);
        this.n = (ConfessionSceneParameter) animScene.getSceneParameter();
        this.a = AnimSceneResManager.getInstance();
        this.A = this.a.getScreenW();
        this.e = this.n.getFromUser();
        this.f = this.n.getToUser();
        this.g = this.n.getText();
        this.d = new AnimBitmap(this.a.drawableToBitmap(R.drawable.become_god_bg));
        this.d.setAlpha(153);
        this.mAnimEntities[0] = this.d;
        this.s = (float) this.d.getWidth();
        this.m = (int) ((((float) this.a.getScreenW()) - this.s) - 10.0f);
        this.l = new AnimIntEvaluator(1, 10, this.a.getScreenW(), this.m);
        this.p = this.a.getResources().getDimensionPixelSize(R.dimen.anim_upgrade_text_size);
        this.b = new Paint();
        this.c = new Paint();
        this.c.setAntiAlias(true);
        this.b.setAntiAlias(true);
        this.b.setTextSize((float) this.p);
        this.c.setTextSize((float) this.p);
        this.c.setColor(-1);
        this.b.setColor(this.a.getResources().getColor(R.color.anim_become_god_text_color));
        this.k = this.a.dp2px(110.0f);
        this.h = this.a.dp2px(38.0f);
        this.i = this.a.getResources().getDimensionPixelOffset(R.dimen.anim_upgrade_text_line_spacing);
        this.j = this.a.dp2px(27.0f);
        this.o = this.c.measureText("To");
        this.q = this.c.measureText("By");
        this.t = this.c.measureText(this.e);
        this.r = this.c.measureText(this.g);
    }

    public boolean frameControl(int i) {
        if (i == 1) {
            this.l.resetEvaluator(1, 10, this.a.getScreenW(), this.m);
        } else if (i == 10) {
            this.l.resetEvaluator(10, 160, this.m, this.m);
        } else if (i == 160) {
            this.l.resetEvaluator(160, R$styleable.Theme_Custom_label_subscribe_bg_gd_color, this.m, -this.a.getScreenW());
        }
        this.d.setTranslateX(this.l.evaluate(i));
        return false;
    }

    public IAnimEntity[] initAnimEntities() {
        return new IAnimEntity[1];
    }

    public void drawElement(Canvas canvas) {
        int i;
        if (!this.u) {
            i = this.mAnimScene.getSceneParameter().getPoint().y;
            this.d.setTranslateX(this.mAnimScene.getSceneParameter().getPoint().x);
            this.d.setTranslateY(i);
            this.v = (float) (i + this.h);
            this.w = (this.v + ((float) this.i)) + ((float) this.p);
            this.x = (this.w + ((float) this.i)) + ((float) this.p);
            this.u = true;
        }
        if (!(this.y || TextUtils.isEmpty(this.n.getBgUrl()))) {
            GiftSceneUtil.scaleBitmap(this.n.getBgUrl(), this.z, new a(this));
        }
        this.d.animTranslate().animAlpha().draw(canvas);
        i = this.d.getTranslateX();
        float f = (float) (this.k + i);
        float f2 = (((float) i) + this.s) - ((float) this.j);
        float f3 = (((float) this.i) + f) + this.o;
        float f4 = (((f2 - f) - this.r) / 2.0f) + f;
        f2 -= this.t;
        float f5 = (f2 - ((float) this.i)) - this.q;
        canvas.drawText("To", f, this.v, this.c);
        canvas.drawText(this.f, f3, this.v, this.b);
        canvas.drawText(this.g, f4, this.w, this.c);
        canvas.drawText(this.e, f2, this.x, this.b);
        canvas.drawText("By", f5, this.x, this.c);
    }
}
