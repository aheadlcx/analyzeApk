package com.xiaomi.metoknlp.devicediscover;

import android.os.AsyncTask;
import com.xiaomi.metoknlp.a.b;
import org.json.JSONException;
import org.json.JSONObject;

class k extends AsyncTask {
    final /* synthetic */ n a;
    private boolean b;

    private k(n nVar) {
        this.a = nVar;
        this.b = true;
    }

    private String b(String str) {
        String str2 = null;
        String a = b.a(str, str2);
        if (a != null) {
            try {
                str2 = new JSONObject(a).getString("real-ip");
            } catch (JSONException e) {
            }
        }
        return str2;
    }

    protected String a(String... strArr) {
        if (this.b) {
            try {
                return b(strArr[0]);
            } catch (Exception e) {
            }
        }
        return null;
    }

    protected void a(String str) {
        if (this.b) {
            this.a.j.sendMessage(this.a.j.obtainMessage(3, str));
        }
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((String[]) objArr);
    }

    protected void onCancelled() {
        this.b = false;
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((String) obj);
    }
}
