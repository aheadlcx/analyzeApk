package cn.v6.sixrooms.surfaceanim.animinterface;

public interface IOnAnimDrawListener {
    public static final int STATE_DRAW_BEGIN = 1;
    public static final int STATE_DRAW_END = 2;
    public static final int STATE_DRAW_PAUSE = 3;

    void onDrawState(int i);
}
