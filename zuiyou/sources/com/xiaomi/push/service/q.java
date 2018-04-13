package com.xiaomi.push.service;

import android.accounts.Account;
import android.accounts.OnAccountsUpdateListener;
import android.text.TextUtils;

class q implements OnAccountsUpdateListener {
    final /* synthetic */ p a;

    q(p pVar) {
        this.a = pVar;
    }

    public void onAccountsUpdated(Account[] accountArr) {
        Object obj = null;
        if (accountArr != null && accountArr.length > 0) {
            for (Account account : accountArr) {
                if (account != null && TextUtils.equals("com.xiaomi", account.type)) {
                    break;
                }
            }
            Account account2 = null;
            if (!(account2 == null || TextUtils.isEmpty(account2.name))) {
                obj = 1;
            }
            boolean c = r.a(this.a.a).c();
            if (obj != null && !c) {
                r.a(this.a.a).a(account2.name);
                this.a.a(account2.name);
            } else if (obj == null && c) {
                r.a(this.a.a).a();
                this.a.a("0");
            } else if (obj != null && c && !TextUtils.equals(r.a(this.a.a).b(), account2.name)) {
                r.a(this.a.a).a(account2.name);
                this.a.a(account2.name);
            }
        }
    }
}
