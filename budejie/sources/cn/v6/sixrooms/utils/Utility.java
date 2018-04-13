package cn.v6.sixrooms.utils;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Utility {
    private static Dialog a;

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter adapter = listView.getAdapter();
        if (adapter != null) {
            int count = adapter.getCount();
            int i = 0;
            for (int i2 = 0; i2 < count; i2++) {
                View view = adapter.getView(i2, null, listView);
                view.measure(0, 0);
                i += view.getMeasuredHeight();
            }
            LayoutParams layoutParams = listView.getLayoutParams();
            layoutParams.height = (listView.getDividerHeight() * (adapter.getCount() - 1)) + i;
            listView.setLayoutParams(layoutParams);
        }
    }

    public static void dealException(Activity activity) {
        Dialog createConfirmDialogs = new DialogUtils(activity).createConfirmDialogs(14, "提示", "账号异常,有账号在异地登陆!", "退出", new af());
        a = createConfirmDialogs;
        createConfirmDialogs.setOnDismissListener(new ag());
        a.show();
    }
}
