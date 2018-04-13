package com.facebook.imagepipeline.producers;

import android.net.Uri;
import android.util.Base64;
import com.facebook.common.b.a;
import com.facebook.common.memory.g;
import com.facebook.imagepipeline.g.e;
import com.facebook.imagepipeline.request.ImageRequest;
import com.iflytek.speech.VoiceWakeuperAidl;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class k extends y {
    public k(g gVar) {
        super(a.a(), gVar);
    }

    protected e a(ImageRequest imageRequest) throws IOException {
        byte[] a = a(imageRequest.b().toString());
        return a(new ByteArrayInputStream(a), a.length);
    }

    protected String a() {
        return "DataFetchProducer";
    }

    static byte[] a(String str) {
        com.facebook.common.internal.g.a(str.substring(0, 5).equals("data:"));
        int indexOf = str.indexOf(44);
        String substring = str.substring(indexOf + 1, str.length());
        if (b(str.substring(0, indexOf))) {
            return Base64.decode(substring, 0);
        }
        return Uri.decode(substring).getBytes();
    }

    static boolean b(String str) {
        if (!str.contains(VoiceWakeuperAidl.PARAMS_SEPARATE)) {
            return false;
        }
        String[] split = str.split(VoiceWakeuperAidl.PARAMS_SEPARATE);
        return split[split.length - 1].equals("base64");
    }
}
