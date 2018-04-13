package qsbk.app.activity.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import qsbk.app.R;

public class UserInfoDialog extends AlertDialog {
    public UserInfoDialog(Context context, int i) {
        super(context, i);
    }

    public UserInfoDialog(Context context) {
        super(context);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_user_info_remind);
    }
}
