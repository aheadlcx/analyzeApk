package qsbk.app.fragments;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import qsbk.app.QsbkApp;
import qsbk.app.utils.ToastAndDialog;
import qsbk.app.utils.UIHelper;

class bj implements OnEditorActionListener {
    final /* synthetic */ CircleTopicsFragment a;

    bj(CircleTopicsFragment circleTopicsFragment) {
        this.a = circleTopicsFragment;
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 3 && keyEvent.getKeyCode() != 66) {
            return false;
        }
        CircleTopicsFragment.e(this.a).clearFocus();
        UIHelper.hideKeyboard(this.a.getActivity());
        String replaceAll = CircleTopicsFragment.e(this.a).getText().toString().replaceAll(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "");
        if ((replaceAll == null || replaceAll.length() == 0) && !TextUtils.isEmpty(CircleTopicsFragment.j(this.a))) {
            CircleTopicsFragment.e(this.a).setText(CircleTopicsFragment.j(this.a));
            replaceAll = CircleTopicsFragment.j(this.a);
        }
        if (replaceAll == null || replaceAll.length() == 0) {
            ToastAndDialog.makeNegativeToast(QsbkApp.mContext, "请输入关键词", Integer.valueOf(0)).show();
        } else if (replaceAll.contains("打卡") && CircleTopicsFragment.k(this.a)) {
            ToastAndDialog.makeNegativeToast(this.a.getActivity(), "不可以添加打卡话题哦～").show();
            return true;
        } else {
            CircleTopicsFragment.a(this.a, 1);
            CircleTopicsFragment.l(this.a);
            CircleTopicsFragment.a(this.a, replaceAll, 1);
        }
        return true;
    }
}
