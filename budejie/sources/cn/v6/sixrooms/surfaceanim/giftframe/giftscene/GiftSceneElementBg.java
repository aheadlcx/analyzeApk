package cn.v6.sixrooms.surfaceanim.giftframe.giftscene;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.R;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.GiftScene.GiftSceneParameter;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.util.GiftSceneUtil;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;

public abstract class GiftSceneElementBg extends GiftSceneElement {
    private Bitmap a;
    protected int alpha = 0;
    protected int alphaText = 255;
    private a b = new a(this);
    private boolean c;
    protected Bitmap mBg;
    protected int mBgMarginLeft;
    protected GiftScene mGiftScene;
    protected int mIconHeight;
    protected int mIconMarginLeft;
    protected int mIconMarginTop;
    protected int mIconWidth;
    protected Paint mPaint;
    protected GiftSceneParameter mPara;
    protected int mTextLineSpace;
    protected int mTextMarginLeft;
    protected int mTextMarginTop;
    protected Paint mTextPaint;
    protected int mTextSize;

    class a extends BaseBitmapDataSubscriber {
        final /* synthetic */ GiftSceneElementBg a;

        a(GiftSceneElementBg giftSceneElementBg) {
            this.a = giftSceneElementBg;
        }

        protected final void onNewResultImpl(Bitmap bitmap) {
            this.a.a = bitmap;
            if (this.a.a != null && !this.a.a.isRecycled()) {
                Resources resources = AnimSceneResManager.getInstance().getResources();
                float dimension = resources.getDimension(R.dimen.gift_icon_width);
                float dimension2 = resources.getDimension(R.dimen.gift_icon_height);
                Matrix matrix = new Matrix();
                matrix.postScale(dimension / ((float) this.a.a.getWidth()), dimension2 / ((float) this.a.a.getHeight()));
                Bitmap.createBitmap(this.a.a, 0, 0, this.a.a.getWidth(), this.a.a.getHeight(), matrix, true);
                this.a.c = true;
            }
        }

        protected final void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
            if (this.a.a == null || this.a.a.isRecycled()) {
                this.a.a = this.a.getBitmap(R.drawable.gift_default_icon);
            }
        }
    }

    public abstract Bitmap elementBg();

    public GiftSceneElementBg(AnimScene animScene) {
        super(animScene);
        this.mGiftScene = (GiftScene) animScene;
        this.mPara = (GiftSceneParameter) this.mGiftScene.getSceneParameter();
        Resources resources = AnimSceneResManager.getInstance().getContext().getResources();
        this.mBg = elementBg();
        this.a = getBitmap(R.drawable.gift_default_icon);
        this.mPaint = new Paint();
        this.mTextPaint = new Paint();
        this.mTextPaint.setAntiAlias(true);
        this.mTextPaint.setColor(-1);
        this.mTextSize = resources.getDimensionPixelSize(R.dimen.gift_text_size);
        this.mTextPaint.setTextSize((float) this.mTextSize);
        this.mBgMarginLeft = resources.getDimensionPixelSize(R.dimen.gift_bg_margin_left);
        this.mTextMarginLeft = resources.getDimensionPixelSize(R.dimen.gift_text_margin_left);
        this.mTextMarginTop = resources.getDimensionPixelSize(R.dimen.gift_text_margin_top);
        this.mTextLineSpace = resources.getDimensionPixelSize(R.dimen.gift_text_line_spacing);
        this.mIconWidth = AnimSceneResManager.getInstance().getResources().getDimensionPixelSize(R.dimen.gift_icon_width);
        if (this.mBg != null) {
            this.mIconMarginLeft = (this.mBgMarginLeft + (this.mBg.getWidth() / 2)) - (this.mIconWidth / 2);
        }
        this.mIconHeight = AnimSceneResManager.getInstance().getResources().getDimensionPixelSize(R.dimen.gift_icon_height);
    }

    public void drawElement(Canvas canvas) {
        if (!frameControl(this.mCurFrame)) {
            this.mPaint.setAlpha(this.alpha);
            int i = this.mAnimScene.getSceneParameter().getPoint().y;
            canvas.drawBitmap(this.mBg, (float) this.mBgMarginLeft, (float) i, this.mPaint);
            this.mTextPaint.setAlpha(this.alphaText);
            canvas.drawText(this.mPara.getText1(), (float) this.mTextMarginLeft, (float) ((this.mTextMarginTop + i) + this.mTextSize), this.mTextPaint);
            canvas.drawText(this.mPara.getText2(), (float) this.mTextMarginLeft, (float) (((this.mTextMarginTop + i) + (this.mTextSize * 2)) + this.mTextLineSpace), this.mTextPaint);
            this.mIconMarginTop = (i - (this.mIconHeight / 2)) + (this.mBg.getHeight() / 2);
            if (this.a == null || this.a.isRecycled() || !this.c) {
                GiftSceneUtil.getIconBitmap(this.mPara.getIconUrl(), this.b);
            }
            if (this.a != null && !this.a.isRecycled()) {
                canvas.drawBitmap(this.a, (float) this.mIconMarginLeft, (float) this.mIconMarginTop, this.mPaint);
            }
        }
    }

    public boolean frameControl(int i) {
        if (i <= 10) {
            return true;
        }
        if (i <= 16) {
            this.alpha += 42;
            return false;
        } else if (i <= this.mAnimScene.getSceneParameter().getMaxFrameNum() - 4) {
            this.alpha = 255;
            return false;
        } else if (i < this.mAnimScene.getSceneParameter().getMaxFrameNum()) {
            this.alpha /= 4;
            return false;
        } else if (i < this.mAnimScene.getSceneParameter().getMaxFrameNum()) {
            return false;
        } else {
            this.alpha = 0;
            this.alphaText = 0;
            return false;
        }
    }
}
