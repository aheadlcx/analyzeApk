package com.qiniu.android.http;

import com.baidu.mobstat.Config;
import com.qiniu.android.collect.UploadInfoCollector.RecordMsg;
import com.qiniu.android.utils.StringUtils;
import com.xiaomi.mipush.sdk.Constants;
import org.eclipse.paho.client.mqttv3.MqttTopic;

final class l extends RecordMsg {
    final /* synthetic */ String a;
    final /* synthetic */ int b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;
    final /* synthetic */ int e;
    final /* synthetic */ double f;
    final /* synthetic */ String g;
    final /* synthetic */ long h;

    l(String str, int i, String str2, String str3, int i2, double d, String str4, long j) {
        this.a = str;
        this.b = i;
        this.c = str2;
        this.d = str3;
        this.e = i2;
        this.f = d;
        this.g = str4;
        this.h = j;
    }

    public String toRecordMsg() {
        String replace = (this.a + "").split(Config.TRACE_TODAY_VISIT_SPLIT)[0].replace(MqttTopic.TOPIC_LEVEL_SEPARATOR, "");
        return StringUtils.join(new String[]{this.b + "", this.c, this.d, replace, this.e + "", this.f + "", this.g, this.h + ""}, Constants.ACCEPT_TIME_SEPARATOR_SP);
    }
}
