package qsbk.app.model;

import java.io.Serializable;

public class LaiseeVoice extends Laisee implements Serializable {
    public LaiseeVoice(String str, String str2) {
        super(str, str2);
    }

    public LaiseeVoice(String str, String str2, boolean z) {
        super(str, str2, z);
    }

    public LaiseeVoice(LaiseeImInfo laiseeImInfo) {
        super(laiseeImInfo);
    }
}
