package cn.v6.sixrooms.surfaceanim.smaillfly;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import cn.v6.sixrooms.surfaceanim.AnimBitmap;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.AnimSceneElement;
import cn.v6.sixrooms.surfaceanim.R;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimEntity;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.util.GiftSceneUtil;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;

public class SmaillFlyElement extends AnimSceneElement {
    private int a = AnimSceneResManager.getInstance().getResources().getDimensionPixelSize(R.dimen.small_fly_msg_speed);
    private SmaillFlySceneParameter b = ((SmaillFlySceneParameter) this.mAnimScene.getSceneParameter());
    private String c;
    private String d;
    private AnimBitmap e;
    private Bitmap f;
    private Paint g = new Paint();
    private Paint h = new Paint();
    private boolean i;
    private float j;
    private float k;
    private boolean l;
    private int m = this.u.getResources().getDimensionPixelSize(R.dimen.small_fly_icon_margin_left);
    private int n = this.u.getResources().getDimensionPixelSize(R.dimen.small_fly_icon_margin_top);
    private int o = this.u.getResources().getDimensionPixelSize(R.dimen.small_fly_name_margin_top);
    private int p = this.u.getResources().getDimensionPixelSize(R.dimen.small_fly_msg_margin_top);
    private int q = this.u.getResources().getDimensionPixelSize(R.dimen.small_fly_name_margin_left);
    private int r = this.u.getResources().getDimensionPixelSize(R.dimen.small_fly_icon_size);
    private int s = this.u.getResources().getDimensionPixelSize(R.dimen.small_fly_name_size);
    private int t = this.u.getResources().getDimensionPixelSize(R.dimen.small_fly_text_size);
    private AnimSceneResManager u = AnimSceneResManager.getInstance();
    private int v = this.u.getScreenW();
    private a w = new a();

    private class a extends BaseBitmapDataSubscriber {
        final /* synthetic */ SmaillFlyElement a;

        private a(SmaillFlyElement smaillFlyElement) {
            this.a = smaillFlyElement;
        }

        protected final void onNewResultImpl(Bitmap bitmap) {
            if (bitmap == null || bitmap.isRecycled()) {
                this.a.a();
                return;
            }
            this.a.f = bitmap;
            this.a.i = true;
        }

        protected final void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
            if (this.a.f == null || this.a.f.isRecycled()) {
                this.a.a();
            }
        }
    }

    public SmaillFlyElement(AnimScene animScene) {
        super(animScene);
        this.g.setTextSize((float) this.t);
        this.h.setTextSize((float) this.s);
        this.g.setAntiAlias(true);
        this.h.setAntiAlias(true);
        this.g.setColor(-1);
        this.g.setTypeface(Typeface.DEFAULT_BOLD);
        this.h.setColor(this.u.getResources().getColor(R.color.anim_smaill_fly_text_name_color));
        this.c = this.b.getFromUser();
        this.d = this.b.getText();
        float measureText = this.g.measureText(this.d);
        float measureText2 = this.h.measureText(this.c);
        int dimension = (int) this.u.getResources().getDimension(R.dimen.small_fly_bg_height);
        float dp2px = (float) this.u.dp2px(56.5f);
        if (measureText <= measureText2) {
            measureText = measureText2;
        }
        this.e = new AnimBitmap(this.u.drawableToBitmap(R.drawable.smaill_fly_text_bg, (int) (measureText + dp2px), dimension));
        this.e.getPaint().setAlpha(153);
        a();
    }

    public void drawElement(Canvas canvas) {
        if (!this.l) {
            this.j = (float) this.mAnimScene.getSceneParameter().getPoint().y;
            this.k = (float) this.u.getScreenW();
            this.l = true;
        }
        canvas.drawBitmap(this.e.getBitmap(), this.k, this.j, this.e.getPaint());
        canvas.drawText(this.c, b(this.k), a(this.j), this.h);
        canvas.drawText(this.d, b(this.k), (a(this.j) + ((float) this.p)) + ((float) this.t), this.g);
        if (!this.i || this.f == null || this.f.isRecycled()) {
            GiftSceneUtil.scaleBitmap(this.b.getPhotoUrl(), this.w, new a(this));
        } else {
            canvas.drawBitmap(this.f, this.k + ((float) this.m), this.j + ((float) this.n), null);
        }
        if (this.k < ((float) ((-this.e.getWidth()) - (this.a * 2)))) {
            this.mAnimScene.getSceneParameter().setMaxFrameNum(this.mAnimScene.getSceneParameter().getCurFrameNum());
        }
        this.k -= (float) this.a;
    }

    private float a(float f) {
        return (((float) this.o) + f) + ((float) this.s);
    }

    private float b(float f) {
        return ((((float) this.m) + f) + ((float) this.r)) + ((float) this.q);
    }

    public boolean frameControl(int i) {
        this.j = (float) this.mAnimScene.getSceneParameter().getPoint().y;
        return false;
    }

    public IAnimEntity[] initAnimEntities() {
        return null;
    }

    private void a() {
        this.f = GiftSceneUtil.scaleBitmap(this.u.getBitmap(this.mAnimScene.getSceneType(), R.drawable.default_photo, true), this.r, this.r);
    }
}
