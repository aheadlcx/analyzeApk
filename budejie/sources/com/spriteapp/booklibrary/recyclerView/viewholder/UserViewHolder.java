package com.spriteapp.booklibrary.recyclerView.viewholder;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.spriteapp.booklibrary.a.d;
import com.spriteapp.booklibrary.a.f;
import com.spriteapp.booklibrary.config.HuaXiSDK;
import com.spriteapp.booklibrary.e.b;
import com.spriteapp.booklibrary.enumeration.LoginStateEnum;
import com.spriteapp.booklibrary.model.UserModel;
import com.spriteapp.booklibrary.recyclerView.decorate.Visitable;
import com.spriteapp.booklibrary.recyclerView.model.StoreUser;
import com.spriteapp.booklibrary.util.ActivityUtil;
import com.spriteapp.booklibrary.util.AppUtil;

public class UserViewHolder extends BaseViewHolder<Visitable> {
    private final View divideView;
    private Context mContext;
    private TextView mFalsePointTextView;
    private String mLoginText = this.mContext.getResources().getString(f.book_reader_is_loading);
    private TextView mRealPointTextView;
    private TextView mRechargeTextView;

    public UserViewHolder(View view, Context context) {
        super(view);
        this.mContext = context;
        this.mRealPointTextView = (TextView) view.findViewById(d.book_reader_real_point);
        this.mFalsePointTextView = (TextView) view.findViewById(d.book_reader_false_point);
        this.mRechargeTextView = (TextView) view.findViewById(d.book_reader_recharge_text_view);
        this.divideView = view.findViewById(d.book_reader_divide_view);
    }

    public void bindViewData(Visitable visitable) {
        if (visitable instanceof StoreUser) {
            com.spriteapp.booklibrary.e.d a = b.a();
            if (a != null) {
                this.mRealPointTextView.setTextColor(a.b());
                this.mFalsePointTextView.setTextColor(a.b());
                this.mRechargeTextView.setTextColor(a.c());
                this.divideView.setBackgroundColor(a.e());
                this.mRechargeTextView.setBackgroundResource(a.d());
            }
            if (HuaXiSDK.mLoginState == LoginStateEnum.LOADING) {
                this.mRechargeTextView.setText(this.mLoginText);
            } else if (HuaXiSDK.mLoginState == LoginStateEnum.FAILED) {
                this.mRechargeTextView.setText(this.mContext.getResources().getString(f.book_reader_reload_text));
            } else if (HuaXiSDK.mLoginState == LoginStateEnum.UN_LOGIN) {
                this.mRechargeTextView.setText(this.mContext.getResources().getString(f.book_reader_login_text));
            } else {
                this.mRechargeTextView.setText(this.mContext.getResources().getString(f.book_reader_recharge_text));
            }
            this.mRechargeTextView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (!UserViewHolder.this.mLoginText.equals(UserViewHolder.this.mRechargeTextView.getText().toString())) {
                        if (AppUtil.isLogin()) {
                            ActivityUtil.toWebViewActivity(UserViewHolder.this.mContext, "http://s.hxdrive.net/user_recharge");
                        } else {
                            HuaXiSDK.getInstance().toLoginPage(UserViewHolder.this.mContext);
                        }
                    }
                }
            });
            UserModel userModel = ((StoreUser) visitable).getUserModel();
            if (userModel == null) {
                this.mRealPointTextView.setText("0");
                this.mFalsePointTextView.setText("0");
                return;
            }
            this.mRealPointTextView.setText(userModel.getUser_real_point() + "");
            this.mFalsePointTextView.setText(userModel.getUser_false_point() + "");
        }
    }
}
