package com.meizu.cloud.pushsdk.pushtracer.tracker;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.location.Location;
import android.os.Build;
import android.os.Build.VERSION;
import android.view.Display;
import android.view.WindowManager;
import com.meizu.cloud.pushsdk.pushtracer.constant.Parameters;
import com.meizu.cloud.pushsdk.pushtracer.utils.Logger;
import com.meizu.cloud.pushsdk.pushtracer.utils.Util;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Subject {
    private static String TAG = Subject.class.getSimpleName();
    private HashMap<String, Object> geoLocationPairs;
    private HashMap<String, String> mobilePairs;
    private HashMap<String, String> standardPairs;

    public static class SubjectBuilder {
        private Context context = null;

        public SubjectBuilder context(Context context) {
            this.context = context;
            return this;
        }

        public Subject build() {
            return new Subject();
        }
    }

    private Subject(SubjectBuilder subjectBuilder) {
        this.standardPairs = new HashMap();
        this.geoLocationPairs = new HashMap();
        this.mobilePairs = new HashMap();
        setOsType();
        setOsVersion();
        setDeviceModel();
        setDeviceVendor();
        if (subjectBuilder.context != null) {
            setContextualParams(subjectBuilder.context);
        }
        Logger.i(TAG, "Subject created successfully.", new Object[0]);
    }

    public void setContextualParams(Context context) {
        setLocation(context);
        setCarrier(context);
    }

    private void addToMobileContext(String str, String str2) {
        if (str != null && str2 != null && !str.isEmpty() && !str2.isEmpty()) {
            this.mobilePairs.put(str, str2);
        }
    }

    private void addToGeoLocationContext(String str, Object obj) {
        if ((str != null && obj != null && !str.isEmpty()) || ((obj instanceof String) && !((String) obj).isEmpty())) {
            this.geoLocationPairs.put(str, obj);
        }
    }

    private void setDefaultTimezone() {
        setTimezone(Calendar.getInstance().getTimeZone().getID());
    }

    private void setDefaultLanguage() {
        setLanguage(Locale.getDefault().getDisplayLanguage());
    }

    private void setOsType() {
        addToMobileContext(Parameters.OS_TYPE, "android-" + VERSION.RELEASE);
    }

    private void setOsVersion() {
        addToMobileContext(Parameters.OS_VERSION, Build.DISPLAY);
    }

    private void setDeviceModel() {
        addToMobileContext(Parameters.DEVICE_MODEL, Build.MODEL);
    }

    private void setDeviceVendor() {
        addToMobileContext(Parameters.DEVICE_MANUFACTURER, Build.MANUFACTURER);
    }

    @TargetApi(19)
    public void setDefaultScreenResolution(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        try {
            Display.class.getMethod("getSize", new Class[]{Point.class});
            defaultDisplay.getSize(point);
            setScreenResolution(point.x, point.y);
        } catch (NoSuchMethodException e) {
            Logger.e(TAG, "Display.getSize isn't available on older devices.", new Object[0]);
            setScreenResolution(defaultDisplay.getWidth(), defaultDisplay.getHeight());
        }
    }

    public void setLocation(Context context) {
        Location location = Util.getLocation(context);
        if (location == null) {
            Logger.e(TAG, "Location information not available.", new Object[0]);
            return;
        }
        addToGeoLocationContext(Parameters.LATITUDE, Double.valueOf(location.getLatitude()));
        addToGeoLocationContext(Parameters.LONGITUDE, Double.valueOf(location.getLongitude()));
        addToGeoLocationContext(Parameters.ALTITUDE, Double.valueOf(location.getAltitude()));
        addToGeoLocationContext(Parameters.LATLONG_ACCURACY, Float.valueOf(location.getAccuracy()));
        addToGeoLocationContext("speed", Float.valueOf(location.getSpeed()));
        addToGeoLocationContext(Parameters.BEARING, Float.valueOf(location.getBearing()));
    }

    public void setCarrier(Context context) {
        String carrier = Util.getCarrier(context);
        if (carrier != null) {
            addToMobileContext(Parameters.CARRIER, carrier);
        }
    }

    public void setUserId(String str) {
        this.standardPairs.put("uid", str);
    }

    public void setScreenResolution(int i, int i2) {
        this.standardPairs.put("res", Integer.toString(i) + "x" + Integer.toString(i2));
    }

    public void setViewPort(int i, int i2) {
        this.standardPairs.put(Parameters.VIEWPORT, Integer.toString(i) + "x" + Integer.toString(i2));
    }

    public void setColorDepth(int i) {
        this.standardPairs.put(Parameters.COLOR_DEPTH, Integer.toString(i));
    }

    public void setTimezone(String str) {
        this.standardPairs.put(Parameters.TIMEZONE, str);
    }

    public void setLanguage(String str) {
        this.standardPairs.put(Parameters.LANGUAGE, str);
    }

    public void setIpAddress(String str) {
        this.standardPairs.put("ip", str);
    }

    public void setUseragent(String str) {
        this.standardPairs.put(Parameters.USERAGENT, str);
    }

    public void setNetworkUserId(String str) {
        this.standardPairs.put(Parameters.NETWORK_UID, str);
    }

    public void setDomainUserId(String str) {
        this.standardPairs.put(Parameters.DOMAIN_UID, str);
    }

    public Map<String, Object> getSubjectLocation() {
        return this.geoLocationPairs;
    }

    public Map<String, String> getSubjectMobile() {
        return this.mobilePairs;
    }

    public Map<String, String> getSubject() {
        return this.standardPairs;
    }
}
