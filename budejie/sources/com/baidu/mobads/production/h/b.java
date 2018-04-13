package com.baidu.mobads.production.h;

import android.content.Context;
import android.widget.RelativeLayout;
import com.baidu.mobad.video.XAdContext;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.ActivityState;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotState;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.VideoState;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.VisitorAction;
import com.baidu.mobads.interfaces.IXAdContainer;
import com.baidu.mobads.interfaces.IXAdInstanceInfo$CreativeType;
import com.baidu.mobads.interfaces.IXAdInternalConstants;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.interfaces.IXAdResponseInfo;
import com.baidu.mobads.interfaces.IXLinearAdSlot;
import com.baidu.mobads.openad.d.c;
import com.baidu.mobads.openad.interfaces.event.IOAdEventDispatcher;
import com.baidu.mobads.production.a;
import com.baidu.mobads.production.u;
import com.baidu.mobads.vo.d;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class b extends a implements IXLinearAdSlot, IOAdEventDispatcher {
    private a w;
    private boolean x;

    public /* synthetic */ IXAdRequestInfo getAdRequestInfo() {
        return q();
    }

    public b(Context context, String str) {
        super(context);
        setId(str);
        this.o = SlotType.SLOT_TYPE_PREROLL;
        this.x = true;
    }

    protected void h() {
        this.m = 8000;
    }

    public void g() {
        this.s.i("XPrerollAdSlot", "afterAdContainerInit()");
        dispatchEvent(new com.baidu.mobads.openad.c.b(com.baidu.mobads.openad.c.b.COMPLETE));
    }

    public void request() {
        int parseInt;
        int parseInt2;
        this.w = new a(getApplicationContext(), getActivity(), this.o, this);
        this.w.d(getId());
        HashMap parameter = getParameter();
        String str = (String) parameter.get(XAdContext.PARAMETER_KEY_OF_BASE_WIDTH);
        String str2 = (String) parameter.get(XAdContext.PARAMETER_KEY_OF_BASE_HEIGHT);
        if (str != null) {
            try {
                parseInt = Integer.parseInt(str);
            } catch (Exception e) {
                parseInt = 0;
            }
        } else {
            parseInt = 0;
        }
        if (str2 != null) {
            try {
                parseInt2 = Integer.parseInt(str2);
            } catch (Exception e2) {
                parseInt2 = 0;
            }
        } else {
            parseInt2 = 0;
        }
        this.w.d(parseInt);
        this.w.e(parseInt2);
        super.a(this.w);
    }

    protected void a(c cVar, u uVar, int i) {
        String str = (String) getParameter().get(IXAdInternalConstants.PARAMETER_KEY_OF_AD_REQUESTING_TIMEOUT);
        if (str != null) {
            try {
                i = Integer.parseInt(str);
            } catch (Exception e) {
            }
        }
        uVar.a(cVar, (double) i);
    }

    protected void c(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        if (iXAdContainer.getAdContainerContext().getAdInstanceInfo().getCreativeType() == IXAdInstanceInfo$CreativeType.STATIC_IMAGE || iXAdContainer.getAdContainerContext().getAdInstanceInfo().getCreativeType() == IXAdInstanceInfo$CreativeType.GIF) {
            JSONObject jSONObject;
            com.baidu.mobads.vo.b bVar = (com.baidu.mobads.vo.b) this.w.d();
            JSONObject attribute = bVar.getAttribute();
            if (attribute == null) {
                jSONObject = new JSONObject();
            } else {
                jSONObject = attribute;
            }
            try {
                jSONObject.put("supportTipView", this.x);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            bVar.a(jSONObject);
            start();
        }
    }

    protected void d(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        Set hashSet = new HashSet();
        hashSet.addAll(iXAdContainer.getAdContainerContext().getAdInstanceInfo().getStartTrackers());
        a(hashSet);
    }

    private void a(Set<String> set) {
        com.baidu.mobads.openad.d.a aVar = new com.baidu.mobads.openad.d.a();
        for (String cVar : set) {
            c cVar2 = new c(cVar, "");
            cVar2.e = 1;
            aVar.a(cVar2, Boolean.valueOf(true));
        }
    }

    protected void e(IXAdContainer iXAdContainer, HashMap<String, Object> hashMap) {
        super.e(iXAdContainer, hashMap);
        this.l = SlotState.COMPLETED;
    }

    public d q() {
        return this.w;
    }

    public void setVideoDisplayBase(RelativeLayout relativeLayout) {
        this.e = relativeLayout;
    }

    public void setActivityState(ActivityState activityState) {
    }

    public void setVideoState(VideoState videoState) {
    }

    public void setParameter(String str, Object obj) {
    }

    public Object getParameter(String str) {
        return null;
    }

    public void setContentVideoAssetCurrentTimePosition(double d) {
    }

    public void notifyVisitorAction(VisitorAction visitorAction) {
    }

    public void setMaxDuration(int i) {
    }

    public void setMaxAdNum(int i) {
    }

    public int getDuration() {
        if (this.h == null) {
            return super.getDuration();
        }
        return (int) this.h.getDuration();
    }

    public int getPlayheadTime() {
        if (this.h == null) {
            return super.getPlayheadTime();
        }
        return (int) this.h.getPlayheadTime();
    }

    public void load() {
        this.q.set(true);
        super.load();
    }

    public void start() {
        if (this.q.get()) {
            super.start();
        } else {
            load();
        }
    }

    public void stop() {
        this.s.i("XPrerollAdSlot", "stop()" + getSlotState().getValue());
        super.stop();
    }

    public void pause() {
        this.s.i("XPrerollAdSlot", "pause()" + getSlotState().getValue());
        if (getSlotState() == SlotState.PLAYING) {
            super.pause();
        }
    }

    public void resume() {
        this.s.i("XPrerollAdSlot", "resume()" + getSlotState().getValue());
        if (getSlotState() == SlotState.PAUSED) {
            super.resume();
        }
    }

    public void setSupportTipView(boolean z) {
        this.x = z;
    }

    public void c(IXAdResponseInfo iXAdResponseInfo) {
    }

    public boolean d() {
        return true;
    }
}
