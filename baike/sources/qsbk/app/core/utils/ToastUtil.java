package qsbk.app.core.utils;

import qsbk.app.core.widget.toast.ToastManager;

public class ToastUtil {
    public static void Long(String str) {
        ToastManager.show(AppUtils.getInstance().getAppContext(), str, 1, true, true);
    }

    public static void Long(int i) {
        Long(AppUtils.getInstance().getAppContext().getString(i));
    }

    public static void Short(String str) {
        ToastManager.show(AppUtils.getInstance().getAppContext(), str, 0, true, true);
    }

    public static void Short(int i) {
        Short(AppUtils.getInstance().getAppContext().getString(i));
    }
}
