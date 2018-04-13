package cn.v6.sixrooms.surfaceanim.flybanner.becomegod;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import cn.v6.sixrooms.surfaceanim.AnimBitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.AnimSceneElement;
import cn.v6.sixrooms.surfaceanim.R;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimEntity;
import cn.v6.sixrooms.surfaceanim.flybanner.utils.BecomeGodTextUtils;
import cn.v6.sixrooms.surfaceanim.flybanner.utils.TextInfo;
import cn.v6.sixrooms.surfaceanim.flybanner.utils.touch.SurfaceTouchManager;
import cn.v6.sixrooms.surfaceanim.flybanner.utils.touch.TouchEntity;
import cn.v6.sixrooms.surfaceanim.flybanner.utils.touch.TouchEntity.TouchParameter;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.util.GiftSceneUtil;
import cn.v6.sixrooms.surfaceanim.util.AnimIntEvaluator;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;

public abstract class BaseBecomeGodElement extends AnimSceneElement {
    private float A;
    private float B;
    private float C;
    private float D;
    private boolean E;
    private a F = new a();
    private TouchEntity G;
    private int H;
    boolean a;
    private int[] b;
    private AnimSceneResManager c;
    private BecomeGodSceneParameter d;
    private AnimIntEvaluator e;
    private Paint f;
    private Paint g;
    private AnimBitmap h;
    private Bitmap i;
    private String j;
    private String k;
    private String l;
    private int m;
    private int n;
    private float o;
    private int p;
    private float q;
    private float r;
    private float s;
    private float t;
    private float u;
    private float v;
    private float w;
    private float x;
    private float y;
    private TextInfo z;

    private class a extends BaseBitmapDataSubscriber {
        final /* synthetic */ BaseBecomeGodElement a;

        private a(BaseBecomeGodElement baseBecomeGodElement) {
            this.a = baseBecomeGodElement;
        }

        protected final void onNewResultImpl(Bitmap bitmap) {
            if (bitmap == null || bitmap.isRecycled()) {
                this.a.h.setBitmap(this.a.c.drawableToBitmap(R.drawable.become_god_bg));
                this.a.h.setAlpha(153);
                return;
            }
            this.a.h.setBitmap(bitmap);
            this.a.h.setAlpha(255);
            this.a.E = true;
        }

        protected final void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
            this.a.h.setBitmap(this.a.c.drawableToBitmap(R.drawable.become_god_bg));
            this.a.h.setAlpha(153);
        }
    }

    protected abstract int[] getFrameControlArray();

    protected abstract int getGoBitmapResId();

    public BaseBecomeGodElement(AnimScene animScene) {
        super(animScene);
        this.d = (BecomeGodSceneParameter) animScene.getSceneParameter();
        this.c = AnimSceneResManager.getInstance();
        this.H = this.c.getScreenW();
        this.j = this.d.getFromUser();
        this.k = this.d.getToUser();
        this.l = this.d.getGodType().getValue();
        this.b = getFrameControlArray();
        this.h = new AnimBitmap(this.c.drawableToBitmap(R.drawable.become_god_bg));
        this.h.setAlpha(153);
        this.i = this.c.getBitmap(this.mAnimScene.getSceneType(), getGoBitmapResId(), true);
        this.mAnimEntities[0] = this.h;
        this.m = this.h.getWidth();
        this.p = this.c.getScreenW() - (this.m + 10);
        this.e = new AnimIntEvaluator(1, 1, this.c.getScreenW(), this.p);
        this.f = new Paint();
        this.g = new Paint();
        this.g.setAntiAlias(true);
        this.f.setAntiAlias(true);
        this.g.setColor(-1);
        this.f.setColor(this.c.getResources().getColor(R.color.anim_become_god_text_color));
        this.n = this.c.getResources().getDimensionPixelSize(R.dimen.anim_upgrade_text_size);
        this.f.setTextSize((float) this.n);
        this.g.setTextSize((float) this.n);
        this.z = BecomeGodTextUtils.getNewlineInfo(this.g, this.j, this.k, (float) this.c.getResources().getDimensionPixelOffset(R.dimen.anim_upgrade_msg_width));
        this.o = (float) this.c.getResources().getDimensionPixelOffset(R.dimen.anim_upgrade_text_line_spacing);
        this.t = this.g.measureText(this.j);
        this.q = this.g.measureText(BecomeGodTextUtils.s1);
        this.r = this.g.measureText(BecomeGodTextUtils.s2);
        this.s = this.g.measureText(BecomeGodTextUtils.s5);
        this.u = (float) this.c.getResources().getDimensionPixelOffset(R.dimen.anim_upgrade_msg_margin_left);
        this.v = (float) this.c.getResources().getDimensionPixelOffset(R.dimen.anim_upgrade_msg_margin_top);
        this.w = (float) this.i.getHeight();
        this.x = (this.w - ((float) this.n)) / 2.0f;
        this.y = (((float) this.i.getWidth()) - this.g.measureText(BecomeGodTextUtils.go)) / 2.0f;
    }

    public boolean frameControl(int i) {
        if (i == 1) {
            this.e.resetEvaluator(1, this.b[0], this.c.getScreenW(), this.p);
        } else if (i == this.b[0]) {
            this.e.resetEvaluator(this.b[0], this.b[1], this.p, this.p);
            SurfaceTouchManager surfaceTouchManager = SurfaceTouchManager.getDefault();
            this.G = new TouchEntity();
            int translateX = this.h.getTranslateX();
            int i2 = this.m + translateX;
            int translateY = this.h.getTranslateY();
            Rect rect = new Rect(translateX, translateY, i2, this.h.getHeight() + translateY);
            TouchParameter touchParameter = new TouchParameter();
            touchParameter.setRid(this.d.getToRoomId()).setUid(this.d.getToRoomUid());
            this.G.setRect(rect);
            this.G.setWhat(100);
            this.G.setTouchParameter(touchParameter);
            surfaceTouchManager.addTouchEntity(this.G);
        } else if (i == this.b[1]) {
            this.e.resetEvaluator(this.b[1], this.b[2], this.p, -this.c.getScreenW());
            SurfaceTouchManager.getDefault().removeTouchEntity(this.G);
        }
        this.h.setTranslateX(this.e.evaluate(i));
        return false;
    }

    public IAnimEntity[] initAnimEntities() {
        return new IAnimEntity[1];
    }

    public void drawElement(Canvas canvas) {
        if (!this.a) {
            int i = this.mAnimScene.getSceneParameter().getPoint().y;
            this.h.setTranslateX(this.mAnimScene.getSceneParameter().getPoint().x);
            this.h.setTranslateY(i);
            this.A = (((float) i) + this.v) + ((float) this.n);
            this.B = (this.A + this.o) + ((float) this.n);
            this.C = (this.B + this.o) + ((float) this.n);
            this.D = (this.C - this.w) + this.x;
            this.a = true;
        }
        float translateX = ((float) this.h.getTranslateX()) + this.u;
        float f = this.q + translateX;
        float f2 = this.t + f;
        float f3 = (this.s + translateX) + this.o;
        float f4 = this.y + f3;
        if (!this.E && !TextUtils.isEmpty(this.d.getBgUrl())) {
            GiftSceneUtil.scaleBitmap(this.d.getBgUrl(), this.F, new a(this));
            this.h.animTranslate().animAlpha().draw(canvas);
            canvas.drawText(BecomeGodTextUtils.s1, translateX, this.A, this.g);
            canvas.drawText(this.j, f, this.A, this.f);
            canvas.drawText(BecomeGodTextUtils.s5, translateX, this.C, this.g);
            canvas.drawBitmap(this.i, f3, this.D, null);
            canvas.drawText(BecomeGodTextUtils.go, f4, this.C - ((float) this.c.getScalePx(3)), this.g);
            if (this.z != null) {
                canvas.drawText(BecomeGodTextUtils.s2, f2, this.A, this.g);
                if (this.z.isNewLine()) {
                    canvas.drawText(this.z.getFrontStr(), f2 + this.r, this.A, this.f);
                    canvas.drawText(this.z.getBackStr(), translateX, this.B, this.f);
                } else {
                    canvas.drawText(this.k, f2 + this.r, this.A, this.f);
                }
                String str = "的房间荣升" + this.l;
                if (this.z != null && this.z.isNewLine()) {
                    translateX += this.g.measureText(this.z.getBackStr());
                }
                canvas.drawText(str, translateX, this.B, this.g);
                return;
            }
            canvas.drawText(new StringBuilder(BecomeGodTextUtils.s4).append(this.l).toString(), translateX, this.B, this.g);
        }
    }
}
