package cn.xiaochuankeji.tieba.ui.videomaker.edittext;

import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.text.Layout;
import android.text.style.LineBackgroundSpan;
import cn.xiaochuankeji.tieba.ui.utils.e;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class c implements LineBackgroundSpan {
    private final Paint a = new Paint();
    private final List<Path> b;

    public c(int i, Layout layout, float f, float f2) {
        float a = (float) e.a(f2);
        this.a.setAntiAlias(true);
        this.a.setColor(i);
        this.a.setStyle(Style.FILL_AND_STROKE);
        this.a.setPathEffect(new CornerPathEffect(a));
        this.b = a(layout, f, f2, a);
    }

    private List<Path> a(Layout layout, float f, float f2, float f3) {
        int i;
        int lineCount = layout.getLineCount();
        ArrayList arrayList = new ArrayList();
        Object obj = null;
        for (i = 0; i < lineCount; i++) {
            RectF rectF = new RectF(layout.getLineLeft(i), (float) layout.getLineTop(i), layout.getLineRight(i), (float) layout.getLineBottom(i));
            if (rectF.width() > 0.0f) {
                if (obj == null) {
                    obj = new ArrayList();
                }
                obj.add(rectF);
            } else if (obj != null) {
                arrayList.add(obj);
                obj = null;
            }
        }
        if (obj != null) {
            arrayList.add(obj);
        }
        List arrayList2 = new ArrayList();
        for (lineCount = 0; lineCount < arrayList.size(); lineCount++) {
            ArrayList arrayList3 = (ArrayList) arrayList.get(lineCount);
            Path path = new Path();
            arrayList2.add(path);
            int size = arrayList3.size();
            PointF[] pointFArr = new PointF[(size * 2)];
            PointF[] pointFArr2 = new PointF[(size * 2)];
            for (int i2 = 0; i2 < size; i2++) {
                RectF rectF2 = (RectF) arrayList3.get(i2);
                pointFArr[i2 * 2] = new PointF(rectF2.left - f, rectF2.top - f2);
                pointFArr[(i2 * 2) + 1] = new PointF(rectF2.left - f, rectF2.bottom + f2);
                pointFArr2[i2 * 2] = new PointF(rectF2.right + f, rectF2.top - f2);
                pointFArr2[(i2 * 2) + 1] = new PointF(rectF2.right + f, rectF2.bottom + f2);
            }
            a(pointFArr, false);
            a(pointFArr2, true);
            ArrayList a = a(pointFArr, f3, false);
            ArrayList a2 = a(pointFArr2, f3, true);
            PointF pointF = (PointF) a.get(0);
            path.moveTo(pointF.x, pointF.y);
            for (i = 1; i < a.size(); i++) {
                pointF = (PointF) a.get(i);
                path.lineTo(pointF.x, pointF.y);
            }
            for (i = a2.size() - 1; i >= 0; i--) {
                pointF = (PointF) a2.get(i);
                path.lineTo(pointF.x, pointF.y);
            }
            path.close();
        }
        return arrayList2;
    }

    private static void a(PointF[] pointFArr, boolean z) {
        int i = 2;
        if (pointFArr.length > 2) {
            while (i < pointFArr.length) {
                PointF pointF = pointFArr[i - 1];
                PointF pointF2 = pointFArr[i];
                if (z) {
                    if (pointF.x < pointF2.x) {
                        pointF.y = pointF2.y;
                    } else {
                        pointF2.y = pointF.y;
                    }
                } else if (pointF.x < pointF2.x) {
                    pointF2.y = pointF.y;
                } else {
                    pointF.y = pointF2.y;
                }
                i += 2;
            }
        }
    }

    private static ArrayList<PointF> a(PointF[] pointFArr, float f, boolean z) {
        ArrayList<PointF> arrayList = new ArrayList();
        Collections.addAll(arrayList, pointFArr);
        int i = 2;
        while (i < arrayList.size()) {
            int i2;
            PointF pointF = (PointF) arrayList.get(i - 1);
            PointF pointF2 = (PointF) arrayList.get(i);
            if (Math.abs(pointF.x - pointF2.x) < f) {
                PointF pointF3 = (PointF) arrayList.get(i - 2);
                PointF pointF4 = (PointF) arrayList.get(i + 1);
                if (z) {
                    if (pointF.x < pointF2.x) {
                        pointF3.x = pointF4.x;
                    } else {
                        pointF4.x = pointF3.x;
                    }
                } else if (pointF.x < pointF2.x) {
                    pointF4.x = pointF3.x;
                } else {
                    pointF3.x = pointF4.x;
                }
                arrayList.remove(i - 1);
                arrayList.remove(i - 1);
                i2 = i;
            } else {
                i2 = i + 2;
            }
            i = i2;
        }
        return arrayList;
    }

    public void drawBackground(Canvas canvas, Paint paint, int i, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6, int i7, int i8) {
        for (Path drawPath : this.b) {
            canvas.drawPath(drawPath, this.a);
        }
    }
}
