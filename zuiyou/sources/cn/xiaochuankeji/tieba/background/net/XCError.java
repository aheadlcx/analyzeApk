package cn.xiaochuankeji.tieba.background.net;

public class XCError extends Exception {
    public static final int ERROR_EMPTY = 9999;
    private static final long serialVersionUID = 1722376114976523524L;
    private int mErrCode;

    public XCError(String str) {
        super(str);
    }

    public int getErrCode() {
        return this.mErrCode;
    }

    public void setErrCode(int i) {
        this.mErrCode = i;
    }
}
