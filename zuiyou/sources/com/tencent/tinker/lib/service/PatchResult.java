package com.tencent.tinker.lib.service;

import java.io.Serializable;

public class PatchResult implements Serializable {
    public long costTime;
    public Throwable e;
    public boolean isSuccess;
    public String patchVersion;
    public String rawPatchFilePath;

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\nPatchResult: \n");
        stringBuffer.append("isSuccess:" + this.isSuccess + "\n");
        stringBuffer.append("rawPatchFilePath:" + this.rawPatchFilePath + "\n");
        stringBuffer.append("costTime:" + this.costTime + "\n");
        if (this.patchVersion != null) {
            stringBuffer.append("patchVersion:" + this.patchVersion + "\n");
        }
        if (this.e != null) {
            stringBuffer.append("Throwable:" + this.e.getMessage() + "\n");
        }
        return stringBuffer.toString();
    }
}
