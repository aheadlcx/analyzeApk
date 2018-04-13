package com.qq.e.comm.services;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.speech.UtilityConfig;
import com.meizu.cloud.pushsdk.handler.impl.model.Statics;
import com.qq.e.comm.managers.GDTADManager;
import com.qq.e.comm.managers.status.SDKStatus;
import com.qq.e.comm.net.NetworkClient.Priority;
import com.qq.e.comm.net.NetworkClientImpl;
import com.qq.e.comm.net.rr.PlainRequest;
import com.qq.e.comm.net.rr.Request;
import com.qq.e.comm.net.rr.Request.Method;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Random;

public class RetCodeService {
    private final Random a;

    static class Holder {
        static final RetCodeService a = new RetCodeService();

        private Holder() {
        }
    }

    public static class RetCodeInfo {
        final String a;
        final String b;
        final String c;
        final int d;
        final int e;
        final int f;
        final int g;
        final int h;

        public RetCodeInfo(String str, String str2, String str3, int i, int i2, int i3, int i4, int i5) {
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = i;
            this.e = i2;
            this.f = i3;
            this.g = i4;
            this.h = i5;
        }

        public String toString() {
            return "RetCodeInfo [host=" + this.a + ", commandid=" + this.b + ", releaseversion=" + this.c + ", resultcode=" + this.d + ", tmcost=" + this.e + ", reqsize=" + this.f + ", rspsize=" + this.g + "]";
        }
    }

    class SendTask implements Runnable {
        private RetCodeInfo a;
        private int b = 100;
        private /* synthetic */ RetCodeService c;

        SendTask(RetCodeService retCodeService, RetCodeInfo retCodeInfo, int i) {
            this.c = retCodeService;
            this.a = retCodeInfo;
        }

        public void run() {
            RetCodeService.a(this.c, this.a, this.b);
        }
    }

    private RetCodeService() {
        this.a = new Random(System.currentTimeMillis());
    }

    private static String a(String str) {
        try {
            return InetAddress.getByName(str).getHostAddress();
        } catch (UnknownHostException e) {
            return "0.0.0.0";
        }
    }

    static /* synthetic */ void a(RetCodeService retCodeService, RetCodeInfo retCodeInfo, int i) {
        Request plainRequest;
        if (retCodeService.a(i)) {
            plainRequest = new PlainRequest("http://wspeed.qq.com/w.cgi", Method.GET, null);
            plainRequest.addQuery("appid", "1000162");
            plainRequest.addQuery("apn", String.valueOf(GDTADManager.getInstance().getDeviceStatus().getNetworkType().getConnValue()));
            plainRequest.addQuery("resultcode", String.valueOf(retCodeInfo.d));
            plainRequest.addQuery("sdkversion", SDKStatus.getSDKVersion());
            plainRequest.addQuery("touin", "");
            plainRequest.addQuery("tmcost", String.valueOf(retCodeInfo.e));
            plainRequest.addQuery("reqsize", String.valueOf(retCodeInfo.f));
            plainRequest.addQuery("rspsize", String.valueOf(retCodeInfo.g));
            plainRequest.addQuery("frequency", String.valueOf(i));
            try {
                String encode = URLEncoder.encode(GDTADManager.getInstance().getDeviceStatus().model, "utf-8");
                plainRequest.addQuery("deviceinfo", encode);
                plainRequest.addQuery(UtilityConfig.KEY_DEVICE_INFO, encode);
                plainRequest.addQuery("commandid", URLEncoder.encode(retCodeInfo.b, "utf-8"));
                plainRequest.addQuery("releaseversion", URLEncoder.encode(retCodeInfo.c, "utf-8"));
                plainRequest.addQuery("serverip", URLEncoder.encode(a(retCodeInfo.a), "utf-8"));
                NetworkClientImpl.getInstance().submit(plainRequest, Priority.Low);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (retCodeService.a(i)) {
            plainRequest = new PlainRequest("http://c.isdspeed.qq.com/code.cgi", Method.GET, null);
            plainRequest.addQuery(SpeechConstant.DOMAIN, retCodeInfo.a);
            plainRequest.addQuery("cgi", retCodeInfo.b);
            plainRequest.addQuery("type", String.valueOf(retCodeInfo.h));
            plainRequest.addQuery("code", String.valueOf(retCodeInfo.d));
            plainRequest.addQuery(Statics.TIME, String.valueOf(retCodeInfo.e));
            plainRequest.addQuery("rate", String.valueOf(i));
            NetworkClientImpl.getInstance().submit(plainRequest, Priority.Low);
        }
    }

    private boolean a(int i) {
        return this.a.nextDouble() < 1.0d / ((double) i);
    }

    public static RetCodeService getInstance() {
        return Holder.a;
    }

    public void send(RetCodeInfo retCodeInfo) {
        new Thread(new SendTask(this, retCodeInfo, 100)).start();
    }
}
