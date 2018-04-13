package cn.xiaochuankeji.tieba.background.upload;

public class UploadException extends Exception {
    public boolean isNeedRetry = true;

    public UploadException(String str) {
        super("[ErrorMessage]: " + str);
    }

    public UploadException(Throwable th) {
        super(th);
    }

    public UploadException(String str, Throwable th) {
        super("[ErrorMessage]: " + str, th);
    }

    public String getMessage() {
        String message = super.getMessage();
        return getCause() == null ? message : getCause().getMessage() + "\n" + message;
    }
}
