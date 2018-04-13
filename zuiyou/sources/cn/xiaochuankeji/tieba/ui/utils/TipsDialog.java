package cn.xiaochuankeji.tieba.ui.utils;

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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.xiaochuan.image.a.a;
import cn.xiaochuankeji.tieba.R;

public class TipsDialog extends a {
    private OnClickListener a;
    @BindView
    AppCompatTextView content;
    @BindView
    View content_container;
    @BindView
    AppCompatTextView left;
    @BindView
    AppCompatTextView right;
    @BindView
    AppCompatTextView title;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.dialog_tips, viewGroup, false);
        ButterKnife.a(this, inflate);
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        Bundle arguments = getArguments();
        this.title.setText(String.valueOf(arguments.getString("td_title")));
        this.content.setText(String.valueOf(arguments.getString("td_content")));
        this.left.setText(String.valueOf(arguments.getString("td_left")));
        this.right.setText(String.valueOf(arguments.getString("td_right")));
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
            final /* synthetic */ TipsDialog a;

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
    public void left() {
        dismiss();
    }

    @OnClick
    public void right(View view) {
        dismiss();
        if (this.a != null) {
            this.a.onClick(view);
        }
    }

    public static TipsDialog a(@NonNull FragmentManager fragmentManager, @NonNull Bundle bundle) {
        Fragment findFragmentByTag = fragmentManager.findFragmentByTag("TipsDialog");
        if (findFragmentByTag == null) {
            TipsDialog tipsDialog = new TipsDialog();
            tipsDialog.setArguments(bundle);
            tipsDialog.show(fragmentManager, "TipsDialog");
            return tipsDialog;
        }
        findFragmentByTag.setArguments(bundle);
        fragmentManager.beginTransaction().show(findFragmentByTag).commitAllowingStateLoss();
        return (TipsDialog) findFragmentByTag;
    }

    public void a(OnClickListener onClickListener) {
        this.a = onClickListener;
    }
}
