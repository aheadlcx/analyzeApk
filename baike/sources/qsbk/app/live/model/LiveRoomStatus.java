package qsbk.app.live.model;

import java.io.Serializable;

public class LiveRoomStatus implements Serializable {
    public static final int CLOSE = 0;
    public static final int RUNNING = 1;
    public static final int SUSPEND = 2;
    public boolean beauty;
    public String filter;
    public String message;
    public int status;
}
