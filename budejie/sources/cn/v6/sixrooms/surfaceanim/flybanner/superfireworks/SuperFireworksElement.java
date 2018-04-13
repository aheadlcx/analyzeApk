package cn.v6.sixrooms.surfaceanim.flybanner.superfireworks;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import cn.v6.sixrooms.surfaceanim.AnimBitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.R;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimEntity;
import cn.v6.sixrooms.surfaceanim.flybanner.utils.SuperFireworksTextUtils;
import cn.v6.sixrooms.surfaceanim.flybanner.utils.TextInfo;
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

public class SuperFireworksElement extends SpecialElement {
    private AnimIntEvaluator a;
    private AnimSceneResManager b;
    private Paint c;
    private Paint d;
    private AnimBitmap e;
    private String f;
    private String g;
    private int h;
    private int i;
    private int j;
    private float k;
    private float l;
    private float m;
    private boolean n;
    private int o;
    private int p;
    private boolean q;
    private SuperFireworksSceneParameter r;
    private float s;
    private float t;
    private a u = new a(this);
    private TextInfo v;
    private float w;
    private TouchEntity x;
    private int y;

    class a extends BaseBitmapDataSubscriber {
        final /* synthetic */ SuperFireworksElement a;

        a(SuperFireworksElement superFireworksElement) {
            this.a = superFireworksElement;
        }

        protected final void onNewResultImpl(Bitmap bitmap) {
            if (bitmap == null || bitmap.isRecycled()) {
                this.a.e.setBitmap(this.a.b.drawableToBitmap(R.drawable.become_god_bg));
                this.a.e.setAlpha(153);
                return;
            }
            this.a.e.setBitmap(bitmap);
            this.a.e.setAlpha(255);
            this.a.q = true;
        }

        protected final void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
            this.a.e.setBitmap(this.a.b.drawableToBitmap(R.drawable.become_god_bg));
            this.a.e.setAlpha(153);
        }
    }

    public SuperFireworksElement(AnimScene animScene) {
        super(animScene);
        this.r = (SuperFireworksSceneParameter) animScene.getSceneParameter();
        this.b = AnimSceneResManager.getInstance();
        this.y = this.b.getScreenW();
        this.f = this.r.getFromUser();
        this.g = this.r.getToUser();
        this.e = new AnimBitmap(this.b.drawableToBitmap(R.drawable.become_god_bg));
        this.e.setAlpha(153);
        this.mAnimEntities[0] = this.e;
        this.s = (float) this.e.getWidth();
        this.p = (int) ((((float) this.b.getScreenW()) - this.s) - 10.0f);
        this.a = new AnimIntEvaluator(1, 10, this.b.getScreenW(), this.p);
        this.o = this.b.getResources().getDimensionPixelSize(R.dimen.anim_upgrade_text_size);
        this.c = new Paint();
        this.d = new Paint();
        this.d.setAntiAlias(true);
        this.c.setAntiAlias(true);
        this.c.setTextSize((float) this.o);
        this.d.setTextSize((float) this.o);
        this.d.setColor(-1);
        this.c.setColor(this.b.getResources().getColor(R.color.anim_become_god_text_color));
        this.i = this.b.dp2px(110.0f);
        this.h = this.b.dp2px(38.0f);
        this.j = this.b.getResources().getDimensionPixelOffset(R.dimen.anim_upgrade_text_line_spacing);
        this.t = this.d.measureText(this.f);
        this.w = this.d.measureText(SuperFireworksTextUtils.s1);
        this.v = SuperFireworksTextUtils.getNewlineInfo(this.d, this.f, this.g, (float) this.b.getResources().getDimensionPixelOffset(R.dimen.anim_upgrade_msg_width));
    }

    public boolean frameControl(int i) {
        if (i == 1) {
            this.a.resetEvaluator(1, 10, this.b.getScreenW(), this.p);
        } else if (i == 10) {
            this.a.resetEvaluator(10, 250, this.p, this.p);
            SurfaceTouchManager surfaceTouchManager = SurfaceTouchManager.getDefault();
            this.x = new TouchEntity();
            int translateX = this.e.getTranslateX();
            int i2 = (int) (((float) translateX) + this.s);
            int translateY = this.e.getTranslateY();
            Rect rect = new Rect(translateX, translateY, i2, this.e.getHeight() + translateY);
            TouchParameter touchParameter = new TouchParameter();
            touchParameter.setRid(this.r.getToRoomId()).setUid(this.r.getToRoomUid());
            this.x.setRect(rect);
            this.x.setWhat(100);
            this.x.setTouchParameter(touchParameter);
            surfaceTouchManager.addTouchEntity(this.x);
        } else if (i == 250) {
            this.a.resetEvaluator(250, R$styleable.Theme_Custom_forward_icon, this.p, -this.b.getScreenW());
            SurfaceTouchManager.getDefault().removeTouchEntity(this.x);
        }
        this.e.setTranslateX(this.a.evaluate(i));
        return false;
    }

    public IAnimEntity[] initAnimEntities() {
        return new IAnimEntity[1];
    }

    public void drawElement(Canvas canvas) {
        if (!this.n) {
            int i = this.mAnimScene.getSceneParameter().getPoint().y;
            this.e.setTranslateX(this.mAnimScene.getSceneParameter().getPoint().x);
            this.e.setTranslateY(i);
            this.k = (float) (i + this.h);
            this.l = (this.k + ((float) this.j)) + ((float) this.o);
            this.m = (this.l + ((float) this.j)) + ((float) this.o);
            this.n = true;
        }
        if (!(this.q || TextUtils.isEmpty(this.r.getBgUrl()))) {
            GiftSceneUtil.scaleBitmap(this.r.getBgUrl(), this.u, new a(this));
        }
        this.e.animTranslate().animAlpha().draw(canvas);
        float translateX = (float) (this.e.getTranslateX() + this.i);
        float f = this.t + translateX;
        float f2 = this.w + f;
        canvas.drawText(this.f, translateX, this.k, this.c);
        canvas.drawText(SuperFireworksTextUtils.s1, f, this.k, this.d);
        if (this.v != null) {
            if (this.v.isNewLine()) {
                String backStr = this.v.getBackStr();
                float measureText = this.d.measureText(backStr);
                canvas.drawText(this.v.getFrontStr(), f2, this.k, this.c);
                canvas.drawText(backStr, translateX, this.l, this.c);
                canvas.drawText(SuperFireworksTextUtils.s2, translateX + measureText, this.l, this.d);
                canvas.drawText(SuperFireworksTextUtils.s3, translateX, this.m, this.d);
            }
            canvas.drawText(this.g, f2, this.k, this.c);
        }
        canvas.drawText(SuperFireworksTextUtils.s2, translateX, this.l, this.d);
        canvas.drawText(SuperFireworksTextUtils.s3, translateX, this.m, this.d);
    }
}
