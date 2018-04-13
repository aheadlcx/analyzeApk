package qsbk.app.im.image;

import android.content.Context;
import qsbk.app.activity.publish.Publish_Image;

public final class IMImageSizeHelper {
    public static final int ASPECT = 125;
    public static final int LONGMAXMULTIPLE = 2;
    public static int MAX = Publish_Image.MIN_IMG_HEIGHT;
    public static int MIN = 100;

    public enum Size {
        Big,
        Medium,
        Small
    }

    public static void set(int i, int i2) {
        MAX = i;
        MIN = i2;
    }

    public static IMImageSize getImageSize(Size size, int i, int i2, Context context) {
        IMImageSize iMImageSize = new IMImageSize(i, i2);
        properSize(iMImageSize, size, context);
        return iMImageSize;
    }

    public static int getSuggestSize(Context context) {
        return (int) (context.getResources().getDisplayMetrics().density * 125.0f);
    }

    public static void properSize(IMImageSize iMImageSize, Size size, Context context) {
        int originWidth = iMImageSize.getOriginWidth();
        int originHeight = iMImageSize.getOriginHeight();
        int suggestSize = getSuggestSize(context);
        float f = ((float) originWidth) / ((float) originHeight);
        switch (b.a[size.ordinal()]) {
            case 1:
                if (f <= 1.0f) {
                    originHeight = Math.min(Math.max(originHeight, suggestSize), MAX);
                    suggestSize = Math.round(((float) originHeight) * f);
                    break;
                }
                suggestSize = Math.min(Math.max(originWidth, suggestSize), MAX);
                originHeight = Math.round(((float) suggestSize) / f);
                break;
            case 2:
                int max = Math.max(originWidth, originHeight);
                if (max >= MIN) {
                    if (max < MIN || max > suggestSize) {
                        if (f <= 1.0f) {
                            originHeight = suggestSize;
                            suggestSize = Math.round(((float) suggestSize) * f);
                            break;
                        }
                        originHeight = Math.round(((float) suggestSize) / f);
                        break;
                    }
                    suggestSize = originWidth;
                    break;
                } else if (f <= 1.0f) {
                    originHeight = MIN;
                    suggestSize = Math.round(((float) originHeight) * f);
                    break;
                } else {
                    suggestSize = MIN;
                    originHeight = Math.round(((float) suggestSize) / f);
                    break;
                }
                break;
            case 3:
                if (f <= 1.0f) {
                    originHeight = MIN;
                    suggestSize = Math.round(((float) originHeight) * f);
                    break;
                }
                suggestSize = MIN;
                originHeight = Math.round(((float) suggestSize) / f);
                break;
            default:
                originHeight = 0;
                suggestSize = 0;
                break;
        }
        if (originHeight < MIN) {
            originHeight = MIN;
        }
        if (suggestSize < MIN) {
            suggestSize = MIN;
        }
        iMImageSize.setDstHeight(originHeight);
        iMImageSize.setDstWidth(suggestSize);
    }
}
