package mtopsdk.mtop.features;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import mtopsdk.common.util.TBSdkLog$LogEnable;
import mtopsdk.common.util.m;

public final class a {
    private static Map a = new HashMap();

    static {
        a(MtopFeatureManager$MtopFeatureEnum.UNIT_INFO_FEATURE, true);
        a(MtopFeatureManager$MtopFeatureEnum.SUPPORT_UTDID_UNIT, true);
        a(MtopFeatureManager$MtopFeatureEnum.DISABLE_X_COMMAND, true);
    }

    public static long a() {
        long j = 0;
        if (!a.isEmpty()) {
            try {
                for (Entry entry : a.entrySet()) {
                    j = ((Boolean) entry.getValue()).booleanValue() ? a((MtopFeatureManager$MtopFeatureEnum) entry.getKey()) | j : j;
                }
            } catch (Exception e) {
                m.c("mtopsdk.MtopFeatureManager", "[getMtopTotalFeatures] get mtop total features error.---" + e.toString());
            }
        }
        return j;
    }

    public static long a(MtopFeatureManager$MtopFeatureEnum mtopFeatureManager$MtopFeatureEnum) {
        return mtopFeatureManager$MtopFeatureEnum == null ? 0 : (long) (1 << ((int) (mtopFeatureManager$MtopFeatureEnum.getFeature() - 1)));
    }

    public static void a(MtopFeatureManager$MtopFeatureEnum mtopFeatureManager$MtopFeatureEnum, boolean z) {
        if (mtopFeatureManager$MtopFeatureEnum != null) {
            a.put(mtopFeatureManager$MtopFeatureEnum, Boolean.valueOf(z));
            if (m.a(TBSdkLog$LogEnable.InfoEnable)) {
                m.b("mtopsdk.MtopFeatureManager", "[setMtopFeatureFlag] set feature=" + mtopFeatureManager$MtopFeatureEnum + " , openFlag=" + z);
            }
        }
    }
}
