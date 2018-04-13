package com.loc;

import org.eclipse.paho.client.mqttv3.MqttTopic;

public final class at {
    String a;
    String b;
    String c;
    String d;
    String e;
    int f;
    int g;
    private String h;
    private String i;

    public at(String str, String str2) {
        this.h = str;
        this.i = str2;
        try {
            String[] split = str.split(MqttTopic.TOPIC_LEVEL_SEPARATOR);
            int length = split.length;
            if (length > 1) {
                this.a = split[length - 1];
                split = this.a.split("_");
                this.b = split[0];
                this.c = split[2];
                this.d = split[1];
                this.f = Integer.parseInt(split[3]);
                this.g = Integer.parseInt(split[4].split("\\.")[0]);
            }
        } catch (Throwable th) {
            w.a(th, "DexDownloadItem", "DexDownloadItem");
        }
    }

    final String a() {
        return this.h;
    }

    final String b() {
        return this.i;
    }
}
