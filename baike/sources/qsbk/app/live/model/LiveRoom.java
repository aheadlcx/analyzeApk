package qsbk.app.live.model;

import java.io.Serializable;
import java.util.List;
import qsbk.app.core.model.Activity;
import qsbk.app.core.model.CustomButton;

public class LiveRoom implements Serializable {
    public List<Activity> activities;
    public List<CustomButton> buttons;
    public long roomID;
    public LiveRoomStatus room_status;
    public String srvIP;
    public String sys_msg;

    public boolean isActivitiesValid() {
        return (this.activities == null || this.activities.isEmpty()) ? false : true;
    }
}
