package cn.v6.sixrooms.ui.phone;

import android.content.Intent;
import cn.v6.sixrooms.utils.DialogUtils.DialogListener;
import com.alibaba.wireless.security.SecExceptionCode;

final class y implements DialogListener {
    final /* synthetic */ DialogSupportCustomizeActivity a;

    y(DialogSupportCustomizeActivity dialogSupportCustomizeActivity) {
        this.a = dialogSupportCustomizeActivity;
    }

    public final void positive(int i) {
        if (i == SecExceptionCode.SEC_ERROR_OPENSDK) {
            this.a.startActivity(new Intent(this.a, UserManagerActivity.class));
            this.a.finish();
        }
    }

    public final void negative(int i) {
    }
}
