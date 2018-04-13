package qsbk.app.live.model;

import qsbk.app.core.model.BarrageDecorData;
import qsbk.app.core.utils.ConfigInfoUtil;

public class LiveBarrageMessage extends LiveMessage {
    public LiveBarrageMessageContent m;

    public LiveCommonMessageContent getLiveMessageContent() {
        return this.m;
    }

    public BarrageDecorData getBarrageDecorData() {
        return ConfigInfoUtil.instance().getBarrageDecorData(this.m.b);
    }
}
