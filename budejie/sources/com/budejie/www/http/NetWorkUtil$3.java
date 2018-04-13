package com.budejie.www.http;

import android.content.Context;
import android.os.AsyncTask;
import com.budejie.www.ad.AdManager;
import com.budejie.www.type.GetVipStatusResult;
import com.budejie.www.util.ai;
import com.budejie.www.util.z;
import net.tsz.afinal.a.a;

class NetWorkUtil$3 extends a<String> {
    final /* synthetic */ Context a;

    NetWorkUtil$3(Context context) {
        this.a = context;
    }

    public /* synthetic */ void onSuccess(Object obj) {
        a((String) obj);
    }

    public void a(String str) {
        super.onSuccess(str);
        new AsyncTask<String, String, GetVipStatusResult>(this) {
            final /* synthetic */ NetWorkUtil$3 a;

            {
                this.a = r1;
            }

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return a((String[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                a((GetVipStatusResult) obj);
            }

            protected void a(GetVipStatusResult getVipStatusResult) {
            }

            protected GetVipStatusResult a(String... strArr) {
                try {
                    GetVipStatusResult getVipStatusResult = (GetVipStatusResult) z.a(strArr[0], GetVipStatusResult.class);
                    if (getVipStatusResult != null) {
                        ai.b(this.a.a, getVipStatusResult.getIs_vip(), getVipStatusResult.token);
                        if (ai.c(this.a.a)) {
                            AdManager.closeAdAndUpdateList();
                        } else {
                            AdManager.openAd();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(new String[]{str});
    }
}
