package cn.v6.sixrooms.ui.phone.room.spirit;

import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.room.BaseRoomActivity;
import cn.v6.sixrooms.utils.DensityUtil;
import cn.v6.sixrooms.utils.DisPlayUtil;
import cn.v6.sixrooms.view.interfaces.IOnConfigurationChangedListenter;

public class FlyTextSpirit implements ISpirit, IOnConfigurationChangedListenter {
    private int a = V6Coop.getInstance().getContext().getResources().getDimensionPixelSize(R.dimen.fly_msg_speed);
    private float b = ((float) V6Coop.getInstance().getContext().getResources().getDimensionPixelSize(R.dimen.fly_msg_stroke_size));
    private float c;
    private String d;
    private Paint e;
    private float f = ((float) DensityUtil.getScreenWidth());
    private boolean g;
    private BaseRoomActivity h;

    public FlyTextSpirit(BaseRoomActivity baseRoomActivity, String str) {
        this.h = baseRoomActivity;
        this.e = new Paint();
        this.e.setAntiAlias(true);
        this.e.setTextSize((float) V6Coop.getInstance().getContext().getResources().getDimensionPixelSize(R.dimen.fly_msg_size));
        this.d = str;
    }

    public void draw(Canvas canvas, ISpiritControl iSpiritControl) {
        if (this.a == 0) {
            iSpiritControl.removeSpirit(this);
        }
        if (!this.g) {
            this.f = (float) DensityUtil.getScreenWidth();
            this.c = a(this.h.mRoomType);
            this.g = true;
        }
        canvas.drawColor(0, Mode.CLEAR);
        this.e.setColor(Color.parseColor("#bf3b7d"));
        canvas.drawText(this.d, this.f, this.c - this.b, this.e);
        canvas.drawText(this.d, this.f, this.c + this.b, this.e);
        canvas.drawText(this.d, this.f + this.b, this.c, this.e);
        canvas.drawText(this.d, this.f + this.b, this.c + this.b, this.e);
        canvas.drawText(this.d, this.f + this.b, this.c - this.b, this.e);
        canvas.drawText(this.d, this.f - this.b, this.c, this.e);
        canvas.drawText(this.d, this.f - this.b, this.c + this.b, this.e);
        canvas.drawText(this.d, this.f - this.b, this.c - this.b, this.e);
        this.e.setColor(-1);
        canvas.drawText(this.d, this.f, this.c, this.e);
        if (this.f < (-this.e.measureText(this.d)) - ((float) (this.a * 2))) {
            iSpiritControl.removeSpirit(this);
        }
        this.f -= (float) this.a;
    }

    private float a(int i) {
        switch (i) {
            case 0:
                return (float) (DisPlayUtil.getPlayerHeight(V6Coop.getInstance().getContext()) - V6Coop.getInstance().getContext().getResources().getDimensionPixelSize(R.dimen.fly_msg_marginbottom));
            case 1:
                return (float) (DisPlayUtil.getFamilyPlayerHeight(V6Coop.getInstance().getContext()) - V6Coop.getInstance().getContext().getResources().getDimensionPixelSize(R.dimen.fly_msg_marginbottom));
            case 2:
            case 3:
                return (float) ((DensityUtil.getScreenHeight() - V6Coop.getInstance().getContext().getResources().getDimensionPixelSize(R.dimen.landscape_full_screen_chart_height)) - V6Coop.getInstance().getContext().getResources().getDimensionPixelSize(R.dimen.flytext_landscape_full_margin_bottom));
            case 4:
                return (float) (((DensityUtil.getScreenHeight() - V6Coop.getInstance().getContext().getResources().getDimensionPixelSize(R.dimen.portrait_full_screen_chart_height)) - V6Coop.getInstance().getContext().getResources().getDimensionPixelSize(R.dimen.flytext_portrait_full_margin_bottom)) - DensityUtil.getStatusBarHeight(this.h));
            default:
                return (float) (DisPlayUtil.getPlayerHeight(V6Coop.getInstance().getContext()) - V6Coop.getInstance().getContext().getResources().getDimensionPixelSize(R.dimen.fly_msg_marginbottom));
        }
    }

    public void updateRoomType(int i) {
        this.c = a(i);
    }

    public void onConfigurationChanged(Configuration configuration) {
        updateRoomType(this.h.mRoomType);
    }
}
