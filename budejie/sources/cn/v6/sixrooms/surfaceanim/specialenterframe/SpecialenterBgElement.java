package cn.v6.sixrooms.surfaceanim.specialenterframe;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.AnimSceneElement;
import cn.v6.sixrooms.surfaceanim.R;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimEntity;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.util.GiftSceneUtil;
import cn.v6.sixrooms.surfaceanim.specialenterframe.SpecialEnterScene.SpecialEnterParameter;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;

public class SpecialenterBgElement extends AnimSceneElement {
    private Paint a;
    private int b;
    private Bitmap c;
    private Bitmap d;
    private Bitmap e;
    private a f = new a();
    private boolean g;
    private int h = -2304;
    private int i;
    private int j;
    private int k;
    protected Bitmap mBg;
    protected int mBgMarginLeft;
    protected SpecialEnterScene mGiftScene = ((SpecialEnterScene) this.mAnimScene);
    protected int mIconHeight;
    protected int mIconMarginTop;
    protected Paint mLightPaint;
    protected Paint mPaint;
    protected SpecialEnterParameter mPara = ((SpecialEnterParameter) this.mGiftScene.getSceneParameter());
    protected int mTextMarginTop;
    protected Paint mTextPaint;
    protected int mTextSize;

    private class a extends BaseBitmapDataSubscriber {
        final /* synthetic */ SpecialenterBgElement a;

        private a(SpecialenterBgElement specialenterBgElement) {
            this.a = specialenterBgElement;
        }

        protected final void onNewResultImpl(Bitmap bitmap) {
            this.a.d = bitmap;
            if (this.a.d == null || this.a.d.isRecycled()) {
                this.a.d = AnimSceneResManager.getInstance().getBitmap(this.a.mAnimScene.getSceneType(), R.drawable.badge_default, true);
            } else {
                this.a.g = true;
            }
        }

        protected final void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
            if (this.a.d == null || this.a.d.isRecycled()) {
                this.a.d = AnimSceneResManager.getInstance().getBitmap(this.a.mAnimScene.getSceneType(), R.drawable.badge_default, true);
            }
        }
    }

    public SpecialenterBgElement(AnimScene animScene) {
        super(animScene);
        Resources resources = AnimSceneResManager.getInstance().getContext().getResources();
        this.mBg = AnimSceneResManager.getInstance().getBitmap(this.mAnimScene.getSceneType(), R.drawable.special_enter_bg, true);
        this.e = AnimSceneResManager.getInstance().getBitmap(this.mAnimScene.getSceneType(), R.drawable.special_enter_star, true);
        this.c = AnimSceneResManager.getInstance().getBitmap(this.mAnimScene.getSceneType(), R.drawable.special_enter_light, true);
        if (TextUtils.isEmpty(this.mPara.getRichUrl())) {
            this.d = AnimSceneResManager.getInstance().getBitmap(this.mAnimScene.getSceneType(), FortuneUtils.getFortuneLevelUrl(this.mPara.getRich()), true);
        } else {
            this.d = AnimSceneResManager.getInstance().getBitmap(this.mAnimScene.getSceneType(), R.drawable.custom_wealth_default);
        }
        this.mPaint = new Paint();
        this.mTextPaint = new Paint();
        this.mTextPaint.setAntiAlias(true);
        this.mTextPaint.setColor(this.h);
        this.mTextSize = resources.getDimensionPixelSize(R.dimen.special_enter_text_size);
        this.mTextPaint.setTextSize((float) this.mTextSize);
        this.mLightPaint = new Paint();
        this.a = new Paint();
    }

    public void drawElement(Canvas canvas) {
        if (!frameControl(this.mCurFrame)) {
            this.k = this.mAnimScene.getSceneParameter().getPoint().y;
            this.mIconMarginTop = (this.k - (this.d.getHeight() / 2)) + (this.mBg.getHeight() / 2);
            this.mTextMarginTop = this.k + this.mTextSize;
            canvas.drawBitmap(this.mBg, (float) this.mBgMarginLeft, (float) this.k, this.mPaint);
            if (!TextUtils.isEmpty(this.mPara.getRichUrl()) && (this.d == null || this.d.isRecycled() || !this.g)) {
                GiftSceneUtil.getOriginIconBitmap(this.mPara.getRichUrl(), this.f);
            }
            canvas.drawBitmap(this.d, (float) (this.mBgMarginLeft - 2), (float) this.mIconMarginTop, this.mPaint);
            this.mLightPaint.setAlpha(this.i);
            canvas.drawBitmap(this.c, (float) this.b, (float) this.k, this.mLightPaint);
            this.a.setAlpha(this.j);
            canvas.drawBitmap(this.e, (float) (this.mBg.getWidth() - AnimSceneResManager.getInstance().getResources().getDimensionPixelSize(R.dimen.special_enter_star_margin_left)), (float) this.k, this.a);
            canvas.drawText(this.mPara.getContent(), (float) ((this.mBgMarginLeft + this.d.getWidth()) + 15), (float) this.mTextMarginTop, this.mTextPaint);
        }
    }

    public boolean frameControl(int i) {
        if (i <= 12) {
            this.mBgMarginLeft = AnimSceneResManager.getInstance().getScreenW() - (((AnimSceneResManager.getInstance().getScreenW() - 12) / 12) * i);
            this.b = this.mBgMarginLeft;
            this.i = 255;
            this.j = 0;
        } else if (i <= 56) {
            int i2;
            if (i >= 48) {
                i2 = 0;
            } else if (i <= 38 || i >= 48) {
                i2 = 255;
            } else {
                i2 = 255 - ((i - 38) * 28);
            }
            this.i = i2;
            this.b = (this.d.getWidth() + 12) + ((i - 12) * ((this.mBg.getWidth() - this.d.getWidth()) / 44));
            this.j = 255;
        } else {
            this.mBgMarginLeft = 12 - ((i - 56) * 14);
            this.i = 0;
            this.j = 0;
        }
        return false;
    }

    public IAnimEntity[] initAnimEntities() {
        return new IAnimEntity[0];
    }
}
