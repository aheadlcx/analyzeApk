package cn.xiaochuankeji.badge;

public class ShortcutBadgeException extends Exception {
    public ShortcutBadgeException(String str) {
        super(str);
    }

    public ShortcutBadgeException(String str, Exception exception) {
        super(str, exception);
    }
}
