package qsbk.app.model;

import qsbk.app.core.model.CommonVideo;

public class QsbkCommonVideo extends CommonVideo {
    public String live_id;

    public String toString() {
        return super.toString() + "QsbkCommonVideo{" + "live_id='" + this.live_id + '\'' + '}';
    }
}
