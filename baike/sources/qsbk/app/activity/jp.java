package qsbk.app.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import qsbk.app.adapter.BaseImageAdapter;
import qsbk.app.utils.SharePreferenceUtils;

class jp implements OnClickListener {
    final /* synthetic */ CommonSettingActivity a;

    jp(CommonSettingActivity commonSettingActivity) {
        this.a = commonSettingActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        String str = "";
        switch (i) {
            case 0:
                str = "auto";
                break;
            case 1:
                str = "wifi";
                break;
            case 2:
                str = BaseImageAdapter.IMAGELOADWAY_TEXTONLY;
                break;
        }
        SharePreferenceUtils.setSharePreferencesValue("imageLoadWay", str);
        this.a.e.setSubTitle(this.a.a(str));
        dialogInterface.dismiss();
    }
}
