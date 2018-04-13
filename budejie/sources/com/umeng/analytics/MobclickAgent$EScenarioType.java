package com.umeng.analytics;

public enum MobclickAgent$EScenarioType {
    E_UM_NORMAL(0),
    E_UM_GAME(1),
    E_UM_ANALYTICS_OEM(224),
    E_UM_GAME_OEM(225);
    
    private int a;

    private MobclickAgent$EScenarioType(int i) {
        this.a = i;
    }

    public int toValue() {
        return this.a;
    }
}
