package com.ali.auth.third.core.config;

public class ConfigManager {
    public static int APP_KEY_INDEX = 0;
    public static boolean DEBUG = false;
    public static String LOGIN_HOST = "http://login.taobao.com/minisdk/login.htm";
    public static String LOGIN_URLS = "((https|http)://)login.m.taobao.com/login.htm(.*),((https|http)://)login.tmall.com(.*),((https|http)://)login.taobao.com/member/login.jhtml(.*)";
    public static String LOGOUT_URLS;
    public static String POSTFIX_OF_SECURITY_JPG = "";
    public static String POSTFIX_OF_SECURITY_JPG_USER_SET;
    public static final Version SDK_VERSION = new Version(1, 2, 0);
    private static final ConfigManager SINGLETON_INSTANCE = new ConfigManager();
    public static String bindUrl = "http://login.m.taobao.com/cooperation/bindLogin.htm?code=%s&IBB=%s&appkey=%s";
    public static String unbindUrl = "https://accountlink.taobao.com/sdkUnbind.htm";
    private Environment env;

    private ConfigManager() {
    }

    public Environment getEnvironment() {
        return this.env;
    }

    public static ConfigManager getInstance() {
        return SINGLETON_INSTANCE;
    }

    public void init(int i) {
        this.env = Environment.values()[i];
        LOGIN_HOST = new String[]{"http://login.waptest.tbsandbox.com/minisdk/login.htm", "http://login.waptest.taobao.com/minisdk/login.htm", "http://login.wapa.taobao.com/minisdk/login.htm", "http://login.m.taobao.com/minisdk/login.htm"}[i];
        LOGIN_URLS = new String[]{"((https|http)://)login.waptest.tbsandbox.com/login.htm(.*)", "((https|http)://)login.waptest.taobao.com/login.htm(.*)", "((https|http)://)login.wapa.taobao.com/login.htm(.*)", "((https|http)://)login.m.taobao.com/login.htm(.*),((https|http)://)login.tmall.com(.*),((https|http)://)login.taobao.com/member/login.jhtml(.*)"}[i];
        LOGOUT_URLS = new String[]{"((https|http)://)login.waptest.tbsandbox.com/logout.htm(.*)", "((https|http)://)login.waptest.taobao.com/logout.htm(.*)", "((https|http)://)login.wapa.taobao.com/logout.htm(.*)", "((https|http)://)login.m.taobao.com/logout.htm(.*)"}[i];
        bindUrl = new String[]{"http://login.waptest.tbsandbox.com/cooperation/bindLogin.htm?code=%s&IBB=%s&appkey=%s", "http://login.waptest.taobao.com/cooperation/bindLogin.htm?code=%s&IBB=%s&appkey=%s", "http://login.wapa.taobao.com/cooperation/bindLogin.htm?code=%s&IBB=%s&appkey=%s", "http://login.m.taobao.com/cooperation/bindLogin.htm?code=%s&IBB=%s&appkey=%s"}[i];
        unbindUrl = new String[]{"https://accountlink.daily.taobao.net/sdkUnbind.htm", "https://accountlink.daily.taobao.net/sdkUnbind.htm", "https://accountlink.taobao.com/sdkUnbind.htm", "https://accountlink.taobao.com/sdkUnbind.htm"}[i];
        if (POSTFIX_OF_SECURITY_JPG_USER_SET == null) {
            POSTFIX_OF_SECURITY_JPG = new String[]{"", "", "", ""}[i];
            return;
        }
        POSTFIX_OF_SECURITY_JPG = POSTFIX_OF_SECURITY_JPG_USER_SET;
    }

    public static int getAppKeyIndex() {
        return APP_KEY_INDEX;
    }
}
