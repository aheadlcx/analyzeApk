package cn.v6.sixrooms.surfaceanim.exception;

public class NoAnimSceneFactoryException extends RuntimeException {
    public NoAnimSceneFactoryException() {
        super("没有设置AnimSceneFactory!");
    }
}
