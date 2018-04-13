package cn.tatagou.sdk.d;

import android.content.Context;
import android.os.AsyncTask;
import cn.tatagou.sdk.b.e;
import cn.tatagou.sdk.pojo.AppHomeData;
import cn.tatagou.sdk.pojo.RcmmParam;
import cn.tatagou.sdk.pojo.RcmmSpecial;
import cn.tatagou.sdk.pojo.Special;
import cn.tatagou.sdk.util.c;
import cn.tatagou.sdk.util.f;
import cn.tatagou.sdk.util.n;
import cn.tatagou.sdk.util.o;
import cn.tatagou.sdk.util.p;
import java.util.List;

public class a extends AsyncTask<String, Boolean, Boolean> {
    private Context a;
    private List<Special> b;
    private c c;

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((Boolean) obj);
    }

    public a(Context context, List<Special> list, c cVar) {
        this.a = context;
        this.b = list;
        this.c = cVar;
    }

    protected Boolean a(String... strArr) {
        boolean onRcmmSpecialCateSortDataReady;
        RcmmParam rcmmParam = AppHomeData.getInstance().getRcmmParam();
        if (!(rcmmParam == null || rcmmParam.getRcmmSpecials() == null)) {
            RcmmSpecial rcmmSpecials = rcmmParam.getRcmmSpecials();
            e.getInstance().delSpByTime(this.a, f.unixTS2TimeTamp(p.str2Int(rcmmSpecials.getNovelty() != null ? rcmmSpecials.getNovelty().getMaxDays() : null), "yyyy-MM-dd HH:mm:ss"));
            n.onUnRcmmSpDataReady(this.a, this.b);
            if (this.b.size() > 0) {
                onRcmmSpecialCateSortDataReady = o.onRcmmSpecialCateSortDataReady(this.b, rcmmSpecials);
                return Boolean.valueOf(onRcmmSpecialCateSortDataReady);
            }
        }
        onRcmmSpecialCateSortDataReady = false;
        return Boolean.valueOf(onRcmmSpecialCateSortDataReady);
    }

    protected void a(Boolean bool) {
        super.onPostExecute(bool);
        if (this.c != null) {
            this.c.onRcmmSpTaskExecuteResult(bool.booleanValue());
        }
    }
}
