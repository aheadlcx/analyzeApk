package com.google.tagmanager;

import com.google.analytics.containertag.proto.Serving.GaExperimentRandom;
import com.google.analytics.containertag.proto.Serving.GaExperimentSupplemental;
import com.google.analytics.containertag.proto.Serving.Supplemental;
import com.google.analytics.midtier.proto.containertag.TypeSystem.Value;
import java.util.Map;

public class ExperimentMacroHelper {
    public static void handleExperimentSupplemental(DataLayer dataLayer, Supplemental supplemental) {
        if (supplemental.experimentSupplemental == null) {
            Log.w("supplemental missing experimentSupplemental");
            return;
        }
        clearKeys(dataLayer, supplemental.experimentSupplemental);
        pushValues(dataLayer, supplemental.experimentSupplemental);
        setRandomValues(dataLayer, supplemental.experimentSupplemental);
    }

    private static void clearKeys(DataLayer dataLayer, GaExperimentSupplemental gaExperimentSupplemental) {
        for (Value valueToString : gaExperimentSupplemental.valueToClear) {
            dataLayer.clearPersistentKeysWithPrefix(Types.valueToString(valueToString));
        }
    }

    private static void pushValues(DataLayer dataLayer, GaExperimentSupplemental gaExperimentSupplemental) {
        for (Value valueToMap : gaExperimentSupplemental.valueToPush) {
            Map valueToMap2 = valueToMap(valueToMap);
            if (valueToMap2 != null) {
                dataLayer.push(valueToMap2);
            }
        }
    }

    private static void setRandomValues(DataLayer dataLayer, GaExperimentSupplemental gaExperimentSupplemental) {
        for (GaExperimentRandom gaExperimentRandom : gaExperimentSupplemental.experimentRandom) {
            if (gaExperimentRandom.key == null) {
                Log.w("GaExperimentRandom: No key");
            } else {
                Object obj = dataLayer.get(gaExperimentRandom.key);
                Long valueOf;
                if (obj instanceof Number) {
                    valueOf = Long.valueOf(((Number) obj).longValue());
                } else {
                    valueOf = null;
                }
                long j = gaExperimentRandom.minRandom;
                long j2 = gaExperimentRandom.maxRandom;
                if (!gaExperimentRandom.retainOriginalValue || r0 == null || r0.longValue() < j || r0.longValue() > j2) {
                    if (j <= j2) {
                        obj = Long.valueOf(Math.round((Math.random() * ((double) (j2 - j))) + ((double) j)));
                    } else {
                        Log.w("GaExperimentRandom: random range invalid");
                    }
                }
                dataLayer.clearPersistentKeysWithPrefix(gaExperimentRandom.key);
                Map expandKeyValue = dataLayer.expandKeyValue(gaExperimentRandom.key, obj);
                if (gaExperimentRandom.lifetimeInMilliseconds > 0) {
                    if (expandKeyValue.containsKey("gtm")) {
                        Object obj2 = expandKeyValue.get("gtm");
                        if (obj2 instanceof Map) {
                            ((Map) obj2).put("lifetime", Long.valueOf(gaExperimentRandom.lifetimeInMilliseconds));
                        } else {
                            Log.w("GaExperimentRandom: gtm not a map");
                        }
                    } else {
                        expandKeyValue.put("gtm", DataLayer.mapOf("lifetime", Long.valueOf(gaExperimentRandom.lifetimeInMilliseconds)));
                    }
                }
                dataLayer.push(expandKeyValue);
            }
        }
    }

    private static Map<Object, Object> valueToMap(Value value) {
        Object valueToObject = Types.valueToObject(value);
        if (valueToObject instanceof Map) {
            return (Map) valueToObject;
        }
        Log.w("value: " + valueToObject + " is not a map value, ignored.");
        return null;
    }
}
