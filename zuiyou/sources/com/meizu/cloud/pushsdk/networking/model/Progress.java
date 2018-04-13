package com.meizu.cloud.pushsdk.networking.model;

import java.io.Serializable;

public class Progress implements Serializable {
    public long currentBytes;
    public long totalBytes;

    public Progress(long j, long j2) {
        this.currentBytes = j;
        this.totalBytes = j2;
    }
}
