package com.spriteapp.booklibrary.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog.Builder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import com.spriteapp.booklibrary.a.d;
import com.spriteapp.booklibrary.a.e;
import com.spriteapp.booklibrary.a.f;
import com.spriteapp.booklibrary.base.Base;
import com.spriteapp.booklibrary.enumeration.AutoSubEnum;
import com.spriteapp.booklibrary.enumeration.PageStyleEnum;
import com.spriteapp.booklibrary.ui.presenter.LoginOutPresenter;
import com.spriteapp.booklibrary.ui.view.LoginOutView;
import com.spriteapp.booklibrary.util.AppUtil;
import com.spriteapp.booklibrary.util.GlideCacheUtil;
import com.spriteapp.booklibrary.util.SharedPreferencesUtil;
import com.spriteapp.booklibrary.util.ToastUtil;

public class SettingActivity extends TitleActivity implements LoginOutView {
    private static final String TAG = "SettingActivity";
    private Switch mAutoBuySwitch;
    private TextView mCacheTextView;
    private RelativeLayout mClearCacheLayout;
    private Button mLoginOutButton;
    private LoginOutPresenter mLoginOutPresenter;
    private Switch mPageModeSwitch;

    public void initData() {
        setTitle("设置");
        getCacheSize();
        this.mLoginOutPresenter = new LoginOutPresenter();
        this.mLoginOutPresenter.attachView((LoginOutView) this);
        this.mLoginOutButton.setVisibility(8);
    }

    private void getCacheSize() {
        this.mCacheTextView.setText(GlideCacheUtil.getInstance().getCacheSize(this));
    }

    public void configViews() {
        boolean z = true;
        super.configViews();
        boolean z2 = SharedPreferencesUtil.getInstance().getInt("hua_xi_book_auto_sub") == AutoSubEnum.AUTO_SUB.getValue();
        if (SharedPreferencesUtil.getInstance().getInt("hua_xi_page_change_style") != PageStyleEnum.FLIP_STYLE.getValue()) {
            z = false;
        }
        this.mAutoBuySwitch.setChecked(z2);
        this.mPageModeSwitch.setChecked(z);
    }

    public void addContentView() {
        this.mContainerLayout.addView(getLayoutInflater().inflate(e.book_reader_activity_setting, null), -1, -1);
    }

    public void findViewId() {
        super.findViewId();
        this.mClearCacheLayout = (RelativeLayout) findViewById(d.book_reader_clear_cache_layout);
        this.mCacheTextView = (TextView) findViewById(d.book_reader_cache_text_view);
        this.mAutoBuySwitch = (Switch) findViewById(d.book_reader_auto_buy_switch);
        this.mPageModeSwitch = (Switch) findViewById(d.book_reader_page_mode_switch);
        this.mLoginOutButton = (Button) findViewById(d.book_reader_login_out_button);
        setListener();
    }

    private void setListener() {
        this.mClearCacheLayout.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                GlideCacheUtil.getInstance().clearImageAllCache(SettingActivity.this);
                ToastUtil.showSingleToast("清理缓存成功");
                SettingActivity.this.mCacheTextView.setVisibility(8);
            }
        });
        this.mAutoBuySwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                int value;
                SharedPreferencesUtil instance = SharedPreferencesUtil.getInstance();
                String str = "hua_xi_book_auto_sub";
                if (z) {
                    value = AutoSubEnum.AUTO_SUB.getValue();
                } else {
                    value = AutoSubEnum.NOT_AUTO_SUB.getValue();
                }
                instance.putInt(str, value);
            }
        });
        this.mPageModeSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                int value;
                SharedPreferencesUtil instance = SharedPreferencesUtil.getInstance();
                String str = "hua_xi_page_change_style";
                if (z) {
                    value = PageStyleEnum.FLIP_STYLE.getValue();
                } else {
                    value = PageStyleEnum.DEFAULT_STYLE.getValue();
                }
                instance.putInt(str, value);
            }
        });
        this.mLoginOutButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                SettingActivity.this.showLoginOutDialog();
            }
        });
    }

    private void showLoginOutDialog() {
        new Builder(this).setTitle(getResources().getString(f.book_reader_prompt)).setMessage(getResources().getString(f.book_reader_sure_to_login_out)).setNegativeButton(getResources().getString(f.book_reader_cancel_text), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).setPositiveButton(getResources().getString(f.book_reader_sure), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                SettingActivity.this.mLoginOutPresenter.loginOut();
                dialogInterface.dismiss();
            }
        }).create().show();
    }

    public void onError(Throwable th) {
    }

    public void setData(Base<Void> base) {
        AppUtil.loginOut();
        finish();
    }

    public void showNetWorkProgress() {
        showDialog();
    }

    public void disMissProgress() {
        dismissDialog();
    }

    public Context getMyContext() {
        return this;
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.mLoginOutPresenter != null) {
            this.mLoginOutPresenter.detachView();
        }
    }
}
