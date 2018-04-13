package cn.v6.sixrooms.room.sofa;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.text.TextUtils;
import cn.v6.sixrooms.bean.SofaBean;
import cn.v6.sixrooms.presenter.runnable.SofaControlable;
import cn.v6.sixrooms.room.BaseRoomActivity;
import java.util.HashMap;
import java.util.Map;

public class SofaPresenter implements OnDismissListener, SofaControlable {
    private String a;
    private Map<String, SofaBean> b;
    private BaseRoomActivity c;
    private SofaDialog d;
    private boolean e;

    public SofaPresenter(BaseRoomActivity baseRoomActivity) {
        this.c = baseRoomActivity;
    }

    public void setSofaMap(Map<String, SofaBean> map) {
        this.b = map;
        this.e = true;
        if (this.d != null && this.d.isShowing()) {
            a(map);
        }
    }

    private void a(Map<String, SofaBean> map) {
        for (SofaBean updateSofa : map.values()) {
            this.d.updateSofa(updateSofa);
        }
    }

    public void updateSofa(SofaBean sofaBean) {
        if (this.b == null) {
            this.b = new HashMap();
        }
        this.b.put(sofaBean.getSite(), sofaBean);
        this.e = true;
        if (this.d != null && this.d.isShowing()) {
            this.d.updateSofa(sofaBean);
        }
    }

    public void setRuid(String str) {
        this.a = str;
    }

    public void showDialog(int i) {
        if (this.d == null) {
            this.d = new SofaDialog(this.c);
            this.d.setSofaControl(this);
            this.d.setOnDismissListener(this);
        }
        if (!this.d.isShowing()) {
            this.d.show(i);
            if (this.e) {
                a(this.b);
            }
        }
    }

    public void sendSofa(int i, int i2) {
        if (!TextUtils.isEmpty(this.a)) {
            this.c.sendKickSofa(this.a, i, i2);
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        this.e = false;
    }
}
