package qsbk.app.live.ui.family;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.core.net.NetRequest;
import qsbk.app.core.net.UrlConstants;
import qsbk.app.core.utils.ToastUtil;
import qsbk.app.live.R;

class f implements OnClickListener {
    final /* synthetic */ FamilyCreateActivity a;

    f(FamilyCreateActivity familyCreateActivity) {
        this.a = familyCreateActivity;
    }

    public void onClick(View view) {
        if (this.a.b == null) {
            ToastUtil.Long(R.string.family_upload_avatar_hint);
            return;
        }
        String obj = this.a.e.getText().toString();
        String obj2 = this.a.f.getText().toString();
        String obj3 = this.a.g.getText().toString();
        if (TextUtils.isEmpty(obj)) {
            ToastUtil.Long(R.string.family_create_name_at_least);
        } else if (this.a.a(obj)) {
            ToastUtil.Long(R.string.family_contains_only_chinese_english);
        } else {
            int b = this.a.c(obj);
            if (b < 2) {
                ToastUtil.Long(R.string.family_create_name_at_least);
            } else if (b > 8) {
                ToastUtil.Long(R.string.family_create_name_at_most);
            } else if (TextUtils.isEmpty(obj2)) {
                ToastUtil.Long(R.string.family_create_badge_not_equal);
            } else if (!this.a.b(obj2)) {
                ToastUtil.Long(R.string.family_badge_contains_only_chinese);
            } else if (obj2.length() != 2) {
                ToastUtil.Long(R.string.family_create_badge_not_equal);
            } else if (TextUtils.isEmpty(obj3) || this.a.c(obj3) <= 256) {
                String str;
                if (this.a.n == 0) {
                    str = UrlConstants.FAMILY_CREATE;
                } else {
                    str = UrlConstants.FAMILY_UPDATE;
                }
                NetRequest.getInstance().post(str, new g(this, obj, obj2, obj3, str));
            } else {
                ToastUtil.Long(R.string.family_create_notice_at_most);
            }
        }
    }
}
