package com.ali.auth.third.core.cookies;

import android.text.TextUtils;
import com.ali.auth.third.core.context.KernelContext;
import com.ali.auth.third.core.model.Constants;
import com.ali.auth.third.core.service.impl.CredentialManager;
import com.ali.auth.third.core.trace.SDKLogger;
import com.ali.auth.third.core.util.FileUtils;
import java.util.ArrayList;
import java.util.List;

public class CookieManagerWrapper {
    public static final CookieManagerWrapper INSTANCE = new CookieManagerWrapper();
    private static final String TAG = CookieManagerWrapper.class.getSimpleName();
    private String[] mCookies;

    private CookieManagerWrapper() {
    }

    public synchronized void refreshCookie() {
        if (this.mCookies == null) {
            try {
                String readFileData = FileUtils.readFileData(KernelContext.getApplicationContext(), Constants.COOKIES);
                if (!TextUtils.isEmpty(readFileData)) {
                    SDKLogger.d(TAG, "get cookie from storage:" + readFileData);
                    this.mCookies = TextUtils.split(readFileData, Constants.COOKIE_SPLIT);
                }
            } catch (Throwable th) {
            }
        }
        if (this.mCookies != null) {
            injectCookie(this.mCookies, null);
        }
    }

    public synchronized void injectCookie(String[] strArr, String[] strArr2) {
        this.mCookies = strArr;
        if (KernelContext.context != null) {
            if (strArr != null) {
                LoginCookie parseCookie;
                String loginCookie;
                SDKLogger.d(TAG, "injectCookie cookies != null");
                List<LoginCookie> arrayList = new ArrayList();
                for (Object obj : strArr) {
                    if (!TextUtils.isEmpty(obj)) {
                        try {
                            parseCookie = LoginCookieUtils.parseCookie(obj);
                            String httpDomin = LoginCookieUtils.getHttpDomin(parseCookie);
                            loginCookie = parseCookie.toString();
                            SDKLogger.d(TAG, "add cookie: " + loginCookie);
                            CookieManagerService.getWebViewProxy().setCookie(httpDomin, loginCookie);
                            if (TextUtils.equals(parseCookie.domain, ".taobao.com")) {
                                arrayList.add(parseCookie);
                            }
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                    }
                }
                if (strArr2 != null) {
                    if (strArr2.length > 0 && !arrayList.isEmpty()) {
                        for (LoginCookie parseCookie2 : arrayList) {
                            String str = parseCookie2.domain;
                            for (String loginCookie2 : strArr2) {
                                parseCookie2.domain = loginCookie2;
                                String httpDomin2 = LoginCookieUtils.getHttpDomin(parseCookie2);
                                String loginCookie3 = parseCookie2.toString();
                                SDKLogger.d(TAG, "add cookies to domain:" + loginCookie2 + ", cookie = " + loginCookie3);
                                CookieManagerService.getWebViewProxy().setCookie(httpDomin2, loginCookie3);
                            }
                            parseCookie2.domain = str;
                        }
                    }
                }
                CookieManagerService.getWebViewProxy().flush();
                if (this.mCookies != null) {
                    FileUtils.writeFileData(KernelContext.context, Constants.COOKIES, TextUtils.join(Constants.COOKIE_SPLIT, strArr));
                }
            } else {
                clearCookies();
            }
        }
    }

    public void clearCookies() {
        CookieManagerService.getWebViewProxy().removeSessionCookie();
        if (this.mCookies == null) {
            String readFileData = FileUtils.readFileData(KernelContext.getApplicationContext(), Constants.COOKIES);
            if (!TextUtils.isEmpty(readFileData)) {
                SDKLogger.d(TAG, "get cookie from storage:" + readFileData);
                this.mCookies = TextUtils.split(readFileData, Constants.COOKIE_SPLIT);
            }
        }
        if (this.mCookies != null) {
            Object obj;
            LoginCookie parseCookie;
            String str;
            String httpDomin;
            List<LoginCookie> arrayList = new ArrayList();
            for (Object obj2 : this.mCookies) {
                if (!TextUtils.isEmpty(obj2)) {
                    try {
                        parseCookie = LoginCookieUtils.parseCookie(obj2);
                        if (!"munb".equals(parseCookie.name)) {
                            String httpDomin2 = LoginCookieUtils.getHttpDomin(parseCookie);
                            LoginCookieUtils.expiresCookies(parseCookie);
                            CookieManagerService.getWebViewProxy().setCookie(httpDomin2, parseCookie.toString());
                            if (TextUtils.equals(parseCookie.domain, ".taobao.com")) {
                                arrayList.add(parseCookie);
                            }
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            }
            String[] strArr;
            try {
                String[] strArr2;
                if (CredentialManager.INSTANCE.getInternalSession().otherInfo != null) {
                    obj2 = CredentialManager.INSTANCE.getInternalSession().otherInfo.get("ssoDomainList");
                    if (obj2 != null && (obj2 instanceof ArrayList)) {
                        strArr2 = (String[]) ((ArrayList) obj2).toArray(new String[0]);
                        strArr = strArr2;
                        if (!(strArr == null || strArr.length <= 0 || arrayList.isEmpty())) {
                            for (LoginCookie parseCookie2 : arrayList) {
                                str = parseCookie2.domain;
                                for (String httpDomin3 : strArr) {
                                    if ("munb".equals(parseCookie2.name)) {
                                        parseCookie2.domain = httpDomin3;
                                        httpDomin3 = LoginCookieUtils.getHttpDomin(parseCookie2);
                                        LoginCookieUtils.expiresCookies(parseCookie2);
                                        CookieManagerService.getWebViewProxy().setCookie(httpDomin3, parseCookie2.toString());
                                    }
                                }
                                parseCookie2.domain = str;
                            }
                        }
                        SDKLogger.d(TAG, "injectCookie cookies is null");
                        this.mCookies = null;
                        FileUtils.writeFileData(KernelContext.getApplicationContext(), Constants.COOKIES, "");
                    }
                }
                strArr2 = null;
                strArr = strArr2;
            } catch (Exception e) {
                e.printStackTrace();
                strArr = null;
            }
            for (LoginCookie parseCookie22 : arrayList) {
                str = parseCookie22.domain;
                for (String httpDomin32 : strArr) {
                    if ("munb".equals(parseCookie22.name)) {
                        parseCookie22.domain = httpDomin32;
                        httpDomin32 = LoginCookieUtils.getHttpDomin(parseCookie22);
                        LoginCookieUtils.expiresCookies(parseCookie22);
                        CookieManagerService.getWebViewProxy().setCookie(httpDomin32, parseCookie22.toString());
                    }
                }
                parseCookie22.domain = str;
            }
            SDKLogger.d(TAG, "injectCookie cookies is null");
            this.mCookies = null;
            FileUtils.writeFileData(KernelContext.getApplicationContext(), Constants.COOKIES, "");
        }
        CookieManagerService.getWebViewProxy().removeExpiredCookie();
        CookieManagerService.getWebViewProxy().flush();
    }
}
