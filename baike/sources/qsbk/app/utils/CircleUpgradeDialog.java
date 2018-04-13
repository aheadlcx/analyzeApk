package qsbk.app.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import qsbk.app.widget.UpgradeView;

public class CircleUpgradeDialog extends Dialog {
    private int a;

    private CircleUpgradeDialog(Context context, int i) {
        super(context);
        this.a = i;
        Window window = getWindow();
        window.requestFeature(1);
        window.clearFlags(65792);
        window.setSoftInputMode(32);
    }

    public static CircleUpgradeDialog show(Context context, int i) {
        CircleUpgradeDialog circleUpgradeDialog = new CircleUpgradeDialog(context, i);
        circleUpgradeDialog.show();
        return circleUpgradeDialog;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.getDecorView().setPadding(0, 0, 0, 0);
        LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        window.setAttributes(attributes);
        setCanceledOnTouchOutside(true);
        setCancelable(true);
        View upgradeView = new UpgradeView(getContext());
        upgradeView.setLevel(this.a);
        setContentView(upgradeView);
        upgradeView.setOnClickListener(new g(this));
    }
}
