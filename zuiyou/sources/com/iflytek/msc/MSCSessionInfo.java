package com.iflytek.msc;

public class MSCSessionInfo {
    public byte[] buffer;
    public int buflen;
    public int epstatues;
    public int errorcode;
    public int rsltstatus;
    public int sesstatus;

    public MSCSessionInfo() {
        this.buffer = null;
        this.buflen = -1;
        this.buffer = null;
        this.sesstatus = -1;
        this.rsltstatus = 2;
    }
}
