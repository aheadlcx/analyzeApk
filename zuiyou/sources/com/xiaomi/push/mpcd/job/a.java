package com.xiaomi.push.mpcd.job;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.xiaomi.xmpush.thrift.d;

public class a extends f {
    public a(Context context, int i) {
        super(context, i);
    }

    public int a() {
        return 7;
    }

    public String b() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Account[] accounts = AccountManager.get(this.d).getAccounts();
            for (int i = 0; i < Math.min(accounts.length, 10); i++) {
                Account account = accounts[i];
                if (i > 0) {
                    stringBuilder.append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                }
                stringBuilder.append(account.name).append(",").append(account.type);
            }
        } catch (Throwable th) {
        }
        return stringBuilder.toString();
    }

    protected boolean c() {
        return this.d.getPackageManager().checkPermission("android.permission.GET_ACCOUNTS", this.d.getPackageName()) == 0;
    }

    public d d() {
        return d.Account;
    }
}
