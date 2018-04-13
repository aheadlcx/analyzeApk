package qsbk.app.core.utils;

import android.util.Log;
import qsbk.app.core.model.CustomButton;
import qsbk.app.core.utils.CompressUtils.CompressListener;

class n implements CompressListener {
    final /* synthetic */ String a;
    final /* synthetic */ String[] b;
    final /* synthetic */ String c;
    final /* synthetic */ m d;

    n(m mVar, String str, String[] strArr, String str2) {
        this.d = mVar;
        this.a = str;
        this.b = strArr;
        this.c = str2;
    }

    public void onFinished() {
        FileUtils.deleteFileIfExist(this.a);
        if (this.b[3].equals(CustomButton.EVENT_TYPE_GIFT)) {
            PreferenceUtils.instance().putString(PrefrenceKeys.KEY_GIFT_ANIM + this.b[0], this.b[2]);
        } else if (this.b[3].equals("scene")) {
            PreferenceUtils.instance().putString(PrefrenceKeys.KEY_SCENE_ANIM + this.b[0], this.b[2]);
        } else if (this.b[3].equals("market")) {
            Log.e(GiftResSync.a, "hotpatch: download scene data less");
            PreferenceUtils.instance().putString(PrefrenceKeys.KEY_MARKET_ANIM + this.b[0], this.b[2]);
        }
        if (!AppUtils.getInstance().isLowSpecificationDevice() && !this.b[3].equals("market")) {
            GiftResSync.encrypt(this.c);
        }
    }
}
