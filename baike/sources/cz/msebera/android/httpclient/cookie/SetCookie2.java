package cz.msebera.android.httpclient.cookie;

import cz.msebera.android.httpclient.annotation.Obsolete;

public interface SetCookie2 extends SetCookie {
    @Obsolete
    void setCommentURL(String str);

    @Obsolete
    void setDiscard(boolean z);

    @Obsolete
    void setPorts(int[] iArr);
}
