package cn.v6.sixrooms.room.presenter;

import android.text.TextUtils;
import cn.v6.sixrooms.bean.WrapRoomInfo;
import cn.v6.sixrooms.engine.RoomInfoEngine;
import java.util.concurrent.CopyOnWriteArrayList;

public class InroomPresenter {
    private static volatile InroomPresenter d;
    private RoomInfoEngine a;
    private Playerabel b;
    private Socketable c;
    private WrapRoomInfo e;
    private boolean f = true;
    private CopyOnWriteArrayList<Inroomable> g = new CopyOnWriteArrayList();

    public interface Inroomable {
        void error(int i);

        void handlerError(String str, String str2);

        void setPriv(String str);

        void setWrapRoomInfo(WrapRoomInfo wrapRoomInfo);
    }

    public interface Playerabel {
        void setRtmpURL(String str);
    }

    public interface Socketable {
        void createSocket(WrapRoomInfo wrapRoomInfo);
    }

    private InroomPresenter() {
        if (this.a == null) {
            this.a = new RoomInfoEngine(new d(this));
        }
    }

    public static InroomPresenter getInstance() {
        if (d == null) {
            synchronized (InroomPresenter.class) {
                if (d == null) {
                    d = new InroomPresenter();
                }
            }
        }
        return d;
    }

    public void onDestroy() {
        this.b = null;
        this.c = null;
        this.g.clear();
        this.e = null;
        d = null;
    }

    public void registerPlayer(Playerabel playerabel) {
        this.b = playerabel;
    }

    public void unregisterPlayer() {
        this.b = null;
    }

    public void registerSocket(Socketable socketable) {
        this.c = socketable;
    }

    public void unregisterSocket() {
        this.c = null;
    }

    public void registerInroom(Inroomable inroomable) {
        if (!this.g.contains(inroomable)) {
            this.g.add(inroomable);
        }
    }

    public void unregisterInroom(Inroomable inroomable) {
        this.g.remove(inroomable);
    }

    public WrapRoomInfo getLocalRoomInfo() {
        return this.e;
    }

    public void setLocalRoomInfo(WrapRoomInfo wrapRoomInfo) {
        this.e = wrapRoomInfo;
    }

    public void getNetRoomInfo(String str, String str2, String str3, String str4, String str5) {
        if (this.e == null || !this.e.getRoominfoBean().getRid().equals(str2)) {
            this.f = true;
        } else {
            this.f = false;
        }
        if (TextUtils.isEmpty(str5)) {
            this.a.getRoomInfo(str, str4, str3, str2);
        } else {
            this.a.getRoomInfoByUid(str, str4, str3, str5);
        }
    }

    public void getRtmp(String str) {
        this.a.getRTMPAddress(str);
    }
}
