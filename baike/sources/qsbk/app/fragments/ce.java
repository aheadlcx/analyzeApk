package qsbk.app.fragments;

import qsbk.app.QsbkApp;
import qsbk.app.http.SimpleCallBack;
import qsbk.app.utils.ToastAndDialog;

class ce implements SimpleCallBack {
    final /* synthetic */ ContactQiuYouFragment a;

    ce(ContactQiuYouFragment contactQiuYouFragment) {
        this.a = contactQiuYouFragment;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onSuccess(org.json.JSONObject r6) {
        /*
        r5 = this;
        r2 = 1;
        r1 = 0;
        r0 = r5.a;
        r0 = r0.getActivity();
        if (r0 == 0) goto L_0x0016;
    L_0x000a:
        r0 = r5.a;
        r0 = r0.getActivity();
        r0 = r0.isFinishing();
        if (r0 == 0) goto L_0x0017;
    L_0x0016:
        return;
    L_0x0017:
        r0 = "has_more";
        r0 = r6.getInt(r0);	 Catch:{ JSONException -> 0x0106, Exception -> 0x00e2 }
        r3 = r5.a;	 Catch:{ JSONException -> 0x0106, Exception -> 0x00e2 }
        if (r0 != r2) goto L_0x008b;
    L_0x0021:
        r0 = r2;
    L_0x0022:
        r3.g = r0;	 Catch:{ JSONException -> 0x0106, Exception -> 0x00e2 }
    L_0x0024:
        r0 = "has_more";
        r0 = r6.getBoolean(r0);	 Catch:{ JSONException -> 0x0103, Exception -> 0x00e2 }
        r3 = r5.a;	 Catch:{ JSONException -> 0x0103, Exception -> 0x00e2 }
        r3.g = r0;	 Catch:{ JSONException -> 0x0103, Exception -> 0x00e2 }
    L_0x002e:
        r0 = r5.a;	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r3 = "total";
        r3 = r6.optInt(r3);	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r0.t = r3;	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r0 = r5.a;	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r0 = r0.f;	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        if (r2 != r0) goto L_0x005d;
    L_0x003f:
        r0 = r5.a;	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r0 = r0.i;	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r0.clear();	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r0 = r5.a;	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r0 = r0.k;	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        if (r0 == 0) goto L_0x005d;
    L_0x004c:
        r0 = r5.a;	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r0 = r0.k;	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r2 = r5.a;	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r2 = r2.t;	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r3 = r5.a;	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r3 = r3.j;	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r0.qiuYouNum(r2, r3);	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
    L_0x005d:
        r0 = "data";
        r2 = r6.getJSONArray(r0);	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r0 = r2.length();	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        if (r0 != 0) goto L_0x008d;
    L_0x0069:
        r0 = r5.a;	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r0.a();	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
    L_0x006e:
        r0 = r1;
    L_0x006f:
        r3 = r2.length();	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        if (r0 >= r3) goto L_0x00aa;
    L_0x0075:
        r3 = new qsbk.app.model.BaseUserInfo;	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r3.<init>();	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r4 = r2.getJSONObject(r0);	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r3.parseBaseInfo(r4);	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r4 = r5.a;	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r4 = r4.i;	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r4.add(r3);	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r0 = r0 + 1;
        goto L_0x006f;
    L_0x008b:
        r0 = r1;
        goto L_0x0022;
    L_0x008d:
        r0 = r5.a;	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r0 = r0.d;	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r0.hide();	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        goto L_0x006e;
    L_0x0095:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = qsbk.app.QsbkApp.mContext;
        r2 = "数据解析出错";
        r1 = java.lang.Integer.valueOf(r1);
        r0 = qsbk.app.utils.ToastAndDialog.makeNegativeToast(r0, r2, r1);
        r0.show();
        goto L_0x0016;
    L_0x00aa:
        r0 = r5.a;	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r2 = r0.f;	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r2 = r2 + 1;
        r0.f = r2;	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r0 = r5.a;	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r0 = r0.h;	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r0.notifyDataSetChanged();	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r0 = r5.a;	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r0 = r0.b;	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r0.refreshDone();	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r0 = r5.a;	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r0 = r0.i;	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r0 = r0.size();	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        if (r0 <= 0) goto L_0x00d2;
    L_0x00ca:
        r0 = r5.a;	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r0 = r0.b;	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r2 = 1;
        r0.loadMoreDone(r2);	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
    L_0x00d2:
        r0 = r5.a;	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r0 = r0.g;	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        if (r0 != 0) goto L_0x00f9;
    L_0x00d8:
        r0 = r5.a;	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r0 = r0.b;	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r2 = 0;
        r0.setLoadMoreEnable(r2);	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        goto L_0x0016;
    L_0x00e2:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = qsbk.app.QsbkApp.mContext;
        r2 = qsbk.app.utils.HttpClient.getLocalErrorStr();
        r1 = java.lang.Integer.valueOf(r1);
        r0 = qsbk.app.utils.ToastAndDialog.makeNegativeToast(r0, r2, r1);
        r0.show();
        goto L_0x0016;
    L_0x00f9:
        r0 = r5.a;	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r0 = r0.b;	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        r2 = 1;
        r0.setLoadMoreEnable(r2);	 Catch:{ JSONException -> 0x0095, Exception -> 0x00e2 }
        goto L_0x0016;
    L_0x0103:
        r0 = move-exception;
        goto L_0x002e;
    L_0x0106:
        r0 = move-exception;
        goto L_0x0024;
        */
        throw new UnsupportedOperationException("Method not decompiled: qsbk.app.fragments.ce.onSuccess(org.json.JSONObject):void");
    }

    public void onFailure(int i, String str) {
        ToastAndDialog.makeNegativeToast(QsbkApp.mContext, str, Integer.valueOf(0)).show();
    }
}
