package com.baidu.mobad.video;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.widget.RelativeLayout;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.ActivityState;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.ScreenSizeMode;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotState;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.VideoState;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.VisitorAction;
import com.baidu.mobads.interfaces.IXAdContext;
import com.baidu.mobads.interfaces.IXAdInstanceInfo;
import com.baidu.mobads.interfaces.IXAdInternalConstants;
import com.baidu.mobads.interfaces.IXAdManager;
import com.baidu.mobads.interfaces.IXAdProd;
import com.baidu.mobads.interfaces.IXLinearAdSlot;
import com.baidu.mobads.interfaces.event.IXAdEvent;
import com.baidu.mobads.interfaces.utils.IXAdLogger;
import com.baidu.mobads.interfaces.utils.IXAdURIUitls;
import com.baidu.mobads.openad.c.c;
import com.baidu.mobads.openad.d.a;
import com.baidu.mobads.openad.interfaces.event.IOAdEvent;
import com.baidu.mobads.openad.interfaces.event.IOAdEventDispatcher;
import com.baidu.mobads.openad.interfaces.event.IOAdEventListener;
import com.baidu.mobads.production.h.b;
import com.baidu.mobads.utils.XAdSDKFoundationFacade;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class XAdContext implements IXAdContext {
    public static final String PARAMETER_KEY_OF_BASE_HEIGHT = "BASE_HEIGHT";
    public static final String PARAMETER_KEY_OF_BASE_WIDTH = "BASE_WIDTH";
    public static final String TAG = "XAdContext";
    protected IXAdLogger a;
    int b = 0;
    int c = 0;
    private HashMap<String, Object> d = new HashMap();
    private ScreenSizeMode e = ScreenSizeMode.FULL_SCREEN;
    private VideoState f = VideoState.IDLE;
    private ActivityState g = ActivityState.CREATE;
    private VisitorAction h;
    private double i;
    private int j;
    private int k;
    private Context l;
    private String m;
    private Location n;
    private Activity o;
    private RelativeLayout p;
    private final IOAdEventDispatcher q;
    private final XAdSlotManager r;

    public static class AdSlotEventListener implements IOAdEventListener {
        public static final String TAG = "AdSlotEventListener";
        private final Context a;
        private final IXAdProd b;
        private final IOAdEventDispatcher c;

        public AdSlotEventListener(Context context, IXAdProd iXAdProd, IOAdEventDispatcher iOAdEventDispatcher) {
            this.a = context;
            this.b = iXAdProd;
            this.c = iOAdEventDispatcher;
        }

        public void run(IOAdEvent iOAdEvent) {
            XAdSDKFoundationFacade.getInstance().getAdLogger().i(TAG, iOAdEvent.getType());
            XAdSDKFoundationFacade.getInstance().getCommonUtils().a(new b(this, iOAdEvent));
        }
    }

    public XAdContext(Context context, String str, Location location) {
        this.l = context;
        this.m = str;
        this.n = location;
        this.q = new c();
        this.r = new XAdSlotManager();
        this.a = XAdSDKFoundationFacade.getInstance().getAdLogger();
    }

    public void setVideoDisplayBase(RelativeLayout relativeLayout) {
        this.p = relativeLayout;
        setActivity((Activity) this.p.getContext());
        new Handler(getActivity().getMainLooper()).post(new a(this));
    }

    public void setActivity(Activity activity) {
        if (activity != null && this.o == null) {
            this.o = activity;
            if (this.l == null) {
                this.l = this.o.getApplicationContext();
            }
        }
    }

    public Activity getActivity() {
        return this.o;
    }

    public void setActivityState(ActivityState activityState) {
        this.g = activityState;
        this.a.i(TAG, activityState.getValue());
        IXAdProd retrievePrerollAdSlot = this.r.retrievePrerollAdSlot();
        if (retrievePrerollAdSlot != null) {
            if (activityState == ActivityState.PAUSE) {
                retrievePrerollAdSlot.pause();
            }
            if (activityState == ActivityState.RESUME) {
                retrievePrerollAdSlot.resume();
            }
        }
    }

    public void setParameter(String str, Object obj) {
        this.d.put(str, obj);
    }

    public Object getParameter(String str) {
        return this.d.get(str);
    }

    public void setContentVideoScreenMode(ScreenSizeMode screenSizeMode) {
        this.e = screenSizeMode;
        IXAdProd retrievePrerollAdSlot = this.r.retrievePrerollAdSlot();
        if (this.e == ScreenSizeMode.FULL_SCREEN && retrievePrerollAdSlot != null && retrievePrerollAdSlot.getSlotState() == SlotState.PLAYING) {
            IXAdInstanceInfo currentAdInstance = retrievePrerollAdSlot.getCurrentAdInstance();
            if (currentAdInstance != null) {
                int playheadTime = (int) retrievePrerollAdSlot.getCurrentXAdContainer().getPlayheadTime();
                IXAdURIUitls uRIUitls = XAdSDKFoundationFacade.getInstance().getURIUitls();
                List arrayList = new ArrayList();
                List fullScreenTrackers = currentAdInstance.getFullScreenTrackers();
                for (int i = 0; i < fullScreenTrackers.size(); i++) {
                    arrayList.add(uRIUitls.addParameter((String) fullScreenTrackers.get(i), NotificationCompat.CATEGORY_PROGRESS, "" + playheadTime));
                }
                currentAdInstance.setFullScreenTrackers(arrayList);
                Set hashSet = new HashSet();
                hashSet.addAll(currentAdInstance.getFullScreenTrackers());
                a(hashSet);
            }
        }
    }

    private void a(Set<String> set) {
        a aVar = new a();
        for (String cVar : set) {
            com.baidu.mobads.openad.d.c cVar2 = new com.baidu.mobads.openad.d.c(cVar, "");
            cVar2.e = 1;
            aVar.a(cVar2, Boolean.valueOf(true));
        }
    }

    public void setContentVideoState(VideoState videoState) {
        this.f = videoState;
    }

    public void setContentVideoPlayheadTime(double d) {
        this.i = d;
    }

    public void setAdServerRequestingTimeout(int i) {
        this.j = i;
    }

    public void setAdCreativeLoadingTimeout(int i) {
        this.k = i;
    }

    public void submitRequest() {
        IXAdProd retrievePrerollAdSlot = this.r.retrievePrerollAdSlot();
        if (this.j > 0 && this.k > 0) {
            HashMap parameter = retrievePrerollAdSlot.getParameter();
            parameter.put(IXAdInternalConstants.PARAMETER_KEY_OF_AD_REQUESTING_TIMEOUT, "" + this.j);
            parameter.put(IXAdInternalConstants.PARAMETER_KEY_OF_AD_CREATIVE_LOADING_TIMEOUT, "" + this.k);
            parameter.put(PARAMETER_KEY_OF_BASE_WIDTH, "" + this.b);
            parameter.put(PARAMETER_KEY_OF_BASE_HEIGHT, "" + this.c);
            retrievePrerollAdSlot.setParameter(parameter);
        }
        retrievePrerollAdSlot.request();
    }

    public IXLinearAdSlot newPrerollAdSlot(String str, int i, int i2) {
        if (!this.r.containsAdSlot(str).booleanValue()) {
            IXAdProd bVar = new b(this.o, str);
            bVar.setActivity(this.o);
            bVar.setAdSlotBase(this.p);
            bVar.setId(str);
            IOAdEventListener adSlotEventListener = new AdSlotEventListener(this.l, bVar, this.q);
            bVar.removeAllListeners();
            bVar.addEventListener(com.baidu.mobads.openad.c.b.COMPLETE, adSlotEventListener);
            bVar.addEventListener(IXAdEvent.AD_STARTED, adSlotEventListener);
            bVar.addEventListener(IXAdEvent.AD_STOPPED, adSlotEventListener);
            bVar.addEventListener(IXAdEvent.AD_ERROR, adSlotEventListener);
            bVar.addEventListener("AdUserClick", adSlotEventListener);
            this.r.addAdSlot(bVar);
        }
        return this.r.retrievePrerollAdSlot();
    }

    public IXAdProd getSlotById(String str) {
        return this.r.retrieveAdSlotById(str);
    }

    public void addEventListener(String str, IOAdEventListener iOAdEventListener) {
        this.q.addEventListener(str, iOAdEventListener);
    }

    public void removeEventListener(String str, IOAdEventListener iOAdEventListener) {
        this.q.removeEventListener(str, iOAdEventListener);
    }

    public void dispatchEvent(IOAdEvent iOAdEvent) {
    }

    public IXAdManager getXAdManager() {
        return null;
    }

    public void notifyVisitorAction(VisitorAction visitorAction) {
        this.h = visitorAction;
    }

    public void dispose() {
    }

    public void setVideoDisplayBaseWidth(int i) {
        this.b = i;
    }

    public void setVideoDisplayBaseHeight(int i) {
        this.c = i;
    }

    public void setSupportTipView(boolean z) {
        this.r.retrievePrerollAdSlot().setSupportTipView(z);
    }
}
