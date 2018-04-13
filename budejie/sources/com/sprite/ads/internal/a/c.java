package com.sprite.ads.internal.a;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.text.TextUtils;
import cn.v6.sixrooms.room.statistic.StatiscEvent;
import com.alibaba.baichuan.android.trade.constants.AppLinkConstants;
import com.facebook.common.util.UriUtil;
import com.sprite.ads.internal.bean.ResponseData;
import com.sprite.ads.internal.bean.data.AdItem;
import com.sprite.ads.internal.bean.data.SelfItem;
import com.sprite.ads.internal.log.ADLog;
import com.sprite.ads.internal.net.ADNetException;
import com.sprite.ads.internal.net.b;
import com.sprite.ads.internal.utils.NetUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class c {
    private static c a = new c();
    private Activity b;
    private b c = new b();

    public interface a {
        void onClick();

        void onDismiss();

        void onPositive();
    }

    public static c a() {
        return a;
    }

    private void a(Context context, AdItem adItem) {
        this.c.a(context, adItem);
    }

    private void a(final a aVar, final AdItem adItem) {
        if (!this.b.isFinishing()) {
            AlertDialog create = new Builder(this.b).setMessage(TextUtils.isEmpty(adItem.getTitle()) ? "是否下载该应用" : "是否下载 " + adItem.getTitle()).setPositiveButton("是", new OnClickListener(this) {
                final /* synthetic */ c c;

                public void onClick(DialogInterface dialogInterface, int i) {
                    if (aVar != null) {
                        aVar.onPositive();
                    }
                    this.c.e(adItem);
                }
            }).setNegativeButton("否", new OnClickListener(this) {
                final /* synthetic */ c a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).create();
            create.setOnDismissListener(new OnDismissListener(this) {
                final /* synthetic */ c b;

                public void onDismiss(DialogInterface dialogInterface) {
                    if (aVar != null) {
                        aVar.onDismiss();
                    }
                }
            });
            create.show();
        }
    }

    private void a(a aVar, AdItem adItem, int i, String str) {
        if (!TextUtils.isEmpty(str)) {
            if ("store".equals(adItem.target)) {
                d(this.b, adItem);
            } else if (UriUtil.HTTP_SCHEME.equals(adItem.target)) {
                b(this.b, adItem);
            } else if ("downer".equals(adItem.target)) {
                c(this.b, adItem);
            } else if ("browser".equals(adItem.target)) {
                a(this.b, adItem);
            } else if (str.startsWith("mod")) {
                if (aVar != null) {
                    aVar.onClick();
                }
            } else if ("down".equals(adItem.target)) {
                b(aVar, adItem, i);
            }
        }
    }

    private void a(AdItem adItem, String str) {
        if (adItem == null) {
            ADLog.d("广告数据为空，无法汇报");
            return;
        }
        if ("show".equals(str) && (adItem instanceof SelfItem)) {
            String str2 = ((SelfItem) adItem).extra != null ? ((SelfItem) adItem).extra.third_report_url : "";
            if (!"".equals(str2)) {
                com.sprite.ads.internal.net.a.a(str2, new b(this) {
                    final /* synthetic */ c a;

                    {
                        this.a = r1;
                    }

                    public void a(ResponseData responseData) {
                    }

                    public void a(ADNetException aDNetException) {
                    }
                });
            }
        }
        com.sprite.ads.internal.net.a.a(b(adItem, str), new b(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void a(ResponseData responseData) {
            }

            public void a(ADNetException aDNetException) {
            }
        });
    }

    private Map<String, String> b(AdItem adItem, String str) {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("resid", adItem.postId);
        hashMap.put("rt", str);
        hashMap.put("position", adItem.position + "");
        hashMap.put(AppLinkConstants.TIME, (System.currentTimeMillis() / 1000) + "");
        hashMap.put(com.alipay.sdk.app.statistic.c.d, a.a(new TreeMap(hashMap)));
        return hashMap;
    }

    private void b(Context context, AdItem adItem) {
        this.c.b(context, adItem);
    }

    private void c(Context context, AdItem adItem) {
        this.c.b(context, adItem);
    }

    private void d(Context context, AdItem adItem) {
        this.c.c(context, adItem);
    }

    private void e(AdItem adItem) {
        this.c.a(adItem);
    }

    public void a(Activity activity) {
        this.b = activity;
    }

    public void a(a aVar, AdItem adItem, int i) {
        if (adItem == null) {
            ADLog.d("广告数据为空，无法汇报");
            return;
        }
        d(adItem);
        a(aVar, adItem, i, adItem.getUrl());
    }

    public void a(AdItem adItem) {
        a(adItem, "download");
    }

    public void a(SelfItem selfItem) {
        d(selfItem);
    }

    public void b(a aVar, AdItem adItem, int i) {
        switch (i) {
            case 0:
                e(adItem);
                if (aVar != null) {
                    aVar.onDismiss();
                    return;
                }
                return;
            case 2:
                e(adItem);
                if (aVar != null) {
                    aVar.onDismiss();
                    return;
                }
                return;
            case 3:
                if ("WIFI".equalsIgnoreCase(NetUtil.getNetType(this.b))) {
                    e(adItem);
                    if (aVar != null) {
                        aVar.onDismiss();
                        return;
                    }
                    return;
                }
                a(aVar, adItem);
                return;
            default:
                a(aVar, adItem);
                return;
        }
    }

    public void b(AdItem adItem) {
        a(adItem, "finish");
    }

    public void b(SelfItem selfItem) {
        c(selfItem);
    }

    public void c(AdItem adItem) {
    }

    public void c(SelfItem selfItem) {
        a((AdItem) selfItem, "play");
    }

    public void d(AdItem adItem) {
        a(adItem, StatiscEvent.CLICK);
    }

    public void d(SelfItem selfItem) {
        a((AdItem) selfItem, "show");
    }
}
