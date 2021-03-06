package cn.xiaochuankeji.tieba.ui.auth;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.AppCompatTextView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.xiaochuan.image.a.a;
import cn.xiaochuan.jsbridge.b;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.my.account.InputPhoneNumberActivity;
import cn.xiaochuankeji.tieba.webview.WebActivity;

public class GetAccountTipsFragment extends a {
    @BindView
    LinearLayout content;
    @BindView
    AppCompatTextView find_account;
    @BindView
    AppCompatTextView find_pwd;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_get_account_tips, viewGroup, false);
        ButterKnife.a(this, inflate);
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (getDialog() != null) {
            Window window = getDialog().getWindow();
            if (window != null) {
                if (VERSION.SDK_INT > 19) {
                    window.setFlags(67108864, 67108864);
                }
                window.setSoftInputMode(2);
                window.setLayout(-1, -1);
            }
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        Dialog appCompatDialog = new AppCompatDialog(getActivity(), R.style.SheetTheme);
        appCompatDialog.setCanceledOnTouchOutside(true);
        appCompatDialog.setCancelable(true);
        appCompatDialog.setOnKeyListener(new OnKeyListener(this) {
            final /* synthetic */ GetAccountTipsFragment a;

            {
                this.a = r1;
            }

            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i != 4) {
                    return false;
                }
                this.a.dismiss();
                return true;
            }
        });
        return appCompatDialog;
    }

    public void dismiss() {
        super.dismiss();
    }

    @OnClick
    public void close() {
        dismiss();
    }

    @OnClick
    public void findPW() {
        InputPhoneNumberActivity.a(getActivity(), 103, "pwd");
        c.a().f();
        dismiss();
    }

    @OnClick
    public void findAccount() {
        WebActivity.a(getActivity(), b.a("找回账号", cn.xiaochuankeji.tieba.background.utils.d.a.d("https://$$/help/account/feedback")));
        c.a().g();
        dismiss();
    }

    public static void a(FragmentManager fragmentManager) {
        Fragment findFragmentByTag = fragmentManager.findFragmentByTag("GetAccountTips");
        if (findFragmentByTag == null) {
            new GetAccountTipsFragment().show(fragmentManager, "GetAccountTips");
        } else {
            fragmentManager.beginTransaction().show(findFragmentByTag).commitAllowingStateLoss();
        }
    }
}
