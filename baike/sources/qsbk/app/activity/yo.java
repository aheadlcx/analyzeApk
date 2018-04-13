package qsbk.app.activity;

import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import qsbk.app.nearby.ui.ShakeDialogFragment;

class yo implements OnClickListener {
    final /* synthetic */ OtherInfoActivity a;

    yo(OtherInfoActivity otherInfoActivity) {
        this.a = otherInfoActivity;
    }

    public void onClick(View view) {
        DialogInterface.OnClickListener yqVar;
        DialogInterface.OnClickListener onClickListener = null;
        String str = "";
        String str2 = "";
        String str3 = "";
        switch (zd.a[OtherInfoActivity.b(this.a).ordinal()]) {
            case 1:
                str = "取消粉TA后，不能发图片。是否取消粉?";
                str3 = "取消粉";
                onClickListener = new yp(this);
                str2 = "再想想";
                yqVar = new yq(this);
                break;
            case 2:
            case 3:
                str = "是否取消粉TA?";
                str3 = "取消粉";
                onClickListener = new yr(this);
                str2 = "再想想";
                yqVar = new ys(this);
                break;
            case 4:
                str = "是否确定移出黑名单?";
                str2 = "移出黑名单";
                yqVar = new yt(this);
                str3 = "再想想";
                onClickListener = new yu(this);
                break;
            case 5:
                OtherInfoActivity.a(this.a, 0, 0);
                yqVar = null;
                break;
            case 6:
            case 7:
                ShakeDialogFragment newInstance = ShakeDialogFragment.newInstance(OtherInfoActivity.a(this.a).userName);
                newInstance.setOnShakedListener(new yv(this));
                newInstance.show(this.a.getSupportFragmentManager(), ShakeDialogFragment.class.getSimpleName());
                newInstance.setCancelable(false);
                yqVar = null;
                break;
            default:
                yqVar = null;
                break;
        }
        OtherInfoActivity.a(this.a, str, str2, str3, yqVar, onClickListener);
    }
}
