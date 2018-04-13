package qsbk.app.activity;

import android.util.Pair;
import qsbk.app.core.AsyncTask;
import qsbk.app.nearby.api.NearbyEngine;
import qsbk.app.utils.ReadCircle;
import qsbk.app.utils.ReadQiushi;
import qsbk.app.utils.ReportArticle;
import qsbk.app.utils.ReportCommon;

class rq extends AsyncTask<String, Void, Pair<Integer, String>> {
    final /* synthetic */ MainActivity a;

    rq(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    protected Pair<Integer, String> a(String... strArr) {
        ReadQiushi.init();
        ReadCircle.init();
        ReportArticle.init();
        ReportArticle.reportHandler(false);
        ReportCommon.init();
        ReportCommon.reportHandler(false);
        NearbyEngine.instance().updateUserConfig(this.a);
        return null;
    }
}
