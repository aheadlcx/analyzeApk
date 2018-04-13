package com.bdj.picture.edit.network;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

public class b extends AsyncTask<String, Integer, Object> {
    protected boolean a = false;
    private Map<String, String> b = null;
    private a c;
    private String d;
    private int e;
    private boolean f;
    private ProgressDialog g;
    private Context h;
    private String i;
    private boolean j;
    private Map<String, Object> k = new HashMap();

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((String[]) objArr);
    }

    protected /* synthetic */ void onProgressUpdate(Object[] objArr) {
        a((Integer[]) objArr);
    }

    public b(String str, a aVar) {
        this.d = str;
        this.c = aVar;
        this.a = false;
    }

    public b a(boolean z, Context context, String str, boolean z2) {
        this.f = z;
        this.h = context;
        this.i = str;
        this.j = z2;
        return this;
    }

    public String getUrl() {
        return this.d;
    }

    public void a() {
        if (!isCancelled()) {
            cancel(false);
        }
    }

    private void c() {
        a();
    }

    private void d() {
        if (this.g == null && this.h != null) {
            this.g = new ProgressDialog(this.h);
            this.g.setProgressStyle(0);
            this.g.setCanceledOnTouchOutside(false);
            if (this.j) {
                this.g.setOnCancelListener(new b$1(this));
            }
        }
        CharSequence charSequence = this.i;
        if (charSequence == null) {
            charSequence = "数据加载中...";
        }
        this.g.setMessage(charSequence);
        this.g.show();
    }

    private void e() {
        if (this.g != null && this.g.isShowing()) {
            this.g.dismiss();
            this.g = null;
        }
    }

    protected void onPreExecute() {
        if (this.f) {
            d();
        }
    }

    protected Object a(String[] strArr) {
        if (isCancelled()) {
            return null;
        }
        if (this.a) {
            return b(this.d);
        }
        return a(this.d);
    }

    protected boolean a(HttpResponse httpResponse) {
        Header contentEncoding = httpResponse.getEntity().getContentEncoding();
        if (contentEncoding == null) {
            return false;
        }
        for (HeaderElement name : contentEncoding.getElements()) {
            if (name.getName().equalsIgnoreCase("gzip")) {
                return true;
            }
        }
        return false;
    }

    protected Object a(String str) {
        Object b_a = new b$a();
        b_a.c = false;
        try {
            HttpResponse execute = new DefaultHttpClient().execute(new HttpGet(str));
            if (isCancelled()) {
                return b_a;
            }
            Log.v("HttpRequestTask", "HTTP RETURN CODE:" + execute.getStatusLine().getStatusCode());
            this.e = execute.getStatusLine().getStatusCode();
            if (this.e == 200) {
                InputStream gZIPInputStream;
                InputStream content = execute.getEntity().getContent();
                if (a(execute)) {
                    Log.v("HttpRequestTask", "use gzip");
                    gZIPInputStream = new GZIPInputStream(content);
                } else {
                    Log.v("HttpRequestTask", "not use gzip");
                    gZIPInputStream = content;
                }
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(gZIPInputStream));
                StringBuffer stringBuffer = new StringBuffer("");
                String str2 = "";
                str2 = System.getProperty("line.separator");
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null || isCancelled()) {
                        bufferedReader.close();
                    } else {
                        stringBuffer.append(readLine + str2);
                    }
                }
                bufferedReader.close();
                if (isCancelled()) {
                    return null;
                }
                Log.v("HttpRequestTask", stringBuffer.toString());
                return a(str, stringBuffer);
            }
            b_a.d = "err http status code";
            b_a.a = -16777216 + this.e;
            return b_a;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("HttpRequestTask", "sendHttpRequest meet exception: " + e.getMessage());
            b_a.d = e.getMessage();
            b_a.a = -16777215;
            return b_a;
        }
    }

    protected Object b(String str) {
        Object b_a = new b$a();
        b_a.c = false;
        Log.d("HttpRequestTask", "Request is:" + str);
        try {
            HttpResponse execute = new DefaultHttpClient().execute(new HttpPost(str));
            if (isCancelled()) {
                return b_a;
            }
            Log.v("HttpRequestTask", "HTTP RETURN CODE:" + execute.getStatusLine().getStatusCode());
            if (execute.getStatusLine().getStatusCode() == 200) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(execute.getEntity().getContent()));
                StringBuffer stringBuffer = new StringBuffer("");
                String str2 = "";
                str2 = System.getProperty("line.separator");
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null || isCancelled()) {
                        break;
                    }
                    stringBuffer.append(readLine + str2);
                }
                bufferedReader.close();
                Log.v("HttpRequestTask", stringBuffer.toString());
                if (isCancelled()) {
                    return b_a;
                }
                return a(str, stringBuffer);
            }
            b_a.a = -2;
            b_a.d = "server issues";
            return b_a;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("HttpRequestTask", "sendHttpPostRequest meet exception: " + e.getMessage());
            b_a.a = -1;
            b_a.d = e.getMessage();
            return b_a;
        }
    }

    protected void onCancelled() {
        super.onCancelled();
        b();
    }

    protected void b() {
        if (this.c != null) {
            this.c.a(this);
        }
        e();
    }

    protected b$a a(String str, StringBuffer stringBuffer) {
        b$a b_a = new b$a();
        b_a.c = false;
        try {
            String stringBuffer2 = stringBuffer.toString();
            Log.d("HttpRequestTask", "url: " + str + ", \nresponse: " + stringBuffer2);
            JSONObject jSONObject = new JSONObject(stringBuffer2);
            b_a.c = true;
            b_a.a = 0;
            b_a.b = jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
            b_a.c = false;
            b_a.a = -3;
            b_a.d = "error response string";
        }
        return b_a;
    }

    protected void a(Integer... numArr) {
    }

    protected void onPostExecute(Object obj) {
        if (!isCancelled()) {
            b$a b_a = (b$a) obj;
            if (b_a != null) {
                if (b_a.c) {
                    if (this.c != null) {
                        this.c.a(b_a.b, this);
                    }
                } else if (this.c != null) {
                    this.c.a(b_a.a, b_a.d, b_a.b, this);
                }
            } else if (this.c != null) {
                this.c.a("networking issue!", this);
            }
            b();
        }
    }
}
