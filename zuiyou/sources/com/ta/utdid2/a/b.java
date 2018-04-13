package com.ta.utdid2.a;

import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import com.ta.utdid2.b.a.d;
import com.ta.utdid2.b.a.f;
import com.tencent.tauth.AuthActivity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public class b {
    private static final String TAG = b.class.getName();
    private static b a = null;
    /* renamed from: a */
    private Object f18a = new Object();
    private Context mContext;

    class a extends Thread {
        com.ut.device.a a;
        /* renamed from: a */
        String f19a = "";
        /* renamed from: a */
        HttpPost f20a;
        final /* synthetic */ b b;
        /* renamed from: b */
        String f21b;
        String c;
        String d = "";

        public a(b bVar, HttpPost httpPost) {
            this.b = bVar;
            this.f20a = httpPost;
        }

        public a(b bVar, HttpPost httpPost, com.ut.device.a aVar, String str, String str2, String str3) {
            this.b = bVar;
            this.f20a = httpPost;
            this.a = aVar;
            this.f21b = str;
            this.c = str2;
            this.d = str3;
        }

        public void run() {
            HttpResponse execute;
            BufferedReader bufferedReader = null;
            if (this.a != null) {
                this.a.a(1000, this.f21b);
            }
            try {
                execute = new DefaultHttpClient().execute(this.f20a);
            } catch (Exception e) {
                if (this.a != null) {
                    this.a.a(1002, this.f21b);
                }
                Log.e(b.TAG, e.toString());
                execute = null;
            }
            if (execute != null) {
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(execute.getEntity().getContent(), Charset.forName("UTF-8")));
                } catch (Exception e2) {
                    if (this.a != null) {
                        this.a.a(1002, this.f21b);
                    }
                    Log.e(b.TAG, e2.toString());
                }
            } else {
                Log.e(b.TAG, "response is null!");
            }
            String str = "";
            if (bufferedReader != null) {
                while (true) {
                    str = bufferedReader.readLine();
                    if (str == null) {
                        break;
                    }
                    try {
                        if (d.e) {
                            Log.d(b.TAG, str);
                        }
                        this.f19a = str;
                    } catch (Exception e22) {
                        if (this.a != null) {
                            this.a.a(1002, this.f21b);
                        }
                        Log.e(b.TAG, e22.toString());
                    }
                }
            } else {
                Log.e(b.TAG, "BufferredReader is null!");
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                    if (d.e) {
                        Log.d(b.TAG, "close the bufferreader");
                    }
                } catch (IOException e3) {
                    Log.e(b.TAG, e3.toString());
                }
            }
            if (this.a == null) {
                synchronized (this.b.mContext) {
                    this.b.mContext.notifyAll();
                }
                return;
            }
            str = b.a(this.f19a, this.f21b);
            this.a.a(1001, str);
            c.a(this.b.mContext, this.c, str, this.d);
        }

        public String b() {
            return this.f19a;
        }
    }

    public static synchronized b a(Context context) {
        b bVar;
        synchronized (b.class) {
            if (a == null) {
                a = new b(context);
            }
            bVar = a;
        }
        return bVar;
    }

    public b(Context context) {
        this.mContext = context;
    }

    public void a(String str, String str2, String str3, String str4, com.ut.device.a aVar) {
        String b = b(str, str2, str3, str4);
        if (d.e) {
            Log.d(TAG, "url:" + b + "; len:" + b.length());
        }
        new a(this, new HttpPost(b), aVar, str4, str, str2).start();
    }

    public String a(String str, String str2, String str3, String str4) {
        String str5 = "";
        String b = b(str, str2, str3, str4);
        int i = f.b(this.mContext) ? 3000 : 1000;
        if (d.e) {
            Log.d(TAG, "url:" + b + "; timeout:" + i);
        }
        a aVar = new a(this, new HttpPost(b));
        aVar.start();
        try {
            synchronized (this.f18a) {
                this.f18a.wait((long) i);
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        str5 = aVar.b();
        if (d.e) {
            Log.d(TAG, "mLine:" + str5);
        }
        return a(str5, str4);
    }

    private static String a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return str2;
        }
        String str3 = "";
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string;
            if (jSONObject.has("data")) {
                jSONObject = jSONObject.getJSONObject("data");
                if (!jSONObject.has(AuthActivity.ACTION_KEY) || !jSONObject.has("aid")) {
                    return str2;
                }
                string = jSONObject.getString(AuthActivity.ACTION_KEY);
                return (string.equalsIgnoreCase("new") || string.equalsIgnoreCase("changed")) ? jSONObject.getString("aid") : str2;
            } else if (!jSONObject.has("isError") || !jSONObject.has(NotificationCompat.CATEGORY_STATUS)) {
                return str2;
            } else {
                string = jSONObject.getString("isError");
                str3 = jSONObject.getString(NotificationCompat.CATEGORY_STATUS);
                if (!string.equalsIgnoreCase("true")) {
                    return str2;
                }
                if (!str3.equalsIgnoreCase("404") && !str3.equalsIgnoreCase("401")) {
                    return str2;
                }
                if (d.e) {
                    Log.d(TAG, "remove the AID, status:" + str3);
                }
                return "";
            }
        } catch (JSONException e) {
            Log.e(TAG, e.toString());
            return str2;
        } catch (Exception e2) {
            Log.e(TAG, e2.toString());
            return str2;
        }
    }

    private static String b(String str, String str2, String str3, String str4) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            str3 = URLEncoder.encode(str3, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return stringBuilder.append("http://hydra.alibaba.com/").append(str).append("/get_aid/").append("?").append("auth[token]=").append(str2).append("&type=").append("utdid").append("&id=").append(str3).append("&aid=").append(str4).toString();
    }
}
