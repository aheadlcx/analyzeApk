package kotlin.text;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 1}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "", "Lkotlin/text/CharCategory;", "invoke"}, k = 3, mv = {1, 1, 6})
final class a extends Lambda implements Function0<Map<Integer, ? extends CharCategory>> {
    public static final a INSTANCE = new a();

    a() {
        super(0);
    }

    @NotNull
    public final Map<Integer, CharCategory> invoke() {
        Object[] objArr = (Object[]) CharCategory.values();
        Map<Integer, CharCategory> linkedHashMap = new LinkedHashMap(e.coerceAtLeast(ad.mapCapacity(objArr.length), 16));
        for (Object obj : objArr) {
            linkedHashMap.put(Integer.valueOf(((CharCategory) obj).getValue()), obj);
        }
        return linkedHashMap;
    }
}
