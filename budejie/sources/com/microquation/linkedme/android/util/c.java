package com.microquation.linkedme.android.util;

import com.alibaba.baichuan.android.trade.constants.UserTrackerConstants;
import com.ixintui.pushsdk.SdkConstants;
import com.tencent.open.GameAppOperation;
import com.umeng.analytics.pro.x;

public class c {

    public enum a {
        IdentityID("identity_id"),
        Identity("identity"),
        DeviceFingerprintID("device_fingerprint_id"),
        SessionID("session_id"),
        LinkClickID("click_id"),
        LinkLKME("lkme"),
        LinkLKMECC("lkme.cc"),
        LinkWWWLKMECC("www.lkme.cc"),
        AppLinkUsed("linkedme_used"),
        ReferringData("referring_data"),
        OS("os"),
        URIScheme("uri_scheme"),
        AppIdentifier("app_identifier"),
        GoogleAdvertisingID("google_advertising_id"),
        LATVal("lat_val"),
        Clicked_LINKEDME_Link("clicked_linkedme_link"),
        AndroidDeepLinkPath("$android_deeplink_path"),
        DeepLinkPath("$deeplink_path"),
        CanonicalIdentifier("$canonical_identifier"),
        ContentTitle("$og_title"),
        ContentDesc("$og_description"),
        ContentImgUrl("$og_image_url"),
        CanonicalUrl("$canonical_url"),
        ContentType("$content_type"),
        ContentKeyWords("$keywords"),
        ContentExpiryTime("$exp_date"),
        Params("params"),
        External_Intent_URI("external_intent_uri"),
        External_Intent_Extra("external_intent_extra"),
        Last_Round_Trip_Time("lrtt"),
        LINKEDME_Instrumentation("instrumentation"),
        Queue_Wait_Time("qwt"),
        LKME_SIGN("sign"),
        LKME_DEVICE_ID(x.u),
        LKME_DEVICE_NAME(x.B),
        LKME_DEVICE_TYPE(x.T),
        LKME_DEVICE_IMEI("device_imei"),
        LKME_IMEI("imei"),
        LKME_IMEI_MD5("imei_md5"),
        LKME_DEVICE_ANDROID_ID("device_android_id"),
        LKME_ANDROID_ID("android_id"),
        LKME_ANDROID_ID_MD5("android_id_md5"),
        LKME_DEVICE_SERIAL_NUMBER("device_serial_number"),
        LKME_DEVICE_MAC("device_mac"),
        LKME_LOCAL_IP("local_ip"),
        LKME_DEVICE_FINGERPRINT("device_fingerprint"),
        LKME_DEVICE_BRAND(x.x),
        LKME_DEVICE_MODEL(x.v),
        LKME_HAS_BLUETOOTH("has_bluetooth"),
        LKME_HAS_NFC("has_nfc"),
        LKME_HAS_SIM("has_sim"),
        LKME_OS("os"),
        LKME_OS_VERSION_INT("os_version_int"),
        LKME_OS_VERSION(x.q),
        LKME_SCREEN_DPI("screen_dpi"),
        LKME_SCREEN_HEIGHT("screen_height"),
        LKME_SCREEN_WIDTH("screen_width"),
        LKME_IS_WIFI("is_wifi"),
        LKME_IS_REFERRABLE("is_referrable"),
        LKME_IS_DEBUG("is_debug"),
        LKME_LAT_VAL("lat_val"),
        LKME_GoogleAdvertisingID("google_advertising_id"),
        LKME_CARRIER(x.H),
        LKME_APP_VERSION("app_version"),
        LKME_APP_VERSION_CODE("app_version_code"),
        LKME_SDK_UPDATE("sdk_update"),
        LKME_SDK_VERSION("sdk_version"),
        LKME_RETRY_TIMES("retry_times"),
        LKME_DF_ID("device_fingerprint_id"),
        LKME_IDENTITY_ID("identity_id"),
        LKME_LINK("link"),
        LKME_SESSION_ID("session_id"),
        LKME_CLOSE_SESSION("close_session"),
        LKME_STATION_SESSION("station_session"),
        LKME_BF_ID("browser_fingerprint_id"),
        LKME_PARAMS("params"),
        LKME_IS_FIRST_SESSION("is_first_session"),
        LKME_CLICKED_LINKEDME_LINK("clicked_linkedme_link"),
        LKME_METADATA("$metadata"),
        LKME_CONTROLL("$control"),
        LKME_IDENTITY("identity"),
        LKME_USER_ID(UserTrackerConstants.USER_ID),
        LKME_BUTTON_ID("btn_id"),
        LKME_DEEPLINK_MD5("deeplink_md5_new"),
        LKME_IS_TEST_URL("is_test_url"),
        LKME_DEEPLINK_URL("deeplink_url"),
        LKME_URI_SCHEME("uri_scheme"),
        LKME_TIMESTAMP("timestamp"),
        LKME_APPS_DATA("apps_data"),
        LKME_DEVICE_UPDATE("device_update"),
        LKME_CLOSE_ENABLE("close_enable"),
        LKME_IS_GAL("is_gal"),
        LKME_GAL_INTERVAL(x.ap),
        LKME_GAL_REQ_INTERVAL("req_interval"),
        LKME_GAL_TRACK("track"),
        LKME_LNG(x.af),
        LKME_LAT(x.ae),
        LKME_APP_NAME(GameAppOperation.QQFAV_DATALINE_APPNAME),
        LKME_WF_INFO("wf_session");
        
        private String aV;

        private a(String str) {
            this.aV = "";
            this.aV = str;
        }

        public String a() {
            return this.aV;
        }

        public String toString() {
            return this.aV;
        }
    }

    public enum b {
        POINT_NAME("point_name"),
        POINT_PROPERTIES("point_properties");
        
        private String c;

        private b(String str) {
            this.c = "";
            this.c = str;
        }

        public String a() {
            return this.c;
        }

        public String toString() {
            return this.c;
        }
    }

    public enum c {
        IS_LC("is_lc"),
        LC_FINE("lc_fine"),
        LC_INTERVAL("lc_interval"),
        KEEP_TRACKING("keep_tracking"),
        MIN_TIME("min_time"),
        MIN_DISTANCE("min_distance"),
        DELAY("delay"),
        PERIOD("period"),
        DURATION("duration"),
        LC_UP("lc_up"),
        LC_DATA("lc_data"),
        SI_DATA("si_data");
        
        private String m;

        private c(String str) {
            this.m = "";
            this.m = str;
        }

        public String a() {
            return this.m;
        }

        public String toString() {
            return this.m;
        }
    }

    public enum d {
        ORDER_ID("order_id"),
        PAY_ACCOUNT("pay_account"),
        ORDER_AMOUNT("order_amount"),
        ORDER_DETAIL("order_detail");
        
        private String e;

        private d(String str) {
            this.e = "";
            this.e = str;
        }

        public String a() {
            return this.e;
        }

        public String toString() {
            return this.e;
        }
    }

    public enum e {
        ACCOUNT("account");
        
        private String b;

        private e(String str) {
            this.b = "";
            this.b = str;
        }

        public String a() {
            return this.b;
        }

        public String toString() {
            return this.b;
        }
    }

    public enum f {
        Tags("tags"),
        Alias("alias"),
        Type("type"),
        Duration("duration"),
        Channel("channel"),
        Feature("feature"),
        Stage("stage"),
        LKME_Link("lkme_link"),
        LKME_NewUser("lkme_new_user"),
        LKME_H5Url("h5_url"),
        Data("data"),
        Params("params");
        
        private String m;

        private f(String str) {
            this.m = "";
            this.m = str;
        }

        public String a() {
            return this.m;
        }

        public String toString() {
            return this.m;
        }
    }

    public enum g {
        RedeemRewards("v1/redeem"),
        GetURL("/sdk/url"),
        RegisterInstall("/sdk/install"),
        RegisterClose("/sdk/close"),
        RegisterOpen("/sdk/open"),
        GAL("/sdk/is_gal"),
        APPList("/sdk/applist"),
        TrackingRegister(SdkConstants.REGISTER),
        TrackingLogin("login"),
        TrackingPay("pay"),
        TrackingCustomPoint("custom_point"),
        LC("/sdk/lc");
        
        private String m;

        private g(String str) {
            this.m = "";
            this.m = str;
        }

        public String a() {
            return this.m;
        }

        public String toString() {
            return this.m;
        }
    }
}
