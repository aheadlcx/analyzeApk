package cn.v6.sixrooms.surfaceanim.exception;

public class AnimSceneFrameRegisterException extends RuntimeException {
    public AnimSceneFrameRegisterException() {
        super("注册AnimSceneFrame失败!");
    }

    public AnimSceneFrameRegisterException(String str) {
        super("注册AnimSceneFrame失败! " + str);
    }
}
