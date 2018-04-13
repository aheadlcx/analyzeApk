package cn.tatagou.sdk.d;

import android.os.AsyncTask;
import cn.tatagou.sdk.pojo.Special;
import cn.tatagou.sdk.util.c;
import cn.tatagou.sdk.util.n;
import java.util.List;

public class b extends AsyncTask<String, Integer, Boolean> {
    private List<Special> a;
    private List<Special> b;
    private List<Special> c;
    private c d;

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((Boolean) obj);
    }

    public b(List<Special> list, List<Special> list2, List<Special> list3, c cVar) {
        this.a = list;
        this.b = list2;
        this.c = list3;
        this.d = cVar;
    }

    protected Boolean a(String... strArr) {
        return Boolean.valueOf(n.onSpecialListDataReady(this.a, this.b, this.c, strArr[0], strArr[1]));
    }

    protected void a(Boolean bool) {
        super.onPostExecute(bool);
        if (this.d != null) {
            this.d.onNormalSpTaskExecutResult(bool.booleanValue());
        }
    }
}
