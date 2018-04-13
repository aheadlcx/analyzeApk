package com.baidu.mobad.video;

import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotState;
import com.baidu.mobads.interfaces.IXAdProd;

class a implements Runnable {
    final /* synthetic */ XAdContext a;

    a(XAdContext xAdContext) {
        this.a = xAdContext;
    }

    public void run() {
        IXAdProd retrievePrerollAdSlot = this.a.r.retrievePrerollAdSlot();
        if (retrievePrerollAdSlot != null && retrievePrerollAdSlot.getSlotState() == SlotState.PLAYING) {
            retrievePrerollAdSlot.resize();
        }
    }
}
