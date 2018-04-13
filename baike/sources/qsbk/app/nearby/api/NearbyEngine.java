package qsbk.app.nearby.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.text.TextUtils;
import android.util.Pair;
import com.xiaomi.mipush.sdk.Constants;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import qsbk.app.QsbkApp;
import qsbk.app.core.utils.PrefrenceKeys;
import qsbk.app.exception.QiushibaikeException;
import qsbk.app.model.Job;
import qsbk.app.nearby.ui.NearbySelectView;
import qsbk.app.utils.HttpClient;
import qsbk.app.utils.LogUtil;
import qsbk.app.utils.SharePreferenceUtils;

public class NearbyEngine {
    public static final int RESP_NEED_INFO = -110;
    public static final int RESP_NEED_INFO_DIALOG = -120;
    public static final int RESP_SUCCESS = 0;
    private static NearbyEngine a = null;
    private boolean b = false;

    private NearbyEngine() {
    }

    public static synchronized NearbyEngine instance() {
        NearbyEngine nearbyEngine;
        synchronized (NearbyEngine.class) {
            if (a == null) {
                a = new NearbyEngine();
            }
            nearbyEngine = a;
        }
        return nearbyEngine;
    }

    public static void saveLastLocationToLocal(double d, double d2) {
        SharePreferenceUtils.setSharePreferencesValue(PrefrenceKeys.LOCAL_PHONE_LOCATION, d + Constants.ACCEPT_TIME_SEPARATOR_SP + d2);
    }

    public static Pair<Double, Double> getLastSavedPosition(boolean z) {
        Object sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue(PrefrenceKeys.LOCAL_PHONE_LOCATION);
        if (TextUtils.isEmpty(sharePreferencesValue)) {
            return null;
        }
        String[] split = sharePreferencesValue.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
        try {
            Pair<Double, Double> pair = new Pair(Double.valueOf(Double.parseDouble(split[0])), Double.valueOf(Double.parseDouble(split[1])));
            if (z) {
                SharePreferenceUtils.setSharePreferencesValue(PrefrenceKeys.LOCAL_PHONE_LOCATION, "");
            }
            return pair;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getCurrentUserInfoUrl() {
        LogUtil.d("currentUser:" + QsbkApp.currentUser.userId);
        return String.format(qsbk.app.Constants.URL_USER_INFO, new Object[]{QsbkApp.currentUser.userId});
    }

    public String getUserLocalPrefByKey(String str) {
        LogUtil.e(String.format("get key:%s value:%s", new Object[]{String.format(str, new Object[]{QsbkApp.currentUser.userId}), SharePreferenceUtils.getSharePreferencesValue(String.format(str, new Object[]{QsbkApp.currentUser.userId}))}));
        return SharePreferenceUtils.getSharePreferencesValue(String.format(str, new Object[]{QsbkApp.currentUser.userId}));
    }

    public boolean getLocalUserFlagByKey(String str) {
        return !TextUtils.isEmpty(getUserLocalPrefByKey(str));
    }

    public void setLocalUserFlagByKey(String str, String str2) {
        LogUtil.d(String.format("set key:%s value:%s", new Object[]{String.format(str, new Object[]{QsbkApp.currentUser.userId}), str2}));
        SharePreferenceUtils.setSharePreferencesValue(r0, str2);
    }

    public void setLocalUserFlagByKey(String str, boolean z) {
        String format = String.format(str, new Object[]{QsbkApp.currentUser.userId});
        if (z) {
            SharePreferenceUtils.setSharePreferencesValue(format, "true");
        } else {
            SharePreferenceUtils.remove(format);
        }
    }

    public boolean isNearbyInfoComplete() {
        return getLocalUserFlagByKey("nearby_complete_%s");
    }

    public void setNearbyInfoComplete(boolean z) {
        setLocalUserFlagByKey("nearby_complete_%s", z);
    }

    public boolean isNearbyNoMoreWarn() {
        return getLocalUserFlagByKey("nearby_nowarn_%s");
    }

    public void setNearbyNoMoreWarn(boolean z) {
        setLocalUserFlagByKey("nearby_nowarn_%s", z);
    }

    public boolean isNearbyNoMoreWarnInfoComplete() {
        return getLocalUserFlagByKey("nearby_nowarn_complete_%s");
    }

    public void setNearbyNoMoreWarnInfoComplete(boolean z) {
        setLocalUserFlagByKey("nearby_nowarn_complete_%s", z);
    }

    public void setNearbyNoMoreWarn() {
        setNearbyNoMoreWarn(true);
    }

    public void setNearbyNoMoreWarnInfoComplete() {
        setNearbyNoMoreWarnInfoComplete(true);
    }

    public void setLocalFilterLastLogin(int i) {
        setLocalUserFlagByKey("nearby_filter_lastlogin_%s", String.valueOf(i));
    }

    public String getLocalFilterSex() {
        String userLocalPrefByKey = getUserLocalPrefByKey("nearby_filter_sex_%s");
        LogUtil.d("femail:" + "F".equals(userLocalPrefByKey));
        if (userLocalPrefByKey == null || (!"F".equals(userLocalPrefByKey) && !"M".equals(userLocalPrefByKey))) {
            return NearbySelectView.GENDER_ALL;
        }
        return userLocalPrefByKey;
    }

    public void setLocalFilterSex(String str) {
        setLocalUserFlagByKey("nearby_filter_sex_%s", str);
    }

    public int getLocalFilterTime() {
        int parseInt;
        try {
            parseInt = Integer.parseInt(getUserLocalPrefByKey("nearby_filter_lastlogin_%s"));
        } catch (Exception e) {
            parseInt = NearbySelectView.TIME_3DAY;
        }
        if (parseInt == 15 || parseInt == NearbySelectView.TIME_1DAY || parseInt == NearbySelectView.TIME_3DAY || parseInt == 60) {
            return parseInt;
        }
        return NearbySelectView.TIME_3DAY;
    }

    public boolean isLocationServiceEnabled(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        boolean isProviderEnabled = locationManager.isProviderEnabled("gps");
        boolean isProviderEnabled2 = locationManager.isProviderEnabled("network");
        LogUtil.d("gps enabled:" + isProviderEnabled);
        LogUtil.d("networkd enabled:" + isProviderEnabled2);
        return isProviderEnabled || isProviderEnabled2;
    }

    public boolean isHasGps() {
        return QsbkApp.mContext.getPackageManager().hasSystemFeature("android.hardware.location");
    }

    public Map<String, String[]> getLocalHometown(Context context) {
        JSONException e;
        String string = context.getSharedPreferences("hometown_job_config", 0).getString("config", null);
        if (string == null || string.length() <= 10) {
            return null;
        }
        Map<String, String[]> linkedHashMap;
        try {
            JSONArray jSONArray = new JSONObject(string).getJSONArray("hometown_data");
            int length = jSONArray.length();
            linkedHashMap = new LinkedHashMap(length);
            int i = 0;
            while (i < length) {
                try {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    String string2 = jSONObject.getString("province");
                    JSONArray jSONArray2 = jSONObject.getJSONArray("city");
                    int length2 = jSONArray2.length();
                    Object obj = new String[length2];
                    for (int i2 = 0; i2 < length2; i2++) {
                        obj[i2] = jSONArray2.getJSONObject(i2).getString("name");
                    }
                    linkedHashMap.put(string2, obj);
                    i++;
                } catch (JSONException e2) {
                    e = e2;
                }
            }
            return linkedHashMap;
        } catch (JSONException e3) {
            JSONException jSONException = e3;
            linkedHashMap = null;
            e = jSONException;
            e.printStackTrace();
            return linkedHashMap;
        }
    }

    public LinkedList<Job> getLocalJobs(Context context) {
        JSONException e;
        String string = context.getSharedPreferences("hometown_job_config", 0).getString("config", null);
        if (string == null || string.length() <= 10) {
            return null;
        }
        LinkedList<Job> linkedList;
        try {
            JSONArray jSONArray = new JSONObject(string).getJSONArray("job_data");
            int length = jSONArray.length();
            linkedList = new LinkedList();
            int i = 0;
            while (i < length) {
                try {
                    linkedList.add(new Job(jSONArray.getJSONObject(i).optString("name")));
                    i++;
                } catch (JSONException e2) {
                    e = e2;
                }
            }
            return linkedList;
        } catch (JSONException e3) {
            JSONException jSONException = e3;
            linkedList = null;
            e = jSONException;
            e.printStackTrace();
            return linkedList;
        }
    }

    public synchronized void updateUserConfig(Context context) {
        JSONObject jSONObject;
        String str;
        JSONObject jSONObject2;
        String optString;
        JSONException e;
        double d;
        double d2;
        double parseDouble;
        JSONObject jSONObject3;
        String optString2;
        String optString3;
        double d3;
        if (!this.b) {
            this.b = true;
            SharedPreferences sharedPreferences = context.getSharedPreferences("hometown_job_config", 0);
            String string = sharedPreferences.getString("config", null);
            JSONObject jSONObject4 = new JSONObject();
            if (string == null || string.length() <= 10) {
                jSONObject = jSONObject4;
                string = null;
                str = null;
            } else {
                try {
                    jSONObject2 = new JSONObject(string);
                    try {
                        optString = jSONObject2.optString("hometown_version");
                    } catch (JSONException e2) {
                        e = e2;
                        optString = null;
                        e.printStackTrace();
                        jSONObject = jSONObject2;
                        string = null;
                        str = optString;
                        if (str == null) {
                            str = "0";
                        }
                        if (string == null) {
                            string = "0";
                        }
                        d = 0.0d;
                        d = Double.parseDouble(str);
                        d2 = 0.0d;
                        parseDouble = Double.parseDouble(string);
                        jSONObject3 = new JSONObject(HttpClient.getIntentce().get(String.format(qsbk.app.Constants.URL_CONFIG + "?hometown=%s&job=%s", new Object[]{str, string})));
                        optString2 = jSONObject3.optString("hometown_version");
                        optString3 = jSONObject3.optString("job_version");
                        d2 = 0.0d;
                        d3 = 0.0d;
                        d2 = Double.parseDouble(optString2);
                        try {
                            d3 = Double.parseDouble(optString3);
                        } catch (NumberFormatException e3) {
                        }
                        if (d2 - d > 0.0d) {
                            jSONObject.put("hometown_version", optString2);
                            jSONObject.put("hometown_data", jSONObject3.getJSONArray("hometown_data"));
                        }
                        if (d3 - parseDouble > 0.0d) {
                            jSONObject.put("job_version", optString3);
                            jSONObject.put("job_data", jSONObject3.getJSONArray("job_data"));
                        }
                        sharedPreferences.edit().putString("config", jSONObject.toString()).apply();
                        this.b = false;
                    }
                    try {
                        string = jSONObject2.optString("job_version");
                        jSONObject = jSONObject2;
                        str = optString;
                    } catch (JSONException e4) {
                        e = e4;
                        e.printStackTrace();
                        jSONObject = jSONObject2;
                        string = null;
                        str = optString;
                        if (str == null) {
                            str = "0";
                        }
                        if (string == null) {
                            string = "0";
                        }
                        d = 0.0d;
                        d = Double.parseDouble(str);
                        d2 = 0.0d;
                        parseDouble = Double.parseDouble(string);
                        jSONObject3 = new JSONObject(HttpClient.getIntentce().get(String.format(qsbk.app.Constants.URL_CONFIG + "?hometown=%s&job=%s", new Object[]{str, string})));
                        optString2 = jSONObject3.optString("hometown_version");
                        optString3 = jSONObject3.optString("job_version");
                        d2 = 0.0d;
                        d3 = 0.0d;
                        d2 = Double.parseDouble(optString2);
                        d3 = Double.parseDouble(optString3);
                        if (d2 - d > 0.0d) {
                            jSONObject.put("hometown_version", optString2);
                            jSONObject.put("hometown_data", jSONObject3.getJSONArray("hometown_data"));
                        }
                        if (d3 - parseDouble > 0.0d) {
                            jSONObject.put("job_version", optString3);
                            jSONObject.put("job_data", jSONObject3.getJSONArray("job_data"));
                        }
                        sharedPreferences.edit().putString("config", jSONObject.toString()).apply();
                        this.b = false;
                    }
                } catch (JSONException e5) {
                    e = e5;
                    jSONObject2 = jSONObject4;
                    optString = null;
                    e.printStackTrace();
                    jSONObject = jSONObject2;
                    string = null;
                    str = optString;
                    if (str == null) {
                        str = "0";
                    }
                    if (string == null) {
                        string = "0";
                    }
                    d = 0.0d;
                    d = Double.parseDouble(str);
                    d2 = 0.0d;
                    parseDouble = Double.parseDouble(string);
                    jSONObject3 = new JSONObject(HttpClient.getIntentce().get(String.format(qsbk.app.Constants.URL_CONFIG + "?hometown=%s&job=%s", new Object[]{str, string})));
                    optString2 = jSONObject3.optString("hometown_version");
                    optString3 = jSONObject3.optString("job_version");
                    d2 = 0.0d;
                    d3 = 0.0d;
                    d2 = Double.parseDouble(optString2);
                    d3 = Double.parseDouble(optString3);
                    if (d2 - d > 0.0d) {
                        jSONObject.put("hometown_version", optString2);
                        jSONObject.put("hometown_data", jSONObject3.getJSONArray("hometown_data"));
                    }
                    if (d3 - parseDouble > 0.0d) {
                        jSONObject.put("job_version", optString3);
                        jSONObject.put("job_data", jSONObject3.getJSONArray("job_data"));
                    }
                    sharedPreferences.edit().putString("config", jSONObject.toString()).apply();
                    this.b = false;
                }
            }
            if (str == null) {
                str = "0";
            }
            if (string == null) {
                string = "0";
            }
            d = 0.0d;
            try {
                d = Double.parseDouble(str);
            } catch (NumberFormatException e6) {
            }
            d2 = 0.0d;
            try {
                parseDouble = Double.parseDouble(string);
            } catch (NumberFormatException e7) {
                parseDouble = d2;
            }
            try {
                jSONObject3 = new JSONObject(HttpClient.getIntentce().get(String.format(qsbk.app.Constants.URL_CONFIG + "?hometown=%s&job=%s", new Object[]{str, string})));
                optString2 = jSONObject3.optString("hometown_version");
                optString3 = jSONObject3.optString("job_version");
                d2 = 0.0d;
                d3 = 0.0d;
                try {
                    d2 = Double.parseDouble(optString2);
                } catch (NumberFormatException e8) {
                }
                d3 = Double.parseDouble(optString3);
                if (d2 - d > 0.0d) {
                    jSONObject.put("hometown_version", optString2);
                    jSONObject.put("hometown_data", jSONObject3.getJSONArray("hometown_data"));
                }
                if (d3 - parseDouble > 0.0d) {
                    jSONObject.put("job_version", optString3);
                    jSONObject.put("job_data", jSONObject3.getJSONArray("job_data"));
                }
                sharedPreferences.edit().putString("config", jSONObject.toString()).apply();
            } catch (QiushibaikeException e9) {
                e9.printStackTrace();
            } catch (JSONException e10) {
                e10.printStackTrace();
            } catch (Exception e11) {
                e11.printStackTrace();
            }
            this.b = false;
        }
    }

    public ILocationManager changeLocationMgr(ILocationManager iLocationManager) {
        if (iLocationManager instanceof BDLocationManger) {
            SharePreferenceUtils.setSharePreferencesValue(PrefrenceKeys.PRE_KEY_LOCATION_MANAGER, "gaode");
        } else {
            SharePreferenceUtils.setSharePreferencesValue(PrefrenceKeys.PRE_KEY_LOCATION_MANAGER, "baidu");
        }
        if (iLocationManager != null) {
            iLocationManager.stop();
        }
        return getLastLocationManager();
    }

    public ILocationManager getLastLocationManager() {
        Object sharePreferencesValue = SharePreferenceUtils.getSharePreferencesValue(PrefrenceKeys.PRE_KEY_LOCATION_MANAGER);
        if (TextUtils.isEmpty(sharePreferencesValue) || !sharePreferencesValue.equals("gaode")) {
            LogUtil.d("use baidu location");
            return BDLocationManger.instance();
        }
        LogUtil.d("use gaode location");
        return GDLocationManager.instance();
    }
}
