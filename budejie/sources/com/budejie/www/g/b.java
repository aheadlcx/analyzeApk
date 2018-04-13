package com.budejie.www.g;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.ClipboardManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.Toast;
import cn.v6.sixrooms.room.statistic.StatisticCodeTable;
import com.budejie.www.R;
import com.budejie.www.activity.AccountActivity;
import com.budejie.www.activity.BudejieApplication;
import com.budejie.www.activity.JokeToPictureActivity;
import com.budejie.www.activity.label.enumeration.CommonLabelOperator;
import com.budejie.www.activity.label.enumeration.CommonLabelOperator.CommonLabelAction;
import com.budejie.www.bean.HuodongBean;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.busevent.ShareEvent;
import com.budejie.www.c.h;
import com.budejie.www.c.m;
import com.budejie.www.http.NetWorkUtil.RequstMethod;
import com.budejie.www.http.j;
import com.budejie.www.http.n;
import com.budejie.www.util.an;
import com.budejie.www.util.ay;
import com.budejie.www.util.p;
import com.elves.update.DownloadServer;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import com.sina.weibo.sdk.auth.a.a;
import com.sina.weibo.sdk.auth.d;
import com.tencent.mm.sdk.constants.Build;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.tauth.Tencent;
import com.umeng.analytics.MobclickAgent;
import com.umeng.onlineconfig.OnlineConfigAgent;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.HashMap;

public class b {
    public static Activity a;
    private static int c = 0;
    public b$d b;
    private Activity d;
    private a e;
    private Tencent f;
    private Toast g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private d m;
    private h n;
    private com.budejie.www.c.d o;
    private SharedPreferences p;
    private Toast q;
    private String r;

    public static void a(Activity activity) {
        if (a != null && activity == a) {
            a = null;
        }
    }

    public b(Activity activity) {
        this.d = activity;
        a = activity;
    }

    public b(Activity activity, a aVar, Tencent tencent, d dVar) {
        this.d = activity;
        a = activity;
        this.e = aVar;
        this.f = tencent;
        this.m = dVar;
        this.n = new h(activity);
        this.o = new com.budejie.www.c.d(activity);
        this.h = OnlineConfigAgent.getInstance().getConfigParams(this.d, "分享标题_段子_QQ");
        this.i = OnlineConfigAgent.getInstance().getConfigParams(this.d, "分享标题_图片_QQ");
        this.j = OnlineConfigAgent.getInstance().getConfigParams(this.d, "分享标题_声音_QQ");
        this.k = OnlineConfigAgent.getInstance().getConfigParams(this.d, "分享标题_视频_QQ");
        this.l = OnlineConfigAgent.getInstance().getConfigParams(this.d, "分享标题_长文_QQ");
        if (TextUtils.isEmpty(this.h)) {
            this.h = "分享一条好玩的段子，点击查看";
        }
        if (TextUtils.isEmpty(this.i)) {
            this.i = "分享一张搞笑的图片，点击查看";
        }
        if (TextUtils.isEmpty(this.j)) {
            this.j = "分享一条有趣的语音，点击查看";
        }
        if (TextUtils.isEmpty(this.k)) {
            this.k = "分享一条有趣的视频，点击查看";
        }
        if (TextUtils.isEmpty(this.l)) {
            this.l = "分享一个链接，点击查看";
        }
        this.f = Tencent.createInstance("100336987", this.d);
    }

    public void onEventMainThread(ShareEvent shareEvent) {
        ListItemObject listItemObject = shareEvent.c;
        IWXAPI createWXAPI;
        switch (b$7.a[shareEvent.b.ordinal()]) {
            case 1:
                if (listItemObject == null && this.b != null) {
                    b(this.b.k, this.b.d, shareEvent.a);
                    return;
                }
                return;
            case 2:
                if (listItemObject == null) {
                    return;
                }
                if (!shareEvent.d) {
                    createWXAPI = WXAPIFactory.createWXAPI(this.d, "wx592fdc48acfbe290", true);
                    createWXAPI.registerApp("wx592fdc48acfbe290");
                    b(listItemObject, createWXAPI, null, null);
                    return;
                } else if (this.b != null) {
                    b(listItemObject, this.b.a, this.b.d, shareEvent.a);
                    return;
                } else {
                    return;
                }
            case 3:
                if (listItemObject == null) {
                    return;
                }
                if (!shareEvent.d) {
                    createWXAPI = WXAPIFactory.createWXAPI(this.d, "wx592fdc48acfbe290", true);
                    createWXAPI.registerApp("wx592fdc48acfbe290");
                    a(listItemObject, createWXAPI, null, null);
                    return;
                } else if (this.b != null) {
                    a(listItemObject, this.b.a, this.b.d, shareEvent.a);
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    public OnClickListener a(int i, Bundle bundle) {
        if (i == 2) {
            return new b$c(this, bundle);
        }
        if (i == 3) {
            return new b$a(this);
        }
        if (i == 7) {
            return new b$b(this, bundle);
        }
        return null;
    }

    public OnClickListener a(int i, Bundle bundle, Handler handler, IWXAPI iwxapi, m mVar, n nVar, com.elves.update.a aVar, SharedPreferences sharedPreferences, Handler handler2) {
        if (i != 5) {
            return null;
        }
        this.b = null;
        this.b = new b$d(this, this.d, iwxapi, bundle, handler, mVar, nVar, aVar, sharedPreferences, handler2);
        if (c == 0) {
            EventBus.getDefault().register(this);
        }
        c++;
        return this.b;
    }

    private void b(String str) {
        if (TextUtils.isEmpty(str)) {
            this.g = an.a(this.d, this.d.getString(R.string.operate_fail), -1);
            this.g.show();
        } else if (com.elves.update.d.a()) {
            String str2 = Environment.getExternalStorageDirectory().getPath() + "/elves/apk";
            String substring = str.substring(str.lastIndexOf("/") + 1);
            Intent intent = new Intent(this.d, DownloadServer.class);
            intent.putExtra("apkPath", str2);
            intent.putExtra("url", str);
            intent.putExtra("apkName", substring);
            this.d.startService(intent);
        } else {
            this.g = an.a(this.d, this.d.getString(R.string.sd_message), -1);
            this.g.show();
        }
    }

    private CommonLabelOperator a(ListItemObject listItemObject, CommonLabelAction commonLabelAction) {
        CommonLabelOperator commonLabelOperator = new CommonLabelOperator();
        commonLabelOperator.context = this.d;
        commonLabelOperator.data = listItemObject;
        commonLabelOperator.operatorAction = commonLabelAction;
        return commonLabelOperator;
    }

    private void a(int i, ListItemObject listItemObject) {
        Intent intent = new Intent(this.d, JokeToPictureActivity.class);
        intent.putExtra("joke_to_pic_position", i);
        intent.putExtra("joke_to_pic_post", listItemObject);
        this.d.startActivity(intent);
    }

    private boolean a() {
        if (an.a(this.d)) {
            return true;
        }
        this.g = an.a(this.d, this.d.getString(R.string.nonet), -1);
        this.g.show();
        return false;
    }

    private void a(String str, int i, SharedPreferences sharedPreferences) {
        if (i != -1) {
            sharedPreferences.edit().putInt("position", i).commit();
        }
        Intent intent = new Intent(this.d, AccountActivity.class);
        if ("sina".equals(str)) {
            MobclickAgent.onEvent(this.d, "weibo_bind", "sina_start");
            if (this.e == null) {
                this.e = new a(this.d);
            }
            this.e.a(this.m);
        } else if ("tenct".equals(str)) {
            MobclickAgent.onEvent(this.d, "weibo_bind", "tencent_start");
            intent.putExtra("title", this.d.getString(R.string.tencentweibo));
            intent.putExtra("weibo", "tenct");
            this.d.startActivityForResult(intent, 11111);
        }
    }

    private void a(Bundle bundle, String str, Handler handler, Object obj) {
        this.f.shareToQzone(this.d, bundle, new b$1(this, obj, str, handler));
    }

    public void a(String str, IWXAPI iwxapi, SharedPreferences sharedPreferences, Handler handler, ListItemObject listItemObject, HashMap<String, String> hashMap, m mVar, n nVar, Handler handler2, com.elves.update.a aVar) {
        boolean isWXAppInstalled = iwxapi.isWXAppInstalled();
        boolean z = iwxapi.getWXAppSupportAPI() >= Build.TIMELINE_SUPPORTED_SDK_INT;
        if (handler == null) {
            sharedPreferences.edit().putBoolean("isRecommend", true).commit();
        }
        p.a(null, this.d, isWXAppInstalled, z, sharedPreferences, new b$2(this, hashMap, nVar, listItemObject, sharedPreferences, str, aVar, handler2, iwxapi, handler), false, false);
    }

    public static void a(Context context, String str, String str2, String str3) {
        net.tsz.afinal.a.b g = j.g(context, str, str3, str2);
        BudejieApplication.a.a(RequstMethod.GET, j.a(str, str3, str2), g, new b$3());
    }

    public void a(SharedPreferences sharedPreferences, ListItemObject listItemObject, com.elves.update.a aVar, Handler handler, n nVar, HashMap<String, String> hashMap) {
        if (an.a(sharedPreferences)) {
            String uid = listItemObject.getUid();
            HashMap a = nVar.a(uid);
            if (TextUtils.isEmpty((CharSequence) a.get("qq_uid")) || "null".equals(a.get("qq_uid"))) {
                a("tenct", -1, sharedPreferences);
                return;
            }
            nVar.a(this.d, listItemObject, "qq", uid, a, aVar, handler);
            return;
        }
        a("tenct", -1, sharedPreferences);
    }

    public void a(ListItemObject listItemObject, IWXAPI iwxapi, Handler handler) {
        a(listItemObject, iwxapi, handler, "");
    }

    public void b(ListItemObject listItemObject, IWXAPI iwxapi, Handler handler) {
        b(listItemObject, iwxapi, handler, "");
    }

    public void a(ListItemObject listItemObject, IWXAPI iwxapi, Handler handler, String str) {
        Log.d("WeiXinTools", "shareToWxGroup");
        if (handler != null) {
            handler.sendEmptyMessage(91);
        }
        ay.a(this.d, listItemObject, 1, iwxapi, false, this.p, str);
    }

    public void b(ListItemObject listItemObject, IWXAPI iwxapi, Handler handler, String str) {
        if (iwxapi.isWXAppSupportAPI()) {
            if (handler != null) {
                handler.sendEmptyMessage(91);
            }
            ay.a(this.d, listItemObject, 0, iwxapi, true, this.p, str);
            return;
        }
        this.g = an.a(this.d, "微信版本过低，暂不支持发送消息", -1);
        this.g.show();
    }

    public void a(ListItemObject listItemObject, Handler handler) {
        a(listItemObject, handler, "");
    }

    public void a(ListItemObject listItemObject, Handler handler, String str) {
        Bundle bundle = new Bundle();
        String weixin_url = listItemObject.getWeixin_url();
        if (TextUtils.isEmpty(weixin_url)) {
            weixin_url = "http://www.budejie.com/budejie";
        }
        ArrayList arrayList = new ArrayList();
        if (listItemObject.getIfPP()) {
            bundle.putString("title", "分享用户");
            bundle.putString("summary", listItemObject.getContent());
            if (TextUtils.isEmpty(listItemObject.getImgUrl())) {
                arrayList.add("http://img.spriteapp.cn/ws/img/budejie_logo.png");
            } else if (Scheme.ofUri(listItemObject.getImgUrl()) == Scheme.FILE) {
                arrayList.add(listItemObject.getCnd_img());
            } else {
                arrayList.add(listItemObject.getImgUrl());
            }
            bundle.putStringArrayList("imageUrl", arrayList);
            bundle.putString("targetUrl", weixin_url);
        } else {
            if (weixin_url.contains("?")) {
                bundle.putString("targetUrl", weixin_url + "&f=qq&d=android");
            } else {
                bundle.putString("targetUrl", weixin_url + "?f=qq&d=android");
            }
            if (TextUtils.isEmpty(listItemObject.getWid())) {
                bundle.putInt("req_type", 1);
                weixin_url = OnlineConfigAgent.getInstance().getConfigParams(this.d, "微信、微信朋友圈-推荐朋友-标题");
                if (TextUtils.isEmpty(weixin_url)) {
                    weixin_url = "推荐一个搞笑的应用";
                }
                bundle.putString("title", weixin_url);
                bundle.putString("summary", listItemObject.getContent());
                arrayList.add("http://img.spriteapp.cn/ws/img/budejie_logo.png");
                bundle.putStringArrayList("imageUrl", arrayList);
            } else if (listItemObject.getRichObject() != null) {
                bundle.putInt("req_type", 2);
                if (TextUtils.isEmpty(OnlineConfigAgent.getInstance().getConfigParams(this.d, "分享标题_长文_Qzone"))) {
                    weixin_url = "我分享了一个链接，点击查看";
                }
                bundle.putString("title", listItemObject.getRichObject().getTitle());
                bundle.putString("summary", listItemObject.getRichObject().getDesc());
                if (an.f(listItemObject.getRichObject().getImgUrl())) {
                    arrayList.add(listItemObject.getRichObject().getImgUrl());
                } else {
                    arrayList.add(ay.d);
                }
                bundle.putStringArrayList("imageUrl", arrayList);
            } else if (!TextUtils.isEmpty(listItemObject.getVoiceUri())) {
                bundle.putInt("req_type", 2);
                weixin_url = OnlineConfigAgent.getInstance().getConfigParams(this.d, "分享标题_声音_Qzone");
                if (TextUtils.isEmpty(weixin_url)) {
                    weixin_url = "我分享了一段语音，点击收听";
                }
                bundle.putString("title", weixin_url);
                bundle.putString("summary", listItemObject.getContent());
                bundle.putString("audio_url", listItemObject.getVoiceUri());
                if (TextUtils.isEmpty(listItemObject.getImgUrl())) {
                    arrayList.add("http://img.spriteapp.cn/ws/img/budejie_logo.png");
                } else if (Scheme.ofUri(listItemObject.getImgUrl()) == Scheme.FILE) {
                    arrayList.add(listItemObject.getCnd_img());
                } else {
                    arrayList.add(listItemObject.getImgUrl());
                }
                bundle.putStringArrayList("imageUrl", arrayList);
            } else if (!TextUtils.isEmpty(listItemObject.getVideouri())) {
                bundle.putInt("req_type", 2);
                weixin_url = OnlineConfigAgent.getInstance().getConfigParams(this.d, "分享标题_视频_Qzone");
                if (TextUtils.isEmpty(weixin_url)) {
                    weixin_url = "我分享了一段视频，点击查看";
                }
                bundle.putString("title", weixin_url);
                bundle.putString("summary", listItemObject.getContent());
                bundle.putString("audio_url", listItemObject.getVideouri());
                if (TextUtils.isEmpty(listItemObject.getImgUrl())) {
                    arrayList.add("http://img.spriteapp.cn/ws/img/budejie_logo.png");
                } else if (Scheme.ofUri(listItemObject.getImgUrl()) == Scheme.FILE) {
                    arrayList.add(listItemObject.getCnd_img());
                } else {
                    arrayList.add(listItemObject.getImgUrl());
                }
                bundle.putStringArrayList("imageUrl", arrayList);
            } else if (TextUtils.isEmpty(listItemObject.getImgUrl()) || listItemObject.getHeight() == 0) {
                bundle.putInt("req_type", 1);
                weixin_url = OnlineConfigAgent.getInstance().getConfigParams(this.d, "分享标题_段子_Qzone");
                if (TextUtils.isEmpty(weixin_url)) {
                    weixin_url = "我分享了一条段子，点击查看";
                }
                bundle.putString("title", weixin_url);
                bundle.putString("summary", listItemObject.getContent());
                arrayList.add("http://img.spriteapp.cn/ws/img/budejie_logo.png");
                bundle.putStringArrayList("imageUrl", arrayList);
            } else {
                bundle.putInt("req_type", 1);
                weixin_url = OnlineConfigAgent.getInstance().getConfigParams(this.d, "分享标题_图片_Qzone");
                if (TextUtils.isEmpty(weixin_url)) {
                    weixin_url = "我分享了一张图片，点击查看";
                }
                bundle.putString("title", weixin_url);
                bundle.putString("summary", listItemObject.getContent());
                if (TextUtils.isEmpty(listItemObject.getImgUrl())) {
                    arrayList.add("http://img.spriteapp.cn/ws/img/budejie_logo.png");
                } else if (Scheme.ofUri(listItemObject.getImgUrl()) == Scheme.FILE) {
                    arrayList.add(listItemObject.getCnd_img());
                } else {
                    arrayList.add(listItemObject.getImgUrl());
                }
                bundle.putStringArrayList("imageUrl", arrayList);
            }
        }
        bundle.putString("appName", this.d.getString(R.string.app_name));
        a(bundle, listItemObject.getWid(), handler, (Object) listItemObject);
    }

    public void b(ListItemObject listItemObject, Handler handler) {
        b(listItemObject, handler, "");
    }

    public void b(ListItemObject listItemObject, Handler handler, String str) {
        Bundle bundle = new Bundle();
        if (TextUtils.isEmpty(str)) {
            String weixin_url = listItemObject.getWeixin_url();
            if (TextUtils.isEmpty(weixin_url)) {
                weixin_url = "http://www.budejie.com/budejie";
            }
            if (listItemObject.getIfPP()) {
                bundle.putString("title", "分享用户");
                bundle.putString("summary", listItemObject.getContent());
                String imgUrl = listItemObject.getImgUrl();
                if (Scheme.ofUri(imgUrl) == Scheme.FILE) {
                    imgUrl = listItemObject.getCnd_img();
                }
                bundle.putString("imageUrl", imgUrl);
                bundle.putString("targetUrl", weixin_url);
            } else {
                if (weixin_url.contains("?")) {
                    bundle.putString("targetUrl", weixin_url + "&f=qq&d=android");
                } else {
                    bundle.putString("targetUrl", weixin_url + "?f=qq&d=android");
                }
                if (TextUtils.isEmpty(listItemObject.getWid())) {
                    weixin_url = OnlineConfigAgent.getInstance().getConfigParams(this.d, "微信、微信朋友圈-推荐朋友-标题");
                    if (TextUtils.isEmpty(weixin_url)) {
                        weixin_url = "推荐一个搞笑的应用";
                    }
                    bundle.putString("title", weixin_url);
                    bundle.putString("imageUrl", "http://img.spriteapp.cn/ws/img/budejie_logo.png");
                    bundle.putString("summary", listItemObject.getContent());
                } else if (listItemObject.getRichObject() != null) {
                    bundle.putString("title", listItemObject.getRichObject().getTitle());
                    bundle.putString("summary", listItemObject.getRichObject().getDesc());
                    if (an.f(listItemObject.getRichObject().getImgUrl())) {
                        bundle.putString("imageUrl", listItemObject.getRichObject().getImgUrl());
                    } else {
                        bundle.putString("imageUrl", ay.d);
                    }
                } else if (TextUtils.isEmpty(listItemObject.getImgUrl()) || listItemObject.getHeight() == 0) {
                    bundle.putInt("req_type", 5);
                    bundle.putString("imageLocalUrl", str);
                    bundle.putInt("cflag", 2);
                } else if (!TextUtils.isEmpty(listItemObject.getImgUrl()) && listItemObject.getHeight() != 0 && TextUtils.isEmpty(listItemObject.getVoiceUri()) && TextUtils.isEmpty(listItemObject.getVideouri())) {
                    bundle.putString("title", this.i);
                    weixin_url = listItemObject.getImgUrl();
                    if (Scheme.ofUri(weixin_url) == Scheme.FILE) {
                        weixin_url = listItemObject.getCnd_img();
                    }
                    bundle.putString("imageUrl", weixin_url);
                    bundle.putString("summary", listItemObject.getContent());
                } else if (!TextUtils.isEmpty(listItemObject.getVoiceUri())) {
                    bundle.putString("title", this.j);
                    bundle.putString("summary", listItemObject.getContent());
                    weixin_url = listItemObject.getImgUrl();
                    if (Scheme.ofUri(weixin_url) == Scheme.FILE) {
                        weixin_url = listItemObject.getCnd_img();
                    }
                    bundle.putString("imageUrl", weixin_url);
                } else if (!TextUtils.isEmpty(listItemObject.getVideouri())) {
                    bundle.putString("title", this.k);
                    bundle.putString("summary", listItemObject.getContent());
                    weixin_url = listItemObject.getImgUrl();
                    if (Scheme.ofUri(weixin_url) == Scheme.FILE) {
                        weixin_url = listItemObject.getCnd_img();
                    }
                    bundle.putString("imageUrl", weixin_url);
                }
            }
        } else {
            bundle.putInt("req_type", 5);
            bundle.putString("imageLocalUrl", str);
            bundle.putInt("cflag", 2);
        }
        bundle.putString("appName", "百思不得姐");
        bundle.putString("site", "百思不得姐100336987");
        this.f.shareToQQ(this.d, bundle, new b$4(this, listItemObject, handler));
    }

    public void a(ListItemObject listItemObject, SharedPreferences sharedPreferences) {
        if (listItemObject.getIfPP()) {
            a(this.d, listItemObject.getWid(), "sms", StatisticCodeTable.PROFILE);
        } else if (TextUtils.isEmpty(listItemObject.getWid())) {
            a(this.d, listItemObject.getWid(), "sms", "app");
        } else {
            a(this.d, listItemObject.getWid(), "sms", "topic");
        }
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:"));
        String str = "";
        if (sharedPreferences.getBoolean("isRecommend", false)) {
            str = listItemObject.getContent() + "?f=sms&d=android";
            sharedPreferences.edit().putBoolean("isRecommend", false).commit();
        } else {
            String wid = listItemObject.getWid();
            String content = listItemObject.getContent();
            this.r = OnlineConfigAgent.getInstance().getConfigParams(this.d, "sms_title");
            if (TextUtils.isEmpty(this.r)) {
                this.r = "分享自#百思不得姐#";
            }
            str = listItemObject.getWeixin_url();
            if (!TextUtils.isEmpty(str)) {
                if (str.contains("?")) {
                    str = str + "&f=qq&d=android";
                } else {
                    str = str + "?f=qq&d=android";
                }
            }
            if ((listItemObject.getType() != null && listItemObject.getType().equals("29")) || wid == null) {
                str = "";
            }
            str = this.r + content + str;
        }
        intent.putExtra("sms_body", str);
        this.d.startActivity(intent);
    }

    public void b(ListItemObject listItemObject, SharedPreferences sharedPreferences) {
        CharSequence charSequence;
        ClipboardManager clipboardManager = (ClipboardManager) this.d.getSystemService("clipboard");
        String str = "";
        if (sharedPreferences.getBoolean("isRecommend", false)) {
            charSequence = listItemObject.getContent() + "?f=sms&d=android";
            sharedPreferences.edit().putBoolean("isRecommend", false).commit();
        } else {
            String wid = listItemObject.getWid();
            String content = listItemObject.getContent();
            this.r = OnlineConfigAgent.getInstance().getConfigParams(this.d, "sms_title");
            if (TextUtils.isEmpty(this.r)) {
                this.r = "分享自#百思不得姐#";
            }
            str = listItemObject.getWeixin_url();
            if (!TextUtils.isEmpty(str)) {
                if (str.contains("?")) {
                    str = str + "&f=qq&d=android";
                } else {
                    str = str + "?f=qq&d=android";
                }
            }
            if ((listItemObject.getType() != null && listItemObject.getType().equals("29")) || wid == null) {
                str = "";
            }
            charSequence = this.r + content + str;
        }
        clipboardManager.setText(charSequence);
        this.g = an.a(this.d, this.d.getString(R.string.shareToClipboard), -1);
        this.g.show();
    }

    public void a(SharedPreferences sharedPreferences, HuodongBean huodongBean, com.elves.update.a aVar, Handler handler, n nVar, HashMap<String, String> hashMap) {
        if (an.a(sharedPreferences)) {
            HashMap a = nVar.a(huodongBean.getUid());
            if (TextUtils.isEmpty((CharSequence) a.get("qq_uid")) || "null".equals(a.get("qq_uid"))) {
                a("tenct", -1, sharedPreferences);
                return;
            }
            nVar.a(this.d, huodongBean, a, aVar, handler);
            return;
        }
        a("tenct", -1, sharedPreferences);
    }

    public void a(SharedPreferences sharedPreferences, HuodongBean huodongBean, IWXAPI iwxapi) {
        if (iwxapi.isWXAppSupportAPI()) {
            ay.a(this.d, huodongBean, 0, iwxapi, true, sharedPreferences);
            return;
        }
        this.q = an.a(this.d, this.d.getString(R.string.wx_low_version), -1);
        this.q.show();
    }

    public void b(SharedPreferences sharedPreferences, HuodongBean huodongBean, IWXAPI iwxapi) {
        ay.a(this.d, huodongBean, 1, iwxapi, false, sharedPreferences);
    }

    public void a(HuodongBean huodongBean, Handler handler) {
        Bundle bundle = new Bundle();
        String str = "http://www.budejie.com/budejie";
        if (!TextUtils.isEmpty(huodongBean.getShareUrl())) {
            str = huodongBean.getShareUrl();
        }
        bundle.putString("targetUrl", str);
        if (TextUtils.isEmpty(huodongBean.getPicUrl())) {
            str = this.h;
            if (!TextUtils.isEmpty(huodongBean.getTitle())) {
                str = huodongBean.getTitle();
            }
            bundle.putString("title", str);
            bundle.putString("imageUrl", "http://img.spriteapp.cn/ws/img/budejie_logo.png");
            bundle.putString("summary", huodongBean.getContent());
        } else if (!TextUtils.isEmpty(huodongBean.getPicUrl()) && TextUtils.isEmpty(huodongBean.getVoiceUrl())) {
            str = this.i;
            if (!TextUtils.isEmpty(huodongBean.getTitle())) {
                str = huodongBean.getTitle();
            }
            bundle.putString("title", str);
            bundle.putString("imageUrl", huodongBean.getPicUrl());
            bundle.putString("summary", huodongBean.getContent());
        } else if (!TextUtils.isEmpty(huodongBean.getVoiceUrl())) {
            str = this.j;
            if (!TextUtils.isEmpty(huodongBean.getTitle())) {
                str = huodongBean.getTitle();
            }
            bundle.putString("title", str);
            bundle.putString("summary", huodongBean.getContent());
            bundle.putString("imageUrl", huodongBean.getPicUrl());
        }
        bundle.putString("appName", "百思不得姐");
        bundle.putString("site", "百思不得姐100336987");
        this.f.shareToQQ(this.d, bundle, new b$5(this, handler, huodongBean));
    }

    public void b(HuodongBean huodongBean, Handler handler) {
        Bundle bundle = new Bundle();
        String str = "http://www.budejie.com/budejie";
        if (!TextUtils.isEmpty(huodongBean.getShareUrl())) {
            str = huodongBean.getShareUrl();
        }
        bundle.putString("targetUrl", str);
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(huodongBean.getVoiceUrl())) {
            bundle.putInt("req_type", 2);
            str = this.j;
            if (!TextUtils.isEmpty(huodongBean.getTitle())) {
                str = huodongBean.getTitle();
            }
            bundle.putString("title", str);
            bundle.putString("summary", huodongBean.getContent());
            bundle.putString("audio_url", huodongBean.getVoiceUrl());
            if (TextUtils.isEmpty(huodongBean.getPicUrl())) {
                arrayList.add("http://img.spriteapp.cn/ws/img/budejie_logo.png");
            } else {
                arrayList.add(huodongBean.getPicUrl());
            }
            bundle.putStringArrayList("imageUrl", arrayList);
        } else if (TextUtils.isEmpty(huodongBean.getPicUrl())) {
            bundle.putInt("req_type", 1);
            str = this.h;
            if (!TextUtils.isEmpty(huodongBean.getTitle())) {
                str = huodongBean.getTitle();
            }
            bundle.putString("title", str);
            bundle.putString("summary", huodongBean.getContent());
            arrayList.add("http://img.spriteapp.cn/ws/img/budejie_logo.png");
            bundle.putStringArrayList("imageUrl", arrayList);
        } else {
            str = this.i;
            if (!TextUtils.isEmpty(huodongBean.getTitle())) {
                str = huodongBean.getTitle();
            }
            bundle.putInt("req_type", 1);
            bundle.putString("title", str);
            bundle.putString("summary", huodongBean.getContent());
            if (TextUtils.isEmpty(huodongBean.getPicUrl())) {
                arrayList.add("http://img.spriteapp.cn/ws/img/budejie_logo.png");
            } else {
                arrayList.add(huodongBean.getPicUrl());
            }
            bundle.putStringArrayList("imageUrl", arrayList);
        }
        bundle.putString("appName", this.d.getString(R.string.app_name));
        a(bundle, huodongBean.getHuodongId(), handler, (Object) huodongBean);
    }

    public void a(HuodongBean huodongBean, SharedPreferences sharedPreferences) {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:"));
        String str = "";
        if (sharedPreferences.getBoolean("isRecommend", false)) {
            str = huodongBean.getContent() + "?f=sms&d=android";
            sharedPreferences.edit().putBoolean("isRecommend", false).commit();
        } else {
            str = huodongBean.getContent();
            this.r = OnlineConfigAgent.getInstance().getConfigParams(this.d, "sms_title");
            if (TextUtils.isEmpty(this.r)) {
                this.r = "分享自#百思不得姐#";
            }
            if (TextUtils.isEmpty(huodongBean.getShareUrl())) {
                str = this.r + str + "http://www.budejie.com/budejie/land.php?pid=" + huodongBean.getHuodongId() + "&f=sms&d=android";
            } else {
                str = this.r + str + huodongBean.getShareUrl() + "&f=sms&d=android";
            }
        }
        intent.putExtra("sms_body", str);
        this.d.startActivity(intent);
    }

    public void b(HuodongBean huodongBean, SharedPreferences sharedPreferences) {
        CharSequence charSequence;
        ClipboardManager clipboardManager = (ClipboardManager) this.d.getSystemService("clipboard");
        String str = "";
        if (sharedPreferences.getBoolean("isRecommend", false)) {
            charSequence = huodongBean.getContent() + "?f=sms&d=android";
            sharedPreferences.edit().putBoolean("isRecommend", false).commit();
        } else {
            str = huodongBean.getContent();
            this.r = OnlineConfigAgent.getInstance().getConfigParams(this.d, "sms_title");
            if (TextUtils.isEmpty(this.r)) {
                this.r = "分享自#百思不得姐#";
            }
            charSequence = this.r + str + "http://www.budejie.com/budejie/land.php?pid=" + huodongBean.getHuodongId() + "&f=sms&d=android";
        }
        clipboardManager.setText(charSequence);
        this.g = an.a(this.d, this.d.getString(R.string.shareToClipboard), -1);
        this.g.show();
    }

    public void a(String str) {
        Intent intent = new Intent("action.share.huodong.success");
        intent.putExtra("shareType", str);
        this.d.sendBroadcast(intent);
    }

    public void a(SharedPreferences sharedPreferences, HuodongBean huodongBean, com.elves.update.a aVar, Handler handler, m mVar, n nVar, HashMap<String, String> hashMap) {
        if (an.a(sharedPreferences)) {
            String uid = huodongBean.getUid();
            HashMap a = nVar.a(uid);
            if (TextUtils.isEmpty((CharSequence) a.get("weibo_uid")) || "null".equals(a.get("weibo_uid"))) {
                a("sina", -1, sharedPreferences);
                return;
            } else if (an.a(Long.parseLong(mVar.c(uid)))) {
                nVar.b(this.d, huodongBean, a, aVar, handler);
                return;
            } else {
                a("sina", -1, sharedPreferences);
                return;
            }
        }
        a("sina", -1, sharedPreferences);
    }

    public void b(SharedPreferences sharedPreferences, HuodongBean huodongBean, com.elves.update.a aVar, Handler handler, n nVar, HashMap<String, String> hashMap) {
        if (an.a(sharedPreferences)) {
            HashMap a = nVar.a(huodongBean.getUid());
            if (TextUtils.isEmpty((CharSequence) a.get("qq_uid")) || "null".equals(a.get("qq_uid"))) {
                a("tenct", -1, sharedPreferences);
                return;
            }
            nVar.b(this.d, huodongBean, a, aVar, handler);
            return;
        }
        a("tenct", -1, sharedPreferences);
    }

    public void c(SharedPreferences sharedPreferences, HuodongBean huodongBean, IWXAPI iwxapi) {
        if (iwxapi.isWXAppSupportAPI()) {
            ay.b(this.d, huodongBean, 0, iwxapi, true, sharedPreferences);
            return;
        }
        this.q = an.a(this.d, this.d.getString(R.string.wx_low_version), -1);
        this.q.show();
    }

    public void d(SharedPreferences sharedPreferences, HuodongBean huodongBean, IWXAPI iwxapi) {
        ay.b(this.d, huodongBean, 1, iwxapi, false, sharedPreferences);
    }

    public void c(HuodongBean huodongBean, Handler handler) {
        Bundle bundle = new Bundle();
        String str = "http://www.budejie.com/budejie";
        if (!TextUtils.isEmpty(huodongBean.getShareUrl())) {
            str = huodongBean.getShareUrl();
        }
        bundle.putString("targetUrl", str);
        ArrayList arrayList = new ArrayList();
        if (!TextUtils.isEmpty(huodongBean.getVoiceUrl())) {
            bundle.putInt("req_type", 2);
            str = this.j;
            if (!TextUtils.isEmpty(huodongBean.getTitle())) {
                str = huodongBean.getTitle();
            }
            bundle.putString("title", str);
            bundle.putString("summary", huodongBean.getContent());
            bundle.putString("audio_url", huodongBean.getVoiceUrl());
            if (TextUtils.isEmpty(huodongBean.getPicUrl())) {
                arrayList.add("http://img.spriteapp.cn/ws/img/budejie_logo.png");
            } else {
                arrayList.add(huodongBean.getPicUrl());
            }
            bundle.putStringArrayList("imageUrl", arrayList);
        } else if (TextUtils.isEmpty(huodongBean.getPicUrl())) {
            bundle.putInt("req_type", 1);
            str = this.h;
            if (!TextUtils.isEmpty(huodongBean.getTitle())) {
                str = huodongBean.getTitle();
            }
            bundle.putString("title", str);
            bundle.putString("summary", huodongBean.getContent());
            arrayList.add("http://img.spriteapp.cn/ws/img/budejie_logo.png");
            bundle.putStringArrayList("imageUrl", arrayList);
        } else {
            str = this.i;
            if (!TextUtils.isEmpty(huodongBean.getTitle())) {
                str = huodongBean.getTitle();
            }
            bundle.putInt("req_type", 1);
            bundle.putString("title", str);
            bundle.putString("summary", huodongBean.getContent());
            if (TextUtils.isEmpty(huodongBean.getPicUrl())) {
                arrayList.add("http://img.spriteapp.cn/ws/img/budejie_logo.png");
            } else {
                arrayList.add(huodongBean.getPicUrl());
            }
            bundle.putStringArrayList("imageUrl", arrayList);
        }
        bundle.putString("appName", this.d.getString(R.string.app_name));
        a(bundle, huodongBean.getTheme_id() + "", handler, (Object) huodongBean);
    }

    public void d(HuodongBean huodongBean, Handler handler) {
        Bundle bundle = new Bundle();
        String str = "http://www.budejie.com/budejie";
        if (!TextUtils.isEmpty(huodongBean.getShareUrl())) {
            str = huodongBean.getShareUrl();
        }
        bundle.putString("targetUrl", str);
        if (TextUtils.isEmpty(huodongBean.getPicUrl())) {
            str = this.h;
            if (!TextUtils.isEmpty(huodongBean.getTitle())) {
                str = huodongBean.getTitle();
            }
            bundle.putString("title", str);
            bundle.putString("imageUrl", "http://img.spriteapp.cn/ws/img/budejie_logo.png");
            bundle.putString("summary", huodongBean.getContent());
        } else if (!TextUtils.isEmpty(huodongBean.getPicUrl()) && TextUtils.isEmpty(huodongBean.getVoiceUrl())) {
            str = this.i;
            if (!TextUtils.isEmpty(huodongBean.getTitle())) {
                str = huodongBean.getTitle();
            }
            bundle.putString("title", str);
            bundle.putString("imageUrl", huodongBean.getPicUrl());
            bundle.putString("summary", huodongBean.getContent());
        } else if (!TextUtils.isEmpty(huodongBean.getVoiceUrl())) {
            str = this.j;
            if (!TextUtils.isEmpty(huodongBean.getTitle())) {
                str = huodongBean.getTitle();
            }
            bundle.putString("title", str);
            bundle.putString("summary", huodongBean.getContent());
            bundle.putString("imageUrl", huodongBean.getPicUrl());
        }
        bundle.putString("appName", "百思不得姐");
        bundle.putString("site", "百思不得姐100336987");
        this.f.shareToQQ(this.d, bundle, new b$6(this, handler, huodongBean));
    }

    public void c(HuodongBean huodongBean, SharedPreferences sharedPreferences) {
        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:"));
        String str = "";
        if (sharedPreferences.getBoolean("isRecommend", false)) {
            str = huodongBean.getContent() + "?f=sms&d=android";
            sharedPreferences.edit().putBoolean("isRecommend", false).commit();
        } else {
            str = huodongBean.getContent();
            this.r = OnlineConfigAgent.getInstance().getConfigParams(this.d, "sms_title");
            if (TextUtils.isEmpty(this.r)) {
                this.r = "分享自#百思不得姐#";
            }
            str = this.r + str + "http://www.budejie.com/budejie/land_theme.php?theme_id=" + huodongBean.getTheme_id() + "&f=sms&d=android";
        }
        intent.putExtra("sms_body", str);
        this.d.startActivity(intent);
    }

    public void d(HuodongBean huodongBean, SharedPreferences sharedPreferences) {
        CharSequence charSequence;
        ClipboardManager clipboardManager = (ClipboardManager) this.d.getSystemService("clipboard");
        String str = "";
        if (sharedPreferences.getBoolean("isRecommend", false)) {
            charSequence = huodongBean.getContent() + "?f=sms&d=android";
            sharedPreferences.edit().putBoolean("isRecommend", false).commit();
        } else {
            str = huodongBean.getContent();
            this.r = OnlineConfigAgent.getInstance().getConfigParams(this.d, "sms_title");
            if (TextUtils.isEmpty(this.r)) {
                this.r = "分享自#百思不得姐#";
            }
            charSequence = this.r + str + "http://www.budejie.com/budejie/land_theme.php?theme_id=" + huodongBean.getTheme_id() + "&f=sms&d=android";
        }
        clipboardManager.setText(charSequence);
        this.g = an.a(this.d, this.d.getString(R.string.shareToClipboard), -1);
        this.g.show();
    }
}
