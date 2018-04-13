package cn.v6.sixrooms.room;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.widget.PopupWindow.OnDismissListener;
import cn.v6.sixrooms.bean.RoomUpgradeMsg;
import cn.v6.sixrooms.room.popwindows.GodUpgradeWindow;
import cn.v6.sixrooms.room.popwindows.RoomUpgradeWindow;
import cn.v6.sixrooms.room.popwindows.UpgradeWindow;
import java.util.ArrayList;
import java.util.List;

public class RoomUpgradeWindowManager implements OnDismissListener {
    private final int a = 0;
    private Context b;
    private List<RoomUpgradeMsg> c;
    private UpgradeWindow d;
    private Handler e;

    public RoomUpgradeWindowManager(Context context) {
        this.b = context;
    }

    public void addShowMsg(RoomUpgradeMsg roomUpgradeMsg) {
        if ("main".equals(Thread.currentThread().getName())) {
            if (this.e == null) {
                this.e = new RoomUpgradeWindowManager$a(this);
            }
            if (this.d == null || ((this.c == null || this.c.size() == 0) && !a())) {
                a(roomUpgradeMsg);
                return;
            }
            if (this.c == null) {
                this.c = new ArrayList();
            }
            this.c.add(roomUpgradeMsg);
            return;
        }
        throw new RuntimeException("not main thread!");
    }

    private void a(RoomUpgradeMsg roomUpgradeMsg) {
        if (((Activity) this.b).isFinishing()) {
            this.e.removeCallbacksAndMessages(null);
            return;
        }
        if (!roomUpgradeMsg.getType().equals(RoomUpgradeMsg.TYPE_COIN) || roomUpgradeMsg.getRank() < 25 || roomUpgradeMsg.getRank() > 27) {
            this.d = new RoomUpgradeWindow(this.b, this);
        } else {
            this.d = new GodUpgradeWindow(this.b, this);
        }
        this.d.show(roomUpgradeMsg);
    }

    private boolean a() {
        return this.d != null && this.d.isShowing();
    }

    public void release() {
        if (a()) {
            this.d.dismiss();
        }
        if (this.e != null) {
            this.e.removeCallbacksAndMessages(null);
            this.e = null;
        }
        if (this.c != null) {
            this.c.clear();
            this.c = null;
        }
    }

    public void onDismiss() {
        this.d = null;
        if (this.c != null && this.c.size() > 0) {
            this.e.sendEmptyMessage(0);
        }
    }
}
