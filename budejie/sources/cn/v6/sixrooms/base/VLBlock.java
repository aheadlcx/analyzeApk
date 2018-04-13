package cn.v6.sixrooms.base;

public abstract class VLBlock {
    protected String mCreateDesc;
    protected boolean mFlag = false;
    protected BlockItem mRefBlockItem = null;

    protected abstract void process(boolean z);

    public VLBlock() {
        if (SixRoomsUtils.appIsDebug()) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            StackTraceElement stackTraceElement2 = Thread.currentThread().getStackTrace()[4];
            this.mCreateDesc = stackTraceElement2.getClassName() + "::" + stackTraceElement2.getMethodName() + "(" + stackTraceElement2.getFileName() + ":" + stackTraceElement2.getLineNumber() + ") ** " + stackTraceElement.getClassName() + "::" + stackTraceElement.getMethodName() + "(" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + ")";
            return;
        }
        this.mCreateDesc = "";
    }
}
