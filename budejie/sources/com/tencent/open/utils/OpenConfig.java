package com.tencent.open.utils;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.SystemClock;
import com.tencent.connect.common.Constants;
import com.tencent.open.a.f;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class OpenConfig {
    private static Map<String, OpenConfig> a = Collections.synchronizedMap(new HashMap());
    private static String b = null;
    private Context c = null;
    private String d = null;
    private JSONObject e = null;
    private long f = 0;
    private int g = 0;
    private boolean h = true;

    public static OpenConfig getInstance(Context context, String str) {
        OpenConfig openConfig;
        synchronized (a) {
            f.a("openSDK_LOG.OpenConfig", "getInstance begin");
            if (str != null) {
                b = str;
            }
            if (str == null) {
                if (b != null) {
                    str = b;
                } else {
                    str = "0";
                }
            }
            openConfig = (OpenConfig) a.get(str);
            if (openConfig == null) {
                openConfig = new OpenConfig(context, str);
                a.put(str, openConfig);
            }
            f.a("openSDK_LOG.OpenConfig", "getInstance end");
        }
        return openConfig;
    }

    private OpenConfig(Context context, String str) {
        this.c = context.getApplicationContext();
        this.d = str;
        a();
        b();
    }

    private void a() {
        try {
            this.e = new JSONObject(a("com.tencent.open.config.json"));
        } catch (JSONException e) {
            this.e = new JSONObject();
        }
    }

    private String a(String str) {
        InputStream openFileInput;
        String str2 = "";
        try {
            String str3;
            if (this.d != null) {
                str3 = str + "." + this.d;
            } else {
                str3 = str;
            }
            openFileInput = this.c.openFileInput(str3);
        } catch (FileNotFoundException e) {
            try {
                openFileInput = this.c.getAssets().open(str);
            } catch (IOException e2) {
                e2.printStackTrace();
                return str2;
            }
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openFileInput, Charset.forName("UTF-8")));
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    stringBuffer.append(readLine);
                } else {
                    str2 = stringBuffer.toString();
                    try {
                        openFileInput.close();
                        bufferedReader.close();
                        return str2;
                    } catch (IOException e22) {
                        e22.printStackTrace();
                        return str2;
                    }
                }
            } catch (IOException e3) {
                e3.printStackTrace();
                try {
                    openFileInput.close();
                    bufferedReader.close();
                    return str2;
                } catch (IOException e222) {
                    e222.printStackTrace();
                    return str2;
                }
            } catch (Throwable th) {
                try {
                    openFileInput.close();
                    bufferedReader.close();
                } catch (IOException e2222) {
                    e2222.printStackTrace();
                }
                throw th;
            }
        }
    }

    private void a(String str, String str2) {
        try {
            if (this.d != null) {
                str = str + "." + this.d;
            }
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(this.c.openFileOutput(str, 0), Charset.forName("UTF-8"));
            outputStreamWriter.write(str2);
            outputStreamWriter.flush();
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void b() {
        if (this.g != 0) {
            b("update thread is running, return");
            return;
        }
        this.g = 1;
        final Bundle bundle = new Bundle();
        bundle.putString("appid", this.d);
        bundle.putString("appid_for_getting_config", this.d);
        bundle.putString("status_os", VERSION.RELEASE);
        bundle.putString("status_machine", Build.MODEL);
        bundle.putString("status_version", VERSION.SDK);
        bundle.putString("sdkv", Constants.SDK_VERSION);
        bundle.putString("sdkp", "a");
        new Thread(this) {
            final /* synthetic */ OpenConfig b;

            public void run() {
                try {
                    this.b.a(Util.parseJson(HttpUtils.openUrl2(this.b.c, "http://cgi.connect.qq.com/qqconnectopen/openapi/policy_conf", "GET", bundle).response));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.b.g = 0;
            }
        }.start();
    }

    private void a(JSONObject jSONObject) {
        b("cgi back, do update");
        this.e = jSONObject;
        a("com.tencent.open.config.json", jSONObject.toString());
        this.f = SystemClock.elapsedRealtime();
    }

    private void c() {
        int optInt = this.e.optInt("Common_frequency");
        if (optInt == 0) {
            optInt = 1;
        }
        if (SystemClock.elapsedRealtime() - this.f >= ((long) (optInt * 3600000))) {
            b();
        }
    }

    public int getInt(String str) {
        b("get " + str);
        c();
        return this.e.optInt(str);
    }

    public long getLong(String str) {
        b("get " + str);
        c();
        return this.e.optLong(str);
    }

    public boolean getBoolean(String str) {
        b("get " + str);
        c();
        Object opt = this.e.opt(str);
        if (opt == null) {
            return false;
        }
        if (opt instanceof Integer) {
            return !opt.equals(Integer.valueOf(0));
        } else if (opt instanceof Boolean) {
            return ((Boolean) opt).booleanValue();
        } else {
            return false;
        }
    }

    private void b(String str) {
        if (this.h) {
            f.a("openSDK_LOG.OpenConfig", str + "; appid: " + this.d);
        }
    }
}
