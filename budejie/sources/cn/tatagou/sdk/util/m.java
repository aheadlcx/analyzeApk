package cn.tatagou.sdk.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.util.Log;
import cn.tatagou.sdk.activity.FeedBackActivity;
import cn.tatagou.sdk.activity.TrackListActivity;
import cn.tatagou.sdk.activity.TtgMainActivity;
import cn.tatagou.sdk.activity.TtgMainTabActivity;
import cn.tatagou.sdk.activity.TtgMineActivity;
import cn.tatagou.sdk.android.TtgCallback;
import cn.tatagou.sdk.android.TtgConfig;
import cn.tatagou.sdk.android.TtgConfigKey;
import cn.tatagou.sdk.android.TtgConfigKey.Feedback;
import cn.tatagou.sdk.android.TtgInterface;
import cn.tatagou.sdk.android.TtgSDK;
import cn.tatagou.sdk.e.a.b;
import cn.tatagou.sdk.pojo.AppHomeData;
import cn.tatagou.sdk.pojo.Config;
import cn.tatagou.sdk.pojo.Coupon;
import cn.tatagou.sdk.pojo.H5Params;
import cn.tatagou.sdk.pojo.Item;
import cn.tatagou.sdk.pojo.MyMap;
import cn.tatagou.sdk.pojo.Special;
import cn.tatagou.sdk.pojo.TtgTitleBar;
import cn.tatagou.sdk.pojo.TtgUrl;
import cn.tatagou.sdk.view.IUpdateViewManager;
import cn.tatagou.sdk.view.TtgMainView;
import java.io.Serializable;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class m {
    public static void openGoodsDetails(Activity activity, Item item, String str, String str2) {
        openGoodsDetails(activity, item, null, null, str, str2);
    }

    public static void openGoodsDetails(Activity activity, Item item, String str, String str2, String str3, String str4) {
        if (a.isBcSucc(activity)) {
            H5Params detailType = new H5Params().setTitle(a.getWebTitle(str4)).setType(5).setTypeParams(item.getTaobaoId()).setFinalPrices(item.getFinalPrice()).setCouponType(item.getBadges()).setDetailType(str4);
            Coupon coupon = item.getCoupon();
            if (coupon != null) {
                detailType.setCoupon(coupon);
            }
            detailType.openH5(activity);
            b.itemStatEvent(item, str, str2, str3);
            b.createFootPrintApi(activity, item.getId());
            a(item.getId());
        }
    }

    private static void a(String str) {
        IUpdateViewManager.getInstance().notifyIUpdateView("commoditydDetails", Boolean.valueOf(true));
        Map hashMap = new HashMap();
        hashMap.put(TtgConfigKey.KEY_TTG_GOODSID, str);
        hashMap.put(TtgConfigKey.KEY_TTG_DEVICEID, p.phoneImei(TtgSDK.getContext()));
        IUpdateViewManager.getInstance().notifyIUpdateView(TtgConfigKey.KEY_TTG_GOODSDETAILS, hashMap);
    }

    public static void intoMine(Activity activity) {
        if (TtgSDK.sBcInitFlag == -1) {
            a.bcInitFailNotice();
        } else {
            activity.startActivity(new Intent(activity, TtgMineActivity.class));
        }
    }

    public static void openSpecialList(Context context, String str, String str2, String str3, String str4, String str5) {
        if (TtgSDK.sBcInitFlag == -1) {
            a.bcInitFailNotice();
            return;
        }
        b.spStatEvent(str, str2, str5);
        Intent intent = new Intent(context, TrackListActivity.class);
        intent.putExtra("productId", str);
        intent.putExtra("title", str4);
        intent.putExtra("cateid", str3);
        intent.setFlags(276824064);
        context.startActivity(intent);
    }

    public static void openTtgUrl(Context context, String str, int i) {
        try {
            if ("simple".equals(Uri.parse(str).getQueryParameter("activity"))) {
                TtgInterface.openTtgMain(context, str, i);
            } else {
                TtgInterface.openTabTtgMain(context, str, i);
            }
        } catch (Throwable e) {
            Log.d("TTG", "openTtgUrl: " + e.getMessage(), e);
        }
    }

    public static void openTtgMain(Context context, String str, int i) {
        if (!p.isEmpty(str)) {
            Log.d("TTG", "openTtgMain url: " + str);
            TtgConfig.getInstance().setPid(i);
            if (str.contains("ttg://home") || str.contains("ttg://cate")) {
                Parcelable ttgUrl = new TtgUrl(str);
                TtgTitleBar.getInstance().setBackIconShown(true);
                Intent intent = new Intent(context, TtgMainActivity.class);
                intent.putExtra(TtgConfigKey.TTG_URl, ttgUrl);
                intent.setFlags(276824064);
                context.startActivity(intent);
                return;
            }
            a(context, str, i);
        }
    }

    public static void openTabTtgMain(Context context, String str, int i) {
        if (!p.isEmpty(str)) {
            TtgConfig.getInstance().setPid(i);
            if (str.contains("ttg://home") || str.contains("ttg://cate")) {
                Parcelable ttgUrl = new TtgUrl(str);
                TtgTitleBar.getInstance().setBackIconShown(true);
                AppHomeData.getInstance().setTtgUrl(ttgUrl);
                if (TtgMainTabActivity.a) {
                    Log.d("TTG", "openTabTtgMain:  在活动状态");
                    IUpdateViewManager.getInstance().notifyIUpdateView("TTG_TAB_HOME", ttgUrl);
                    return;
                }
                Log.d("TTG", "openTabTtgMain:  不在活动状态");
                Intent intent = new Intent(context, TtgMainTabActivity.class);
                intent.putExtra(TtgConfigKey.TTG_URl, ttgUrl);
                intent.setFlags(276824064);
                context.startActivity(intent);
                return;
            }
            a(context, str, i);
        }
    }

    public static void openTtgMainView(String str, int i) {
        if (!p.isEmpty(str)) {
            TtgConfig.getInstance().setPid(i);
            if (str.contains("ttg://home") || str.contains("ttg://cate")) {
                TtgUrl ttgUrl = new TtgUrl(str);
                IUpdateViewManager.getInstance().notifyIUpdateView("TtgMainView", ttgUrl);
                TtgMainView.setTtgUrl(ttgUrl);
            }
        }
    }

    public static Map<String, String> getSdkInfo(Context context) {
        Map<String, String> hashMap = new HashMap();
        hashMap.put(TtgConfigKey.DEVICE_ID, p.phoneImei(context));
        hashMap.put(TtgConfigKey.VER, "2.4.4.6");
        if (p.isEmpty(Config.getInstance().getSex())) {
            Config.getInstance().setSex(b.getSex());
        }
        hashMap.put(TtgConfigKey.USER_SEX, Config.getInstance().getSex());
        return hashMap;
    }

    private static void a(Context context, String str, int i) {
        Uri parseUrl = new TtgUrl(str).parseUrl();
        if (parseUrl != null) {
            Log.d("url", "openTtgOther: " + str);
            String queryParameter = parseUrl.getQueryParameter("title");
            String queryParameter2 = parseUrl.getQueryParameter("notify");
            String queryParameter3;
            if (str.contains("ttg://tburl")) {
                queryParameter3 = Uri.parse(str).getQueryParameter("url");
                try {
                    queryParameter3 = URLDecoder.decode(queryParameter3, "UTF-8");
                } catch (Throwable e) {
                    Log.e("TTG", "URLDecoder decode ttgUrl: " + e.getMessage(), e);
                }
                Log.d("paramsUrl", "openTtgOther: " + queryParameter3);
                openTtgH5(context, new H5Params().setTitle(queryParameter).setType(7).setTypeParams(queryParameter3).setNotify(queryParameter2), String.valueOf(i));
            } else if (str.contains("ttg://item")) {
                queryParameter3 = Uri.parse(str).getQueryParameter("id");
                openTtgH5(context, new H5Params().setTitle(queryParameter).setType(5).setTypeParams(queryParameter3).setBack(Uri.parse(str).getQueryParameter("return")).setNotify(queryParameter2), String.valueOf(i));
            } else if (str.contains("ttg://special")) {
                openSpecialList(context, Uri.parse(str).getQueryParameter("id"), null, null, queryParameter, null);
            }
        }
    }

    public static void recoverTtgMain(Context context, String str, int i) {
        if (!p.isEmpty(str)) {
            TtgConfig.getInstance().setPid(i);
            if (str.contains("ttg://home") || str.contains("ttg://cate")) {
                TtgUrl ttgUrl = new TtgUrl(str);
                AppHomeData.getInstance().setTtgUrl(ttgUrl);
                IUpdateViewManager.getInstance().notifyIUpdateView("TtgHome", ttgUrl);
                return;
            }
            a(context, str, i);
        }
    }

    public static void openBcCartOrOrder(Context context, String str, int i, Map<String, String> map) {
        String str2;
        String str3 = "HOME";
        if (map == null || map.size() <= 0) {
            str2 = str3;
        } else {
            str2 = (String) map.get(TtgConfigKey.TTG_EVENT_FROM);
        }
        str3 = null;
        if (i == 2) {
            str3 = "购物车";
            b.cartStatEvent(str2);
        } else if (i == 3) {
            str3 = "订单管理";
            b.myOrdersStatEvent(str2);
        }
        new H5Params().setType(i).setTypeParams(str).setTitle(str3).openH5(context);
    }

    public static void openH5(Context context, String str, int i, String str2) {
        new H5Params().setType(i).setTypeParams(str).setTitle(str2).openH5(context);
    }

    public static void openTtgFeedBack(Context context, Map<String, String> map) {
        if (map == null) {
            map = new HashMap();
        }
        map.put(Feedback.KEY_FEEDBACKTYPE, "ttgFb");
        openFeedbackActivity(context, map);
    }

    public static void countUnreadFeedback(TtgCallback ttgCallback) {
        b.countUnreadFeedback(ttgCallback);
    }

    public static void openPubFeedBack(Context context, Map<String, String> map) {
        if (map == null) {
            map = new HashMap();
        }
        map.put(Feedback.KEY_FEEDBACKTYPE, "hostAppFb");
        openFeedbackActivity(context, map);
    }

    public static void openFeedbackActivity(Context context, Map<String, String> map) {
        Intent intent = new Intent(context, FeedBackActivity.class);
        Serializable myMap = new MyMap();
        myMap.setMap(map);
        intent.putExtra("FeedbackParams", myMap);
        intent.setFlags(276824064);
        context.startActivity(intent);
    }

    public static void openTtgMine(Context context, Map<String, String> map) {
        String str = "HOME";
        if (map != null && map.size() > 0) {
            str = (String) map.get(TtgConfigKey.TTG_EVENT_FROM);
        }
        b.mineStatEvent(str);
        Intent intent = new Intent(context, TtgMineActivity.class);
        intent.setFlags(276824064);
        context.startActivity(intent);
    }

    public static void openUrlH5(Activity activity, Special special) {
        if (special != null) {
            b.spStatEvent(special.getId(), special.getMarker(), null);
            new H5Params().setType("URL".equals(special.getIsBanner()) ? 6 : 7).setTypeParams(special.getUrl()).setTitle(special.getTitle()).openH5(activity);
        }
    }

    public static void openTtgH5(Context context, H5Params h5Params, String str) {
        b.ttgStatEvent(str);
        h5Params.openH5(context);
    }
}
