package com.b;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import com.budejie.www.bean.UserItem;
import com.budejie.www.util.ai;
import com.spriteapp.booklibrary.config.HuaXiConfig$Builder;
import com.spriteapp.booklibrary.config.HuaXiSDK;
import com.spriteapp.booklibrary.listener.ChannelListener;
import com.spriteapp.booklibrary.model.RegisterModel;
import com.spriteapp.booklibrary.ui.activity.HomeActivity;
import com.spriteapp.booklibrary.ui.activity.RecentReadActivity;
import com.spriteapp.booklibrary.ui.fragment.NativeBookStoreFragment;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.tauth.Tencent;

public class a {
    private static SharedPreferences c;
    public Tencent a;
    private Context b;
    private IWXAPI d;
    private b e;
    private ChannelListener f = new a$1(this);

    public void a(Context context) {
        this.b = context;
    }

    public void a() {
        boolean z = false;
        c = this.b.getSharedPreferences("weiboprefer", 0);
        HuaXiConfig$Builder context = new HuaXiConfig$Builder().setChannelId(2).setSignSecret("jm6j32avpdkfd1s3o5gnnqs9my5vuujco2zv37").setClientId(8).setChannelListener(this.f).setContext(this.b);
        if (ai.a(this.b) != 0) {
            z = true;
        }
        HuaXiSDK.getInstance().init(context.setNightMode(z).build());
    }

    public static void a(UserItem userItem) {
        RegisterModel registerModel = new RegisterModel();
        if (userItem != null) {
            registerModel = new RegisterModel();
            registerModel.setUserId(userItem.getId());
            registerModel.setUserName(userItem.getName());
        }
        HuaXiSDK.getInstance().syncLoginStatus(registerModel);
    }

    public static NativeBookStoreFragment b() {
        return new NativeBookStoreFragment();
    }

    public static void c() {
        HuaXiSDK.getInstance().loginOut();
    }

    public static void a(boolean z) {
        HuaXiSDK.getInstance().changeMode(z);
    }

    public static void b(Context context) {
        context.startActivity(new Intent(context, HomeActivity.class));
    }

    public static void c(Context context) {
        context.startActivity(new Intent(context, RecentReadActivity.class));
    }
}
