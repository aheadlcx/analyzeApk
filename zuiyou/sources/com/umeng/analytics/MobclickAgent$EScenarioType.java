package com.umeng.analytics;

import com.tencent.tinker.android.dx.instruction.Opcodes;

public enum MobclickAgent$EScenarioType {
    E_UM_NORMAL(0),
    E_UM_GAME(1),
    E_UM_ANALYTICS_OEM(Opcodes.SHL_INT_LIT8),
    E_UM_GAME_OEM(Opcodes.SHR_INT_LIT8);
    
    private int a;

    private MobclickAgent$EScenarioType(int i) {
        this.a = i;
    }

    public int toValue() {
        return this.a;
    }
}
