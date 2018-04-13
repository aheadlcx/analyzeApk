package cn.xiaochuan.image.a;

import android.app.Dialog;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import cn.xiaochuankeji.a.a.d;
import cn.xiaochuankeji.a.a.f;

public class c extends a {
    public static c a() {
        return new c();
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        Dialog appCompatDialog = new AppCompatDialog(getActivity(), f.LoadingTheme);
        appCompatDialog.setCanceledOnTouchOutside(false);
        appCompatDialog.setCancelable(false);
        return appCompatDialog;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(d.ucrop_loading, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setDismissMessage(null);
        }
        super.onDestroyView();
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        Dialog dialog = getDialog();
        if (dialog != null) {
            Window window = dialog.getWindow();
            window.addFlags(Integer.MIN_VALUE);
            if (VERSION.SDK_INT > 19) {
                window.setFlags(67108864, 67108864);
            }
            window.setSoftInputMode(3);
            window.setLayout(-1, -1);
        }
    }

    public void a(FragmentManager fragmentManager) {
        Fragment findFragmentByTag = fragmentManager.findFragmentByTag("crop_loading");
        if (findFragmentByTag == null) {
            show(fragmentManager, "crop_loading");
        } else {
            fragmentManager.beginTransaction().show(findFragmentByTag).commitAllowingStateLoss();
        }
    }

    public void dismiss() {
        dismissAllowingStateLoss();
    }

    public void dismissAllowingStateLoss() {
        if (!isDetached() && getFragmentManager() != null) {
            try {
                super.dismissAllowingStateLoss();
            } catch (Exception e) {
            }
        }
    }
}
