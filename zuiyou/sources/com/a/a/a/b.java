package com.a.a.a;

import android.content.Context;
import android.webkit.WebView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class b {
    public static String a(String str) {
        return str.replace("javascript:ZuiyouJSBridge.", "").replaceAll("\\(.*\\);", "");
    }

    public static String b(String str) {
        if (str.startsWith("zuiyou://return/_fetchQueue/")) {
            return str.replace("zuiyou://return/_fetchQueue/", "");
        }
        String[] split = str.replace("zuiyou://return/", "").split("/");
        if (split.length < 2) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < split.length; i++) {
            stringBuilder.append(split[i]);
        }
        return stringBuilder.toString();
    }

    public static String c(String str) {
        String[] split = str.replace("zuiyou://return/", "").split("/");
        if (split.length >= 1) {
            return split[0];
        }
        return null;
    }

    public static void a(WebView webView, String str) {
        webView.loadUrl("javascript:" + a(webView.getContext(), str));
    }

    public static String a(Context context, String str) {
        Exception e;
        Throwable th;
        String str2 = null;
        InputStream open;
        try {
            open = context.getAssets().open(str);
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(open));
                StringBuilder stringBuilder = new StringBuilder();
                String readLine;
                do {
                    readLine = bufferedReader.readLine();
                    if (!(readLine == null || readLine.matches("^\\s*\\/\\/.*"))) {
                        stringBuilder.append(readLine);
                        continue;
                    }
                } while (readLine != null);
                bufferedReader.close();
                open.close();
                str2 = stringBuilder.toString();
                if (open != null) {
                    try {
                        open.close();
                    } catch (IOException e2) {
                    }
                }
            } catch (Exception e3) {
                e = e3;
                try {
                    e.printStackTrace();
                    if (open != null) {
                        try {
                            open.close();
                        } catch (IOException e4) {
                        }
                    }
                    return str2;
                } catch (Throwable th2) {
                    th = th2;
                    if (open != null) {
                        try {
                            open.close();
                        } catch (IOException e5) {
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e6) {
            e = e6;
            open = null;
            e.printStackTrace();
            if (open != null) {
                open.close();
            }
            return str2;
        } catch (Throwable th3) {
            open = null;
            th = th3;
            if (open != null) {
                open.close();
            }
            throw th;
        }
        return str2;
    }
}
