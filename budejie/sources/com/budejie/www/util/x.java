package com.budejie.www.util;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.budejie.www.bean.Spider;
import com.budejie.www.bean.SpiderResult;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class x {
    private static x b;
    private SpiderResult a;

    public x(Activity activity) {
        try {
            this.a = (SpiderResult) z.a(t.a(activity, "spider_result_cache_file"), SpiderResult.class);
        } catch (Exception e) {
        }
        if (this.a == null) {
            this.a = (SpiderResult) z.a(an.c(activity, "spider.json"), SpiderResult.class);
        }
        aa.b("HtmlUtils", "spiderResult=" + this.a.toString());
    }

    public static x a(Activity activity) {
        if (b == null) {
            b = new x(activity);
        }
        return b;
    }

    public boolean a(String str) {
        if (!(this.a == null || this.a.spider == null)) {
            for (String str2 : this.a.spider.keySet()) {
                System.out.println("key= " + str2);
                if (str.contains(str2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Spider b(String str) {
        Spider spider = null;
        if (!(this.a == null || this.a.spider == null)) {
            for (String str2 : this.a.spider.keySet()) {
                Spider spider2;
                System.out.println("key= " + str2);
                if (str.contains(str2)) {
                    spider2 = (Spider) this.a.spider.get(str2);
                } else {
                    spider2 = spider;
                }
                spider = spider2;
            }
        }
        return spider;
    }

    public String a(Context context, String str) {
        String str2 = "";
        str2 = "";
        Spider b = b(str);
        if (!(b == null || TextUtils.isEmpty(b.title))) {
            str2 = a(c(str), b);
        }
        aa.b("HtmlUtils", "title=" + str2);
        return str2;
    }

    public ArrayList<String> b(Context context, String str) {
        ArrayList<String> b;
        Iterator it;
        String str2 = "";
        ArrayList<String> arrayList = new ArrayList();
        Spider b2 = b(str);
        if (b2 != null) {
            if (TextUtils.isEmpty(b2.videourl)) {
                arrayList.add(str);
            } else {
                b = b(c(str), b2);
                it = b.iterator();
                while (it.hasNext()) {
                    aa.b("HtmlUtils", "videoUrl=" + ((String) it.next()));
                }
                return b;
            }
        }
        b = arrayList;
        it = b.iterator();
        while (it.hasNext()) {
            aa.b("HtmlUtils", "videoUrl=" + ((String) it.next()));
        }
        return b;
    }

    public String c(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new URL(str).openStream(), "UTF-8"));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                stringBuffer.append(readLine);
            }
            bufferedReader.close();
        } catch (MalformedURLException e) {
            System.out.println("你输入的URL格式有问题！请仔细输入");
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return stringBuffer.toString();
    }

    public String a(String str, Spider spider) {
        String str2 = "";
        Matcher matcher = Pattern.compile(spider.title).matcher(str);
        while (matcher.find()) {
            str2 = matcher.group();
        }
        return d(str2);
    }

    public ArrayList<String> b(String str, Spider spider) {
        ArrayList<String> arrayList = new ArrayList();
        Matcher matcher = Pattern.compile(spider.videourl).matcher(str);
        while (matcher.find()) {
            System.out.println("videoUrl=" + matcher.group());
            arrayList.add(matcher.group());
        }
        return arrayList;
    }

    public String d(String str) {
        return str.replaceAll("<.*?>", "");
    }
}
