package cn.v6.sixrooms.surfaceanim.util;

import cn.v6.sixrooms.surfaceanim.animinterface.IAnimSceneType;

public class AnimSceneType implements IAnimSceneType {
    private int a;

    public AnimSceneType(int i) {
        this.a = i;
    }

    public int getIdentification() {
        return this.a;
    }
}
