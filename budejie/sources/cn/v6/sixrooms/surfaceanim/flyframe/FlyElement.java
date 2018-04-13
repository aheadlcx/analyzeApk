package cn.v6.sixrooms.surfaceanim.flyframe;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import cn.v6.sixrooms.surfaceanim.AnimScene;
import cn.v6.sixrooms.surfaceanim.AnimSceneElement;
import cn.v6.sixrooms.surfaceanim.R;
import cn.v6.sixrooms.surfaceanim.animinterface.IAnimEntity;
import cn.v6.sixrooms.surfaceanim.util.AnimSceneResManager;

public class FlyElement extends AnimSceneElement {
    private Paint a = new Paint();
    private int b = AnimSceneResManager.getInstance().getResources().getDimensionPixelSize(R.dimen.fly_msg_speed);
    private float c = ((float) AnimSceneResManager.getInstance().getResources().getDimensionPixelSize(R.dimen.fly_msg_stroke_size));
    private float d;
    private float e;
    private String f;
    private boolean g;

    public FlyElement(AnimScene animScene) {
        super(animScene);
        this.a.setAntiAlias(true);
        this.a.setTextSize((float) AnimSceneResManager.getInstance().getResources().getDimensionPixelSize(R.dimen.fly_msg_size));
        this.f = ((FlySceneParameter) animScene.getSceneParameter()).getText();
    }

    public boolean frameControl(int i) {
        return false;
    }

    public IAnimEntity[] initAnimEntities() {
        return null;
    }

    public void draw(Canvas canvas) {
        if (!this.g) {
            this.d = (float) this.mAnimScene.getSceneParameter().getPoint().y;
            this.e = (float) this.mAnimScene.getSceneParameter().getPoint().x;
            this.g = true;
        }
        this.a.setColor(Color.parseColor("#bf3b7d"));
        canvas.drawText(this.f, this.e, this.d - this.c, this.a);
        canvas.drawText(this.f, this.e, this.d + this.c, this.a);
        canvas.drawText(this.f, this.e + this.c, this.d, this.a);
        canvas.drawText(this.f, this.e + this.c, this.d + this.c, this.a);
        canvas.drawText(this.f, this.e + this.c, this.d - this.c, this.a);
        canvas.drawText(this.f, this.e - this.c, this.d, this.a);
        canvas.drawText(this.f, this.e - this.c, this.d + this.c, this.a);
        canvas.drawText(this.f, this.e - this.c, this.d - this.c, this.a);
        this.a.setColor(-1);
        canvas.drawText(this.f, this.e, this.d, this.a);
        if (this.e < (-this.a.measureText(this.f)) - ((float) (this.b * 2))) {
            this.mAnimScene.getSceneParameter().setMaxFrameNum(this.mAnimScene.getSceneParameter().getCurFrameNum());
        }
        this.e -= (float) this.b;
    }

    public void drawElement(Canvas canvas) {
    }
}
