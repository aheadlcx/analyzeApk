package cn.v6.sixrooms.surfaceanim.specialframe.poseframe;

import android.graphics.Bitmap;
import android.text.TextUtils;
import cn.v6.sixrooms.surfaceanim.AnimScene.SceneParameter;
import cn.v6.sixrooms.surfaceanim.R;
import cn.v6.sixrooms.surfaceanim.giftframe.giftscene.util.GiftSceneUtil;
import cn.v6.sixrooms.surfaceanim.protocol.PointI;
import cn.v6.sixrooms.surfaceanim.specialframe.SpecialScene;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;
import cn.v6.sixrooms.surfaceanim.util.FrameType;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import java.util.Random;

public class PoseScene extends SpecialScene {
    private PointI[] a;
    private String[] b;
    private int c;
    private int d;
    private int e;
    private String f;
    private a g;
    private Bitmap h;
    private PoseSceneParameter i;
    private int j;
    protected int offsetX = 0;
    protected int offsetY = 0;

    public static class PoseSceneParameter extends SceneParameter {
        private String a;
        private String b;
        private String c;
        private float d;
        private float e;
        private Bitmap f;
        private boolean g;

        public String getIconUrl() {
            return this.a;
        }

        public void setIconUrl(String str) {
            this.a = str;
        }

        public String getPointConfigL() {
            return this.b;
        }

        public void setPointConfigL(String str) {
            this.b = str;
        }

        public String getPointConfigP() {
            return this.c;
        }

        public void setPointConfigP(String str) {
            this.c = str;
        }

        public float getCompoundTime() {
            return this.d;
        }

        public void setCompoundTime(float f) {
            this.d = f;
        }

        public float getTotalTime() {
            return this.e;
        }

        public void setTotalTime(float f) {
            this.e = f;
        }

        public Bitmap getIcon() {
            return this.f;
        }

        public void setIcon(Bitmap bitmap) {
            this.f = bitmap;
        }

        public boolean isIconLoaded() {
            return this.g;
        }

        public void setIconLoaded(boolean z) {
            this.g = z;
        }
    }

    class a extends BaseBitmapDataSubscriber {
        final /* synthetic */ PoseScene a;

        a(PoseScene poseScene) {
            this.a = poseScene;
        }

        protected final void onNewResultImpl(Bitmap bitmap) {
            this.a.h = bitmap;
            if (this.a.h == null || this.a.h.isRecycled()) {
                this.a.h = this.a.getBitmap(R.drawable.gift_default_icon);
            } else {
                this.a.i.setIconLoaded(true);
            }
            this.a.i.setIcon(this.a.h);
        }

        protected final void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {
            if (this.a.h == null || this.a.h.isRecycled()) {
                this.a.h = this.a.getBitmap(R.drawable.gift_default_icon);
            }
            this.a.i.setIcon(this.a.h);
        }
    }

    public PoseScene(SceneParameter sceneParameter) {
        int i = 0;
        super(sceneParameter);
        this.i = (PoseSceneParameter) sceneParameter;
        this.j = (int) (30.0f * this.i.getTotalTime());
        this.i.setMaxFrameNum(this.j);
        this.d = AnimSceneResManager.getInstance().getScreenY();
        this.c = AnimSceneResManager.getInstance().getScreenW();
        if (this.c < this.d) {
            this.f = this.i.getPointConfigP();
        } else {
            this.f = this.i.getPointConfigL();
        }
        if (!TextUtils.isEmpty(this.f)) {
            this.b = this.f.split(",");
            if (this.b.length >= 2 && this.b.length % 2 == 0) {
                int i2;
                int i3;
                int abs;
                int a;
                this.h = getBitmap(R.drawable.gift_default_icon);
                this.i.setIcon(this.h);
                this.g = new a(this);
                float dimension = AnimSceneResManager.getInstance().getResources().getDimension(R.dimen.pose_icon_width);
                GiftSceneUtil.getScaleBitmap(this.i.getIconUrl(), dimension, dimension, this.g);
                this.e = this.b.length / 2;
                this.a = new PointI[this.e];
                int i4 = (this.e / 2) - ((this.e / 2) % 2);
                for (i2 = 0; i2 < i4 / 2; i2++) {
                    i3 = -a(180);
                    abs = Math.abs(i3) + this.c;
                    a = a(this.d);
                    this.a[i2 * 2] = new PointI(i3, a);
                    this.a[(i2 * 2) + 1] = new PointI(abs, a);
                }
                i2 = this.e - i4;
                i4 = this.e - i2;
                while (i < (i2 / 2) + 1) {
                    i3 = (i * 2) + i4;
                    abs = a(this.c);
                    a = -a(180);
                    int abs2 = Math.abs(a) + this.d;
                    if (i3 < this.e) {
                        this.a[i3] = new PointI(abs, a);
                        if (i3 + 1 < this.e) {
                            this.a[i3 + 1] = new PointI(abs, abs2);
                        }
                    }
                    i++;
                }
            }
        }
    }

    private static int a(int i) {
        return (new Random().nextInt(i) % ((i + 0) + 1)) + 0;
    }

    protected int initMaxFrames() {
        return 120;
    }

    protected void initResources() {
    }

    protected void releaseResources() {
        AnimSceneResManager.getInstance().clearSceneBitmaps(getSceneType());
    }

    public FrameType getFrameType() {
        return FrameType.NONE;
    }

    protected void initSceneElement() {
        for (int i = 0; i < this.b.length - 2; i += 2) {
            try {
                addElement(new a(this, this.a[i / 2], new PointI(AnimSceneResManager.getInstance().getScalePx(Integer.parseInt(this.b[i])), AnimSceneResManager.getInstance().getScalePx(Integer.parseInt(this.b[i + 1]))), this.j));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    protected Bitmap getBitmap(int i) {
        return AnimSceneResManager.getInstance().getBitmap(getSceneType(), i);
    }
}
