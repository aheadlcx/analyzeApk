package com.umeng.analytics.social;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import org.json.JSONObject;

public abstract class UMSocialService {

    private static class a extends AsyncTask<Void, Void, c> {
        String a;
        String b;
        b c;
        UMPlatformData[] d;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((c) obj);
        }

        public a(String[] strArr, b bVar, UMPlatformData[] uMPlatformDataArr) {
            this.a = strArr[0];
            this.b = strArr[1];
            this.c = bVar;
            this.d = uMPlatformDataArr;
        }

        protected void onPreExecute() {
            if (this.c != null) {
                this.c.a();
            }
        }

        protected c a(Void... voidArr) {
            String a;
            if (TextUtils.isEmpty(this.b)) {
                a = b.a(this.a);
            } else {
                a = b.a(this.a, this.b);
            }
            try {
                JSONObject jSONObject = new JSONObject(a);
                int optInt = jSONObject.optInt("st");
                c cVar = new c(optInt == 0 ? d.s : optInt);
                String optString = jSONObject.optString("msg");
                if (!TextUtils.isEmpty(optString)) {
                    cVar.a(optString);
                }
                Object optString2 = jSONObject.optString("data");
                if (TextUtils.isEmpty(optString2)) {
                    return cVar;
                }
                cVar.b(optString2);
                return cVar;
            } catch (Exception e) {
                return new c(-99, e);
            }
        }

        protected void a(c cVar) {
            if (this.c != null) {
                this.c.a(cVar, this.d);
            }
        }
    }

    public interface b {
        void a();

        void a(c cVar, UMPlatformData... uMPlatformDataArr);
    }

    private static void a(Context context, b bVar, String str, UMPlatformData... uMPlatformDataArr) {
        int i = 0;
        if (uMPlatformDataArr != null) {
            try {
                int length = uMPlatformDataArr.length;
                while (i < length) {
                    if (uMPlatformDataArr[i].isValid()) {
                        i++;
                    } else {
                        throw new a("parameter is not valid.");
                    }
                }
            } catch (Exception e) {
                return;
            }
        }
        new a(e.a(context, str, uMPlatformDataArr), bVar, uMPlatformDataArr).execute(new Void[0]);
    }

    public static void share(Context context, String str, UMPlatformData... uMPlatformDataArr) {
        a(context, null, str, uMPlatformDataArr);
    }

    public static void share(Context context, UMPlatformData... uMPlatformDataArr) {
        a(context, null, null, uMPlatformDataArr);
    }
}
