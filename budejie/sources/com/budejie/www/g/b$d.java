package com.budejie.www.g;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import cn.v6.sixrooms.utils.phone.HistoryOpenHelper;
import com.budejie.www.R;
import com.budejie.www.activity.ReprintPostsActivity;
import com.budejie.www.activity.label.enumeration.CommonLabelOperator.CommonLabelAction;
import com.budejie.www.bean.ListItemObject;
import com.budejie.www.busevent.DetailAction;
import com.budejie.www.c.m;
import com.budejie.www.http.i;
import com.budejie.www.http.n;
import com.budejie.www.util.ai;
import com.budejie.www.util.an;
import com.budejie.www.util.p;
import com.budejie.www.wallpaper.b.b;
import com.elves.update.a;
import com.google.analytics.tracking.android.HitTypes;
import com.tencent.mm.sdk.constants.Build;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.umeng.analytics.MobclickAgent;
import de.greenrobot.event.EventBus;
import java.util.HashMap;
import java.util.Map;

public class b$d implements OnClickListener {
    IWXAPI a;
    Bundle b;
    HashMap<String, String> c;
    Handler d;
    Handler e;
    m f;
    n g;
    a h;
    SharedPreferences i;
    Context j;
    ListItemObject k = null;
    final /* synthetic */ b l;

    public b$d(b bVar, Context context, IWXAPI iwxapi, Bundle bundle, Handler handler, m mVar, n nVar, a aVar, SharedPreferences sharedPreferences, Handler handler2) {
        this.l = bVar;
        this.j = context;
        this.a = iwxapi;
        this.b = bundle;
        this.d = handler;
        this.f = mVar;
        this.g = nVar;
        this.h = aVar;
        this.i = sharedPreferences;
        this.e = handler2;
        this.c = (HashMap) bundle.getSerializable("weiboMap");
    }

    public void onClick(View view) {
        boolean z = true;
        if (this.e == null) {
            MobclickAgent.onEvent(this.j, "E01-A04", "帖子流果断分享app点击次数");
        }
        this.k = (ListItemObject) view.getTag();
        if (this.k.getOriginal_topic() != null) {
            this.k = this.k.getOriginal_topic();
        }
        final ListItemObject listItemObject = this.k;
        if (this.a == null) {
            this.a = WXAPIFactory.createWXAPI(b.a(this.l), "wx592fdc48acfbe290", true);
            this.a.registerApp("wx592fdc48acfbe290");
        }
        boolean isWXAppInstalled = this.a.isWXAppInstalled();
        int wXAppSupportAPI = this.a.getWXAppSupportAPI();
        if (this.d == null) {
            this.i.edit().putBoolean("isRecommend", true).commit();
        } else {
            this.i.edit().putBoolean("isRecommend", false).commit();
        }
        if (wXAppSupportAPI < Build.TIMELINE_SUPPORTED_SDK_INT) {
            z = false;
        }
        p.a(this.b, b.a(this.l), isWXAppInstalled, z, this.i, new p.a(this) {
            final /* synthetic */ b$d b;

            public void a(int i, Dialog dialog) {
                this.b.c = this.b.g.a(ai.b(b.a(this.b.l)));
                if (i == 1 && b.e(this.b.l)) {
                    MobclickAgent.onEvent(b.a(this.b.l), "E02-A04", "新浪分享点击数");
                    n.a(b.a(this.b.l), listItemObject);
                    dialog.cancel();
                    i.a(b.a(this.b.l), listItemObject);
                } else if (i == 2 && b.e(this.b.l)) {
                    MobclickAgent.onEvent(b.a(this.b.l), "E02-A04", "腾讯微博分享点击数");
                    boolean a = an.a(this.b.i);
                    int i2 = this.b.b.getInt("position", 0);
                    if (!a) {
                        b.a(this.b.l, "tenct", i2, this.b.i);
                    } else if (TextUtils.isEmpty((CharSequence) this.b.c.get("qq_uid")) || "null".equals(this.b.c.get("qq_uid"))) {
                        b.a(this.b.l, "tenct", i2, this.b.i);
                    } else {
                        this.b.g.a(b.a(this.b.l), listItemObject, "qq", this.b.b.getString(HistoryOpenHelper.COLUMN_UID), this.b.c, this.b.h, this.b.e);
                    }
                    dialog.cancel();
                    i.a(b.a(this.b.l), listItemObject);
                } else if (i == 3 && b.e(this.b.l)) {
                    MobclickAgent.onEvent(b.a(this.b.l), "E02-A04", "微信组分享点击数");
                    if (listItemObject.getType() == null || !listItemObject.getType().equals("29")) {
                        this.b.l.b(listItemObject, this.b.a, this.b.d);
                    } else {
                        b.a(this.b.l, i, listItemObject);
                    }
                    dialog.cancel();
                    i.a(b.a(this.b.l), listItemObject);
                } else if (i == 4 && b.e(this.b.l)) {
                    MobclickAgent.onEvent(b.a(this.b.l), "E02-A04", "微信朋友圈分享点击数");
                    if (listItemObject.getType() == null || !listItemObject.getType().equals("29")) {
                        this.b.l.a(listItemObject, this.b.a, this.b.d);
                    } else {
                        b.a(this.b.l, i, listItemObject);
                    }
                    dialog.cancel();
                    i.a(b.a(this.b.l), listItemObject);
                } else if (i == 5) {
                    dialog.cancel();
                    this.b.i.edit().putBoolean("isRecommend", false).commit();
                    MobclickAgent.onEvent(b.a(this.b.l), "E02-A04", "取消");
                    Log.i("ListenerEx", "转发菜单-取消");
                    i.a(b.a(this.b.l), listItemObject);
                } else if (i == 6 && b.e(this.b.l)) {
                    MobclickAgent.onEvent(b.a(this.b.l), "E02-A04", "QQ空间分享点击数");
                    this.b.l.a(listItemObject, this.b.d);
                    dialog.cancel();
                    i.a(b.a(this.b.l), listItemObject);
                } else if (i == 7) {
                    this.b.l.a(listItemObject, this.b.i);
                    dialog.cancel();
                    MobclickAgent.onEvent(b.a(this.b.l), "E02-A04", "短信");
                    Log.i("ListenerEx", "转发菜单-短信");
                    i.a(b.a(this.b.l), listItemObject);
                } else if (i == 12) {
                    this.b.l.b(listItemObject, this.b.i);
                    dialog.cancel();
                    MobclickAgent.onEvent(b.a(this.b.l), "E02-A04", "复制正文");
                    Log.i("ListenerEx", "转发菜单-复制正文");
                } else if (i == 15) {
                    if (listItemObject != null) {
                        if ("41".equals(listItemObject.getType())) {
                            an.a(b.a(this.b.l), listItemObject);
                            MobclickAgent.onEvent(b.a(this.b.l), "E06-A10", "视频id:" + listItemObject.getWid());
                        } else {
                            an.a(b.a(this.b.l), listItemObject);
                        }
                        MobclickAgent.onEvent(b.a(this.b.l), "E06_A11", "分享菜单中点击");
                    }
                    dialog.cancel();
                    Log.i("ListenerEx", "转发菜单-视频下载");
                } else if (i == 16) {
                    EventBus.getDefault().post(DetailAction.SCREEN_SHOT);
                    dialog.cancel();
                    MobclickAgent.onEvent(b.a(this.b.l), "E02-A04", "长截图");
                    Log.i("ListenerEx", "转发菜单-长截图");
                } else if (i == 17) {
                    com.budejie.www.util.a.b(b.a(this.b.l), listItemObject);
                    b.a(listItemObject, "动态桌面", "分享菜单");
                    dialog.cancel();
                } else if (i == 8 && b.e(this.b.l)) {
                    MobclickAgent.onEvent(b.a(this.b.l), "E02-A04", "QQ分享点击数");
                    if (listItemObject.getType() == null || !listItemObject.getType().equals("29")) {
                        this.b.l.b(listItemObject, this.b.d);
                    } else {
                        b.a(this.b.l, i, listItemObject);
                    }
                    dialog.cancel();
                    i.a(b.a(this.b.l), listItemObject);
                } else if (i == 9) {
                    ListItemObject listItemObject = (ListItemObject) this.b.b.getSerializable("data");
                    if (an.a(this.b.i)) {
                        if (listItemObject.isCollect()) {
                            Bundle bundle = new Bundle();
                            bundle.putString("wid", listItemObject.getWid());
                            bundle.putString("imgPath", listItemObject.getImgPath());
                            bundle.putSerializable("data", listItemObject);
                            com.budejie.www.util.m.b(b.a(this.b.l), this.b.d, bundle);
                        } else {
                            Map hashMap = new HashMap();
                            hashMap.put("type", an.g(listItemObject.getType()));
                            hashMap.put("title", listItemObject.getContent());
                            hashMap.put("label", listItemObject.getPlateNames());
                            an.a(b.a(this.b.l), hashMap, "E01_A04");
                            listItemObject.setForwardAndCollect(false);
                            listItemObject.setForwardAndCollect_isweixin(false);
                            this.b.b.putSerializable("data", listItemObject);
                            com.budejie.www.util.m.a(b.a(this.b.l), this.b.d, this.b.b);
                        }
                        dialog.cancel();
                        MobclickAgent.onEvent(b.a(this.b.l), "E02-A04", "收藏");
                        return;
                    }
                    an.a(b.a(this.b.l), 0, null, null, 0);
                } else if (i == 10 && b.e(this.b.l)) {
                    p.a(b.a(this.b.l), this.b.b.getString(HistoryOpenHelper.COLUMN_UID), ((ListItemObject) this.b.b.getSerializable("data")).getWid(), null);
                    dialog.cancel();
                    MobclickAgent.onEvent(b.a(this.b.l), "E02-A04", "举报");
                } else if (i == 11 && b.e(this.b.l)) {
                    p.b(b.a(this.b.l), listItemObject, this.b.d, false, null);
                    dialog.cancel();
                    MobclickAgent.onEvent(b.a(this.b.l), "E02-A04", "删除拉黑");
                    Log.i("ListenerEx", "转发菜单-删除拉黑");
                } else if (i == 14 && b.e(this.b.l)) {
                    if (!an.a(this.b.i)) {
                        an.a(b.a(this.b.l), 0, null, null, 0);
                    } else if ("61".equals(listItemObject.getType()) && listItemObject.getOriginal_topic() == null) {
                        Toast.makeText(b.a(this.b.l), R.string.reprint_post_delete, 0).show();
                    } else {
                        MobclickAgent.onEvent(b.a(this.b.l), "E02-A04", "转载");
                        Intent intent = new Intent(b.a(this.b.l), ReprintPostsActivity.class);
                        intent.putExtra(HitTypes.ITEM, listItemObject);
                        b.a(this.b.l).startActivity(intent);
                        dialog.cancel();
                    }
                } else if (i == 18) {
                    dialog.cancel();
                    EventBus.getDefault().post(b.a(this.b.l, listItemObject, CommonLabelAction.ADD_ESSENCE));
                } else if (i == 19) {
                    dialog.cancel();
                    EventBus.getDefault().post(b.a(this.b.l, listItemObject, CommonLabelAction.TO_TOP));
                } else if (i == 20) {
                    dialog.cancel();
                    EventBus.getDefault().post(b.a(this.b.l, listItemObject, CommonLabelAction.DELETE_POST));
                }
            }
        });
    }
}
