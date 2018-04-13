package com.budejie.www.http;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import com.ali.auth.third.login.LoginConstants;
import com.alibaba.baichuan.android.trade.constants.AlibcConstants;
import com.alipay.sdk.util.h;
import com.budejie.www.R;
import com.budejie.www.R$styleable;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.HomeGroup;
import com.budejie.www.activity.MoreActivity;
import com.budejie.www.activity.OauthWeiboBaseAct;
import com.budejie.www.activity.OauthWeiboCompatActivity;
import com.budejie.www.activity.TipPopUp;
import com.budejie.www.activity.TipPopUp.TypeControl;
import com.budejie.www.activity.htmlpage.HtmlFeatureActivity;
import com.budejie.www.activity.htmlpage.c;
import com.budejie.www.activity.posts.PostsActivity;
import com.budejie.www.bean.HuodongBean;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.bean.TouGaoItem;
import com.budejie.www.c.m;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.util.an;
import com.budejie.www.util.au;
import com.budejie.www.util.ay;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import com.sina.weibo.sdk.a.j;
import com.sina.weibo.sdk.api.BaseMediaObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.auth.b;
import com.tencent.connect.common.Constants;
import com.umeng.analytics.MobclickAgent;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.HashMap;
import net.tsz.afinal.a.a;
import org.apache.http.message.BasicNameValuePair;

public class n {
    private static ListItemObject d;
    Activity a;
    m b;
    private j c = new j();

    public n(Activity activity) {
        this.a = activity;
        this.b = new m(activity);
    }

    public void a(b bVar, String str, int i, Handler handler) {
        BudejieApplication.a.a(this.a, "http://api.budejie.com/api/api_open.php", "post", this.c.a(this.a, bVar, str), null, handler, i);
    }

    public void a(String str, String str2, String str3, String str4, int i, Handler handler) {
        BudejieApplication.a.a(this.a, "http://api.budejie.com/api/api_open.php", "post", this.c.c(this.a, str4, str, str2, str3), null, handler, i);
    }

    public void a(String str, String str2, String str3, int i, Handler handler) {
        MobclickAgent.onEvent(this.a, "E03-A10", "绑定Qzone开始");
        BudejieApplication.a.a(this.a, "http://api.budejie.com/api/api_open.php", "post", this.c.d(this.a, str2, str3, str), null, handler, i);
    }

    public void a(Context context, String str, String str2, a<String> aVar) {
        MobclickAgent.onEvent(this.a, "E03-A10", "绑定weixin开始");
        NetWorkUtil netWorkUtil = BudejieApplication.a;
        RequstMethod requstMethod = RequstMethod.GET;
        String o = j.o();
        j jVar = new j();
        netWorkUtil.a(requstMethod, o, j.o(context, str, str2), (a) aVar);
    }

    public HashMap<String, String> a(String str) {
        HashMap<String, String> hashMap = new HashMap();
        if (!TextUtils.isEmpty(str)) {
            CharSequence a = this.b.a(str);
            String[] split = a.split(h.b);
            if (!(TextUtils.isEmpty(a) || split.length == 0 || "null".equals(a))) {
                for (int i = 0; i < split.length; i++) {
                    CharSequence charSequence = split[i];
                    if (!(TextUtils.isEmpty(charSequence) || "null".equals(charSequence))) {
                        String[] split2 = split[i].split(LoginConstants.EQUAL);
                        hashMap.put(split2[0], split2[1]);
                    }
                }
            }
        }
        return hashMap;
    }

    public void a(String str, String str2, String str3, String str4, String str5, Handler handler, int i, int i2, String str6) {
        net.tsz.afinal.a.b a = this.c.a(this.a, str5, str4, str, str2, str3, str6);
        Bundle bundle = new Bundle();
        bundle.putString("type", str6);
        bundle.putInt("notificationId", i2);
        BudejieApplication.a.a(this.a, "http://api.budejie.com/api/api_open.php", "post", a, null, handler, i, bundle);
    }

    public static void a(Context context, HuodongBean huodongBean) {
        if (huodongBean != null) {
            a(context, huodongBean.getContent(), huodongBean.getPicUrl(), huodongBean.getShareUrl());
        }
    }

    public static void a(Context context, ListItemObject listItemObject) {
        if (listItemObject != null) {
            d = listItemObject;
            String content = listItemObject.getContent();
            String imgUrl = listItemObject.getImgUrl();
            if (listItemObject.getRichObject() != null) {
                if (an.f(listItemObject.getRichObject().getImgUrl())) {
                    imgUrl = listItemObject.getRichObject().getImgUrl();
                }
                content = listItemObject.getRichObject().getTitle();
            }
            if ("1".equals(listItemObject.getIs_gif())) {
                imgUrl = listItemObject.getGifFistFrame();
            }
            if (Scheme.ofUri(imgUrl) == Scheme.FILE) {
                imgUrl = listItemObject.getCnd_img();
            }
            a(context, content, imgUrl, listItemObject.getWeixin_url());
        }
    }

    public static void a(Context context, String str, String str2, String str3) {
        Observable.just(Integer.valueOf(1)).map(new n$2(context, str, str2, str3)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new n$1());
    }

    private static void c(Context context, String str, String str2, String str3) {
        WeiboMultiMessage weiboMultiMessage = new WeiboMultiMessage();
        if (!TextUtils.isEmpty(str2) && str2.endsWith("_6.jpg")) {
            str2 = str2.replace("_6.jpg", "_2.jpg");
        }
        TextObject b = b(str);
        if (b != null) {
            weiboMultiMessage.textObject = b;
        }
        BaseMediaObject d = d(context, str, str2, str3);
        if (d != null) {
            weiboMultiMessage.mediaObject = d;
        }
        if (!weiboMultiMessage.checkArgs()) {
            au.a("分享文件过大");
        } else if (h(context)) {
            if (HomeGroup.A != null) {
                HomeGroup.A.a(weiboMultiMessage, false);
            }
        } else if (context instanceof OauthWeiboBaseAct) {
            if (((OauthWeiboBaseAct) context).mShareHandler != null) {
                ((OauthWeiboBaseAct) context).mShareHandler.a(weiboMultiMessage, false);
            }
        } else if (context instanceof OauthWeiboCompatActivity) {
            if (((OauthWeiboCompatActivity) context).d != null) {
                ((OauthWeiboCompatActivity) context).d.a(weiboMultiMessage, false);
            }
        } else if (context instanceof Activity) {
            com.sina.weibo.sdk.share.b bVar = new com.sina.weibo.sdk.share.b((Activity) context);
            bVar.a();
            bVar.a(weiboMultiMessage, false);
        }
    }

    private static boolean h(Context context) {
        return (context instanceof PostsActivity) && ("tag_essence".equals(((PostsActivity) context).d) || "tag_new".equals(((PostsActivity) context).d));
    }

    private static TextObject b(String str) {
        TextObject textObject = new TextObject();
        if (str.length() > 1024) {
            str = str.substring(0, 1024);
        }
        textObject.g = str;
        if (textObject.a()) {
            return textObject;
        }
        return null;
    }

    private static WebpageObject d(Context context, String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2)) {
            str2 = "http://img.spriteapp.cn/ws/img/budejie_logo.png";
        }
        if (TextUtils.isEmpty(str3)) {
            str3 = "http://www.budejie.com/budejie";
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        WebpageObject webpageObject = new WebpageObject();
        webpageObject.c = j.a();
        webpageObject.d = "@百思不得姐";
        if (str.length() > 1024) {
            str = str.substring(0, 1024);
        }
        webpageObject.e = str;
        Bitmap b = an.b(context, str2);
        if (b == null) {
            return null;
        }
        webpageObject.f = an.a(b, 30, true);
        webpageObject.a = str3;
        if (webpageObject.a()) {
            return webpageObject;
        }
        return null;
    }

    public void a(Context context, HuodongBean huodongBean, HashMap<String, String> hashMap, com.elves.update.a aVar, Handler handler) {
        String type = huodongBean.getType();
        String content = huodongBean.getContent();
        if (content.length() - 140 > 0) {
            huodongBean.setContent(content.substring(0, R$styleable.Theme_Custom_send_btn_text_color));
        }
        content = huodongBean.getPicUrl();
        if (!TextUtils.isEmpty(content) && content.endsWith("_6.jpg")) {
            huodongBean.setPicUrl(content.replace("_6.jpg", "_2.jpg"));
        }
        int currentTimeMillis = ((int) System.currentTimeMillis()) / 100;
        if (Constants.SOURCE_QZONE.equals(type)) {
            aVar.a(currentTimeMillis, context.getString(R.string.sharing));
        } else {
            aVar.a(currentTimeMillis, context.getString(R.string.weibo_sharing));
        }
        if (c.SHARE_PLATFORM_SINA.equals(type)) {
            a(huodongBean, (String) hashMap.get("weibo_token"), handler, 600, currentTimeMillis);
        } else if (c.SHARE_PLATFORM_TENCENT.equals(type)) {
            a(huodongBean, (String) hashMap.get("qq_token"), handler, 600, currentTimeMillis);
        } else if (c.SHARE_PLATFORM_QZONE.equals(type)) {
            huodongBean.setUid((String) hashMap.get("qzone_uid"));
            a(huodongBean, (String) hashMap.get("qzone_token"), handler, 600, currentTimeMillis);
        }
    }

    public void a(HuodongBean huodongBean, String str, Handler handler, int i, int i2) {
        String type = huodongBean.getType();
        net.tsz.afinal.a.b a = this.c.a(this.a, huodongBean, str);
        Bundle bundle = new Bundle();
        bundle.putString("shareType", type);
        bundle.putInt("notificationId", i2);
        BudejieApplication.a.a(this.a, "http://api.budejie.com/api/api_open.php", "post", a, null, handler, i, bundle);
    }

    public void a(ListItemObject listItemObject, String str, Handler handler, int i, int i2, String str2) {
        String type = listItemObject.getType();
        Bundle bundle = new Bundle();
        bundle.putString("shareType", type);
        bundle.putInt("notificationId", i2);
        BudejieApplication.a.a(this.a, "http://api.budejie.com/api/api_open.php", "post", this.c.a(this.a, str, listItemObject, str2), null, handler, i, bundle);
    }

    public void b(Context context, HuodongBean huodongBean, HashMap<String, String> hashMap, com.elves.update.a aVar, Handler handler) {
        String type = huodongBean.getType();
        String content = huodongBean.getContent();
        if (content.length() - 140 > 0) {
            huodongBean.setContent(content.substring(0, R$styleable.Theme_Custom_send_btn_text_color));
        }
        content = huodongBean.getPicUrl();
        if (!TextUtils.isEmpty(content) && content.endsWith("_6.jpg")) {
            huodongBean.setPicUrl(content.replace("_6.jpg", "_2.jpg"));
        }
        int currentTimeMillis = ((int) System.currentTimeMillis()) / 100;
        if (Constants.SOURCE_QZONE.equals(type)) {
            aVar.a(currentTimeMillis, context.getString(R.string.sharing));
        } else {
            aVar.a(currentTimeMillis, context.getString(R.string.weibo_sharing));
        }
        if (c.SHARE_PLATFORM_SINA.equals(type)) {
            b(huodongBean, (String) hashMap.get("weibo_token"), handler, 601, currentTimeMillis);
        } else if (c.SHARE_PLATFORM_TENCENT.equals(type)) {
            b(huodongBean, (String) hashMap.get("qq_token"), handler, 601, currentTimeMillis);
        } else if (c.SHARE_PLATFORM_QZONE.equals(type)) {
            huodongBean.setUid((String) hashMap.get("qzone_uid"));
            b(huodongBean, (String) hashMap.get("qzone_token"), handler, 601, currentTimeMillis);
        }
    }

    public void b(HuodongBean huodongBean, String str, Handler handler, int i, int i2) {
        String type = huodongBean.getType();
        Bundle bundle = new Bundle();
        bundle.putString("shareType", type);
        bundle.putInt("notificationId", i2);
        BudejieApplication.a.a(this.a, "http://api.budejie.com/api/api_open.php", "post", this.c.a(this.a, str, huodongBean), null, handler, i, bundle);
    }

    public void a(Context context, ListItemObject listItemObject, String str, String str2, HashMap<String, String> hashMap, com.elves.update.a aVar, Handler handler) {
        String title;
        String substring;
        String cnd_img;
        listItemObject.setForwardAndCollect_isweixin(false);
        String str3 = "";
        str3 = "";
        if (listItemObject.getRichObject() != null) {
            if (an.f(listItemObject.getRichObject().getImgUrl())) {
                str3 = listItemObject.getRichObject().getImgUrl();
            } else {
                str3 = ay.d;
            }
            title = listItemObject.getRichObject().getTitle();
        } else {
            str3 = listItemObject.getImgUrl();
            title = listItemObject.getContent();
        }
        if (title.length() - 140 > 0) {
            substring = title.substring(0, R$styleable.Theme_Custom_send_btn_text_color);
        } else {
            substring = title;
        }
        if (!TextUtils.isEmpty(str3) && str3.endsWith("_6.jpg")) {
            str3 = str3.replace("_6.jpg", "_2.jpg");
        }
        if (listItemObject.getIsDraftBean()) {
            str3 = "";
        }
        if (Scheme.ofUri(str3) == Scheme.FILE) {
            cnd_img = listItemObject.getCnd_img();
        } else {
            cnd_img = str3;
        }
        if (!listItemObject.getIfPP() && listItemObject.getWid() != null) {
            Handler n_3 = new n$3(this, context, listItemObject.getWid(), str, handler);
            String str4;
            String str5;
            String str6;
            if ("sina".equals(str)) {
                str4 = cnd_img;
                str5 = substring;
                str6 = str2;
                a(str4, listItemObject.getWid(), str5, str6, (String) hashMap.get("weibo_token"), n_3, 816, 0, str);
            } else if ("qq".equals(str)) {
                str4 = cnd_img;
                str5 = substring;
                str6 = str2;
                a(str4, listItemObject.getWid(), str5, str6, (String) hashMap.get("qq_token"), n_3, 816, 0, str);
            }
        } else if ("sina".equals(str)) {
            listItemObject.setType(c.SHARE_PLATFORM_SINA);
            r2 = listItemObject;
            r4 = handler;
            a(r2, (String) hashMap.get("weibo_token"), r4, 8160, 0, "Home");
        } else if ("qq".equals(str)) {
            listItemObject.setType(c.SHARE_PLATFORM_TENCENT);
            r2 = listItemObject;
            r4 = handler;
            a(r2, (String) hashMap.get("qq_token"), r4, 8160, 0, "Home");
        }
    }

    public void a(String str, String str2, String str3, String str4, Handler handler, int i, String str5, boolean z, String str6) {
        BudejieApplication.a.a(this.a, "http://api.budejie.com/api/api_open.php", "post", this.c.a(this.a, str3, str, str2, str5, str4, str6, z), null, handler, i);
    }

    public boolean a(Context context) {
        String string = context.getSharedPreferences("weiboprefer", 0).getString("id", "");
        if (string.equals("")) {
            return false;
        }
        HashMap a = a(string);
        if ("null".equals(a.get("weibo_uid")) || TextUtils.isEmpty((CharSequence) a.get("weibo_uid"))) {
            return false;
        }
        if (an.a(Long.parseLong(this.b.c(string)))) {
            return true;
        }
        return false;
    }

    public boolean b(Context context) {
        String string = context.getSharedPreferences("weiboprefer", 0).getString("id", "");
        if (string.equals("")) {
            return false;
        }
        HashMap a = a(string);
        return ("null".equals(a.get("qq_uid")) || TextUtils.isEmpty((CharSequence) a.get("qq_uid"))) ? false : true;
    }

    public boolean c(Context context) {
        String string = context.getSharedPreferences("weiboprefer", 0).getString("id", "");
        if (string.equals("")) {
            return false;
        }
        HashMap a = a(string);
        return a.containsKey("qzone_uid") && !TextUtils.isEmpty((CharSequence) a.get("qzone_uid"));
    }

    public boolean d(Context context) {
        String string = context.getSharedPreferences("weiboprefer", 0).getString("id", "");
        if (string.equals("")) {
            return false;
        }
        return this.b.b(string);
    }

    public void a(String str, String str2, Handler handler, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("c", "user"));
        arrayList.add(new BasicNameValuePair("a", "unbind"));
        arrayList.add(new BasicNameValuePair("type", str));
        arrayList.add(new BasicNameValuePair("client", AlibcConstants.PF_ANDROID));
        arrayList.add(new BasicNameValuePair("id", str2));
        BudejieApplication.a.a(this.a, "http://api.budejie.com/api/api_open.php", "post", this.c.b(this.a, str, str2), null, handler, i);
    }

    public void a(Context context, TouGaoItem touGaoItem, String str, String str2, HashMap<String, String> hashMap, com.elves.update.a aVar, Handler handler) {
        String substring;
        if (touGaoItem.getContent().length() - 140 > 0) {
            substring = touGaoItem.getContent().substring(0, R$styleable.Theme_Custom_send_btn_text_color);
        } else {
            substring = touGaoItem.getContent();
        }
        String imgUrl = touGaoItem.getImgUrl();
        if (!TextUtils.isEmpty(imgUrl) && imgUrl.endsWith("_6.jpg")) {
            imgUrl = imgUrl.replace("_6.jpg", "_2.jpg");
        }
        int currentTimeMillis = ((int) System.currentTimeMillis()) / 100;
        if (Constants.SOURCE_QZONE.equals(str)) {
            aVar.a(currentTimeMillis, context.getString(R.string.sharing));
        } else {
            aVar.a(currentTimeMillis, context.getString(R.string.weibo_sharing));
        }
        String str3;
        if ("sina".equals(str)) {
            str3 = str2;
            a(imgUrl, touGaoItem.getDataId(), substring, str3, (String) hashMap.get("weibo_token"), handler, 816, currentTimeMillis, str);
        } else if ("qq".equals(str)) {
            str3 = str2;
            a(imgUrl, touGaoItem.getDataId(), substring, str3, (String) hashMap.get("qq_token"), handler, 816, currentTimeMillis, str);
        } else if (Constants.SOURCE_QZONE.equals(str)) {
            a(imgUrl, touGaoItem.getDataId(), substring, (String) hashMap.get("qzone_uid"), (String) hashMap.get("qzone_token"), handler, 816, currentTimeMillis, str);
        }
    }

    public static void e(Context context) {
        f(context);
        g(context);
    }

    public static void f(Context context) {
        if ((context instanceof HtmlFeatureActivity) || (context instanceof MoreActivity)) {
            au.a((int) R.string.share_successed);
        } else if ((context instanceof OauthWeiboBaseAct) || (context instanceof HomeGroup) || (context instanceof OauthWeiboCompatActivity)) {
            TipPopUp.a(context, TypeControl.share, TypeControl.tiezi);
        } else {
            au.a((int) R.string.share_successed);
        }
    }

    public static void g(Context context) {
        if (context != null && d != null && !TextUtils.isEmpty(d.getWid())) {
            com.budejie.www.g.b.a(context, d.getWid(), "sina", "topic");
        }
    }
}
