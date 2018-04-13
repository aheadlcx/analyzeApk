package cn.v6.sixrooms.hall;

import android.text.TextUtils;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.constants.CommonStrs;
import cn.v6.sixrooms.constants.UrlStrs;
import cn.v6.sixrooms.mvp.interfaces.V6Viewable;
import cn.v6.sixrooms.net.NetworkServiceSingleton;
import cn.v6.sixrooms.room.statistic.StatisticValue;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.SaveUserInfoUtils;
import cn.v6.sixrooms.utils.UrlUtils;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.message.BasicNameValuePair;

final class v {
    private t a;
    private V6Viewable b;

    v(V6Viewable v6Viewable) {
        this.b = v6Viewable;
    }

    final void a(String str, int i, boolean z) {
        if (this.b != null && i == 1 && z) {
            this.b.showLoading();
        }
        if (this.a == null) {
            this.a = new t(new w(this));
        }
        t tVar = this.a;
        String valueOf = String.valueOf(i);
        List arrayList = new ArrayList();
        BasicNameValuePair basicNameValuePair = new BasicNameValuePair("logiuid", GlobleValue.getUserBean() == null ? "" : GlobleValue.getUserBean().getId());
        BasicNameValuePair basicNameValuePair2 = new BasicNameValuePair("encpass", SaveUserInfoUtils.getEncpass(V6Coop.getInstance().getContext()));
        arrayList.add(basicNameValuePair);
        arrayList.add(basicNameValuePair2);
        List arrayList2 = new ArrayList();
        basicNameValuePair2 = new BasicNameValuePair("padapi", "coop-mobile-getlivelistlocation.php");
        BasicNameValuePair basicNameValuePair3 = new BasicNameValuePair("p", valueOf);
        BasicNameValuePair basicNameValuePair4 = new BasicNameValuePair("size", "20");
        BasicNameValuePair basicNameValuePair5 = new BasicNameValuePair("pid", str);
        arrayList2.add(basicNameValuePair2);
        arrayList2.add(basicNameValuePair4);
        arrayList2.add(basicNameValuePair3);
        arrayList2.add(basicNameValuePair5);
        int intValue = Integer.valueOf(valueOf).intValue() - 1;
        if (intValue <= 0) {
            intValue = 0;
        }
        if (!TextUtils.isEmpty(StatisticValue.getInstance().getTypeRecid(CommonStrs.TYPE_LOCATION, String.valueOf(intValue)))) {
            arrayList2.add(new BasicNameValuePair("recid", StatisticValue.getInstance().getTypeRecid(CommonStrs.TYPE_LOCATION, String.valueOf(intValue))));
        }
        NetworkServiceSingleton.createInstance().sendAsyncRequest(new u(tVar, valueOf), UrlUtils.getUrl(UrlStrs.URL_INDEX_INFO, arrayList2), arrayList);
    }
}
