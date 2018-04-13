package cn.v6.sixrooms.room.red;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import cn.v6.sixrooms.listener.RedPackgeLisener;
import cn.v6.sixrooms.room.RoomActivity;
import cn.v6.sixrooms.room.red.DragRedPackagePopupWindow.IRedPackageItem;
import cn.v6.sixrooms.utils.ToastUtils;
import cn.v6.sixrooms.view.interfaces.OnRoomTypeChangeListener;
import java.util.HashMap;

public class DragPopupWindowManager implements RedPackgeLisener, IRedPackageItem, OnRoomTypeChangeListener {
    private boolean a = false;
    private HashMap<String, DragRedPackagePopupWindow> b = new HashMap();
    private IRedPackage c = null;
    private Activity d;
    private final int e = 1;
    private final int f = 2;
    private final int g = 3;
    private final int h = 4;
    private final int i = 5;
    private final int j = 6;
    private View k;
    private int l;
    private Handler m = new a(this);

    public interface IRedPackage {
        void clickRedPackage(String str);
    }

    public DragPopupWindowManager(Activity activity, View view, IRedPackage iRedPackage, int i) {
        this.c = iRedPackage;
        this.k = view;
        this.d = activity;
        this.l = i;
    }

    private void a(String... strArr) {
        for (int i = 0; i < strArr.length; i++) {
            if (!this.b.containsKey(strArr[i])) {
                boolean z;
                Context context = this.d;
                int i2 = this.l;
                if (this.b.size() == 0) {
                    z = true;
                } else {
                    z = false;
                }
                DragRedPackagePopupWindow instance = DragRedPackagePopupWindow.getInstance(context, this, 2, i2, z);
                this.b.put(strArr[i], instance);
                instance.setPackageID(strArr[i]);
                instance.showAtLPosition(this.k);
                instance.showLottery();
            }
        }
    }

    public void updatePckageTime(String str, int i) {
        if (this.b.containsKey(str)) {
            ((DragRedPackagePopupWindow) this.b.get(str)).updateTime(i);
        }
    }

    public void clickPackage(String str) {
        if (this.c != null) {
            this.c.clickRedPackage(str);
        }
    }

    public void onClickEnd(String str) {
        this.b.remove(str);
    }

    public void onReceiveBigFireworks(String[] strArr) {
        Bundle bundle = new Bundle();
        bundle.putStringArray("redid", strArr);
        Message message = new Message();
        message.what = 1;
        message.setData(bundle);
        this.m.sendMessage(message);
    }

    public void onReceiveSuperFireworks(String[] strArr, String[] strArr2) {
        Bundle bundle = new Bundle();
        bundle.putStringArray("id", strArr);
        bundle.putStringArray("arrayTime", strArr2);
        Message message = new Message();
        message.what = 2;
        message.setData(bundle);
        this.m.sendMessage(message);
    }

    public void onReceiveFireworksTimeEnd(String[] strArr, String[] strArr2) {
        Bundle bundle = new Bundle();
        bundle.putStringArray("id", strArr);
        bundle.putStringArray("redid", strArr2);
        Message message = new Message();
        message.what = 3;
        message.setData(bundle);
        this.m.sendMessage(message);
    }

    public void onGetSuccResult(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString("redid", str);
        bundle.putString("content", str2);
        Message message = new Message();
        message.what = 4;
        message.setData(bundle);
        this.m.sendMessage(message);
    }

    public void onGetFailResult(String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        bundle.putString("redid", str);
        bundle.putString("content", str3);
        bundle.putString("state", str2);
        Message message = new Message();
        message.what = 5;
        message.setData(bundle);
        this.m.sendMessage(message);
    }

    public void onRedPackageNone(String str) {
        Message message = new Message();
        Bundle bundle = new Bundle();
        bundle.putString("redid", str);
        message.setData(bundle);
        message.what = 6;
        this.m.sendMessage(message);
    }

    public void destoryPopupWindow() {
        for (String str : this.b.keySet()) {
            ((DragRedPackagePopupWindow) this.b.get(str)).dismiss();
        }
        this.b.clear();
        this.m.removeCallbacksAndMessages(null);
        this.a = true;
    }

    public void restart() {
        this.a = false;
    }

    public void onRoomTypeChange(int i) {
        this.l = i;
        for (String str : this.b.keySet()) {
            ((DragRedPackagePopupWindow) this.b.get(str)).setScreentStyle(i);
        }
    }

    static /* synthetic */ void a(DragPopupWindowManager dragPopupWindowManager, String[] strArr, String[] strArr2) {
        int i = 0;
        while (i < strArr.length) {
            try {
                if (dragPopupWindowManager.b.containsKey(strArr[i])) {
                    ((DragRedPackagePopupWindow) dragPopupWindowManager.b.get(strArr[i])).updateTime(Integer.parseInt(strArr2[i]));
                } else {
                    DragRedPackagePopupWindow instance = DragRedPackagePopupWindow.getInstance(dragPopupWindowManager.d, dragPopupWindowManager, 1, dragPopupWindowManager.l, dragPopupWindowManager.b.size() == 0);
                    instance.setPackageID(strArr[i]);
                    dragPopupWindowManager.b.put(strArr[i], instance);
                    instance.showAtLPosition(dragPopupWindowManager.k);
                    instance.updateTime(Integer.parseInt(strArr2[i]));
                }
                i++;
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    static /* synthetic */ void b(DragPopupWindowManager dragPopupWindowManager, String[] strArr, String[] strArr2) {
        for (int i = 0; i < strArr.length; i++) {
            if (dragPopupWindowManager.b.containsKey(strArr[i])) {
                DragRedPackagePopupWindow dragRedPackagePopupWindow = (DragRedPackagePopupWindow) dragPopupWindowManager.b.remove(strArr[i]);
                dragRedPackagePopupWindow.openLottery(strArr2[i]);
                dragPopupWindowManager.b.put(strArr2[i], dragRedPackagePopupWindow);
            } else {
                dragPopupWindowManager.a(strArr2[i]);
            }
        }
    }

    static /* synthetic */ void a(DragPopupWindowManager dragPopupWindowManager, String str, int i) {
        if (dragPopupWindowManager.b.containsKey(str)) {
            ((DragRedPackagePopupWindow) dragPopupWindowManager.b.get(str)).setResult(i);
        }
    }

    static /* synthetic */ void a(DragPopupWindowManager dragPopupWindowManager, String str, String str2, String str3) {
        if (!dragPopupWindowManager.b.containsKey(str)) {
            return;
        }
        if (str2.equals(RoomActivity.VIDEOTYPE_UNKNOWN)) {
            ((DragRedPackagePopupWindow) dragPopupWindowManager.b.get(str)).setResult(3);
        } else if (dragPopupWindowManager.d != null) {
            ToastUtils.showToast(str3);
        }
    }

    static /* synthetic */ void a(DragPopupWindowManager dragPopupWindowManager, String str) {
        if (dragPopupWindowManager.b.containsKey(str)) {
            ((DragRedPackagePopupWindow) dragPopupWindowManager.b.get(str)).setPackageEnd();
        }
    }
}
