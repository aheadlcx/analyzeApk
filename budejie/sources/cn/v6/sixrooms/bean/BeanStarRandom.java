package cn.v6.sixrooms.bean;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import cn.v6.sixrooms.animation.InterfaceAnimationDraw;
import cn.v6.sixrooms.socket.common.SocketUtil;
import com.alibaba.wireless.security.SecExceptionCode;
import com.budejie.www.R$styleable;
import java.lang.reflect.Array;
import java.util.HashMap;

public class BeanStarRandom implements InterfaceAnimationDraw {
    private float degrees = 15.0f;
    private int[][] distance = new int[][]{new int[]{0, 30, 60, 120, 150, 210, 300, 360}, new int[]{0, 30, 60, 90, 120, 180, 210, 270, 300, 360, R$styleable.Theme_Custom_new_detail_content_bg, 420, 480, 510, 570, 600}};
    private int distanceIndex = 0;
    private int[] distanceTimeArray = new int[]{0, 0, 0, 450, 0, 0, 0, 450, 0, 0, 450, 0, 0, 0, 450, 0};
    private int[] duration = new int[]{450, 630};
    private HashMap<Integer, int[][]> mapStar = new HashMap();
    private int maxY;
    private int[][] offsetXArray = ((int[][]) Array.newInstance(Integer.TYPE, new int[]{2, 16}));
    private int[][] offsetYArray = ((int[][]) Array.newInstance(Integer.TYPE, new int[]{2, 16}));
    private int[][][] randomX;
    private int[] randomY = new int[]{0, 110, 0, 0, 0, 0, 225, 0, 0, 200, 150, 0, 200, 0, 150, 0};
    private float[] scaleArray = new float[]{0.613f, 0.413f, 0.413f, 0.613f, 0.413f, 0.613f, 0.613f, 0.413f, 0.613f, 0.613f, 0.413f, 0.413f, 0.613f, 0.613f, 0.413f, 0.413f};
    private int screenHeight;
    private int[][] starTimeArray = ((int[][]) Array.newInstance(Integer.TYPE, new int[]{2, 16}));

    public BeanStarRandom(int i, float f, int i2) {
        int i3 = 0;
        r0 = new int[2][][];
        r0[0] = new int[][]{new int[]{R$styleable.Theme_Custom_choose_contact_item_arrow_icon, R$styleable.Theme_Custom_ic_follows_sinafriend}, new int[]{600, 630}, new int[]{720, 720}, new int[]{450, 480}, new int[]{R$styleable.Theme_Custom_recyclerview_load_image_background, R$styleable.Theme_Custom_search_history_background}, new int[]{530, 560}, new int[]{720, 720}, new int[]{660, 690}};
        r0[1] = new int[][]{new int[]{R$styleable.Theme_Custom_choose_contact_item_arrow_icon, R$styleable.Theme_Custom_last_refresh_item_text_theme}, new int[]{1250, 1250}, new int[]{330, 400}, new int[]{1130, 1230}, new int[]{SecExceptionCode.SEC_ERROR_UMID_VALID, SecExceptionCode.SEC_ERROR_UMID_VALID}, new int[]{1030, SecExceptionCode.SEC_ERROR_OPENSDK}, new int[]{530, 560}, new int[]{780, 850}, new int[]{660, 760}, new int[]{570, 575}, new int[]{420, 500}, new int[]{100, 100}, new int[]{SecExceptionCode.SEC_ERROR_MALDETECT, SecExceptionCode.SEC_ERROR_MALDETECT}, new int[]{870, 1000}, new int[]{410, SocketUtil.TYPEID_430}, new int[]{200, 200}};
        this.randomX = r0;
        this.maxY = i;
        this.screenHeight = i2;
        for (int i4 = 0; i4 < this.randomX.length; i4++) {
            for (int i5 = 0; i5 < this.randomX[i4].length; i5++) {
                for (int i6 = 0; i6 < this.randomX[i4][i5].length; i6++) {
                    int[] iArr = this.randomX[i4][i5];
                    iArr[i6] = (int) (((float) iArr[i6]) * f);
                }
            }
        }
        while (i3 < this.randomY.length) {
            int[] iArr2 = this.randomY;
            iArr2[i3] = (int) (((float) iArr2[i3]) * f);
            i3++;
        }
    }

    private Object[] getXyScale(int i, int i2) {
        int intValue;
        int[][] iArr;
        Object obj;
        Object[] objArr = new Object[2];
        for (Integer intValue2 : this.mapStar.keySet()) {
            intValue = intValue2.intValue();
            if (i - this.starTimeArray[i2][intValue] > this.duration[i2]) {
                this.starTimeArray[i2][intValue] = this.distanceTimeArray[intValue] + i;
                randomXY(intValue, this.starTimeArray[i2][intValue]);
            }
        }
        intValue = this.mapStar.size();
        if (intValue < this.randomX[i2].length && this.distanceIndex < this.distance[i2].length && (this.distance[i2][this.distanceIndex] == 0 || i % this.distance[i2][this.distanceIndex] == 0)) {
            this.distanceIndex++;
            randomXY(intValue, i);
        }
        Object obj2 = new float[this.mapStar.size()];
        int[][] iArr2 = (int[][]) Array.newInstance(Integer.TYPE, new int[]{this.mapStar.size(), 2});
        int i3 = 0;
        for (Integer intValue3 : this.mapStar.keySet()) {
            int i4;
            int intValue4 = intValue3.intValue();
            if (i >= this.starTimeArray[i2][intValue4]) {
                iArr = (int[][]) this.mapStar.get(Integer.valueOf(intValue4));
                int i5 = (this.offsetXArray[i2][intValue4] * (i - this.starTimeArray[i2][intValue4])) / this.duration[i2];
                int i6 = (this.offsetYArray[i2][intValue4] * (i - this.starTimeArray[i2][intValue4])) / this.duration[i2];
                iArr2[i3] = new int[]{i5 + iArr[i2][0], iArr[i2][1] + i6};
                obj2[i3] = this.scaleArray[intValue4];
                i4 = i3 + 1;
            } else {
                i4 = i3;
            }
            i3 = i4;
        }
        if (i3 < this.mapStar.size()) {
            Object obj3 = new float[i3];
            iArr = (int[][]) Array.newInstance(Integer.TYPE, new int[]{i3, 2});
            for (intValue4 = 0; intValue4 < i3; intValue4++) {
                iArr[intValue4] = iArr2[intValue4];
                obj3[intValue4] = obj2[intValue4];
            }
            obj = obj3;
        } else {
            iArr = iArr2;
            obj = obj2;
        }
        objArr[0] = iArr;
        objArr[1] = obj;
        return objArr;
    }

    private void randomXY(int i, int i2) {
        int i3;
        int[] iArr = new int[2];
        for (i3 = 0; i3 < 2; i3++) {
            int i4 = this.randomX[i3][i][0];
            iArr[i3] = (int) ((((double) (this.randomX[i3][i][1] - i4)) * Math.random()) + ((double) i4));
        }
        int[] iArr2 = new int[2];
        int[] iArr3 = new int[2];
        for (i3 = 0; i3 < 2; i3++) {
            if (this.randomY[i] > 100) {
                iArr2[i3] = (int) (((double) (this.randomY[i] - 100)) + (Math.random() * 100.0d));
            } else {
                iArr2[i3] = (int) (Math.random() * ((double) this.randomY[i]));
            }
        }
        iArr3[0] = (int) (((float) (this.maxY - iArr2[0])) - (310.0f * this.scaleArray[i]));
        iArr3[1] = (int) (((float) (this.screenHeight - iArr2[1])) + (310.0f * this.scaleArray[i]));
        int[] iArr4 = new int[2];
        for (i3 = 0; i3 < 2; i3++) {
            iArr4[i3] = (int) ((-1.0d * Math.tan((((double) this.degrees) * 3.141592653589793d) / 180.0d)) * ((double) iArr3[i3]));
            this.offsetXArray[i3][i] = iArr4[i3];
            this.offsetYArray[i3][i] = iArr3[i3];
            this.starTimeArray[i3][i] = i2;
        }
        HashMap hashMap = this.mapStar;
        Integer valueOf = Integer.valueOf(i);
        r4 = new int[2][];
        r4[0] = new int[]{iArr[0], iArr2[0]};
        r4[1] = new int[]{iArr[1], iArr2[1]};
        hashMap.put(valueOf, r4);
    }

    public void draw(Canvas canvas, Paint paint, Bitmap bitmap, int i, int i2, int i3, int i4) {
        int i5 = (i4 == 3 || i4 == 2) ? 1 : 0;
        Object[] xyScale = getXyScale(i3, i5);
        if (xyScale[0] != null) {
            int[][] iArr = (int[][]) xyScale[0];
            float[] fArr = (float[]) xyScale[1];
            for (int i6 = 0; i6 < iArr.length; i6++) {
                Matrix matrix = new Matrix();
                matrix.postScale(fArr[i6], fArr[i6]);
                matrix.postRotate(this.degrees, (fArr[i6] * ((float) bitmap.getWidth())) / 2.0f, (fArr[i6] * ((float) bitmap.getHeight())) / 2.0f);
                matrix.postTranslate((float) iArr[i6][0], (float) iArr[i6][1]);
                canvas.drawBitmap(bitmap, matrix, paint);
            }
        }
    }
}
