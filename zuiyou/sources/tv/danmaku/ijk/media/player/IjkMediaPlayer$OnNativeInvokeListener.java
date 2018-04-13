package tv.danmaku.ijk.media.player;

import android.os.Bundle;

public interface IjkMediaPlayer$OnNativeInvokeListener {
    public static final String ARG_ERROR = "error";
    public static final String ARG_FAMILIY = "family";
    public static final String ARG_FD = "fd";
    public static final String ARG_HTTP_CODE = "http_code";
    public static final String ARG_IP = "ip";
    public static final String ARG_OFFSET = "offset";
    public static final String ARG_PORT = "port";
    public static final String ARG_RETRY_COUNTER = "retry_counter";
    public static final String ARG_SEGMENT_INDEX = "segment_index";
    public static final String ARG_URL = "url";
    public static final int CTRL_DID_TCP_OPEN = 131074;
    public static final int CTRL_WILL_CONCAT_RESOLVE_SEGMENT = 131079;
    public static final int CTRL_WILL_HTTP_OPEN = 131075;
    public static final int CTRL_WILL_LIVE_OPEN = 131077;
    public static final int CTRL_WILL_TCP_OPEN = 131073;
    public static final int EVENT_DID_HTTP_OPEN = 2;
    public static final int EVENT_DID_HTTP_SEEK = 4;
    public static final int EVENT_WILL_HTTP_OPEN = 1;
    public static final int EVENT_WILL_HTTP_SEEK = 3;

    boolean onNativeInvoke(int i, Bundle bundle);
}
