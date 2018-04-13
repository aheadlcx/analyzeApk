package cn.v6.sixrooms.room;

import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import cn.v6.sixrooms.R;
import cn.v6.sixrooms.utils.DisPlayUtil;
import cn.v6.sixrooms.utils.LogUtils;

final class bc implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ RoomActivity b;

    bc(RoomActivity roomActivity, int i) {
        this.b = roomActivity;
        this.a = i;
    }

    public final void run() {
        boolean z = true;
        int dimension;
        int familyPlayerHeight;
        LayoutParams layoutParams;
        if (this.a == 1) {
            dimension = (int) this.b.getResources().getDimension(R.dimen.room_video_margin_top);
            familyPlayerHeight = DisPlayUtil.getFamilyPlayerHeight(this.b);
            layoutParams = new RelativeLayout.LayoutParams(-1, familyPlayerHeight);
            layoutParams.setMargins(0, dimension, 0, 0);
            this.b.i.setLayoutParams(layoutParams);
            layoutParams = new RelativeLayout.LayoutParams(-1, familyPlayerHeight);
            layoutParams.setMargins(0, dimension, 0, 0);
            this.b.m.setLayoutParams(layoutParams);
        } else if (this.a == 0) {
            dimension = DisPlayUtil.getPCPlayerHeight(this.b);
            familyPlayerHeight = DisPlayUtil.getPCPlayerWidth(this.b);
            layoutParams = new RelativeLayout.LayoutParams(familyPlayerHeight, dimension);
            int pCPlayerMargin = DisPlayUtil.getPCPlayerMargin(this.b);
            int dimension2 = (int) this.b.getResources().getDimension(R.dimen.room_video_margin_top);
            layoutParams.setMargins(pCPlayerMargin, dimension2, pCPlayerMargin, 0);
            this.b.i.setLayoutParams(layoutParams);
            layoutParams = new RelativeLayout.LayoutParams(familyPlayerHeight, dimension);
            layoutParams.setMargins(0, dimension2, 0, 0);
            this.b.m.setLayoutParams(layoutParams);
        } else if (this.a == 2 || this.a == 3) {
            if (this.a != 3) {
                z = false;
            }
            familyPlayerHeight = DisPlayUtil.getLandscapePlayerHeight(this.b, z);
            r3 = (familyPlayerHeight - DisPlayUtil.getLandscapeHeight(this.b)) / 2;
            if (r3 >= 0) {
                r4 = new RelativeLayout.LayoutParams(-1, familyPlayerHeight);
                r4.setMargins(0, 0 - r3, 0, 0 - r3);
                this.b.i.setLayoutParams(r4);
                this.b.m.setLayoutParams(r4);
            }
            if (r3 < 0) {
                dimension = DisPlayUtil.getLandscapePlayerWidth(this.b, z);
                familyPlayerHeight = (dimension - this.b.getResources().getDisplayMetrics().widthPixels) / 2;
                layoutParams = new RelativeLayout.LayoutParams(dimension, DisPlayUtil.getLandscapeHeight(this.b));
                layoutParams.setMargins(0 - familyPlayerHeight, 0, 0 - familyPlayerHeight, 0);
                this.b.i.setLayoutParams(layoutParams);
                this.b.m.setLayoutParams(layoutParams);
            }
        } else if (this.a == 4) {
            familyPlayerHeight = DisPlayUtil.getPortraitPlayerHeight(this.b, true);
            r3 = (familyPlayerHeight - DisPlayUtil.getPortraitHeight(this.b)) / 2;
            LogUtils.e("onLayoutChange", r3 + "--" + familyPlayerHeight + "DisPlayUtil.getPortraitHeight(RoomActivity.this)=" + DisPlayUtil.getPortraitHeight(this.b));
            if (r3 >= 0) {
                r4 = new RelativeLayout.LayoutParams(-1, familyPlayerHeight);
                r4.setMargins(0, 0 - r3, 0, 0 - r3);
                this.b.i.setLayoutParams(r4);
                this.b.m.setLayoutParams(r4);
            }
            if (r3 < 0) {
                dimension = DisPlayUtil.getPortraitPlayerWidth(this.b, true, this.b.ao.getHeight());
                familyPlayerHeight = (dimension - this.b.getResources().getDisplayMetrics().widthPixels) / 2;
                layoutParams = new RelativeLayout.LayoutParams(dimension, this.b.ao.getHeight());
                layoutParams.setMargins(0 - familyPlayerHeight, 0, 0 - familyPlayerHeight, 0);
                this.b.i.setLayoutParams(layoutParams);
                this.b.m.setLayoutParams(layoutParams);
            }
        }
    }
}
