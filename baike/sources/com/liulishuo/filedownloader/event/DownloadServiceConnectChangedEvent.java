package com.liulishuo.filedownloader.event;

public class DownloadServiceConnectChangedEvent extends IDownloadEvent {
    public static final String ID = "event.service.connect.changed";
    private final ConnectStatus b;
    private final Class<?> c;

    public enum ConnectStatus {
        connected,
        disconnected,
        lost
    }

    public DownloadServiceConnectChangedEvent(ConnectStatus connectStatus, Class<?> cls) {
        super(ID);
        this.b = connectStatus;
        this.c = cls;
    }

    public ConnectStatus getStatus() {
        return this.b;
    }

    public boolean isSuchService(Class<?> cls) {
        return this.c != null && this.c.getName().equals(cls.getName());
    }
}
