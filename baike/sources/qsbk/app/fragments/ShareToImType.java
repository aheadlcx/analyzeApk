package qsbk.app.fragments;

public enum ShareToImType {
    TYPE_ARTICLE(0),
    TYPE_WEB(1),
    TYPE_CIRCLE_TOPIC(2),
    TYPE_LIVE(3),
    TYPE_CIRCLE_ARTICLE(4),
    TYPE_LIVE_ACTIVITY(5),
    TYPE_NEWS(6),
    TYPE_QSJX(7),
    TYPE_QIUSHI_TOPIC(8);
    
    private int a;

    private ShareToImType(int i) {
        this.a = i;
    }

    public int getValue() {
        return this.a;
    }
}
