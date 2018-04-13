package cn.xiaochuankeji.tieba.ui.videomaker.sticker.a;

import android.support.annotation.DrawableRes;
import cn.xiaochuankeji.tieba.R;
import cn.xiaochuankeji.tieba.ui.videomaker.sticker.entity.a;
import java.util.LinkedList;

public class b {
    public static String a = "贴纸";
    private static LinkedList<a> b = new LinkedList();

    static {
        b.add(a(R.drawable.s_v_1_double_57, R.drawable.s_v_1_double, 0.57f));
        b.add(a(R.drawable.s_v_2_line_57, R.drawable.s_v_2_line, 0.57f));
        b.add(a(R.drawable.s_v_3_wavy_line_57, R.drawable.s_v_3_wavy_line, 0.57f));
        b.add(a(R.drawable.s_v_4_circle_67, R.drawable.s_v_4_circle, 0.3f));
        b.add(a(R.drawable.s_v_5_drops_9, R.drawable.s_v_5_drops, 0.09f));
        b.add(a(R.drawable.s_v_6_square_22, R.drawable.s_v_6_square, 0.22f));
        b.add(a(R.drawable.s_v_7_cloud_30, R.drawable.s_v_7_cloud, 0.3f));
        b.add(a(R.drawable.s_v_8_circle_frame_67, R.drawable.s_v_8_circle_frame, 0.3f));
        b.add(a(R.drawable.s_v_9_curve_26, R.drawable.s_v_9_curve, 0.26f));
        b.add(a(R.drawable.s_v_10_line_frame_47, R.drawable.s_v_10_line_frame, 0.47f));
        b.add(a(R.drawable.s_v_11_square_frame_22, R.drawable.s_v_11_square_frame, 0.22f));
        b.add(a(R.drawable.s_v_12_triangle_26, R.drawable.s_v_12_triangle, 0.26f));
        b.add(a(R.drawable.s_v_13_17, R.drawable.s_v_13, 0.17f));
        b.add(a(R.drawable.s_v_14_22, R.drawable.s_v_14, 0.22f));
        b.add(a(R.drawable.s_v_15_10, R.drawable.s_v_15, 0.04f));
        b.add(a(R.drawable.s_v_16_22, R.drawable.s_v_16, 0.22f));
        b.add(a(R.drawable.s_v_17_17, R.drawable.s_v_17, 0.17f));
        b.add(a(R.drawable.s_v_18_47, R.drawable.s_v_18, 0.47f));
    }

    public static void a() {
        a = "贴纸";
    }

    private static a a(@DrawableRes int i, @DrawableRes int i2, float f) {
        a aVar = new a();
        aVar.m = i;
        aVar.n = i2;
        aVar.s = f;
        aVar.o = "贴纸";
        return aVar;
    }

    public static LinkedList<a> b() {
        return b;
    }
}
