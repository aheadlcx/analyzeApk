package qsbk.app.core.utils;

import java.util.Comparator;
import qsbk.app.core.model.HitColorData;

class h implements Comparator<HitColorData> {
    final /* synthetic */ ConfigInfoUtil a;

    h(ConfigInfoUtil configInfoUtil) {
        this.a = configInfoUtil;
    }

    public int compare(HitColorData hitColorData, HitColorData hitColorData2) {
        return hitColorData.count - hitColorData2.count;
    }
}
