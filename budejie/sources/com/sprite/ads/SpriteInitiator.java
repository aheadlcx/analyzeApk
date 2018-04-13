package com.sprite.ads;

import android.content.Context;
import com.sprite.ads.internal.log.ADLog;
import java.util.ArrayList;
import java.util.List;

public class SpriteInitiator {
    public static final String CM_INITIATOR = "com.sprite.ads.third.cmad.CMInitiator";
    public static final String MINTEGRAL_INITIATOR = "com.sprite.ads.third.mintegral.MintegralInitiator";
    public static final String QH_INITIATOR = "com.sprite.ads.third.qh.QHInitiator";
    public static final String TOUTIAO_INITIATOR = "com.sprite.ads.third.toutiao.ToutiaoInitiator";

    public static class Param {
        public String appId;
        public String initiator;

        public Param(String str, String str2) {
            this.initiator = str;
            this.appId = str2;
        }
    }

    private Initiator createInitiator(String str) {
        try {
            return (Initiator) Class.forName(str).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (ClassNotFoundException e3) {
            e3.printStackTrace();
        }
        return null;
    }

    private List<Param> getAll() {
        List<Param> arrayList = new ArrayList();
        arrayList.add(new Param(CM_INITIATOR, "1963"));
        arrayList.add(new Param(QH_INITIATOR, ""));
        arrayList.add(new Param(MINTEGRAL_INITIATOR, ""));
        return arrayList;
    }

    private void initInitiator(Context context, String str, String str2) {
        Initiator createInitiator = createInitiator(str);
        if (createInitiator != null) {
            createInitiator.applicationInit(context, str2);
        }
    }

    public void applicationInit(Context context) {
        applicationInit(context, getAll());
    }

    public void applicationInit(Context context, List<Param> list) {
        if (list == null) {
            ADLog.d("no initiator init");
            return;
        }
        for (Param param : list) {
            initInitiator(context, param.initiator, param.appId);
        }
    }
}
