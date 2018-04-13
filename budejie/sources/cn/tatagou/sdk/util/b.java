package cn.tatagou.sdk.util;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.Log;
import cn.tatagou.sdk.a.a.a;
import cn.tatagou.sdk.a.e;
import cn.tatagou.sdk.android.TtgCallback;
import cn.tatagou.sdk.android.TtgSDK;
import cn.tatagou.sdk.pojo.CommPojo;
import cn.tatagou.sdk.pojo.Config;
import cn.tatagou.sdk.pojo.TtgTitleBar;
import cn.tatagou.sdk.pojo.UnReadFeedback;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import mtopsdk.mtop.antiattack.CheckCodeDO;
import okhttp3.ab;
import retrofit2.d;
import retrofit2.l;

public class b {
    private static final String a = b.class.getSimpleName();

    public static void createFootPrintApi(Context context, String str) {
        ((a) e.getInstance().getService(a.class)).newMyPath(str, a.getTaoBaoUserId()).a(new d<ab>() {
            public void onResponse(retrofit2.b<ab> bVar, l<ab> lVar) {
            }

            public void onFailure(retrofit2.b<ab> bVar, Throwable th) {
            }
        });
    }

    public static void getSysCfg() {
        getSysCfg(null);
    }

    public static void getSysCfg(final c cVar) {
        ((a) e.getInstance().getService(a.class)).otherInformation().a(new d<ab>() {
            public void onResponse(retrofit2.b<ab> bVar, l<ab> lVar) {
                Map a = b.b(lVar);
                if (a != null) {
                    cn.tatagou.sdk.b.a.saveStr("sysConfigInfo", JSON.toJSONString(a));
                    b.parseConfigData(a);
                    if (cVar != null) {
                        cVar.setSysCfg(a);
                    }
                }
            }

            public void onFailure(retrofit2.b<ab> bVar, Throwable th) {
                Log.d(b.a, "datajson: 11111111111：： " + th.getMessage(), th);
            }
        });
    }

    public static void parseConfigData(Map<String, String> map) {
        if (map != null && map.size() > 0) {
            try {
                a((String) map.get("IGNORE_EVENTS"));
                String str = (String) map.get("SLS_THRESHOLD");
                cn.tatagou.sdk.e.a.init(TtgSDK.getContext()).setLogMinBatchSize(p.isEmpty(str) ? 2 : Integer.parseInt(str)).setLogSchema((String) map.get("LOG_SUB")).setTraceId((String) map.get("TRACE_ID")).setIp((String) map.get("IP"));
                str = (String) map.get("TTG_TITLE");
                TtgTitleBar instance = TtgTitleBar.getInstance();
                if (p.isEmpty(str)) {
                    str = TtgTitleBar.getInstance().getTitle();
                }
                instance.setTitle(str);
                Config.getInstance().setLoginType((String) map.get("LOGIN_TYPE")).setIp((String) map.get("IP")).setJsPatch((String) map.get("JS_PATCH")).setTraceId((String) map.get("TRACE_ID")).setTtgAboutPage((String) map.get("ABOUT_PAGE_M")).setRmbgn((String) map.get("RMBGN")).setSpecialNoMoreHint((String) map.get("SPECIAL_NO_MORE_HINT")).setSpecialTopHint((String) map.get("SPECIAL_TOP_HINT")).setTimeForNewItems((String) map.get("timeForNewItems")).setCatNoMoreHint((String) map.get("CAT_NO_MORE_HINT")).setSexList((String) map.get("SEX")).setRmEnd((String) map.get("RMEND")).setRmFresh((String) map.get("RMFRESH")).setAuthFirst((String) map.get("AUTH_FIRST")).setTabTitle((String) map.get("TAB_TITLE"));
            } catch (Throwable e) {
                Log.e(a, "parseConfigData: " + e.getMessage(), e);
            }
        }
    }

    private static void a(String str) {
        if (!p.isEmpty(str)) {
            String[] split = str.split(",");
            if (split.length > 0) {
                cn.tatagou.sdk.e.a.init(TtgSDK.getContext()).setSkippedEvents(Arrays.asList(split));
            }
        }
    }

    public static void reportErrorLog(String str, String str2) {
        reportErrorLog(null, str, str2, null);
    }

    public static void reportErrorLog(Context context, String str, String str2) {
        reportErrorLog(context, str, str2, null);
    }

    public static void reportErrorLog(Context context, String str, String str2, String str3) {
        String str4 = "手机型号:" + Build.MODEL + ", OS:" + VERSION.RELEASE + ", TTG:" + "2.4.4.6" + ", APP:" + Config.getInstance().getAppVersion();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.putAll(cn.tatagou.sdk.e.a.init(context).getParameters());
        if (context != null) {
            linkedHashMap.put("appVer", p.getAppVersionName(context));
        }
        linkedHashMap.put("sdkVer", "2.4.4.6");
        linkedHashMap.put("os", "ANDROID");
        linkedHashMap.put("uuid", p.phoneImei(TtgSDK.getContext()));
        linkedHashMap.put("sys", str4);
        if (str != null) {
            linkedHashMap.put(CheckCodeDO.CHECKCODE_USER_INPUT_KEY, str);
        }
        if (str2 != null) {
            linkedHashMap.put("msg", str2);
        }
        if (str3 != null) {
            linkedHashMap.put("trace", str3);
        }
        ((a) cn.tatagou.sdk.a.d.getInstance().getService(a.class)).errorReport(linkedHashMap).a(new d<ab>() {
            public void onResponse(retrofit2.b<ab> bVar, l<ab> lVar) {
                if (TtgSDK.isDebug) {
                    Log.d(b.a, "errorReport onResponse succ" + lVar.a());
                }
            }

            public void onFailure(retrofit2.b<ab> bVar, Throwable th) {
            }
        });
    }

    public static void apConfig(final TtgCallback ttgCallback) {
        ((a) e.getInstance().getService(a.class)).apConfig().a(new d<ab>() {
            public void onResponse(retrofit2.b<ab> bVar, l<ab> lVar) {
                Map a = b.b(lVar);
                if (a != null) {
                    ttgCallback.getApConfig(a);
                } else {
                    ttgCallback.getApConfig(new HashMap());
                }
            }

            public void onFailure(retrofit2.b<ab> bVar, Throwable th) {
                ttgCallback.getApConfig(new HashMap());
            }
        });
    }

    private static Map<String, String> b(l<ab> lVar) {
        if (lVar.e() != null) {
            try {
                String inputStream2String = p.inputStream2String(((ab) lVar.e()).d());
                if (inputStream2String != null) {
                    JSONObject parseObject = JSON.parseObject(inputStream2String);
                    int parseInt = parseObject.get(CheckCodeDO.CHECKCODE_USER_INPUT_KEY) == null ? 0 : Integer.parseInt(parseObject.get(CheckCodeDO.CHECKCODE_USER_INPUT_KEY).toString());
                    Object obj = parseObject.get("data");
                    if (parseInt == 200 && obj != null) {
                        Map<String, String> map = (Map) JSON.parseObject(obj.toString(), new TypeReference<Map<String, String>>() {
                        }, new Feature[0]);
                        if (map != null && map.size() > 0) {
                            return map;
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

    public static void openReportLaunch() {
        openReportLaunch(null);
    }

    public static void openReportLaunch(Map<String, String> map) {
        Map map2;
        if (map == null) {
            String str = cn.tatagou.sdk.b.a.getStr("sysConfigInfo");
            map2 = str != null ? (Map) JSON.parseObject(str, new TypeReference<Map<String, String>>() {
            }, new Feature[0]) : null;
        } else {
            Map<String, String> map3 = map;
        }
        parseConfigData(map2);
        if (Config.getInstance().isLaunchOpen()) {
            cn.tatagou.sdk.e.a.b.launchStatEvent();
        }
    }

    public static void countUnreadFeedback(final TtgCallback ttgCallback) {
        ((a) e.getInstance().getService(a.class)).outCountUnreadFeedback(1, p.phoneImei(TtgSDK.getContext())).a(new d<ab>() {
            public void onResponse(retrofit2.b<ab> bVar, l<ab> lVar) {
                int parseInt;
                if (!(lVar == null || lVar.e() == null)) {
                    CommPojo commPojo = (CommPojo) cn.tatagou.sdk.a.b.onParseBodyData((ab) lVar.e(), new TypeReference<CommPojo<UnReadFeedback>>(this) {
                        final /* synthetic */ AnonymousClass7 a;

                        {
                            this.a = r1;
                        }
                    }.getType());
                    UnReadFeedback unReadFeedback = commPojo != null ? (UnReadFeedback) commPojo.getData() : null;
                    if (!(unReadFeedback == null || p.isEmpty(unReadFeedback.getUnRead()))) {
                        try {
                            parseInt = Integer.parseInt(unReadFeedback.getUnRead());
                        } catch (Throwable e) {
                            Log.e(b.a, "outCountUnreadFeedback: " + e.getMessage(), e);
                        }
                        if (ttgCallback != null) {
                            ttgCallback.countUnReadFeedback(parseInt);
                        }
                    }
                }
                parseInt = 0;
                if (ttgCallback != null) {
                    ttgCallback.countUnReadFeedback(parseInt);
                }
            }

            public void onFailure(retrofit2.b<ab> bVar, Throwable th) {
                if (ttgCallback != null) {
                    ttgCallback.countUnReadFeedback(0);
                }
            }
        });
    }

    public static String getSex() {
        String str = cn.tatagou.sdk.b.a.getStr("ttgSex");
        if (!p.isEmpty(str)) {
            return str;
        }
        str = cn.tatagou.sdk.b.a.getStr("sex");
        return (!p.isEmpty(str) && str.length() == 1 && ("F".equals(str) || "M".equals(str) || "L".equals(str))) ? str : null;
    }
}
