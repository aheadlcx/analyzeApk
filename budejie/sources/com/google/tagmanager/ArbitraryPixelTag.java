package com.google.tagmanager;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import com.ali.auth.third.core.model.Constants;
import com.google.analytics.containertag.common.FunctionType;
import com.google.analytics.containertag.common.Key;
import com.google.analytics.midtier.proto.containertag.TypeSystem.Value;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class ArbitraryPixelTag extends TrackingTag {
    private static final String ADDITIONAL_PARAMS = Key.ADDITIONAL_PARAMS.toString();
    static final String ARBITRARY_PIXEL_UNREPEATABLE = ("gtm_" + ID + "_unrepeatable");
    private static final String ID = FunctionType.ARBITRARY_PIXEL.toString();
    private static final String UNREPEATABLE = Key.UNREPEATABLE.toString();
    private static final String URL = Key.URL.toString();
    private static final Set<String> unrepeatableIds = new HashSet();
    private final Context mContext;
    private final HitSenderProvider mHitSenderProvider;

    public interface HitSenderProvider {
        HitSender get();
    }

    /* renamed from: com.google.tagmanager.ArbitraryPixelTag$1 */
    class AnonymousClass1 implements HitSenderProvider {
        final /* synthetic */ Context val$context;

        AnonymousClass1(Context context) {
            this.val$context = context;
        }

        public HitSender get() {
            return DelayedHitSender.getInstance(this.val$context);
        }
    }

    public static String getFunctionId() {
        return ID;
    }

    public ArbitraryPixelTag(Context context) {
        this(context, new AnonymousClass1(context));
    }

    @VisibleForTesting
    ArbitraryPixelTag(Context context, HitSenderProvider hitSenderProvider) {
        super(ID, URL);
        this.mHitSenderProvider = hitSenderProvider;
        this.mContext = context;
    }

    public void evaluateTrackingTag(Map<String, Value> map) {
        String valueToString = map.get(UNREPEATABLE) != null ? Types.valueToString((Value) map.get(UNREPEATABLE)) : null;
        if (valueToString == null || !idProcessed(valueToString)) {
            Builder buildUpon = Uri.parse(Types.valueToString((Value) map.get(URL))).buildUpon();
            Value value = (Value) map.get(ADDITIONAL_PARAMS);
            if (value != null) {
                Object valueToObject = Types.valueToObject(value);
                if (valueToObject instanceof List) {
                    for (Object valueToObject2 : (List) valueToObject2) {
                        if (valueToObject2 instanceof Map) {
                            for (Entry entry : ((Map) valueToObject2).entrySet()) {
                                buildUpon.appendQueryParameter(entry.getKey().toString(), entry.getValue().toString());
                            }
                        } else {
                            Log.e("ArbitraryPixel: additional params contains non-map: not sending partial hit: " + buildUpon.build().toString());
                            return;
                        }
                    }
                }
                Log.e("ArbitraryPixel: additional params not a list: not sending partial hit: " + buildUpon.build().toString());
                return;
            }
            String uri = buildUpon.build().toString();
            this.mHitSenderProvider.get().sendHit(uri);
            Log.v("ArbitraryPixel: url = " + uri);
            if (valueToString != null) {
                synchronized (ArbitraryPixelTag.class) {
                    unrepeatableIds.add(valueToString);
                    SharedPreferencesUtil.saveAsync(this.mContext, ARBITRARY_PIXEL_UNREPEATABLE, valueToString, Constants.SERVICE_SCOPE_FLAG_VALUE);
                }
            }
        }
    }

    private synchronized boolean idProcessed(String str) {
        boolean z = true;
        synchronized (this) {
            if (!idInCache(str)) {
                if (idInSharedPreferences(str)) {
                    unrepeatableIds.add(str);
                } else {
                    z = false;
                }
            }
        }
        return z;
    }

    @VisibleForTesting
    boolean idInSharedPreferences(String str) {
        return this.mContext.getSharedPreferences(ARBITRARY_PIXEL_UNREPEATABLE, 0).contains(str);
    }

    @VisibleForTesting
    void clearCache() {
        unrepeatableIds.clear();
    }

    @VisibleForTesting
    boolean idInCache(String str) {
        return unrepeatableIds.contains(str);
    }
}
