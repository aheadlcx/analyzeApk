package cn.v6.sixrooms;

import android.app.Activity;
import android.os.Process;
import cn.v6.sdk.sixrooms.coop.V6Coop;
import cn.v6.sixrooms.room.RoomActivity;
import cn.v6.sixrooms.room.gift.BoxingVoteBean;
import cn.v6.sixrooms.utils.AppCount;
import cn.v6.sixrooms.utils.GlobleValue;
import cn.v6.sixrooms.utils.LogUtils;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Manage {
    private static Manage b;
    private List<Activity> a = new LinkedList();

    private Manage() {
    }

    public static Manage getInstance() {
        if (b == null) {
            b = new Manage();
        }
        return b;
    }

    public void addActivity(Activity activity) {
        LogUtils.e(Manage.class.getSimpleName(), "addActivity  :  " + activity);
        this.a.add(activity);
    }

    public void exit() {
        AppCount.sendAppCountInfo(BoxingVoteBean.BOXING_VOTE_STATE_CLOSE);
        GlobleValue.clearUserBean(V6Coop.getInstance().getContext());
        GlobleValue.status = false;
        for (Activity activity : this.a) {
            LogUtils.e(Manage.class.getSimpleName(), "finish: " + activity);
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        Process.killProcess(Process.myPid());
    }

    public void closeAll() {
        for (Activity activity : this.a) {
            LogUtils.e(Manage.class.getSimpleName(), "finish: " + activity);
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        this.a.clear();
    }

    public boolean removeActivity(Activity activity) {
        return this.a.remove(activity);
    }

    public RoomActivity closeAllAfterRoomActivity() {
        Object obj = null;
        RoomActivity roomActivity = null;
        for (Activity activity : this.a) {
            if (obj == null) {
                if ((activity instanceof RoomActivity) && !activity.isFinishing()) {
                    obj = 1;
                    roomActivity = (RoomActivity) activity;
                }
            } else if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        return roomActivity;
    }

    public void finishActivities(Class... clsArr) {
        List asList = Arrays.asList(clsArr);
        for (Activity activity : this.a) {
            if (asList.contains(activity.getClass())) {
                activity.finish();
            }
        }
    }
}
