package com.sina.weibo.sdk.call;

import android.content.Context;
import android.text.TextUtils;
import com.sina.weibo.sdk.constant.WBPageConstants.ExceptionMsg;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.sina.weibo.sdk.constant.WBPageConstants.Scheme;
import java.util.HashMap;

public final class WeiboPageUtilsV2 {
    private WeiboPageUtilsV2() {
    }

    public static void postNewWeibo(Context context, HashMap<String, String> hashMap) throws WeiboNotInstalledException {
        if (context == null) {
            throw new WeiboIllegalParameterException(ExceptionMsg.CONTEXT_ERROR);
        }
        StringBuilder stringBuilder = new StringBuilder(Scheme.SENDWEIBO);
        if (hashMap != null) {
            stringBuilder.append(CommonUtils.buildUriQuery(hashMap));
        }
        if (hashMap == null || TextUtils.isEmpty((CharSequence) hashMap.get("packagename"))) {
            CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString(), null);
            return;
        }
        StringBuilder stringBuilder2 = new StringBuilder(Scheme.SENDWEIBO);
        if (hashMap != null) {
            stringBuilder2.append(CommonUtils.buildUriQuery(hashMap));
        }
        CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString(), (String) hashMap.get("packagename"));
    }

    public static void viewNearbyPeople(Context context, HashMap<String, String> hashMap) throws WeiboNotInstalledException {
        if (context == null) {
            throw new WeiboIllegalParameterException(ExceptionMsg.CONTEXT_ERROR);
        }
        StringBuilder stringBuilder = new StringBuilder(Scheme.NEARBYPEOPLE);
        if (hashMap != null) {
            stringBuilder.append(CommonUtils.buildUriQuery(hashMap));
        }
        if (hashMap == null || TextUtils.isEmpty((CharSequence) hashMap.get("packagename"))) {
            CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString(), null);
            return;
        }
        StringBuilder stringBuilder2 = new StringBuilder(Scheme.NEARBYPEOPLE);
        if (hashMap != null) {
            stringBuilder2.append(CommonUtils.buildUriQuery(hashMap));
        }
        CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString(), (String) hashMap.get("packagename"));
    }

    public static void viewNearbyWeibo(Context context, HashMap<String, String> hashMap) throws WeiboNotInstalledException {
        if (context == null) {
            throw new WeiboIllegalParameterException(ExceptionMsg.CONTEXT_ERROR);
        }
        StringBuilder stringBuilder = new StringBuilder(Scheme.NEARBYWEIBO);
        if (hashMap != null) {
            stringBuilder.append(CommonUtils.buildUriQuery(hashMap));
        }
        if (hashMap == null || TextUtils.isEmpty((CharSequence) hashMap.get("packagename"))) {
            CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString(), null);
            return;
        }
        StringBuilder stringBuilder2 = new StringBuilder(Scheme.NEARBYWEIBO);
        if (hashMap != null) {
            stringBuilder2.append(CommonUtils.buildUriQuery(hashMap));
        }
        CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString(), (String) hashMap.get("packagename"));
    }

    public static void viewUserInfo(Context context, HashMap<String, String> hashMap) throws WeiboNotInstalledException {
        if (context == null) {
            throw new WeiboIllegalParameterException(ExceptionMsg.CONTEXT_ERROR);
        } else if (hashMap == null || (TextUtils.isEmpty((CharSequence) hashMap.get("uid")) && TextUtils.isEmpty((CharSequence) hashMap.get(ParamKey.NICK)))) {
            throw new WeiboIllegalParameterException(ExceptionMsg.UID_NICK_ERROR);
        } else {
            StringBuilder stringBuilder = new StringBuilder(Scheme.USERINFO);
            if (hashMap != null) {
                stringBuilder.append(CommonUtils.buildUriQuery(hashMap));
            }
            if (hashMap == null || TextUtils.isEmpty((CharSequence) hashMap.get("packagename"))) {
                CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString(), null);
                return;
            }
            StringBuilder stringBuilder2 = new StringBuilder(Scheme.USERINFO);
            if (hashMap != null) {
                stringBuilder2.append(CommonUtils.buildUriQuery(hashMap));
            }
            CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString(), (String) hashMap.get("packagename"));
        }
    }

    public static void viewUsertrends(Context context, HashMap<String, String> hashMap) throws WeiboNotInstalledException {
        if (context == null) {
            throw new WeiboIllegalParameterException(ExceptionMsg.CONTEXT_ERROR);
        } else if (hashMap == null || TextUtils.isEmpty((CharSequence) hashMap.get("uid"))) {
            throw new WeiboIllegalParameterException(ExceptionMsg.UID_NICK_ERROR);
        } else {
            StringBuilder stringBuilder = new StringBuilder(Scheme.USERTRENDS);
            if (hashMap != null) {
                stringBuilder.append(CommonUtils.buildUriQuery(hashMap));
            }
            if (hashMap == null || TextUtils.isEmpty((CharSequence) hashMap.get("packagename"))) {
                CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString(), null);
                return;
            }
            StringBuilder stringBuilder2 = new StringBuilder(Scheme.USERTRENDS);
            if (hashMap != null) {
                stringBuilder2.append(CommonUtils.buildUriQuery(hashMap));
            }
            CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString(), (String) hashMap.get("packagename"));
        }
    }

    public static void viewPageInfo(Context context, HashMap<String, String> hashMap) throws WeiboNotInstalledException {
        if (context == null) {
            throw new WeiboIllegalParameterException(ExceptionMsg.CONTEXT_ERROR);
        } else if (hashMap == null || TextUtils.isEmpty((CharSequence) hashMap.get(ParamKey.PAGEID))) {
            throw new WeiboIllegalParameterException(ExceptionMsg.PAGEID_ERROR);
        } else {
            StringBuilder stringBuilder = new StringBuilder(Scheme.PAGEINFO);
            if (hashMap != null) {
                stringBuilder.append(CommonUtils.buildUriQuery(hashMap));
            }
            if (hashMap == null || TextUtils.isEmpty((CharSequence) hashMap.get("packagename"))) {
                CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString(), null);
                return;
            }
            StringBuilder stringBuilder2 = new StringBuilder(Scheme.PAGEINFO);
            if (hashMap != null) {
                stringBuilder2.append(CommonUtils.buildUriQuery(hashMap));
            }
            CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString(), (String) hashMap.get("packagename"));
        }
    }

    public static void viewPageProductList(Context context, HashMap<String, String> hashMap) throws WeiboNotInstalledException {
        if (context == null) {
            throw new WeiboIllegalParameterException(ExceptionMsg.CONTEXT_ERROR);
        } else if (hashMap == null) {
            throw new WeiboIllegalParameterException(ExceptionMsg.PAGEID_ERROR);
        } else if (TextUtils.isEmpty((CharSequence) hashMap.get(ParamKey.PAGEID))) {
            throw new WeiboIllegalParameterException(ExceptionMsg.PAGEID_ERROR);
        } else if (TextUtils.isEmpty((CharSequence) hashMap.get(ParamKey.CARDID))) {
            throw new WeiboIllegalParameterException(ExceptionMsg.CARDID_ERROR);
        } else {
            int parseInt;
            try {
                parseInt = Integer.parseInt((String) hashMap.get("count"));
            } catch (NumberFormatException e) {
                parseInt = -1;
            }
            if (parseInt < 0) {
                throw new WeiboIllegalParameterException(ExceptionMsg.COUNT_ERROR);
            }
            StringBuilder stringBuilder = new StringBuilder(Scheme.PAGEPRODUCTLIST);
            if (hashMap != null) {
                stringBuilder.append(CommonUtils.buildUriQuery(hashMap));
            }
            if (hashMap == null || TextUtils.isEmpty((CharSequence) hashMap.get("packagename"))) {
                CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString(), null);
                return;
            }
            StringBuilder stringBuilder2 = new StringBuilder(Scheme.PAGEPRODUCTLIST);
            if (hashMap != null) {
                stringBuilder2.append(CommonUtils.buildUriQuery(hashMap));
            }
            CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString(), (String) hashMap.get("packagename"));
        }
    }

    public static void viewPageUserList(Context context, HashMap<String, String> hashMap) throws WeiboNotInstalledException {
        if (context == null) {
            throw new WeiboIllegalParameterException(ExceptionMsg.CONTEXT_ERROR);
        } else if (hashMap == null) {
            throw new WeiboIllegalParameterException(ExceptionMsg.PAGEID_ERROR);
        } else if (TextUtils.isEmpty((CharSequence) hashMap.get(ParamKey.PAGEID))) {
            throw new WeiboIllegalParameterException(ExceptionMsg.PAGEID_ERROR);
        } else if (TextUtils.isEmpty((CharSequence) hashMap.get(ParamKey.CARDID))) {
            throw new WeiboIllegalParameterException(ExceptionMsg.CARDID_ERROR);
        } else {
            int parseInt;
            try {
                parseInt = Integer.parseInt((String) hashMap.get("count"));
            } catch (NumberFormatException e) {
                parseInt = -1;
            }
            if (parseInt < 0) {
                throw new WeiboIllegalParameterException(ExceptionMsg.COUNT_ERROR);
            }
            StringBuilder stringBuilder = new StringBuilder(Scheme.PAGEUSERLIST);
            if (hashMap != null) {
                stringBuilder.append(CommonUtils.buildUriQuery(hashMap));
            }
            if (hashMap == null || TextUtils.isEmpty((CharSequence) hashMap.get("packagename"))) {
                CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString(), null);
                return;
            }
            StringBuilder stringBuilder2 = new StringBuilder(Scheme.PAGEUSERLIST);
            if (hashMap != null) {
                stringBuilder2.append(CommonUtils.buildUriQuery(hashMap));
            }
            CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString(), (String) hashMap.get("packagename"));
        }
    }

    public static void viewPageWeiboList(Context context, HashMap<String, String> hashMap) throws WeiboNotInstalledException {
        if (context == null) {
            throw new WeiboIllegalParameterException(ExceptionMsg.CONTEXT_ERROR);
        } else if (hashMap == null) {
            throw new WeiboIllegalParameterException(ExceptionMsg.PAGEID_ERROR);
        } else if (TextUtils.isEmpty((CharSequence) hashMap.get(ParamKey.PAGEID))) {
            throw new WeiboIllegalParameterException(ExceptionMsg.PAGEID_ERROR);
        } else if (TextUtils.isEmpty((CharSequence) hashMap.get(ParamKey.CARDID))) {
            throw new WeiboIllegalParameterException(ExceptionMsg.CARDID_ERROR);
        } else {
            int parseInt;
            try {
                parseInt = Integer.parseInt((String) hashMap.get("count"));
            } catch (NumberFormatException e) {
                parseInt = -1;
            }
            if (parseInt < 0) {
                throw new WeiboIllegalParameterException(ExceptionMsg.COUNT_ERROR);
            }
            StringBuilder stringBuilder = new StringBuilder(Scheme.PAGEWEIBOLIST);
            if (hashMap != null) {
                stringBuilder.append(CommonUtils.buildUriQuery(hashMap));
            }
            if (hashMap == null || TextUtils.isEmpty((CharSequence) hashMap.get("packagename"))) {
                CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString(), null);
                return;
            }
            StringBuilder stringBuilder2 = new StringBuilder(Scheme.PAGEWEIBOLIST);
            if (hashMap != null) {
                stringBuilder2.append(CommonUtils.buildUriQuery(hashMap));
            }
            CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString(), (String) hashMap.get("packagename"));
        }
    }

    public static void viewPagePhotoList(Context context, HashMap<String, String> hashMap) throws WeiboNotInstalledException {
        if (context == null) {
            throw new WeiboIllegalParameterException(ExceptionMsg.CONTEXT_ERROR);
        } else if (hashMap == null) {
            throw new WeiboIllegalParameterException(ExceptionMsg.PAGEID_ERROR);
        } else if (TextUtils.isEmpty((CharSequence) hashMap.get(ParamKey.PAGEID))) {
            throw new WeiboIllegalParameterException(ExceptionMsg.PAGEID_ERROR);
        } else if (TextUtils.isEmpty((CharSequence) hashMap.get(ParamKey.CARDID))) {
            throw new WeiboIllegalParameterException(ExceptionMsg.CARDID_ERROR);
        } else {
            int parseInt;
            try {
                parseInt = Integer.parseInt((String) hashMap.get("count"));
            } catch (NumberFormatException e) {
                parseInt = -1;
            }
            if (parseInt < 0) {
                throw new WeiboIllegalParameterException(ExceptionMsg.COUNT_ERROR);
            }
            StringBuilder stringBuilder = new StringBuilder(Scheme.PAGEPHOTOLIST);
            if (hashMap != null) {
                stringBuilder.append(CommonUtils.buildUriQuery(hashMap));
            }
            if (hashMap == null || TextUtils.isEmpty((CharSequence) hashMap.get("packagename"))) {
                CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString(), null);
                return;
            }
            StringBuilder stringBuilder2 = new StringBuilder(Scheme.PAGEPHOTOLIST);
            if (hashMap != null) {
                stringBuilder2.append(CommonUtils.buildUriQuery(hashMap));
            }
            CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString(), (String) hashMap.get("packagename"));
        }
    }

    public static void viewPageDetailInfo(Context context, HashMap<String, String> hashMap) throws WeiboNotInstalledException {
        if (context == null) {
            throw new WeiboIllegalParameterException(ExceptionMsg.CONTEXT_ERROR);
        } else if (hashMap == null) {
            throw new WeiboIllegalParameterException(ExceptionMsg.PAGEID_ERROR);
        } else if (TextUtils.isEmpty((CharSequence) hashMap.get(ParamKey.PAGEID))) {
            throw new WeiboIllegalParameterException(ExceptionMsg.PAGEID_ERROR);
        } else if (TextUtils.isEmpty((CharSequence) hashMap.get(ParamKey.CARDID))) {
            throw new WeiboIllegalParameterException(ExceptionMsg.CARDID_ERROR);
        } else {
            StringBuilder stringBuilder = new StringBuilder(Scheme.PAGEDETAILINFO);
            if (hashMap != null) {
                stringBuilder.append(CommonUtils.buildUriQuery(hashMap));
            }
            if (hashMap == null || TextUtils.isEmpty((CharSequence) hashMap.get("packagename"))) {
                CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString(), null);
                return;
            }
            StringBuilder stringBuilder2 = new StringBuilder(Scheme.PAGEDETAILINFO);
            if (hashMap != null) {
                stringBuilder2.append(CommonUtils.buildUriQuery(hashMap));
            }
            CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString(), (String) hashMap.get("packagename"));
        }
    }

    public static void openInWeiboBrowser(Context context, String str, String str2, String str3, String str4) throws WeiboNotInstalledException {
        if (context == null) {
            throw new WeiboIllegalParameterException(ExceptionMsg.CONTEXT_ERROR);
        } else if (TextUtils.isEmpty(str)) {
            throw new WeiboIllegalParameterException(ExceptionMsg.URL_ERROR);
        } else if (TextUtils.isEmpty(str2) || "topnav".equals(str2) || "default".equals(str2) || "fullscreen".equals(str2)) {
            StringBuilder stringBuilder = new StringBuilder(Scheme.BROWSER);
            HashMap hashMap = new HashMap();
            hashMap.put("url", str);
            hashMap.put(ParamKey.SINAINTERNALBROWSER, str2);
            hashMap.put(ParamKey.EXTPARAM, str3);
            stringBuilder.append(CommonUtils.buildUriQuery(hashMap));
            if (TextUtils.isEmpty(str4)) {
                CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString(), null);
                return;
            }
            StringBuilder stringBuilder2 = new StringBuilder(Scheme.BROWSER);
            if (hashMap != null) {
                stringBuilder2.append(CommonUtils.buildUriQuery(hashMap));
            }
            CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString(), str4);
        } else {
            throw new WeiboIllegalParameterException(ExceptionMsg.SINAINTERNALBROWSER);
        }
    }

    public static void displayInWeiboMap(Context context, HashMap<String, String> hashMap) throws WeiboNotInstalledException {
        if (context == null) {
            throw new WeiboIllegalParameterException(ExceptionMsg.CONTEXT_ERROR);
        }
        String str = "http://weibo.cn/dpool/ttt/maps.php?xy=%s,%s&amp;size=320x320&amp;offset=%s";
        String str2 = "";
        String str3 = "";
        String str4 = "";
        if (hashMap != null) {
            str4 = (String) hashMap.get("offset");
            Object obj = (String) hashMap.get(ParamKey.LATITUDE);
            Object obj2 = (String) hashMap.get(ParamKey.LONGITUDE);
        } else {
            String str5 = str3;
            String str6 = str2;
        }
        str3 = null;
        if (!(hashMap == null || TextUtils.isEmpty((CharSequence) hashMap.get("packagename")))) {
            str3 = (String) hashMap.get("packagename");
        }
        if (hashMap != null) {
            openInWeiboBrowser(context, String.format(str, new Object[]{obj2, obj, str4}), "default", (String) hashMap.get(ParamKey.EXTPARAM), str3);
        }
    }

    public static void openQrcodeScanner(Context context, HashMap<String, String> hashMap) throws WeiboNotInstalledException {
        if (context == null) {
            throw new WeiboIllegalParameterException(ExceptionMsg.CONTEXT_ERROR);
        }
        StringBuilder stringBuilder = new StringBuilder(Scheme.QRCODE);
        if (hashMap != null) {
            stringBuilder.append(CommonUtils.buildUriQuery(hashMap));
        }
        if (hashMap == null || TextUtils.isEmpty((CharSequence) hashMap.get("packagename"))) {
            CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString(), null);
            return;
        }
        StringBuilder stringBuilder2 = new StringBuilder(Scheme.QRCODE);
        if (hashMap != null) {
            stringBuilder2.append(CommonUtils.buildUriQuery(hashMap));
        }
        CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString(), (String) hashMap.get("packagename"));
    }

    public static void weiboDetail(Context context, HashMap<String, String> hashMap) throws WeiboNotInstalledException {
        if (context == null) {
            throw new WeiboIllegalParameterException(ExceptionMsg.CONTEXT_ERROR);
        } else if (hashMap == null) {
            throw new WeiboIllegalParameterException(ExceptionMsg.MBLOGID_ERROR);
        } else if (TextUtils.isEmpty((CharSequence) hashMap.get(ParamKey.MBLOGID))) {
            throw new WeiboIllegalParameterException(ExceptionMsg.MBLOGID_ERROR);
        } else {
            StringBuilder stringBuilder = new StringBuilder(Scheme.MBLOGDETAIL);
            if (hashMap != null) {
                stringBuilder.append(CommonUtils.buildUriQuery(hashMap));
            }
            if (hashMap == null || TextUtils.isEmpty((CharSequence) hashMap.get("packagename"))) {
                CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString(), null);
                return;
            }
            StringBuilder stringBuilder2 = new StringBuilder(Scheme.MBLOGDETAIL);
            if (hashMap != null) {
                stringBuilder2.append(CommonUtils.buildUriQuery(hashMap));
            }
            CommonUtils.openWeiboActivity(context, "android.intent.action.VIEW", stringBuilder.toString(), (String) hashMap.get("packagename"));
        }
    }
}
