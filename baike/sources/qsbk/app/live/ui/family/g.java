package qsbk.app.live.ui.family;

import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import qsbk.app.core.net.Callback;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.net.response.BaseResponse;
import qsbk.app.core.utils.AppUtils;
import qsbk.app.core.utils.ToastUtil;
import qsbk.app.live.R;

class g extends Callback {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;
    final /* synthetic */ f e;

    g(f fVar, String str, String str2, String str3, String str4) {
        this.e = fVar;
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
    }

    public Map<String, String> getParams() {
        Map<String, String> hashMap = new HashMap();
        if (this.e.a.n == 1) {
            hashMap.put("family_id", this.e.a.o + "");
            if (!this.a.equals(this.e.a.p)) {
                hashMap.put("name", this.a);
            }
            if (!this.b.equals(this.e.a.q)) {
                hashMap.put("badge", this.b);
            }
            if (!this.c.equals(this.e.a.r)) {
                hashMap.put("notice", this.c.trim());
            }
        } else {
            hashMap.put("familyname", this.a);
            hashMap.put("familybadge", this.b);
            hashMap.put("familynotice", this.c.trim());
            if (!TextUtils.isEmpty(this.e.a.c)) {
                hashMap.put("familycrest", this.e.a.c);
            }
        }
        hashMap.put("source", AppUtils.getInstance().getUserInfoProvider().getUserOrigin() + "");
        hashMap.put("source_id", AppUtils.getInstance().getUserInfoProvider().getUserId() + "");
        return hashMap;
    }

    public void onSuccess(BaseResponse baseResponse) {
        if (baseResponse.getSimpleDataInt(NotificationCompat.CATEGORY_ERROR) != 0) {
            return;
        }
        if (this.e.a.n == 0) {
            ToastUtil.Long(R.string.family_create_success);
            this.e.a.o = (long) baseResponse.getSimpleDataInt("family_id");
            Intent intent = new Intent(this.e.a, FamilyDetailActivity.class);
            intent.putExtra("familyName", this.a);
            intent.putExtra("familyBadge", this.b);
            intent.putExtra("familyNotice", this.c);
            intent.putExtra("familyHead", AppUtils.getInstance().getUserInfoProvider().getUser());
            intent.putExtra("familyId", this.e.a.o);
            this.e.a.startActivity(intent);
            this.e.a.finish();
            return;
        }
        ToastUtil.Long(R.string.family_update_success);
        this.e.a.setResult(-1);
        this.e.a.finish();
    }

    public void onFailed(int i, String str) {
        if (i == -1505 && UrlConstants.FAMILY_CREATE.equals(this.d)) {
            this.e.a.b();
        }
    }
}
