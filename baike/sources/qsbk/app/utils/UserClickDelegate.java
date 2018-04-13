package qsbk.app.utils;

import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.im.OfficialInfoActivity;
import qsbk.app.im.QiushiNotificationCountManager;
import qsbk.app.im.QiuyouquanNotificationCountManager;

public class UserClickDelegate implements OnClickListener {
    private static final String[] a = new String[]{QiuyouquanNotificationCountManager.QIUYOUQUAN_PUSH_UID, QiushiNotificationCountManager.QIUSHI_PUSH_UID, "22584215", "21481189", "24929200", "22335821", "24609252", "114157", "45731", "32879940", "34804704", "33690417", "37152735"};
    private OnClickListener b;
    private String c;
    private String d;
    private String e;

    public UserClickDelegate(String str) {
        this(str, null);
    }

    public UserClickDelegate(String str, OnClickListener onClickListener) {
        this(str, null, null, onClickListener);
    }

    public UserClickDelegate(String str, String str2, String str3, OnClickListener onClickListener) {
        this.c = str;
        this.e = str3;
        this.d = str2;
        this.b = onClickListener;
    }

    public static final boolean isOfficialAccount(String str) {
        for (String equals : a) {
            if (equals.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public void onClick(View view) {
        if (isOfficialAccount(this.c)) {
            OfficialInfoActivity.launch(view.getContext(), this.c, this.d, this.e);
        } else if (this.b != null) {
            this.b.onClick(view);
        }
    }
}
