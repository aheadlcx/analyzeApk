package cn.xiaochuankeji.tieba.ui.videomaker;

import com.tencent.tinker.android.dx.instruction.Opcodes;
import com.umeng.analytics.a;
import org.json.JSONObject;

public class i {
    private static int[][] g = new int[][]{new int[]{720, 1280}, new int[]{540, 960}, new int[]{536, 960}, new int[]{432, Opcodes.FILL_ARRAY_DATA_PAYLOAD}, new int[]{a.p, 640}};
    public final boolean a;
    public final int b;
    public final int c;
    public final boolean d;
    public final int e;
    public final boolean f;

    public i(JSONObject jSONObject) {
        this.a = jSONObject.optBoolean("videomaker_enabled", true);
        this.b = jSONObject.optInt("preview_width", 720);
        this.c = jSONObject.optInt("hwrecoder_width", 540);
        this.d = jSONObject.optBoolean("hwrecoder_enabled", true);
        this.e = jSONObject.optInt("softrecoder_width", 536);
        this.f = jSONObject.optBoolean("hwprocesser_enabled", true);
    }

    public static int a(int i) {
        for (int i2 = 0; i2 < g.length; i2++) {
            if (g[i2][0] == i) {
                return g[i2][1];
            }
        }
        return (int) (((float) i) / a());
    }

    public static float a() {
        return 0.5625f;
    }

    public static i b() {
        return new i(new JSONObject());
    }
}
