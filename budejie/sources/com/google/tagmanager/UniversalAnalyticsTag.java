package com.google.tagmanager;

import android.content.Context;
import com.google.analytics.containertag.common.FunctionType;
import com.google.analytics.containertag.common.Key;
import com.google.analytics.midtier.proto.containertag.TypeSystem.Value;
import com.google.analytics.tracking.android.Fields;
import com.google.analytics.tracking.android.HitTypes;
import com.google.analytics.tracking.android.Tracker;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.commons.httpclient.HttpState;

class UniversalAnalyticsTag extends TrackingTag {
    private static final String ACCOUNT = Key.ACCOUNT.toString();
    private static final String ANALYTICS_FIELDS = Key.ANALYTICS_FIELDS.toString();
    private static final String ANALYTICS_PASS_THROUGH = Key.ANALYTICS_PASS_THROUGH.toString();
    private static final String DEFAULT_TRACKING_ID = "_GTM_DEFAULT_TRACKER_";
    private static final String ID = FunctionType.UNIVERSAL_ANALYTICS.toString();
    private static final String TRACK_TRANSACTION = Key.TRACK_TRANSACTION.toString();
    private static final String TRANSACTION_DATALAYER_MAP = Key.TRANSACTION_DATALAYER_MAP.toString();
    private static final String TRANSACTION_ITEM_DATALAYER_MAP = Key.TRANSACTION_ITEM_DATALAYER_MAP.toString();
    private static Map<String, String> defaultItemMap;
    private static Map<String, String> defaultTransactionMap;
    private final DataLayer mDataLayer;
    private final TrackerProvider mTrackerProvider;
    private final Set<String> mTurnOffAnonymizeIpValues;

    public static String getFunctionId() {
        return ID;
    }

    public UniversalAnalyticsTag(Context context, DataLayer dataLayer) {
        this(context, dataLayer, new TrackerProvider(context));
    }

    @VisibleForTesting
    UniversalAnalyticsTag(Context context, DataLayer dataLayer, TrackerProvider trackerProvider) {
        super(ID, new String[0]);
        this.mDataLayer = dataLayer;
        this.mTrackerProvider = trackerProvider;
        this.mTurnOffAnonymizeIpValues = new HashSet();
        this.mTurnOffAnonymizeIpValues.add("");
        this.mTurnOffAnonymizeIpValues.add("0");
        this.mTurnOffAnonymizeIpValues.add(HttpState.PREEMPTIVE_DEFAULT);
    }

    private boolean checkBooleanProperty(Map<String, Value> map, String str) {
        Value value = (Value) map.get(str);
        return value == null ? false : Types.valueToBoolean(value).booleanValue();
    }

    public void evaluateTrackingTag(Map<String, Value> map) {
        Tracker tracker = this.mTrackerProvider.getTracker(DEFAULT_TRACKING_ID);
        if (checkBooleanProperty(map, ANALYTICS_PASS_THROUGH)) {
            tracker.send(convertToGaFields((Value) map.get(ANALYTICS_FIELDS)));
        } else if (checkBooleanProperty(map, TRACK_TRANSACTION)) {
            sendTransaction(tracker, map);
        } else {
            Log.w("Ignoring unknown tag.");
        }
        this.mTrackerProvider.close(tracker);
    }

    private String getDataLayerString(String str) {
        Object obj = this.mDataLayer.get(str);
        return obj == null ? null : obj.toString();
    }

    private List<Map<String, String>> getTransactionItems() {
        Object obj = this.mDataLayer.get("transactionProducts");
        if (obj == null) {
            return null;
        }
        if (obj instanceof List) {
            for (Object obj2 : (List) obj) {
                if (!(obj2 instanceof Map)) {
                    throw new IllegalArgumentException("Each element of transactionProducts should be of type Map.");
                }
            }
            return (List) obj;
        }
        throw new IllegalArgumentException("transactionProducts should be of type List.");
    }

    private void addParam(Map<String, String> map, String str, String str2) {
        if (str2 != null) {
            map.put(str, str2);
        }
    }

    private void sendTransaction(Tracker tracker, Map<String, Value> map) {
        String dataLayerString = getDataLayerString("transactionId");
        if (dataLayerString == null) {
            Log.e("Cannot find transactionId in data layer.");
            return;
        }
        List<Map> linkedList = new LinkedList();
        try {
            Map convertToGaFields = convertToGaFields((Value) map.get(ANALYTICS_FIELDS));
            convertToGaFields.put(Fields.HIT_TYPE, HitTypes.TRANSACTION);
            for (Entry entry : getTransactionFields(map).entrySet()) {
                addParam(convertToGaFields, (String) entry.getValue(), getDataLayerString((String) entry.getKey()));
            }
            linkedList.add(convertToGaFields);
            List<Map> transactionItems = getTransactionItems();
            if (transactionItems != null) {
                for (Map map2 : transactionItems) {
                    if (map2.get("name") == null) {
                        Log.e("Unable to send transaction item hit due to missing 'name' field.");
                        return;
                    }
                    Map convertToGaFields2 = convertToGaFields((Value) map.get(ANALYTICS_FIELDS));
                    convertToGaFields2.put(Fields.HIT_TYPE, HitTypes.ITEM);
                    convertToGaFields2.put(Fields.TRANSACTION_ID, dataLayerString);
                    for (Entry entry2 : getTransactionItemFields(map).entrySet()) {
                        addParam(convertToGaFields2, (String) entry2.getValue(), (String) map2.get(entry2.getKey()));
                    }
                    linkedList.add(convertToGaFields2);
                }
            }
            for (Map map22 : linkedList) {
                tracker.send(map22);
            }
        } catch (Throwable e) {
            Log.e("Unable to send transaction", e);
        }
    }

    private Map<String, String> valueToMap(Value value) {
        Object valueToObject = Types.valueToObject(value);
        if (!(valueToObject instanceof Map)) {
            return null;
        }
        Map map = (Map) valueToObject;
        Map<String, String> linkedHashMap = new LinkedHashMap();
        for (Entry entry : map.entrySet()) {
            linkedHashMap.put(entry.getKey().toString(), entry.getValue().toString());
        }
        return linkedHashMap;
    }

    private Map<String, String> convertToGaFields(Value value) {
        if (value == null) {
            return new HashMap();
        }
        Map<String, String> valueToMap = valueToMap(value);
        if (valueToMap == null) {
            return new HashMap();
        }
        String str = (String) valueToMap.get(Fields.ANONYMIZE_IP);
        if (str != null && this.mTurnOffAnonymizeIpValues.contains(str.toLowerCase())) {
            valueToMap.remove(Fields.ANONYMIZE_IP);
        }
        return valueToMap;
    }

    private Map<String, String> getTransactionFields(Map<String, Value> map) {
        Value value = (Value) map.get(TRANSACTION_DATALAYER_MAP);
        if (value != null) {
            return valueToMap(value);
        }
        if (defaultTransactionMap == null) {
            Map hashMap = new HashMap();
            hashMap.put("transactionId", Fields.TRANSACTION_ID);
            hashMap.put("transactionAffiliation", Fields.TRANSACTION_AFFILIATION);
            hashMap.put("transactionTax", Fields.TRANSACTION_TAX);
            hashMap.put("transactionShipping", Fields.TRANSACTION_SHIPPING);
            hashMap.put("transactionTotal", Fields.TRANSACTION_REVENUE);
            hashMap.put("transactionCurrency", Fields.CURRENCY_CODE);
            defaultTransactionMap = hashMap;
        }
        return defaultTransactionMap;
    }

    private Map<String, String> getTransactionItemFields(Map<String, Value> map) {
        Value value = (Value) map.get(TRANSACTION_ITEM_DATALAYER_MAP);
        if (value != null) {
            return valueToMap(value);
        }
        if (defaultItemMap == null) {
            Map hashMap = new HashMap();
            hashMap.put("name", Fields.ITEM_NAME);
            hashMap.put("sku", Fields.ITEM_SKU);
            hashMap.put("category", Fields.ITEM_CATEGORY);
            hashMap.put("price", Fields.ITEM_PRICE);
            hashMap.put("quantity", Fields.ITEM_QUANTITY);
            hashMap.put("currency", Fields.CURRENCY_CODE);
            defaultItemMap = hashMap;
        }
        return defaultItemMap;
    }
}
