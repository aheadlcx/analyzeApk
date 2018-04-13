package cn.xiaochuankeji.tieba.background.screen;

public interface Observer {

    public enum ScreenStatus {
        SCREEN_OFF,
        SCREEN_ON,
        USER_PRESENT
    }

    void a(ScreenStatus screenStatus);
}
